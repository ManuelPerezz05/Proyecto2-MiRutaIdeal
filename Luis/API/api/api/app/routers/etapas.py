from fastapi import APIRouter
from typing import List
from sqlalchemy import text
from app.schemas import EtapaResumen, EtapaConTrazado
from app.database import engine
from decimal import Decimal

router = APIRouter(prefix="/etapas", tags=["Etapas"])

@router.get("/resumen", response_model=List[EtapaResumen])
def obtener_resumen_etapas():
    with engine.connect() as conn:
        result = conn.execute(text("""
            SELECT id, nombre, latitud, longitud
            FROM etapas
            ORDER BY orden ASC
        """))

        def convertir_fila(row):
            fila = dict(row._mapping)
            fila["latitud"] = float(fila["latitud"]) if isinstance(fila["latitud"], Decimal) else fila["latitud"]
            fila["longitud"] = float(fila["longitud"]) if isinstance(fila["longitud"], Decimal) else fila["longitud"]
            return fila

        return [convertir_fila(row) for row in result]

@router.get("/con_trazado", response_model=List[EtapaConTrazado])
def obtener_etapas_con_trazado():
    with engine.connect() as conn:
        result = conn.execute(text("""
            SELECT id, nombre, latitud, longitud, ST_AsGeoJSON(trazado) AS trazado
            FROM etapas
            ORDER BY orden ASC
        """))
        return [dict(row._mapping) for row in result]
