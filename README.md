# Projet3PDL_OpenFoodFact
Get data from openfoodfact and create comparision matrix in order to export them in CSV.

A pom.xml file is defined in order to download all the dependencies with maven easily.
Current dependencies:
- OpenCSV
- Gson
- Jackson
- Junit
- okhttp3
- mongo-java-driver

## Configuration

Edit the config.json file at the root of the project.
Multiple example config.json files are at the root in order to quickly test the program, you just need to rename them "config.json" to use them.

### Which field to search

filedToSearch : The name of the field where to search in the OpenFoodfact database. 
Supported fields are "categories_tags", "product_name" and "code".

Be careful that using categories_tags could load several tens of thousands of data.

### Searching things

searchWords : Array of keywords to search. (eg: ['coca cola', 'fanta'])

In the case of the field "categories_tags", it is required to use a country flag before the name (eg:'en:beverages')

### Provider configuration

Be warned that some results may change while using one or another provider.

provider : Which dump do you want to use in order to retreive the data. (eg: "api" or "mongo")

#### In the case of _mongo_ : 

mongoPort : The port you are using to run mongoDb. (optionnal: 27017 by default)
collectionName : Name of your mongoDb collection (optionnal: "products" by default)
dbName : Name of your database.

#### In the case of _api_ :

Be aware that this provider is under development and is subject to change, both on our side and on OpenFoodFact's side.
Currently, "categories_tag" field is not supported by the "api" provider.

#### Complete example : 
```
{
  "fieldToSearch" : "code",
  "searchWords": [
    "3029330003533"
  ],
  "provider": "mongo",
  "dbName": "pdl"
}
```

## Running this tool

Run the "Main" class.
You will be warned if any errors occur, especially concerning the config file.
The generated CSV file will be located at the root of this program.

