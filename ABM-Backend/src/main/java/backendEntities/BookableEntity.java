package backendEntities;

import javax.persistence.Entity;
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
public interface BookableEntity {
	
	/**
	 * @return the {@code name} of the {@code Entity}.
	 */
	public String getName();
	
	/**
	 * Compares the name of {@code this} with the name of the {@code Entity}
	 * passed as parameter.
	 * 
	 * @param obj
	 * @return 0 if the names are equal
	 * @return <0 or >0 if the names are not equal
	 */
	public int compareTo(BookableEntity obj);
}
