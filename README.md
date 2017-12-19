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
Multiple example config.json files are at the root in order to quickly test the program.

### General configuration

filedToSearch : The name of the field where to search in the openfoodfact database. (supported: "code".)


searchWords : Array of keywords to search. (eg: ['coca cola', 'fanta'])


### Provider configuration

provider : Which dump do you want to use in order to retreive the data. (eg: csv or mongo)

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

Run the "Main".
You will be warned if any errors occur, especially concerning the config file.
The generated CSV file will be located at the root of this program.

