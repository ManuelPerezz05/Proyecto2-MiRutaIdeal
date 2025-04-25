from pydantic import BaseModel
from typing import Optional

class RutaBase(BaseModel):
    nombre: str
    descripcion: Optional[str] = None
    distancia_km: float
    altitud_maxima: Optional[int] = None
    altitud_minima: Optional[int] = None
    nivel_dificultad: str
    tipo: str

class RutaCreate(RutaBase):
    pass

class RutaResponse(RutaBase):
    id: int

    class Config:
        orm_mode = True
