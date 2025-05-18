from pydantic import BaseModel, EmailStr

class UsuarioCreate(BaseModel):
    email: EmailStr
    password: str
    nombre: str | None = None

class UsuarioOut(BaseModel):
    id: int
    email: EmailStr
    nombre: str | None = None

    class Config:
        from_attributes = True

class UsuarioUpdate(BaseModel):
    nombre: str | None = None
    password: str | None = None
