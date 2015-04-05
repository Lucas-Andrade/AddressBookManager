package backendEntities;

import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 * A person, that has a name and several {@code Contact}s.
 * 
 * @author Lucas Andrade
 *
 */
@Entity
public class Person extends BookableEntity {
	
	/**
	 * The {@code Person}'s name.
	 */
	private String name;
	
	/**
	 * The set of the known {@code Person}'s {@code Contact}s.
	 */
	@OneToMany(cascade = CascadeType.ALL)
	private Set<Contact> contacts = new TreeSet<Contact>(new ContactComparator());
	
	/**
	 * Constructs a new person, with a name and a set of the {@code Person}'s {@code Contact}s.
	 * @param name
	 * @param contact
	 */
	public Person(String name, Contact... contact){
		this.name = name;
		
		for(int i = 0; i < contact.length; i++){
			this.contacts.add(contact[i]);
		}
	}
	
	/**
	 * Implicit constructor. Constructs a {@code Person} with no name and no 
	 * {@code Contact}s.
	 */
	public Person(){}

	/**
	 * @return the {@code Set} of {@code this} {@code Person}'s contacts.
	 */
	public Set<Contact> getContacts() {
		return contacts;
	}

	/**
	 * Sets the {@code Set} passed as parameter as the {@code Set} of {@code this} {@code Person}'s 
	 * contacts.
	 * @param contacts
	 */
	public void setContacts(Set<Contact> contacts) {
		this.contacts = contacts;
	}

	/**
	 * Sets the {@code String} passed as parameter as the new name of the {@code this}
	 * {@code Person}.
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @see BookableEntity#getName()
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
				+ ((contacts == null) ? 0 : contacts.hashCode());
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
		
		if(! contacts.equals(person.getContacts())){
			return false;
		}
		
		return true;
	}
	
	/**
	 * @see BookableEntity#compareTo(BookableEntity)
	 */
	public int compareTo(BookableEntity obj){
		
		if(equals(obj))
			return 0;
		
		return name.compareTo(obj.getName());
	}
	
}
