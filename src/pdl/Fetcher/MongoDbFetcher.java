package pdl.Fetcher;

import org.json.JSONException;
import org.json.JSONObject;
import pdl.Model.Config;
import com.google.gson.JsonObject;
import com.mongodb.*;
import pdl.Model.Product;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class MongoDbFetcher implements IFetcher {

    private MongoClient mongoClient ;
    private DBCollection dbCollection;
    private Config config;
    private List<Product> listProduct;

    public MongoDbFetcher(Config config)
    {
        this.config = config;
        dbCollection = getDbCollection();
        listProduct = new ArrayList<>();
    }

    public  DBCollection getDbCollection()
    {
        System.out.println("Connecting to the Database");
        DB database = getDB();
        System.out.println("Connected to the Database");
        DBCollection Collection = database.getCollection(config.getCollectionName());
        return  Collection;
    }

    public DB getDB()
    {
        System.out.println(" get name "+this.config.getDbName());

        try {
            this.mongoClient = new MongoClient("localhost" , this.config.getMongoPort() );
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return this.mongoClient.getDB(this.config.getDbName());
    }

    @Override
    public List<String> getProducts() {
        System.out.println("Executing getProducts");
        List<String>products = new ArrayList<>();
        DBObject query =  new BasicDBObject();
        System.out.println(this.config.getSearchWords());
        query.put(this.config.getFieldToSearch(), new BasicDBObject("$in", this.config.getSearchWords()));
        DBCursor DBcursor = dbCollection.find (query);
        while(DBcursor.hasNext()) {
            String result = DBcursor.next()+"";
            products.add(result);
        }
        System.out.println("Executing getProducts done");
        return products;
    }

    @Override
    public void prettify() {
        System.out.println("Executing prettify");
        List<String> listStr = this.getProducts();
        Iterator<String> it = listStr.iterator();
        while(it.hasNext()){
            Product product = new Product();
            JSONObject json = null;
            try {
                json = json = new JSONObject(it.next());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            product.setName(this.getField(json,"product_name"));
            product.setNutritionGrade(this.getField(json,"nutrition_grades"));
            try {
                json = (JSONObject)json.get("nutriments");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            product.addNutriment("sugars_100g", this.getField(json,"sugars_100g"));
            product.addNutriment("fiber_100g",this.getField(json,"fiber_100g"));
            product.addNutriment("sodium_100g",this.getField(json,"sodium_100g"));
            product.addNutriment("carbohydrates_100g",this.getField(json,"carbohydrates_100g"));
            product.addNutriment("fat_100g",this.getField(json,"fat_100g"));
            product.addNutriment("salt_100g",this.getField(json,"salt_100g"));
            product.addNutriment("proteins_100g",this.getField(json,"proteins_100g"));
            product.addNutriment("saturated_fat_100g", this.getField(json,"saturated_fat_100g"));
            product.addNutriment("energy_100g", this.getField(json,"energy_100g"));
            this.listProduct.add(product);
        }
        System.out.println("Executing prettify done");
    }

    public String getField(JSONObject json, String fieldName){
        String str = "";
        try{
            str = json.get(fieldName).toString();
        }
        catch(org.json.JSONException e){
            System.out.println(fieldName + ": not found");
            str = "";
        }
        return str;
    }


    public List<Product> getListProduct() {
        return listProduct;
    }

    public void setListProduct(List<Product> listProduct) {
        this.listProduct = listProduct;
    }
}
