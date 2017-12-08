package pdl.Controller;

import pdl.Fetcher.ApiFetcher;
import pdl.Fetcher.MongoDbFetcher;
import pdl.Model.Config;
import pdl.Utils.ConfigReader;

public class AppController {

    public static void run() throws Exception {
        //Calls the right methods in the right order and pass good parameter to each methods
        //Notify users if there is errors
        //test config
        /**
         * Api Fetcher Test
         */
        ApiFetcher apiFetcher = new ApiFetcher();
        apiFetcher.formatJsonString(apiFetcher.getProducts());

        Config c = ConfigReader.readConfig();
        System.out.println(c.toString());

        MongoDbFetcher iFetcher = new MongoDbFetcher(c,"dumpOFF");
        System.out.println(iFetcher.getProducts());

    }
}
