from sqlalchemy import Column, Integer, String, ForeignKey, Text, DECIMAL, JSON
from sqlalchemy.orm import relationship
from app.database import Base

class Camino(Base):
    __tablename__ = "caminos"

    id = Column(Integer, primary_key=True)
    nombre = Column(String, nullable=False)
    color = Column(String, nullable=False)
    coordenadas = Column(JSON)

    etapas = relationship("Etapa", back_populates="camino")

class Etapa(Base):
    __tablename__ = "etapas"
    id = Column(Integer, primary_key=True, index=True)
    nombre = Column(String)
    orden = Column(Integer)
    camino_id = Column(Integer, ForeignKey("caminos.id"))
    trazado = Column(Text)

    camino = relationship("Camino", back_populates="etapas")
    puntos_interes = relationship("PuntoInteres", back_populates="etapa")

class PuntoInteres(Base):
    __tablename__ = "puntos_interes"

    id = Column(Integer, primary_key=True)
    etapa_id = Column(Integer, ForeignKey("etapas.id", ondelete="CASCADE"))
    tipo = Column(String)
    nombre = Column(String)
    direccion = Column(String)
    telefono = Column(String)
    precio = Column(String)
    latitud = Column(DECIMAL)
    longitud = Column(DECIMAL)

    etapa = relationship("Etapa", back_populates="puntos_interes")

class Usuario(Base):
    __tablename__ = "usuarios"

    id = Column(Integer, primary_key=True, index=True)
    email = Column(String(255), unique=True, nullable=False)
    hashed_password = Column(Text, nullable=False)
    nombre = Column(String(255), nullable=True)

    datos_medicos = relationship("DatosMedicos", back_populates="usuario", uselist=False, cascade="all, delete")


class DatosMedicos(Base):
    __tablename__ = "datos_medicos"

    id = Column(Integer, primary_key=True, index=True)
    usuario_id = Column(Integer, ForeignKey("usuarios.id", ondelete="CASCADE"), unique=True, nullable=False)
    grupo_sanguineo = Column(String(50))
    alergias = Column(Text)
    enfermedades_cronicas = Column(Text)
    medicamentos = Column(Text)

    usuario = relationship("Usuario", back_populates="datos_medicos")