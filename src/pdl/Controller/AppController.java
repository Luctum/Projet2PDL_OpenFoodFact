package pdl.Controller;

import com.mongodb.Mongo;
import pdl.Fetcher.IFetcher;
import pdl.Fetcher.MongoDbFetcher;
import pdl.Model.Config;
import pdl.Utils.ConfigReader;
import pdl.Utils.CsvWriter;

public class AppController {

    public static void run() throws Exception {
        //Calls the right methods in the right order and pass good parameter to each methods
        //Notify users if there is errors
        //test config
        Config c = ConfigReader.readConfig();
        System.out.println("----- CONFIG ----");
        System.out.println(c.toString());
        MongoDbFetcher fetcher = new MongoDbFetcher(c);
        System.out.println("----- PROCESSING -----");
        fetcher.getProducts();
        fetcher.prettify();
        CsvWriter writer = new CsvWriter(c,fetcher.getListProduct());
        writer.createCsv();

    }
}
