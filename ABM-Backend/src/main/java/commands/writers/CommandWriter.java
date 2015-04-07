package commands.writers;

import commands.CommandException;

/**
 * Abstract class of the {@code Command}s that save, update and remove data to the database.
 * In other words, these are the commands that save data to the database, and modify existing data. 
 * 
 * @author Lucas Andrade
 *
 */
public interface CommandWriter {

	/**
	 * Executes the command
	 */
	public void execute() throws CommandException;
}
