from sqlalchemy import Column, Integer, String, Float
from .database import Base

class Ruta(Base):
    __tablename__ = "rutas"

    id = Column(Integer, primary_key=True, index=True)
    nombre = Column(String, index=True)
    descripcion = Column(String, nullable=True)
    distancia_km = Column(Float)
    altitud_maxima = Column(Integer, nullable=True)
    altitud_minima = Column(Integer, nullable=True)
    nivel_dificultad = Column(String)
    tipo = Column(String)
