package pdl.Utils;

import pdl.Model.Config;
import pdl.Model.Product;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;

public class CsvWriter {
    private Config config;
    private List<Product> products;

    public CsvWriter(Config config, List<Product> products){
        this.config = config;
        this.products = products;
    }


    public void createCsv(){
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("result.csv", "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        writer.println(",nutrition_grades,sugars_100g,fiber_100g,sodium_100g,carbohydrates_100g,fat_100g,salt_100g,proteins_100g,saturated_fat_100g,energy_100g");
        Iterator<Product> it = products.iterator();
        while(it.hasNext()){
            writer.println(it.next().toString());
        }
        writer.close();
    }
}
