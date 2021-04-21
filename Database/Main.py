from fastapi import FastAPI
import uvicorn
import database
from fastapi import FastAPI
from pydantic import BaseModel
from fastapi.middleware.cors import CORSMiddleware

from typing import Optional

from fastapi import FastAPI

tagsMetaData = []  # will contain the tags/desciption for verbs
#
# define general tags for the site
#
title = 'Medizinischer Roboter'

description = 'API for automatic robot'

version = '0.5.3'

app = FastAPI(title=title,
              description=description,
              version=version,
              openapi_tags=tagsMetaData)
origins = ['*']

app.add_middleware(
    CORSMiddleware,
    allow_origins=origins,
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

@app.get("/")
async def main():
    return {"message": "Hello World"}

@app.get("/get_metas/")
async def get_metas():
    '''**Patient**
    Create new patient
    Exemple :
    /Patients/?id=1587852?room=1?state=0?madicine=Moderna

    id -> Social Security number
    room -> Room where the patient will stay for the week
    state -> 0=sick, 1->dead, 2->cured
'''
    return database.get_metas()

@app.get("/get_product/")
async def get_product(product: str):
    '''**Patient**
    Create new patient
    Exemple :
    /Patients/?id=1587852?room=1?state=0?madicine=Moderna

    id -> Social Security number
    room -> Room where the patient will stay for the week
    state -> 0=sick, 1->dead, 2->cured
'''
    return database.get_product(product)


@app.get("/put_metas/{designation}/{state}/{color}/{brand}/{model}/{year}/{storage}/{weight}/{barcode}/{reference}/{nic}")
def put_metas(designation, state, color, brand, model, year, storage, weight, barcode, reference: Optional[str], nic: Optional[str]):
    '''**Patient**
    Create new patient
    Exemple :
    /Patients/?id=1587852?room=1?state=0?madicine=Moderna

    id -> Social Security number
    room -> Room where the patient will stay for the week
    state -> 0=sick, 1->dead, 2->cured
'''
    return database.put_metas(designation, state, color, brand, model, year, storage, weight, barcode, reference, nic)


if __name__ == "__main__":
    uvicorn.run("main:app", host="0.0.0.0", port=8080, log_level="info")
