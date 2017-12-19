package pdl_group9.Utils;

import com.google.gson.Gson;
import com.sun.javaws.exceptions.InvalidArgumentException;
import pdl_group9.Model.Config;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public  class ConfigReader {

    private static final String CONFIG_PATH = "config.json";

    public static Config readConfig() throws Exception {
        return readConfig(CONFIG_PATH);
    }

    public static Config readConfig(String path) throws Exception {
        Gson gson = new Gson();
        try {
            BufferedReader br;
            br = new BufferedReader(new FileReader(CONFIG_PATH));
            Config c = gson.fromJson(br , Config.class);
            checkConfig(c);
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
    public static void checkConfig(Config c) throws Exception {
        if(c == null) throw new InvalidArgumentException(new String[]{"'c' must not be null"});
        if(c.getSearchWords().isEmpty()){
            throw new Exception("Missing or empty 'searchWords' property");
        }
        if(!(c.getFieldToSearch().equals("categories_tags") || c.getFieldToSearch().equals("product_name") || c.getFieldToSearch().equals("code"))){
            throw new Exception("Unsupported field " + c.getFieldToSearch());
        }
        if(c.getProvider().equals("mongo") && c.getDbName().equals("")){
            throw new Exception("Missing or empty 'dbName' property while using 'mongo' provider");
        }
        if(!(c.getProvider().equals("api") || c.getProvider().equals("mongo"))){
            throw new Exception("Unrecognised provider, please use 'api' or 'mongo' ");
        }
        if(!(c.getProvider().equals("api") || c.getProvider().equals("mongo"))){
            throw new Exception("Unrecognised provider, please use 'api' or 'mongo' ");
        }
        if(c.getProvider().equals("api") && (c.getFieldToSearch().equals("categories_tags") || (c.getFieldToSearch().equals("product_name")))){
            throw new Exception("Invalid '"+ c.getFieldToSearch() +"' field while using 'api' provider");
        }
    }
}
