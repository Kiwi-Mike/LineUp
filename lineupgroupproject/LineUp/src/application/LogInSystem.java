package application;

public class LogInSystem {
	
	User user;
	Database database;
	
	public LogInSystem() {
		
	}
	
	// Cross-checks against HashMap in Database class whether user email/password exists and is correct. 
	public void checkValidLoginDetails(String email, String password){
		
	}
	
	// Creates an instance of User
	public void createUserObject(){
		
	}
	
	// Cross-checks against HashMap in Database class whether an account with XYZ email already exists. 
	public void checkEmailUsed(String email) {
		
	}
	
	// Returns user object
	public User getUser() {
		return user;
	}

	// Returns database object
	public Database getDatabase() {
		return database;
	}
}
