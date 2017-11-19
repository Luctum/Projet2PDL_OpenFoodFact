package pdl;


import pdl.Controller.AppController;
import pdl.Fetcher.IFetcher;
import pdl.Fetcher.MongoDbFetcher;

public class Main {

    public static void main(String[] args){
          AppController.run();
        /*MongoDbFetcher iFetcher = new MongoDbFetcher();
        iFetcher.testAccesDB();*/
    }

}
