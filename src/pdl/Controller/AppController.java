package pdl.Controller;

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
        MongoDbFetcher iFetcher = new MongoDbFetcher(c,c.getDbName());
        System.out.println("----- PROCESSING -----");
        iFetcher.prettify();
        CsvWriter csvWriter = new CsvWriter(c, iFetcher.getListProduct());
        csvWriter.createCsv();
        System.out.println("Execution done");


    }
}
