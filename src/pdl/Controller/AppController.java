package pdl.Controller;

import pdl.Model.Config;
import pdl.Utils.ConfigReader;

public class AppController {

    public static void run(){
        //Calls the right methods in the right order and pass good parameter to each methods
        //Notify users if there is errors

        //test config
        Config c = ConfigReader.readConfig();
        System.out.println(c.toString());
    }

}
