package backendEntities;

import java.util.Set;
import java.util.TreeSet;

/**
 * A person, that has a name and several {@code Contact}s.
 * 
 * @author Lucas Andrade
 *
 */
public class Person implements Entity {
	
	/**
	 * The {@code Person}'s name.
	 */
	private String name;
	
	/**
	 * The set of the known {@code Person}'s {@code Contact}s.
	 */
	private Set<Contact> properties = new TreeSet<Contact>();
	
	/**
	 * Constructs a new person, with a name and a set of the {@code Person}'s {@code Contact}s.
	 * @param name
	 * @param properties
	 */
	Person(String name, Contact... properties){
		this.name = name;
		
		for(int i = 0; i < properties.length; i++){
			this.properties.add(properties[i]);
		}
	}
	
	/**
	 * @return a {@code Set} with the {@code Person}'s {@code Contact}s.
	 */
	public Set<Contact> getProperties(){
		return properties;
	}
	
	/**
	 * @see Entity#getName()
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * Generates {@code this} object's Hash Code.
	 * @return {@code this} object's Hash Code.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((properties == null) ? 0 : properties.hashCode());
		return result;
	}

	/**
	 * Verifies if {@code this} has the same name, and same properties
	 * as the {@code Object} passed as parameter.
	 * 
	 * @return true if the object passed as parameter has the same properties as {@code this}.
	 */
	@Override
	public boolean equals(Object obj) {
		
		if(obj == null)
			return false;
		
		if(this == obj)
			return true;
		
		if(! getClass().equals(obj.getClass()))
			return false;
		
		Person person = (Person)obj;
		
		if(! name.equals(person.getName()))
			return false;
		
		if(! properties.equals(person.getProperties())){
			return false;
		}
		
		return true;
	}
	
	/**
	 * @see Entity#compareTo(Entity)
	 */
	public int compareTo(Entity obj){
		
		if(equals(obj))
			return 0;
		
		return name.compareTo(obj.getName());
	}
	
}
