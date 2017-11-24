package pdl.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Config {

    private int mongoPort;
    private String fieldToSearch;
    private List<String> searchWords;
    private Map<String, String> filters;
    private String provider;
    private String csvPath;
    private String collectionName;
    private String dbName;


    public Config(){
        this.fieldToSearch = "";
        this.searchWords = new ArrayList<String>();
        this.filters = new HashMap<>();
        this.provider = "";
        // Default mongo port
        this.mongoPort = 27017;
        this.csvPath = "products.csv";
        this.collectionName = "products";
        this.dbName = "";
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public Map<String, String> getFilters() {
        return this.filters;
    }

    public void setFilters(Map<String, String> filters) {
        this.filters = filters;
    }

    public List<String> getSearchWords(){
        return this.searchWords;
    }

    public void setSearchWords(List<String> searchWords) {
        this.searchWords = searchWords;
    }

    public String getFieldToSearch() {
        return fieldToSearch;
    }

    public void setFieldToSearch(String fieldToSearch) {
        this.fieldToSearch = fieldToSearch;
    }

    public int getMongoPort() {
        return this.mongoPort;
    }

    public void setMongoPort(int mongoPort) {
        this.mongoPort = mongoPort;
    }

    public String getCsvPath() {
        return csvPath;
    }

    public void setCsvPath(String csvPath) {
        this.csvPath = csvPath;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("searchWords : ");
        this.searchWords.forEach((key) -> s.append(key).append(","));
        s.append(System.getProperty("line.separator"));
        s.append("Provider : ").append(this.provider);
        s.append(System.getProperty("line.separator"));
        s.append("Filters : ");
        s.append(System.getProperty("line.separator"));
        this.filters.forEach((key, value) -> {
            s.append(key).append(" = ").append(value);
            s.append(System.getProperty("line.separator"));
        });
        s.append("Mongo Port : ").append(this.mongoPort);
        s.append(System.getProperty("line.separator"));
        s.append("Collection name : ").append(this.collectionName);
        s.append(System.getProperty("line.separator"));
        s.append("Db Name : ").append(this.dbName);
        s.append(System.getProperty("line.separator"));
        s.append("Path to csv : ").append(this.csvPath);
        return s.toString();
    }

}
