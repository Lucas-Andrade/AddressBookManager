package backendEntities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;


/**
 * Interface of all entities that can be saved in the Address Book.
 * 
 * @author Lucas Andrade
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class BookableEntity implements NamedEntity{
	
	/**
	 * Unique entity identifier.
	 */
	@Id
	@GeneratedValue
	@Column(name = "Entity_ID")
	private int EntityId;
	
		
	/**
	 * Compares the name of {@code this} with the name of the {@code Entity}
	 * passed as parameter.
	 * 
	 * @param obj
	 * @return 0 if the names are equal
	 * @return <0 or >0 if the names are not equal
	 */
	public abstract int compareTo(BookableEntity obj);
	
	/**
	 * @return the {@code Entity} identifier
	 */
	public int getEntityId() {
		return EntityId;
	}

	/**
	 * Sets a new {@code Entity} identifier
	 * @param entityId
	 */
	public void setEntityId(int entityId) {
		EntityId = entityId;
	}

	
}
