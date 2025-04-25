import os
from dotenv import load_dotenv

# Cargar variables desde .env
load_dotenv()

DATABASE_URL = os.getenv("DATABASE_URL")
