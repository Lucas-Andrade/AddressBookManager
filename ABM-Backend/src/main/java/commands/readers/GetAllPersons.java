package commands.readers;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.Session;
import org.json.JSONArray;
import org.json.JSONObject;

import backendEntities.ApplicationUser;
import backendEntities.BookableEntity;
import commands.CommandException;
import commands.CommandUtils;

/**
 * Command that gets the list of {@code BookableEntities} by one {@code ApplicationUser}, and returns them as a
 * {@code JSONObject}.
 * 
 * @author Lucas Andrade
 *
 */
public class GetAllPersons implements CommandReader{

	/**
	 * username of the person that booked the persons
	 */
	private String username;
	
	/**
	 * Constructs the command that gets the list of {@code BookedEntities} by one {@code ApplicationUser}.
	 * @param username
	 */
	public GetAllPersons(String username){
		this.username = username;
	}
	
	/**
	 * @see CommandReader#call()
	 */
	public JSONObject call() throws CommandException {

		JSONObject json;
		Session session = CommandUtils.openSession();
		try{
			ApplicationUser user = (ApplicationUser) session.get(ApplicationUser.class, username);
			Map<String, BookableEntity> map = user.getBookedEntities();
			Iterator<Entry<String, BookableEntity>> iterator = map.entrySet().iterator();
			
			json = new JSONObject();
			JSONArray jsonArray = new JSONArray();

			while(iterator.hasNext()) {
				jsonArray.put(iterator.next().getValue().getJson());
			}
			
			json.put("Contact List", jsonArray);
			
		}catch(Exception e) {
			throw new CommandException();
		} finally {
			session.close();
		}
		
		return json;
	}

}
