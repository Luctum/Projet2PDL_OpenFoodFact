package pdl.Fetcher;

import pdl.Model.Config;
import com.google.gson.JsonObject;
import com.mongodb.*;


import java.net.UnknownHostException;



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

    @Override
    public String getProducts() {
        StringBuilder res= new StringBuilder();
        DBObject query =  new BasicDBObject();
        query.put(this.config.getFieldToSearch(), new BasicDBObject("$in", this.config.getSearchWords()));
        DBCursor DBcursor = dbCollection.find (query);
        while(DBcursor.hasNext()) {
           res.append(DBcursor.next()).append("\n");
        }
        return res.toString();
    }

    @Override
    public void prettify() {

    }
}
