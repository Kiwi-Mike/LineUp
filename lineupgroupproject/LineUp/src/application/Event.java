package application;

import java.util.ArrayList;

public class Event {
	String eventName;
	ArrayList<Team> eventTeamList = new ArrayList<Team>();
	ArrayList<String> eventUserList;  // Invited users do not have to have a LineUp account, just email. 
	String eventDate;
	Integer eventTime;
	String coachEmail;
	User eventCreator;

	public User getEventCreator() {
		return eventCreator;
	}

	public void setEventCreator(User eventCreator) {
		this.eventCreator = eventCreator;
	}

	/**
	 * Class Constructor When creating an instance of an event the name, date, time,
	 * and manager of the event can be set.
	 * 
	 * @param eventName is a String declaring the name of the event
	 * @param eventDate is an integer declaring the date of the event
	 * @param eventTime is an Integer declaring the time of the event
	 * @param manager   is a String declaring the organizer or manager of the event.
	 */
	Event(String eventName, String eventDate, Integer eventTime, User eventCreator, String coach) {
		
	}

	/**
	 * Method creates event arrayLists
	 */
	public void createEventArrayLists() {
		eventTeamList = new ArrayList<Team>();
		eventUserList = new ArrayList<String>();
	}

	/**
	 * Invite a team to join an event
	 * 
	 * @NotImplementedYet
	 */
	public void inviteTeam() {
		// STILL TO BE IMPLEMENTED
	}

	/**
	 * Method adds a team whose manager has elected to participate in an event to
	 * the events ArrayList of type Team called eventTeamList.
	 * 
	 * @param team The parameter takes an object of type Team and adds it to the
	 *             arrayList called eventTeamList. A team whose manager elects to
	 *             join an event should have this method invoked on them.
	 */
	public void addTeamtoEventList(Team team) {
		eventTeamList.add(team);
	}

	/**
	 * Method removes a Team from the events ArrayList of type Team called
	 * eventTeamList (arrayList is stored in each instance of the event team class.)
	 * 
	 * @param team An object of type Team should be passed as the parameter.
	 */
	public void removeTeamFromEvent(Team team) {
		eventTeamList.remove(team);
	}

	/**
	 * Invites a user to join an event.
	 * 
	 * @NotImplementedYet
	 * 
	 */
	public void inviteUser() {
		// STILL TO BE IMPLEMENTED
	}

	/**
	 * Method adds a user who has elected to participate in an event to the events
	 * ArrayList of type Users called eventUserList
	 * 
	 * @param user The parameter takes an object of type User and adds it to the
	 *             arrayList eventUserList. Users who elect to join an event should
	 *             have this method invoked on them.
	 */
	public void addConfirmedUser(String user) {
		eventUserList.add(user);
	}

	/**
	 * Get an instance of an Events name.
	 * 
	 * @return This method returns a string which is the event name.
	 */
	public String getEventName() {
		return eventName;
	}

	/**
	 * Update the name of an event.
	 * 
	 * @param eventName A string that is the new name of the event
	 */
	public void UpdateEventName(String eventName) {
		this.eventName = eventName;
	}

	/**
	 * Get the date of an event.
	 * 
	 * @return an Integer that represents the date of the event
	 */
	public String getEventDate() {
		return eventDate;
	}

	/**
	 * Update the date of an event.
	 * 
	 * @param eventDate an Integer that represents the new date of the event.
	 */
	public void UpdateEventDate(String eventDate) {
		this.eventDate = eventDate;
	}

	/**
	 * Get the time of the event.
	 * 
	 * @return an integer that represents the time of the event.
	 */
	public Integer getEventTime() {
		return eventTime;
	}

	/**
	 * Update the time of an event.
	 * 
	 * @param eventTime an integer that represents the new time of an event.
	 */
	public void UpdateEventTime(Integer eventTime) {
		this.eventTime = eventTime;
	}

	/**
	 * Get the manager of an event
	 * 
	 * @return manager Which is an object of type User who is the manager of the
	 *         event (an instance of an event Object).
	 */
	public String getManager() {
		return coachEmail;
	}

	/**
	 * Update the manager of an event
	 * 
	 * @param manager Which is an object of type User who is the NEW manager of the
	 *                event (an instance of an event Object).
	 */
	public void UpdateManager(String manager) {
		this.coachEmail = manager;
	}

	/**
	 * Returns a list of teams participating in the event.
	 * 
	 * @return eventTeamList an ArrayList holding objects of type Team.
	 */
	public ArrayList<Team> getEventTeamList() {
		return eventTeamList;
	}

	/**
	 * 
	 * @param eventTeamList
	 */
	public void setEventTeamList(ArrayList<Team> eventTeamList) {
		this.eventTeamList = eventTeamList;
	}

	public ArrayList<String> getEventUserList() {
		return eventUserList;
	}

	public void setEventUserList(ArrayList<String> eventUserList) {
		this.eventUserList = eventUserList;
	}

}
