package pdl.Fetcher;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import pdl.Model.Config;
import pdl.Utils.ConfigReader;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Fetches information from Open Food Facts API
 * @author : Abraham Mubanzo
 * @author : Marlène Akimana
 */
public class ApiFetcher implements IFetcher {
    /**
     * configuration file from the client
     */
    private Config file;
    /**
     * String constant that holds a json file extension
     */
    private final String jsonExtension = ".json";
    /**
     * {@link OkHttpClient} client to send requests to the API
     */
    private OkHttpClient client;
    /**
     * base URL to make calls on the API for product information by its name
     */
    private String searchUrlByProductName;
    /**
     * base URL to make calls on the API for product information by its barcode
     */
    private String searchUrlByProductByCode;
    /**
     * base URL to make calls on the API for category information
     */
    private String searchUrlByCategory;

    /**
     * Preferred Constructor
     * Instantiates {@param searchUrlByProductName}, {@param searchUrlByProductByCode}, {@param searchUrlByCategory}
     */
    public ApiFetcher() throws Exception {
        file = ConfigReader.readConfig();
        this.client= new OkHttpClient();
        this.searchUrlByCategory = "https://ssl-api.openfoodfacts.org/category/";
        this.searchUrlByProductByCode = "https://ssl-api.openfoodfacts.org/code/";
        this.searchUrlByProductName = "https://ssl-api.openfoodfacts.org/api/vO/product/";
    }

    /**
     * Downloads a URL content and return the object content
     * @param url url to send to the API
     * @return {@link ResponseBody} a json object
     * @throws IOException
     */
    public ResponseBody run(String url) throws IOException {
        Request request = new Request.Builder().url(url).build();
        Response response = this.client.newCall(request).execute();
        return response.body();
    }


    /**
     * Container of the JSON data we received from the API
     * @return {@link List} object containing {@link String} objects
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

    /**
     * Analyzes status value rendered by the API
     * @param responseBody Object rendered by the API
     * @return status of an object
     */
    public String getStatus(ResponseBody responseBody){
        return "";
    }

    /**
     * Analyzes a the number of pages for a product
     * @param responseBody Object rendered by the API
     * @return number of pages of a product
     */
    public int pages(ResponseBody responseBody){
        return 0;
    }

    /**
     * Creates a url to send to the API to search for product information by its name
     * @param keyword product name
     */
    public void setSearchUrlByProductName(String keyword){
        this.searchUrlByProductName = this.getSearchUrlByProductName() + keyword + this.jsonExtension;
    }

    /**
     * Creates an URL to send to the API to search for product information by its bar code
     * @param code any product code in Open Food Facts
     */
    public void setSearchByCode(String code){
        this.searchUrlByProductByCode = this.getSearchUrlByProductByCode() + code + this.jsonExtension;
    }

    /**
     * Creates a url to send to the API to search for categories information
     * @param category any category in Open Food Facts
     */
    public void setSearchByCategory(String category){
        this.searchUrlByCategory = this.getSearchUrlByCategory() + category + this.jsonExtension;
    }

    /**
     * @return search url by product name
     */
    public String getSearchUrlByProductName() {
        return this.searchUrlByProductName;
    }

    /**
     * @return search url by product code
     */
    public String getSearchUrlByProductByCode() {
        return this.searchUrlByProductByCode;
    }

    /**
     * @return search url by category
     */
    public String getSearchUrlByCategory() {
        return this.searchUrlByCategory;
    }

}
