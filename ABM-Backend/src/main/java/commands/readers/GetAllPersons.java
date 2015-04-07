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

public class GetAllPersons implements CommandReader{

	private String username;
	
	public GetAllPersons(String username){
		this.username = username;
	}
	
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
			
			json.put("Contacts", jsonArray);
			
		}catch(Exception e) {
			throw new CommandException();
		} finally {
			session.close();
		}
		
		return json;
	}

}
