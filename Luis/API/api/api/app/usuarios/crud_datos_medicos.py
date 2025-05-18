from sqlalchemy.orm import Session
from app.models import DatosMedicos, Usuario
from app.schemas import DatosMedicosCreate

def obtener_datos_medicos(db: Session, usuario_id: int):
    return db.query(DatosMedicos).filter(DatosMedicos.usuario_id == usuario_id).first()

def crear_o_actualizar_datos_medicos(db: Session, usuario: Usuario, datos: DatosMedicosCreate):
    existente = obtener_datos_medicos(db, usuario.id)
    if existente:
        for campo, valor in datos.dict(exclude_unset=True).items():
            setattr(existente, campo, valor)
        db.commit()
        db.refresh(existente)
        return existente
    else:
        nuevo = DatosMedicos(usuario_id=usuario.id, **datos.dict())
        db.add(nuevo)
        db.commit()
        db.refresh(nuevo)
        return nuevo
