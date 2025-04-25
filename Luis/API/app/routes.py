from fastapi import APIRouter, HTTPException, Depends
from sqlalchemy.orm import Session
from typing import List
from . import models, schemas, crud, database

router = APIRouter()

# Crear una ruta
@router.post("/rutas/", response_model=schemas.RutaResponse)
def crear_ruta(ruta: schemas.RutaCreate, db: Session = Depends(database.get_db)):
    return crud.crear_ruta(db, ruta)

# Obtener todas las rutas
@router.get("/rutas/", response_model=List[schemas.RutaResponse])
def obtener_rutas(db: Session = Depends(database.get_db)):
    return crud.obtener_rutas(db)

# Obtener una ruta por ID
@router.get("/rutas/{ruta_id}", response_model=schemas.RutaResponse)
def obtener_ruta(ruta_id: int, db: Session = Depends(database.get_db)):
    ruta = crud.obtener_ruta(db, ruta_id)
    if ruta is None:
        raise HTTPException(status_code=404, detail="Ruta no encontrada")
    return ruta

# Actualizar una ruta
@router.put("/rutas/{ruta_id}", response_model=schemas.RutaResponse)
def actualizar_ruta(ruta_id: int, ruta_actualizada: schemas.RutaCreate, db: Session = Depends(database.get_db)):
    ruta = crud.actualizar_ruta(db, ruta_id, ruta_actualizada)
    if ruta is None:
        raise HTTPException(status_code=404, detail="Ruta no encontrada")
    return ruta

# Eliminar una ruta
@router.delete("/rutas/{ruta_id}")
def eliminar_ruta(ruta_id: int, db: Session = Depends(database.get_db)):
    if not crud.eliminar_ruta(db, ruta_id):
        raise HTTPException(status_code=404, detail="Ruta no encontrada")
    return {"mensaje": "Ruta eliminada"}