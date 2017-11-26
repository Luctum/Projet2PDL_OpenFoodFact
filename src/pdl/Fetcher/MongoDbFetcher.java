package pdl.Fetcher;

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

    public MongoDbFetcher(Config config) throws UnknownHostException {
       this.config = config;
        this.mongoClient = new MongoClient("localhost" , config.getMongoPort() );
        DB database = this.mongoClient.getDB(config.getDbName());
        dbCollection = database.getCollection(config.getCollectionName());
        listProduct = new ArrayList<Product>();
    }

    @Override
    public List<String> getProducts() {
        System.out.println("Executing getProducts ...");
        List<String>products = new ArrayList<>();
        DBObject query =  new BasicDBObject();
        query.put(this.config.getFieldToSearch(), new BasicDBObject("$in", this.config.getSearchWords()));
        DBCursor DBcursor = dbCollection.find (query);
        while(DBcursor.hasNext()) {
            products.add(DBcursor.next()+"");
        }
        System.out.println("DONE");
        return products;
    }

    @Override
    public void prettify() {
        System.out.println("Executing prettify...");
        List<String> listStr = this.getProducts();
        for (String aListStr : listStr) {
            Product product = new Product();
            JSONObject json = json = new JSONObject(aListStr);
            product.setName(this.getField(json, "product_name"));
            product.setNutritionGrade(this.getField(json, "nutrition_grades"));
            json = (JSONObject) json.get("nutriments");
            product.addNutriment("sugars_100g", this.getField(json, "sugars_100g"));
            product.addNutriment("fiber_100g", this.getField(json, "fiber_100g"));
            product.addNutriment("sodium_100g", this.getField(json, "sodium_100g"));
            product.addNutriment("carbohydrates_100g", this.getField(json, "carbohydrates_100g"));
            product.addNutriment("fat_100g", this.getField(json, "fat_100g"));
            product.addNutriment("salt_100g", this.getField(json, "salt_100g"));
            product.addNutriment("proteins_100g", this.getField(json, "proteins_100g"));
            product.addNutriment("saturated_fat_100g", this.getField(json, "saturated_fat_100g"));
            product.addNutriment("energy_100g", this.getField(json, "energy_100g"));
            this.listProduct.add(product);
        }
        System.out.println("DONE");
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
