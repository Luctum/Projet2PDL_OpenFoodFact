package pdl.Utils;

import com.google.gson.Gson;
import pdl.Model.Config;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public  class ConfigReader {

    private static final String CONFIG_PATH = "config.json";

    public static Config readConfig() throws Exception {
        Gson gson = new Gson();
        try {
            BufferedReader br;
            br = new BufferedReader(new FileReader(CONFIG_PATH));
            Config c = gson.fromJson(br , Config.class);
            if(!checkConfig(c)){
                throw new Exception();
            }
            return c;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Impossible de trouver le fichier de configuration : " + CONFIG_PATH);
        }

        return new Config();
    }

    /**
     * Check if the config.json file is complete
     * @param c Config
     * @return boolean true if config is well defined, else return false
     */
    private static boolean checkConfig(Config c){
        if(c.getSearchWords().isEmpty()){
            System.out.println("ERROR : Missing or empty 'searchWords' property");
            return false;
        }
        if(c.getProvider().equals("mongo") && c.getDbName().equals("")){
            System.out.println("ERROR : Missing or empty 'dbName' property while using 'mongo' provider");
            return false;
        }
        return true;
    }

}
