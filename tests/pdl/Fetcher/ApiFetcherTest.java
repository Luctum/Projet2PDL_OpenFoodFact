package pdl.Fetcher;

import org.junit.Before;
import org.junit.Test;

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
        this.apiFetcher = new ApiFetcher();
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
        this.apiFetcher.setSearch("737628064502");
        String url = "https://ssl-api.openfoodfacts.org/api/v0/product/737628064502.json";
        assertEquals(url, this.apiFetcher.getSearchUrlByProductName());
    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void getSearchUrlByProductByCodeTest1() throws Exception {
        this.apiFetcher.setSearch("737628064502");
        String url = "https://ssl-api.openfoodfacts.org/api/v0/product/737628064502.json";
        assertEquals(url, this.apiFetcher.getSearchUrlByProductName());
    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void getSearchUrlByCategoryTest1() throws Exception {
        this.apiFetcher.setSearch("737628064502");
        String url = "https://ssl-api.openfoodfacts.org/api/v0/product/737628064502.json";
        assertEquals(url, this.apiFetcher.getSearchUrlByProductName());
    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void getClientTest1() throws Exception {
        assertTrue(this.apiFetcher.getClient() != null);
    }

}