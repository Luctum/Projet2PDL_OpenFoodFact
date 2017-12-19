package pdl.Fetcher;

import org.junit.Test;
import pdl.Model.Config;
import pdl.Utils.ConfigReader;

import static org.junit.Assert.*;

/**
 * {@link ApiFetcherTest} Test class
 */
public class ConfigTest {
    /**
     * Test if the config file is imported correctly
     * @throws Exception
     */
    @Test
    public void configTestGoodConfig() throws Exception {
        Config c1 = ConfigReader.readConfig("config.json");
        assertNotNull(c1);
    }

}
