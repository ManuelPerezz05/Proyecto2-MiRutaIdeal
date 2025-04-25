from fastapi import FastAPI
from . import models, database
from .routes import router

app = FastAPI()

# Crear las tablas en la base de datos
models.Base.metadata.create_all(bind=database.engine)

# Incluir las rutas
app.include_router(router)

