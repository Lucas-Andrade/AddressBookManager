package backendEntities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.InheritanceType;
import javax.persistence.Inheritance;

import org.json.JSONObject;


/**
 * Interface of all {@code Contact}s that an {@code Entity} can have.
 * It is required that the {@code Contact} has a name, and that the information
 * of the contact can be presented as a String.
 * 
 * @author Lucas Andrade
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Contact {
	
	/**
	 * The name of the type of contact.
	 */
	public final String NAME = "Contact";
	
	/**
	 * {@code Contact}'s unique identifier
	 */
	@Id
	@GeneratedValue
	public int contactId;
	
	/**
	 * @return the {@code Contact}'s information as a {@code String}.
	 */
	public abstract String getContact();
	
	/**
	 * Sets a new contact
	 * @param contact
	 */
	public abstract void setContact(String contact);
	
	/**
	 * @return the name of the type of contact.
	 */
	public abstract String getName();

	/**
	 * @return {@code this} contact's identifier
	 */
	public int getContactId() {
		return contactId;
	}

	/**
	 * Sets a new identifier
	 * @param contactId
	 */
	public void setContactId(int contactId) {
		this.contactId = contactId;
	}

	/**
	 * Compares the specified object with this set for equality.
	 */
	@Override
	public abstract boolean equals(Object object);
	
	/**
	 * @return a {@code JSONObject} representation of the {@code Contact}.
	 */
	public JSONObject getJson() {
		JSONObject json = new JSONObject();
		json.put(getName(), getContact());
		return json;
	}
	
}
