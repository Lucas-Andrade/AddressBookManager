package backendEntities;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * A person, that has a name and several {@code Contact}s.
 * 
 * @author Lucas Andrade
 *
 */
@Entity
public class Person extends BookableEntity {
	
	/**
	 * {@code Person}'s name.
	 */
	@Column(name = "Person_Name")
	protected String name;
	
	/**
	 * The set of the known {@code Person}'s {@code Contact}s.
	 */
	@OneToMany(cascade = CascadeType.ALL)
	private Set<Contact> contacts = new HashSet<Contact>();
	
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
	 * @return the {@code name} of the {@code BookableEntity}.
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * Sets a new name for the {@code BookableEntity}
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
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
		
		if(! checkContacts(person.getContacts())){
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
	
	/**
	 * @return a {@code JSONObject} representation of {@code this}
	 */
	public JSONObject getJson() {

		JSONObject json = new JSONObject();
		json.put("Name", name);
		
		if(contacts.size() == 0) {
			json.put("Contact list", "Empty.");
		} else {
			JSONArray jsonArray = parseContacts();
			json.put("Contacts", jsonArray);
		}
		
		return json;
	}
	
	/**
	 * Constructs a {@code JSONArray} from the {@code contacts}, by using the method
	 * {@link Contact#getJson()}
	 * @return a {@code JSONArray} that represents the {@code contacts}
	 */
	private JSONArray parseContacts() {
		JSONArray array = new JSONArray();
		
		Iterator<Contact> iterator = contacts.iterator();
		while(iterator.hasNext()) {
			array.put(iterator.next().getJson());
		}
		
		return array;
	}

	/**
	 * Verifies if the {@code Set} passed as parameter has the same contacts as {@code this}. This means the {@code Set}s
	 * must have the same size and that every element contained in one must be contained in the other. This is verified
	 * by using the method {@link Contact#equals(Object)}.
	 * 
	 * @param otherSet
	 * @return {@code true} if the {@code Set} passed as parameter has the same contacts as {@code this}.
	 */
	private boolean checkContacts(Set<Contact> otherSet) {
		
		if(otherSet.size() != contacts.size())
			return false;
		
		Iterator<Contact> otherIterator = otherSet.iterator();
		while(otherIterator.hasNext()){
			
			Contact contact = otherIterator.next();
			Iterator<Contact> thisIterator = contacts.iterator();
			boolean hasNext = thisIterator.hasNext();
			while(hasNext){
				
				if(thisIterator.next().equals(contact)){
					break;
				}
				
				hasNext = thisIterator.hasNext();
				if(!hasNext) {
					return false;
				}
			}
			
		}
		
		return true;
		
	}

	
}
