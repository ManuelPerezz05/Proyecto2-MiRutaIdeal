from sqlalchemy.orm import Session
from app.models import Usuario
from app.usuarios.auth import get_password_hash

def obtener_usuario_por_email(db: Session, email: str):
    return db.query(Usuario).filter(Usuario.email == email).first()

def crear_usuario(db: Session, email: str, password: str, nombre: str | None):
    hashed = get_password_hash(password)
    nuevo_usuario = Usuario(email=email, hashed_password=hashed, nombre=nombre)
    db.add(nuevo_usuario)
    db.commit()
    db.refresh(nuevo_usuario)
    return nuevo_usuario

def actualizar_usuario(db: Session, usuario: Usuario, cambios: dict):
    if "password" in cambios:
        cambios["hashed_password"] = get_password_hash(cambios.pop("password"))
    for k, v in cambios.items():
        setattr(usuario, k, v)
    db.commit()
    db.refresh(usuario)
    return usuario
