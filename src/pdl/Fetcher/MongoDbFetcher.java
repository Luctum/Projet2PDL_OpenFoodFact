package pdl.Fetcher;

import pdl.Model.Config;

public class MongoDbFetcher implements IFetcher {

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
