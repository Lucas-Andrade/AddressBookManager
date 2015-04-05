package backendEntities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
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
	 * Contact identifier;
	 */
	@Id
	@GeneratedValue
	private int ContactId;
	
	/**
	 * @return the name of the {@code Property}.
	 */
	public abstract String getPropertyName();
	
	/**
	 * @return the {@code Contact}'s information as a {@code String}.
	 */
	public abstract String getProperty();

	/**
	 * @return the {@code Contact} identifier
	 */
	public int getContactId() {
		return ContactId;
	}

	/**
	 * Sets a new {@code Contact} identifier
	 * @param contactId
	 */
	public void setContactId(int contactId) {
		ContactId = contactId;
	}
	
	
}
