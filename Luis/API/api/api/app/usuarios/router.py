from fastapi import APIRouter, Depends, HTTPException, status
from fastapi.security import OAuth2PasswordBearer, OAuth2PasswordRequestForm
from sqlalchemy.orm import Session
from app.database import SessionLocal
from app.usuarios import crud, auth, schemas
from app.models import Usuario  # ← si necesitas usar Usuario directamente
from jose import jwt, JWTError
from app.usuarios import crud_datos_medicos
from app.schemas import DatosMedicosCreate, DatosMedicosOut

router = APIRouter()
oauth2_scheme = OAuth2PasswordBearer(tokenUrl="/usuarios/login")

def get_db():
    db = SessionLocal()
    try:
        yield db
    finally:
        db.close()

@router.post("/register", response_model=schemas.UsuarioOut)
def register(usuario: schemas.UsuarioCreate, db: Session = Depends(get_db)):
    if crud.obtener_usuario_por_email(db, usuario.email):
        raise HTTPException(status_code=400, detail="Correo ya registrado")
    return crud.crear_usuario(db, usuario.email, usuario.password, usuario.nombre)

@router.post("/login")
def login(form_data: OAuth2PasswordRequestForm = Depends(), db: Session = Depends(get_db)):
    usuario = crud.obtener_usuario_por_email(db, form_data.username)
    if not usuario or not auth.verify_password(form_data.password, usuario.hashed_password):
        raise HTTPException(status_code=401, detail="Credenciales inválidas")
    token = auth.crear_token({"sub": usuario.email})
    return {"access_token": token, "token_type": "bearer"}

def get_current_user(token: str = Depends(oauth2_scheme), db: Session = Depends(get_db)):
    credentials_exception = HTTPException(status_code=401, detail="Token inválido")
    try:
        payload = jwt.decode(token, auth.SECRET_KEY, algorithms=[auth.ALGORITHM])
        email = payload.get("sub")
        if email is None:
            raise credentials_exception
    except JWTError:
        raise credentials_exception
    usuario = crud.obtener_usuario_por_email(db, email)
    if usuario is None:
        raise credentials_exception
    return usuario

@router.get("/me", response_model=schemas.UsuarioOut)
def leer_perfil(usuario_actual = Depends(get_current_user)):
    return usuario_actual

@router.put("/me", response_model=schemas.UsuarioOut)
def actualizar_perfil(cambios: schemas.UsuarioUpdate, db: Session = Depends(get_db), usuario_actual = Depends(get_current_user)):
    return crud.actualizar_usuario(db, usuario_actual, cambios.dict(exclude_unset=True))

@router.get("/me/datos_medicos", response_model=DatosMedicosOut)
def ver_datos_medicos(usuario_actual=Depends(get_current_user), db: Session = Depends(get_db)):
    datos = crud_datos_medicos.obtener_datos_medicos(db, usuario_actual.id)
    if not datos:
        raise HTTPException(status_code=404, detail="Datos médicos no encontrados")
    return datos

@router.post("/me/datos_medicos", response_model=DatosMedicosOut)
def guardar_datos_medicos(datos: DatosMedicosCreate, usuario_actual=Depends(get_current_user), db: Session = Depends(get_db)):
    return crud_datos_medicos.crear_o_actualizar_datos_medicos(db, usuario_actual, datos)