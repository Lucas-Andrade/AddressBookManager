package backend;

import java.util.Set;
import java.util.TreeSet;

public class Person implements Entity {
	
	private String name;
	private Set<Property> properties = new TreeSet<Property>();
	
	Person(String name, Property... properties){
		this.name = name;
		
		for(int i = 0; i < properties.length; i++){
			this.properties.add(properties[i]);
		}
	}
	
	public Set<Property> getProperties(){
		return properties;
	}
	
	public String getName(){
		return name;
	}
	
}
