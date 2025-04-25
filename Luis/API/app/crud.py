from sqlalchemy.orm import Session
from . import models, schemas

def crear_ruta(db: Session, ruta: schemas.RutaCreate):
    nueva_ruta = models.Ruta(**ruta.dict())
    db.add(nueva_ruta)
    db.commit()
    db.refresh(nueva_ruta)
    return nueva_ruta

def obtener_rutas(db: Session):
    return db.query(models.Ruta).all()

def obtener_ruta(db: Session, ruta_id: int):
    return db.query(models.Ruta).filter(models.Ruta.id == ruta_id).first()

def actualizar_ruta(db: Session, ruta_id: int, ruta_actualizada: schemas.RutaCreate):
    ruta = db.query(models.Ruta).filter(models.Ruta.id == ruta_id).first()
    if not ruta:
        return None
    for key, value in ruta_actualizada.dict().items():
        setattr(ruta, key, value)
    db.commit()
    return ruta

def eliminar_ruta(db: Session, ruta_id: int):
    ruta = db.query(models.Ruta).filter(models.Ruta.id == ruta_id).first()
    if ruta:
        db.delete(ruta)
        db.commit()
        return True
    return False
