package pdl.Fetcher;

import org.junit.Before;
import org.junit.Test;
import pdl_group9.Model.Config;
import pdl_group9.Model.Product;
import pdl_group9.Utils.ConfigReader;
import pdl_group9.Fetcher.MongoDbFetcher;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class MongoDbFetcherTest {

    private MongoDbFetcher mongoDbFetcher;
    private Config config ;

    /**
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        this.mongoDbFetcher = new MongoDbFetcher(ConfigReader.readConfig("src/tests/resources/config2.json"));
    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void testGetDB()  throws Exception
    {
       assertFalse("The Database is null", mongoDbFetcher.getDB() == null);
    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void getDbCollection()  throws Exception
    {
        assertFalse("The collection est null", mongoDbFetcher.getDbCollection() == null);
    }

    @Test
    public void getProductName()  throws Exception
    {
        mongoDbFetcher.prettify();
        List<Product> products = mongoDbFetcher.getListProduct();
        Product product = products.iterator().next();
        assertEquals( "Crousti Moelleux Complet", product.getName());
    }

    @Test
    public void getProductNutritionGrade()  throws Exception
    {
        mongoDbFetcher.prettify();
        List<Product> products = mongoDbFetcher.getListProduct();
        Product product = products.iterator().next();
        assertEquals( "a", product.getNutritionGrade());
    }

    @Test
    public void getProductNutriment()  throws Exception
    {
        mongoDbFetcher.prettify();
        List<Product> products = mongoDbFetcher.getListProduct();
        Product product = products.iterator().next();
        assertEquals( "7.0", product.getNutriment("sugars_100g"));
        assertEquals( "5.5", product.getNutriment("fiber_100g"));
        assertEquals( "0.452755905511811", product.getNutriment("sodium_100g"));
        assertEquals( "43", product.getNutriment("carbohydrates_100g"));
        assertEquals( "4", product.getNutriment("fat_100g"));
        assertEquals( "1.15", product.getNutriment("salt_100g"));
        assertEquals( "9.0", product.getNutriment("proteins_100g"));
        assertEquals( "", product.getNutriment("saturated_fat_100g"));
        assertEquals( "1076.0", product.getNutriment("energy_100g"));

    }
}
