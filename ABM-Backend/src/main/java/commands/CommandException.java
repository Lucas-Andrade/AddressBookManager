package commands;

/**
 * This exception occurs when the execution of a {@code Command} catches an exception.
 * 
 * @author Lucas Andrade
 *
 */
@SuppressWarnings("serial")
public class CommandException extends Exception{

	public CommandException() {
	}

	public CommandException(String message) {
		super(message);
	}

	public CommandException(String message, Throwable cause) {
		super(message, cause);
	}
}