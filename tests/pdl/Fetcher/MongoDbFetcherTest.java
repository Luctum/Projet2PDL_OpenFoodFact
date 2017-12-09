package pdl.Fetcher;

import org.junit.Before;
import org.junit.Test;
import pdl.Model.Config;
import pdl.Utils.ConfigReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MongoDbFetcherTest {

    private MongoDbFetcher mongoDbFetcher;
    private Config config ;

    /**
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        config = ConfigReader.readConfig();
        mongoDbFetcher = new MongoDbFetcher(config);
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
}
