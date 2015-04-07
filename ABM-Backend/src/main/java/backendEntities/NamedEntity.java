package backendEntities;

import org.json.JSONObject;

/**
 * Interface of all named entities.
 * 
 * @author Lucas Andrade
 *
 */
public interface NamedEntity {
	
	/**
	 * @return the {@code name} of the {@code BookableEntity}.
	 */
	public String getName();
	
	/**
	 * Sets a new name for the {@code BookableEntity}
	 * @param name
	 */
	public void setName(String name);
	
	/**
	 * @return a {@code JSONObject} representation of the {@code NamedEntity}.
	 */
	public abstract JSONObject getJson();
}
