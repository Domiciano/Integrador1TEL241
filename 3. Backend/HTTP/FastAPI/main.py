from fastapi import FastAPI, Header, HTTPException, status
from fastapi.responses import JSONResponse
from pydantic import BaseModel
from typing import Annotated
from fastapi.middleware.cors import CORSMiddleware

app = FastAPI()
origins = [
    "*"
]
app.add_middleware(
    CORSMiddleware,
    allow_origins=origins,
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

# Endpoint sencillo de Echo
# http://localhost:8000/
@app.get("/")
async def echo():
    return {"message": "Echo"}

# Request Headers
# http://localhost:8000/example
@app.get("/example/")
async def read_items(user_agent: Annotated[str, Header()]):
    return {"User-Agent": user_agent}

# Path params
# http://localhost:8000/items/11
@app.get("/items/{item_id}")
async def read_item(item_id):
    return {"item_id": item_id}

# Query paramas
# http://localhost:8000/items?id=Alfa
# Se crea una base de datos sencilla
fake_items_db = [{"id": "Alfa", "name":"Andrea", "phone":"+573112134512"}, {"id": "Beta", "name":"Bernardo","phone":"+353124241233"}, {"id": "Gamma", "name":"Carlos","phone":"+543124312323"}]
# Y el endpoint GET
@app.get("/items")
async def read_item(id: str = 0):
    foundItem = None
    for item in fake_items_db:
        if item["id"] == id:
            foundItem = item
            break
    return foundItem

# Request Body
# Se crea el modelo de datos
class Item(BaseModel):
    name: str
    description: str 
    price: float

# http://localhost:8000/items
# Body: {"name":"Mi producto", "description":"Lorem ipsum", "price":42.5}
@app.post("/items/")
async def create_item(item: Item):
    return item