package pdl.Fetcher;

import pdl.Model.Config;
import com.google.gson.JsonObject;
import com.mongodb.*;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;


public class MongoDbFetcher implements IFetcher {

    private MongoClient mongoClient ;
    private DBCollection dbCollection;
    private Config config;

    public MongoDbFetcher( Config config, String dbName)
    {
       this.config = config;
        try {
            this.mongoClient = new MongoClient("localhost" , config.getMongoPort() );
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        DB database = this.mongoClient.getDB(dbName);
        dbCollection = database.getCollection("products");
    }

    @Override
    public List<String> getProducts() {

        List<String>products = new ArrayList<>();
        DBObject query =  new BasicDBObject();
        query.put(this.config.getFieldToSearch(), new BasicDBObject("$in", this.config.getSearchWords()));
        DBCursor DBcursor = dbCollection.find (query);
        while(DBcursor.hasNext()) {
            products.add(DBcursor.next()+"");
        }
        return products;
    }

    @Override
    public void prettify() {

    }
}
