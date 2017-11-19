package pdl.Fetcher;

import pdl.Model.Config;
import com.google.gson.JsonObject;
import com.mongodb.*;


import java.net.UnknownHostException;


public class MongoDbFetcher implements IFetcher {
   private MongoClient mongoClient ;
    DBCollection dbCollection;

    public MongoDbFetcher()
    {
        try {
            this.mongoClient = new MongoClient("localhost" , 27017 );
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        DB database = this.mongoClient.getDB("dumpOFF");
        dbCollection = database.getCollection("products");
    }


    public void testAccesDB()
    {
        DBObject query =  new BasicDBObject();

        query.put("_id", "20003470");

        DBCursor DBcursor = dbCollection.find (query);
        Object jsonObject = DBcursor.one();
        System.out.println("resul "+jsonObject.toString());
    }

    @Override
    public void getProducts(String keywords, String filter) {

    Config config;

    public MongoDbFetcher(Config config){
        this.config = config;
    }

    @Override
    public String getProducts() {
        return "";
    }

    @Override
    public void prettify() {

    }
}
