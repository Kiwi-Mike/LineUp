package application;

import java.util.ArrayList;

public class Team {
	private String teamName;
	private ArrayList<User> teamPlayersList;
	private ArrayList<User> teamCoachList;
//	private ImageView logo; 
	private User manager;
	private String sport;
	

	/**
	 * Create a new team object (without logo)
	 *
	 * @param teamName a string which represents the name of a team
	 * @param manager  an object of type User who is the manager of a team
	 */
	public Team(String teamName, User manager) {
		this.teamName = teamName;
		this.manager = manager;
		teamPlayersList = new ArrayList<User>();
		teamCoachList = new ArrayList<User>();
	}

	/**
	 * Invites a user to team.
	 * 
	 * @NotImplementedYet
	 * @param email
	 */
	public void inviteUser(String email) {
		//Creates user through email and adds to teamlist if not in userList
	}
	//invite user to team, takes 'User' as input
	public void inviteUser(User user) {
		this.teamPlayersList.add(user);
	}
	//invite coach to team, takes 'User' as input
	public void inviteCoach(User coachUser) {
		this.teamCoachList.add(coachUser);
	}

	/**
	 * Adds the user that has confirmed attendance to array list of players in team
	 * 
	 * @param
	 */
	public void addConfirmedUser(User player) {
		teamPlayersList.add(player);
	}

	/**
	 * Return team name
	 * 
	 * @return a string which represents the team name
	 */
	public String getTeamName() {
		return teamName;
	}

	/**
	 * Change team name
	 * 
	 * @param teamName a string which represents the NEW team name
	 */
	public void UpdateTeamName(String teamName) {
		this.teamName = teamName;
	}

	/**
	 * Returns the list of players in a team.
	 * 
	 * @return teamPlayersMap is an ArrayList containing objects of type User.
	 */
	public ArrayList<User> getTeamPlayersList() {
		return teamPlayersList;
	}

//	/**
//	 * Set array list, a list of objects of type User which includes each player in the team.
//	 * 
//	 * @param teamPlayersList is an ArrayList which holds objects of type User.
//	 */
//	public void setTeamPlayersList(ArrayList<User> teamPlayersList) {
//		this.teamPlayersList = teamPlayersList;
//	}

	/**
	 * Return's team manager's user object.
	 * 
	 * @return an object of type User who is the manager of the team.
	 */
	public User getManager() {
		return manager;
	}

	/**
	 * Set new object as manager of team.
	 * 
	 * @param manager an object of type User which is the NEW manager for the team
	 */
	public void UpdateManager(User manager) {
		this.manager = manager;
	}
	
	//Returns coach list
	public ArrayList<User> getteamCoachList() {
		return teamCoachList;
	}
	
	//Set sports field
	public void setSport(String sports) {
		this.sport = sports;
	}
	//get sports field
	public String getSport() {
		return this.sport;
	}
}
