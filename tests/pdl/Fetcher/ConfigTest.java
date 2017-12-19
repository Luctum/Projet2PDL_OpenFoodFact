package pdl.Fetcher;

import okhttp3.ResponseBody;
import org.junit.Before;
import org.junit.Test;
import pdl.Model.Config;
import pdl.Utils.ConfigReader;

import static org.junit.Assert.*;

/**
 * {@link ApiFetcherTest} Test class
 */
public class ConfigTest {

    Config c1;
    Config c2;
    Config c3;
    Config c4;
    Config c5;

    @Before
    public void setUp() throws Exception {
        c2 = ConfigReader.readConfig("config2.json");
        c3 = ConfigReader.readConfig("config3.json");
        c4 = ConfigReader.readConfig("config5.json");
        c5 = ConfigReader.readConfig("config5.json");
    }

    /**
     * Test a good config
     * @throws Exception
     */
    @Test(expected = Exception.class)
    public void configTestGoodConfig() throws Exception {
        c1 = ConfigReader.readConfig("config.json");
        assertNotNull(c1);
    }

    /**
     * Test a good config
     * @throws Exception
     */
    @Test
    public void configTestBadConfig() throws Exception {
        c2 = ConfigReader.readConfig("config.json");
        assertNotNull(c1);
    }

    /**
     * Test a good config
     * @throws Exception
     */
    @Test
    public void configTestBadConfig2() throws Exception {
        c2 = ConfigReader.readConfig("config.json");
    }

    /**
     * Test a good config
     * @throws Exception
     */
    @Test
    public void configTestBadConfig3() throws Exception {
        //assertNotNull();
    }
}
