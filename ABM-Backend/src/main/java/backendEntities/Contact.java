package backendEntities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.InheritanceType;
import javax.persistence.Inheritance;


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
	 * The contact itself
	 */
	@Id
	protected String contact;
	
//	/**
//	 * @return the name of the {@code Property}.
//	 */
//	public abstract String getContactName();
	
	/**
	 * @return the {@code Contact}'s information as a {@code String}.
	 */
	public abstract String getContact();
	
	/**
	 * Sets a new contact
	 * @param contact
	 */
	public abstract void setContact(String contact);
	
}
