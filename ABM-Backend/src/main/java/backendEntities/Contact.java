package backendEntities;

/**
 * Interface of all {@code Contact}s that an {@code Entity} can have.
 * It is required that the {@code Contact} has a name, and that the information
 * of the contact can be presented as a String.
 * 
 * @author Lucas Andrade
 *
 */
public interface Contact {
	
	/**
	 * @return the name of the {@code Property}.
	 */
	public String getPropertyName();
	
	/**
	 * @return the {@code Contact}'s information as a {@code String}.
	 */
	public String getProperty();
}
