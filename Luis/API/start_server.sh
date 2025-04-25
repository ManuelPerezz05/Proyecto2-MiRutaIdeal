#!/bin/bash

# Activar el entorno virtual si existe
if [ -d "venv" ]; then
  source venv/bin/activate
else
  echo "No se encontró entorno virtual. Creándolo..."
  python3 -m venv venv
  source venv/bin/activate
fi

# Instalar dependencias
pip install --upgrade pip
pip install -r requirements.txt

# Cargar variables de entorno y levantar uvicorn
uvicorn app.main:app --host 0.0.0.0 --port 8000 --reload
