package commands.readers;

import java.util.concurrent.Callable;

import org.json.JSONObject;

import commands.CommandException;

/**
 * Abstract class of all the {@code Command}s that read data from the database, but don't modify it.
 * 
 * @author Lucas Andrade
 *
 */
public interface CommandReader extends Callable<JSONObject>{

	/**
	 * Calls the command.
	 * @return the {@code JSONObject} that represents the object retrieved from the database.
	 */
	public abstract JSONObject call() throws CommandException;
	
}
