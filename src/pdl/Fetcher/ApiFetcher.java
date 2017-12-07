package pdl.Fetcher;

import java.util.List;

/**
 * Fetches information from Open Food Facts API
 * @author : Abraham Mubanzo
 * @author : Marlène Akimana
 */
public class ApiFetcher implements IFetcher {
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
    public ApiFetcher(){
        this.searchUrlByCategory = "https://ssl-api.openfoodfacts.org/category/";
        this.searchUrlByProductByCode = "https://ssl-api.openfoodfacts.org/api/vO/product/";
        this.searchUrlByProductName = "https://ssl-api.openfoodfacts.org/code/";
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
     * Creates a url to send to the API to search for product information by its name
     * @param keyword product name
     */
    public void setSearch(String keyword){
    }

    /**
     * Creates an URL to send to the API to search for product information by its bar code
     * @param code
     */
    public void setSearchByCode(String code){

    }

    /**
     * Creates a url to send to the API to search for categories information
     * @param category
     */
    public void setSearchByCategory(String category){

    }

    /**
     *
     * @return search url by product name
     */
    public String getSearchUrlByProductName() {
        return this.searchUrlByProductName;
    }

    /**
     *
     * @return search url by product code
     */
    public String getSearchUrlByProductByCode() {
        return this.searchUrlByProductByCode;
    }

    /**
     *
     * @return search url by category
     */
    public String getSearchUrlByCategory() {
        return this.searchUrlByCategory;
    }
}
