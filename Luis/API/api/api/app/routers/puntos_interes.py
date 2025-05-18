from fastapi import APIRouter, Depends
from sqlalchemy.orm import Session
from app.database import SessionLocal
from app.models import PuntoInteres
from app.schemas import PuntoInteresBase
from typing import List

router = APIRouter(prefix="/puntos_interes", tags=["Puntos de Interés"])

def get_db():
    db = SessionLocal()
    try:
        yield db
    finally:
        db.close()

@router.get("/", response_model=List[PuntoInteresBase])
def get_all_puntos(db: Session = Depends(get_db)):
    return db.query(PuntoInteres).all()