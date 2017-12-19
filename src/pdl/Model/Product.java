package pdl.Model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;

public class Product {
	
	private String name;
	
	private String nutritionGrade;
	
	private LinkedHashMap<String,String> nutriments;

	public Product() {
		nutriments = new LinkedHashMap<String, String>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNutritionGrade() {
		return nutritionGrade;
	}

	public void setNutritionGrade(String nutritionGrade) {
		this.nutritionGrade = nutritionGrade;
	}
	
	public void addNutriment(String name, String value){
		this.nutriments.put(name, value);
	}
	
	public void removeNutriment(String name){
		this.nutriments.remove(name);
	}

	public String getNutriment(String key){
		return this.nutriments.get(key);
	}

	public String toString(){
		String str = "";
		str += this.getName();
		str += "," + this.getNutritionGrade();
		Iterator<String> it = this.nutriments.keySet().iterator();
		while(it.hasNext()){
			str += "," + this.nutriments.get(it.next());
		}
		return str;
	}

}
