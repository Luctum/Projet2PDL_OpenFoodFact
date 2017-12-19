package pdl.Fetcher;

import org.junit.Test;
import pdl_group9.Model.Config;

import pdl_group9.Utils.ConfigReader;

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
        Config c1 = ConfigReader.readConfig("src/tests/resources/config1.json");
        assertNotNull(c1);
    }

}
