from fastapi import FastAPI
from app.database import Base, engine
from app.routers import puntos_interes, etapas
from app.usuarios.router import router as usuarios_router
from fastapi.middleware.cors import CORSMiddleware

# Crear tablas si no existen
Base.metadata.create_all(bind=engine)

app = FastAPI()

# CORS para desarrollo
app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

# Rutas del proyecto
app.include_router(puntos_interes.router)
app.include_router(etapas.router, prefix="/etapas", tags=["etapas"])
app.include_router(usuarios_router, prefix="/usuarios", tags=["usuarios"])
