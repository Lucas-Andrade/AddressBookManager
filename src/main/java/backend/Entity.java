package backend;

/**
 * Interface of all entities that can be saved in the Address Book.
 * 
 * @author Lucas Andrade
 *
 */
public interface Entity {
	
	/**
	 * @return the {@code name} of the {@code Entity}.
	 */
	public String getName();
}
