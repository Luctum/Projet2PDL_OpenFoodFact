package pdl_group9.Controller;


import pdl_group9.Fetcher.ApiFetcher;
import pdl_group9.Fetcher.IFetcher;
import pdl_group9.Fetcher.MongoDbFetcher;
import pdl_group9.Model.Config;
import pdl_group9.Utils.ConfigReader;
import pdl_group9.Utils.CsvWriter;

public class AppController {

    public static void run() throws Exception {
        //Calls the right methods in the right order and pass good parameter to each methods
        //Notify users if there is errors
        //test config
        Config c = ConfigReader.readConfig();
        System.out.println("----- CONFIG ----");
        System.out.println(c.toString());
        switch(c.getProvider()){
            case "api":
                setFetcherAndRun(c, new ApiFetcher(c));
                break;
            case "mongo":
                setFetcherAndRun(c, new MongoDbFetcher(c));
                break;
            default:
                System.out.println("FAILED");
                break;
        }
    }

    public static void setFetcherAndRun(Config c, IFetcher fetcher){
        System.out.println("----- PROCESSING -----");
        fetcher.prettify();
        CsvWriter csvWriter = new CsvWriter(c, fetcher.getListProduct());
        csvWriter.createCsv();
        System.out.println("Execution done");
    }
}
