# Projet3PDL_OpenFoodFact
Get data from openfoodfact and create comparision matrix in order to export them in CSV.

A pom.xml file is defined in order to download all the dependencies with maven easily.
Current dependencies:
- OpenCSV
- Gson
- mongo-java-driver
- org.json

## Configuration

Edit the config.json file at the root of the project.

### General configuration

filedToSearch : The name of the field where to search in the openfoodfact database. (eg: "code".)

searchWords : Array of keywords to search. (eg: ["coca cola", "fanta"])

#### In the case of _mongo_ : 

mongoPort : The port you are using to run mongoDb. (optionnal: 27017 by default)
collectionName : Name of your mongoDb collection (optionnal: "products" by default)
dbName : Name of your database.

#### Complete example : 
```
{
  "fieldToSearch" : "product_name",
  "searchWords": [
    "Choco Bites",
    "Chocapic",
    "nutella",
    "La vache qui rit"
  ],
  "mongoPort": 27017,
  "collectionName": "products",
  "dbName": "pdl"
}
```

## Running this tool

Run the main class.
You will be warned if any errors occur.
The generated CSV file will be located at the root of this program with the name "result.csv".

