package commands;

import backendEntities.ApplicationUser;

public class NewUser extends DatabaseCommand{

	private String username;
	private String password;
	private String email;

	public NewUser(String username, String password, String email){
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	public void execute() throws CommandException {
		
		try{
			ApplicationUser user = new ApplicationUser(username, password, email);
			saveUser(user);
		} catch (IllegalArgumentException e) {
			throw new CommandException();
		}
	}

}
