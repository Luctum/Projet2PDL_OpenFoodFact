package pdl.Fetcher;

import okhttp3.ResponseBody;
import org.junit.Before;
import org.junit.Test;
import pdl.Utils.ConfigReader;

import static org.junit.Assert.*;

/**
 * {@link ApiFetcherTest} Test class
 */
public class ApiFetcherTest {
    /**
     * {@link ApiFetcher} object
     */
    private ApiFetcher apiFetcher;
    @Before
    public void setUp() throws Exception {
        this.apiFetcher = new ApiFetcher(ConfigReader.readConfig());
    }

    /**
     * Testing storage ability of the algorithm
     * @throws Exception
     */
    @Test
    public void getProductsTest1() throws Exception {
        assertNotEquals(null, this.apiFetcher.getProducts());
    }

    /**
     * Testing returned value after setting up a search by product name
     * @throws Exception
     */
    @Test
    public void getSearchUrlByProductNameTest1() throws Exception {
        this.apiFetcher.setSearchByProductName("nutella");
        String url = "https://ssl-api.openfoodfacts.org/api/v0/product/nutella.json";
        assertEquals(url, this.apiFetcher.getSearchUrlByProductName());
    }

    /**
     * Testing returned value after setting up a search by barre_code
     * @throws Exception
     */
    @Test
    public void getSearchUrlByProductByCodeTest1() throws Exception {
        this.apiFetcher.setSearchByCode("3017624047813");
        String url = "https://ssl-api.openfoodfacts.org/code/3017624047813.json";
        assertEquals(url, this.apiFetcher.getSearchUrlByProductByCode());
    }

    /**
     * Client test to display information from the api
     * @throws Exception
     */
    @Test
    public void prettyprinter() throws Exception{
        //this.apiFetcher.formatJsonString(this.apiFetcher.getProducts());
        assertTrue(true);
    }

    /**
     * Testing returned value after setting up a search by category
     * @throws Exception
     */
    @Test
    public void getSearchUrlByCategoryTest1() throws Exception {
        this.apiFetcher.setSearchByCategory("pizzas/1");
        String url = "https://ssl-api.openfoodfacts.org/category/pizzas/1.json";
        assertEquals(url, this.apiFetcher.getSearchUrlByCategory());
    }

    /**
     * Testing returned value for the number of products inside a JSON Node tree from the request
     * @throws Exception
     */
    @Test
    public void pagesTest1() throws Exception{
        this.apiFetcher.setSearchByCategory("pizzas");
        ResponseBody r = this.apiFetcher.run(this.apiFetcher.getSearchUrlByCategory());
        assertEquals(20, this.apiFetcher.pages(r));
    }

    /**
     * Testing returned value status should be 1 if the product is found, 0 otherwise
     * @throws Exception
     */
    @Test
    public void statusTest1() throws Exception{
        this.apiFetcher.setSearchByProductName("737628064502");
        ResponseBody r = this.apiFetcher.run(this.apiFetcher.getSearchUrlByProductName());
        assertEquals(1, this.apiFetcher.getStatus(r));
    }
}
