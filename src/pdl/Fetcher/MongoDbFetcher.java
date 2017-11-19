package pdl.Fetcher;

import pdl.Model.Config;
import com.google.gson.JsonObject;
import com.mongodb.*;


import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;


public class MongoDbFetcher implements IFetcher {

    private MongoClient mongoClient ;
    private DBCollection dbCollection;
    private Config config;

    public MongoDbFetcher( Config config)
    {
       this.config = config;
        try {
            this.mongoClient = new MongoClient("localhost" , config.getMongoPort() );
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
    public String getProducts() {

        List<String> searchWords = config.getSearchWords();
        Map<String, String> filters = config.getFilters();

        return null;
    }

    @Override
    public void prettify() {

    }
}
