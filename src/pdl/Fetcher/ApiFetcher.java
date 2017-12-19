package pdl.Fetcher;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import pdl.Model.Config;
import pdl.Model.Product;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Fetches information from Open Food Facts API
 * @author : Abraham Mubanzo
 * @author : Marlène Akimana
 */
public class ApiFetcher implements IFetcher {

    /**
     *
     */
    private boolean isCode;
    private boolean isProduct;
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
     *
     */
    private List<String> products;

    /**
    * List of instanciated products
     */
    private List<Product> listProduct;


    /**
     * Preferred Constructor
     * Instantiates {@param searchUrlByProductName}, {@param searchUrlByProductByCode}, {@param searchUrlByCategory}
     */
    public ApiFetcher(Config c) throws Exception {
        this.isCode = false;
        this.isProduct = false;
        this.file = c;
        this.client= new OkHttpClient();
        this.searchUrlByCategory = "https://ssl-api.openfoodfacts.org/category/";
        this.searchUrlByProductByCode = "https://ssl-api.openfoodfacts.org/code/";
        this.searchUrlByProductName = "https://ssl-api.openfoodfacts.org/api/v0/product/";
        this.products= new ArrayList<>();
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
    public List<String> getProducts() throws NoSuchMethodException {
        PairImpl<Method, Method> p = this.searchMethod(this.file.getFieldToSearch());
        if(p != null) {
            this.file.getSearchWords().forEach(keyword -> {
                String productNode;
                Method getMethod = p.getKey();
                Method setMethod = p.getValue();
                try {
                    setMethod.invoke(this, keyword);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
                try {
                    ResponseBody r = this.run((String) getMethod.invoke(this));
                    assert r != null;
                    if(this.isProduct) {
                        productNode = new ObjectMapper().readTree(r.string()).get("product").toString();
                        products.add(productNode);
                    }else if(this.isCode){
                        productNode = new ObjectMapper().readTree(r.string()).get("products").toString();
                        products.add(productNode);
                    }else{
                        int page_size = this.pages(r);
                        for (int i = 1; i < page_size; i++) {
                            this.setSearchByCategory(keyword + "/" + 1);
                            ResponseBody newResponse = this.run(this.getSearchUrlByCategory());
                            String category_product = new ObjectMapper().readTree(newResponse.string()).get("products").toString();
                            products.add(category_product);
                        }
                    }
                } catch (IOException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            });
        }
        return this.products;
    }
    /**
     * JSON pretty printer
     * @throws Exception
     */
    public void formatJsonString(List<String> products) throws Exception {
        assert products != null;
        products.forEach(product -> {
           ObjectMapper mapper = new ObjectMapper();
           try {
               Object jsonObject = mapper.readValue(product, Object.class);
               String prettyJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObject);
               System.out.println(prettyJson);
           } catch (IOException e) {
               e.printStackTrace();
           }
       });
    }

    /**
     *
     */
    @Override
    public void prettify() {

    }

    /**
     * Helps to find which method will set the URL to send to the API after config file analysis
     * @param fieldToSearch field to search from the config file
     * @return {@link PairImpl} Object which contains field to search as key and a setter method
     * @throws NoSuchMethodException
     */
    public PairImpl<Method, Method> searchMethod(String fieldToSearch) throws NoSuchMethodException {
        switch (fieldToSearch) {
            case "product_name":
                this.isProduct = true;
                return new PairImpl<>(this.getClass().getMethod("getSearchUrlByProductName"), this.getClass().getMethod("setSearchByProductName", String.class));
            case "code":
                this.isCode = true;
                return new PairImpl<>(this.getClass().getMethod("getSearchUrlByProductByCode"), this.getClass().getMethod("setSearchByCode", String.class));
            case "categories_tags":
                return new PairImpl<>(this.getClass().getMethod("getSearchUrlByCategory"), this.getClass().getMethod("setSearchByCategory", String.class));
            default:
                return null;
        }
    }

    /**
     * Analyzes status value rendered by the API
     * @param responseBody Object rendered by the API
     * @return status of an object
     */
    public int getStatus(ResponseBody responseBody) throws IOException {
        return new ObjectMapper().readTree(responseBody.string()).get("status").asInt();
    }

    /**
     * Analyzes a the number of pages for a product
     * @param responseBody Object rendered by the API
     * @return number of pages of a product
     */
    public int pages(ResponseBody responseBody) throws IOException {
        return new ObjectMapper().readTree(responseBody.string()).get("page_size").asInt();
    }

    /**
     * Creates a url to send to the API to search for product information by its name
     * @param keyword product name
     */
    public void setSearchByProductName(String keyword){
        this.searchUrlByProductName = "https://ssl-api.openfoodfacts.org/api/v0/product/";
        this.searchUrlByProductName = this.getSearchUrlByProductName() + keyword + this.jsonExtension;
    }

    /**
     * Creates an URL to send to the API to search for product information by its bar code
     * @param code any product code in Open Food Facts
     */
    public void setSearchByCode(String code){
        this.searchUrlByProductByCode = "https://ssl-api.openfoodfacts.org/code/";
        this.searchUrlByProductByCode = this.getSearchUrlByProductByCode() + code + this.jsonExtension;
    }

    /**
     * Creates a url to send to the API to search for categories information
     * @param category any category in Open Food Facts
     */
    public void setSearchByCategory(String category){
        this.searchUrlByCategory = "https://ssl-api.openfoodfacts.org/category/";
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

    public List<Product> getListProduct() {
        return listProduct;
    }
}
