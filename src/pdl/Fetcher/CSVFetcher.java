package pdl.Fetcher;

import pdl.Model.Config;

import java.util.List;

public class CSVFetcher implements IFetcher {
    private Config config;
    private String pathToCsv;

    /**
     *
     */

    public CSVFetcher(Config configProperties, String pathToFile){
        this.config = configProperties;
        this.pathToCsv = pathToFile;
    }

    /**
    *
    */
    @Override
    public List<String> getProducts() {
        return null;
    }

    /**
     *
     */
    @Override
    public void prettify() {

    }

    public String getPathToCsv() {
        return this.pathToCsv;
    }

    public Config getConfig() {
        return this.config;
    }
}
