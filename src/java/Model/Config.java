package Model;

import java.util.Map;

public class Config {

    private String searchWords;
    private Map<String, String> filters;
    private String provider;

    public String getSearchWords() {
        return searchWords;
    }

    public void setSearchWords(String searchWords) {
        this.searchWords = searchWords;
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

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("Search Words : ").append(this.searchWords);
        s.append(System.getProperty("line.separator"));
        s.append("Provider : ").append(this.provider);
        s.append(System.getProperty("line.separator"));
        s.append("Filters : ");
        s.append(System.getProperty("line.separator"));
        this.filters.forEach((key, value) -> {
            s.append(key).append(" = ").append(value);
            s.append(System.getProperty("line.separator"));
        });

        return s.toString();
    }

}
