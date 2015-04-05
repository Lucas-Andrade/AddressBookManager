package commands;

/**
 * Interface of all the Commands
 * 
 * @author Lucas Andrade
 *
 */
public interface Command {

	/**
	 * Executes the command
	 */
	public void execute() throws CommandException;

}
