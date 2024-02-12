package application;

/**
 * Sample JavaDoc to test commit. change bobbie was here 2023
 */

import java.util.ArrayList;

public class User {

	private String userName;
	private String usersAge;
	private String usersGender;
	private String userEmail;
	private String userPassword;

	ArrayList<Event> userEventsList = new ArrayList<Event>(); // List of events the user is signed up for.
	ArrayList<Team> userTeamsList = new ArrayList<Team>(); // List of teams the user is apart of.

	/**
	 * Constructor for a user, passes a name,email and password specific to the
	 * user.
	 * 
	 * @param userName     First and last name of the user.
	 * @param userEmail    is the email address tide to the user.
	 * @param userPassword is the password the user provides for their log in.
	 */
	public User(String userName, String userEmail, String userPassword) {
		this.userName = userName;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
	}

	// Overload constructor when user invited from email
	public User(String userEmail) {
		this.userEmail = userEmail;
	}

	/**
	 * Returns the users full name.
	 * 
	 * @return First and Last name of the user.
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * 
	 * @param userName first and last name provided by the user.
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUsersAge() {
		return usersAge;
	}

	public void setUsersAge(String usersAge) {
		this.usersAge = usersAge;
	}

	public String getUsersGender() {
		return usersGender;
	}

	public void setUsersGender(String usersGender) {
		this.usersGender = usersGender;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public ArrayList<Event> getUserEventsList() {
		return userEventsList;
	}

	public void setUserEventsList(ArrayList<Event> userEventsList) {
		this.userEventsList = userEventsList;
	}

	public ArrayList<Team> getUserTeamsList() {
		return userTeamsList;
	}

	public void setUserTeamsList(ArrayList<Team> userTeamsList) {
		this.userTeamsList = userTeamsList;
	}

}
