package pdl.Model;

import java.util.HashMap;
import java.util.Iterator;

public class Product {
	
	private String name;
	
	private String nutritionGrade;
	
	private HashMap<String,String> nutriments;

	public Product() {
		nutriments = new HashMap<String, String>();
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
