package pdl.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Config {

    private String fieldToSearch;
    private List<String> searchWords;
    private Map<String, String> filters;
    private String provider;

    private int mongoPort;

    public Config(){
        this.fieldToSearch = "";
        this.searchWords = new ArrayList<>();
        this.filters = new HashMap<>();
        this.provider = "";
        // Default mongo port
        this.mongoPort = 27017;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public Map<String, String> getFilters() {
        return filters;
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
        return mongoPort;
    }

    public void setMongoPort(int mongoPort) {
        this.mongoPort = mongoPort;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
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
        s.append("Mongo Port :").append(this.mongoPort);

        return s.toString();
    }


}
