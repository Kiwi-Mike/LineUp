package application;

import java.lang.reflect.Array;

/**
 * Sample JavaDoc for the purpose of testing commit.
 */

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class Database {

	// HashMap which stores users' email as key and object as value
	HashMap<String, User> userList = new HashMap<String, User>();

	ArrayList<Team> teamList = new ArrayList<Team>();

	ArrayList<Event> eventList = new ArrayList<Event>();

	public Database() {
		
	}
	
	public Collection<User> getAllUsers(){
		return userList.values();
	}  

	// Adds a user to HashMap
	public void addUserToMap(String email, User user) {
		userList.put(user.getUserEmail(), user);
	}

	// Returns key and/or value within hashmap
	public HashMap<String, User> getUserList() {
		return userList;
	}

	// Returns team within TeamList
	public ArrayList<Team> getTeamList() {
		return teamList;
	}

	// Returns event within EventList
	public ArrayList<Event> getEventList() {
		return eventList;
	}
}
