from typing import List, Optional, Union
from pydantic import BaseModel

class PuntoInteresBase(BaseModel):
    id: int
    tipo: Optional[str]
    nombre: Optional[str]
    direccion: Optional[str]
    telefono: Optional[str]
    precio: Optional[str]
    latitud: Optional[float]
    longitud: Optional[float]
    etapa_id: Optional[int]

    class Config:
        from_attributes = True

class EtapaSchema(BaseModel):
    id: int
    nombre: str
    orden: int
    camino_id: int
    trazado: Union[str, List[List[float]]]

    class Config:
        from_attributes = True

class CaminoSchema(BaseModel):
    id: int
    nombre: str
    color: Optional[str]

    class Config:
        from_attributes = True

class EtapaResumen(BaseModel):
    id: int
    nombre: str
    latitud: float
    longitud: float
    trazado: Optional[Union[str, List[List[float]]]] = None

    class Config:
        from_attributes = True

class EtapaGeoJSON(BaseModel):
    id: int
    nombre: str
    geojson: str

class EtapaConTrazado(BaseModel):
    id: int
    nombre: str
    latitud: float
    longitud: float
    trazado: Optional[str]

    class Config:
        orm_mode = True

class DatosMedicosBase(BaseModel):
    grupo_sanguineo: Optional[str]
    alergias: Optional[str]
    enfermedades_cronicas: Optional[str]
    medicamentos: Optional[str]

class DatosMedicosCreate(DatosMedicosBase):
    usuario_id: int

class DatosMedicosOut(DatosMedicosBase):
    id: int
    usuario_id: int

    class Config:
        from_attributes = True  # Para Pydantic v2