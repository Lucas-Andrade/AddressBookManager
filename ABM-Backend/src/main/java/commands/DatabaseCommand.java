package commands;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
/**
 * Abstract class of the {@code Command}s that save, update and remove data to the database.
 * 
 * @author Lucas Andrade
 *
 */
public abstract class DatabaseCommand implements Command{

	public Session openSession() throws CommandException{
		try{
			SessionFactory sessionFact = SessionFactorySingleton.getInstance();
			Session session = sessionFact.openSession();
			session.beginTransaction();
			return session;
		} catch(Exception e) {
			throw new CommandException();
		}
	}
}
