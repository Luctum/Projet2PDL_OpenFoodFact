package Utils;

import Model.Config;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public  class ConfigReader {

    private static final String CONFIG_PATH = "config.json";

    public static Config readConfig(){
        Gson gson = new Gson();
        try {
            BufferedReader br;
            br = new BufferedReader(new FileReader(CONFIG_PATH));
            return gson.fromJson(br , Config.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Impossible de trouver le fichier de configuration : " + CONFIG_PATH);
        }

        return new Config();
    }

}
