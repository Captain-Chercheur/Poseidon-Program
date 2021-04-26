import sqlite3
from sqlite3 import Error
from pathlib import Path
import time

databaseName = "database"


def connectBase():
    ''' return a connector to the database
    '''
    try:
        conn = sqlite3.connect(databaseName)
        return conn
    except:
        return False


# Retourne tous les médicaments disponibles
def get_metas():
    ''' return (yield) the forecasts found in the database
    . if filter is provided, yield only those forecasts from the provided user
    . sort is provided to sort the selected forecasts
    '''
    with connectBase() as coon:
        c = coon.cursor()
        c.execute(f'''
            SELECT * FROM Products;
        ''')

        rows = c.fetchall()

        for id, reference, state, color, brand, model, year, NIC, storage, weight, barcode, designation, descriptionText in rows:
            yield {"id": id, "reference": reference, "state": state, "color": color, "brand": brand, "model": model,
                   "year": year, "NIC": NIC, "storage": storage, "weight": weight, "barcode": barcode,
                   "designation": designation, "descriptionText":descriptionText}


def get_product(barcode):
    ''' return (yield) the forecasts found in the database
    . if filter is provided, yield only those forecasts from the provided user
    . sort is provided to sort the selected forecasts
    '''
    with connectBase() as coon:
        c = coon.cursor()
        c.execute(f'''
            SELECT * FROM Products WHERE BARCODE='{barcode}';

        ''')

        rows = c.fetchall()

        for id, reference, state, color, brand, model, year, NIC, storage, weight, barcode, designation, descriptionText in rows:
            yield {"id": id, "reference": reference, "state": state, "color": color, "brand": brand, "model": model,
                   "year": year, "NIC": NIC, "storage": storage, "weight": weight, "barcode": barcode,
                   "designation": designation, "description": descriptionText}


def get_id():
    ''' return (yield) the forecasts found in the database
    . if filter is provided, yield only those forecasts from the provided user
    . sort is provided to sort the selected forecasts
    '''
    with connectBase() as coon:
        c = coon.cursor()
        c.execute(f'''
            SELECT MAX(id) FROM Products;
        ''')

        rows = c.fetchall()

        for id in rows:
            yield {id}


def get_storage(storage):
    ''' return (yield) the forecasts found in the database
    . if filter is provided, yield only those forecasts from the provided user
    . sort is provided to sort the selected forecasts
    '''
    with connectBase() as coon:
        c = coon.cursor()
        c.execute(f'''
            SELECT STORAGE FROM Products WHERE STORAGE='{storage}';
        ''')

        rows = c.fetchall()

        for storage in rows:
            yield {storage}


def put_metas(designation, state, color, brand, model, year, storage, weight, barcode, reference, descriptionText, nic):
    ''' return (yield) the forecasts found in the database
    . if filter is provided, yield only those forecasts from the provided user
    . sort is provided to sort the selected forecasts
    '''
    with connectBase() as coon:
        c = coon.cursor()
        if not reference:
            c.execute(f'''INSERT INTO Products (designation, state, color, brand, model, year, storage, weight, barcode, reference, descriptionText) 
                    VALUES ('{designation}', '{state}', '{color}', '{brand}', '{model}', '{year}', '{storage}', '{weight}', '{barcode}', '{reference}','{descriptionText}'); ''')
        if not nic:
            c.execute(f'''INSERT INTO Products (designation, state, color, brand, model, year, storage, weight, barcode, nic, descriptionText) 
                    VALUES ('{designation}', '{state}', '{color}', '{brand}', '{model}', '{year}', '{storage}', '{weight}', '{barcode}', '{nic}', '{descriptionText}'); ''')
        if reference and nic:
            c.execute(f'''INSERT INTO Products (designation, state, color, brand, model, year, storage, weight, barcode, nic, reference,descriptionText) 
                    VALUES ('{designation}', '{state}', '{color}', '{brand}', '{model}', '{year}', '{storage}', '{weight}', '{barcode}', '{nic}', '{reference}', '{descriptionText}'); ''')
        if not reference and nic:
            c.execute(f'''INSERT INTO Products (designation, state, color, brand, model, year, storage, weight, barcode,descriptionText) 
                    VALUES ('{designation}', '{state}', '{color}', '{brand}', '{model}', '{year}', '{storage}', '{weight}', '{barcode}', {descriptionText}); ''')

        rows = c.fetchall()

        for Products in rows:
            print(designation, state, color, brand, model, year, storage, weight, barcode, descriptionText)
            yield designation, state, color, brand, model, year, storage, weight, barcode, descriptionText
