package pdl.Fetcher;

import okhttp3.ResponseBody;
import org.junit.Before;
import org.junit.Test;
import pdl.Utils.ConfigReader;

import java.util.List;

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
     *
     * @throws Exception
     */
    @Test
    public void getProductsTest1() throws Exception {
        assertEquals(null, this.apiFetcher.getProducts());
    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void getSearchUrlByProductNameTest1() throws Exception {
        this.apiFetcher.setSearchByProductName("nutella");
        String url = "https://ssl-api.openfoodfacts.org/api/v0/product/nutella.json";
        assertEquals(url, this.apiFetcher.getSearchUrlByProductByCode());
    }

    /**
     * @throws Exception
     */
    @Test
    public void getSearchUrlByProductByCodeTest1() throws Exception {
        this.apiFetcher.setSearchByCode("3017624047813");
        String url = "https://ssl-api.openfoodfacts.org/code/3017624047813.json";
        assertEquals(url, this.apiFetcher.getSearchUrlByProductByCode());
    }

    @Test
    public void prettyprinter() throws Exception{
        this.apiFetcher.formatJsonString(this.apiFetcher.getProducts());
    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void getSearchUrlByCategoryTest1() throws Exception {
        this.apiFetcher.setSearchByCategory("pizzas/1");
        String url = "https://ssl-api.openfoodfacts.org/category/pizzas/1.json";
        assertEquals(url, this.apiFetcher.getSearchUrlByCategory());
    }
    @Test
    public void pagesTest1() throws Exception{
        this.apiFetcher.setSearchByCategory("pizzas");
        ResponseBody r = this.apiFetcher.run(this.apiFetcher.getSearchUrlByCategory());
        assertEquals(20, this.apiFetcher.pages(r));
    }

    @Test
    public void statusTest1() throws Exception{
        this.apiFetcher.setSearchByProductName("737628064502");
        ResponseBody r = this.apiFetcher.run(this.apiFetcher.getSearchUrlByProductName());
        assertEquals(1, this.apiFetcher.getStatus(r));
    }

    @Test
    public void statusTest2() throws Exception{
        this.apiFetcher.setSearchByCategory("pizzas");
        ResponseBody r = this.apiFetcher.run(this.apiFetcher.getSearchUrlByCategory());
        assertEquals(1, this.apiFetcher.getStatus(r));
    }
}
