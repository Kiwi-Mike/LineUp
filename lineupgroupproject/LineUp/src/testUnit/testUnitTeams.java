package testUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import application.Database;
import application.Team;
import application.TeamService;
import application.User;

class testUnitTeams {
	User user;
	User userB;
	Database database;

	@BeforeEach
	public void initialize() {

		user = new User("emailA@gmail.com");
		userB = new User("emailB@gmail.com");

		Team teamA = new Team("Team A", user);
//		Team teamB = new Team("Team B", new User("emailB@gmail.com"));
//		Team teamC = new Team("Team C", new User("emailC@gmail.com"));
//		Team teamD = new Team("Team D", new User("emailD@gmail.com"));

		database = new Database();

		database.getTeamList().add(teamA);
		database.getUserList().put("emailB@gmail.com", userB);
		database.getUserList().put("emailA@gmail.com", user);

		TeamService teamService = new TeamService(database, user);

	}

	/**
	 * Team tests
	 */
	// method for checking a team exists
	@Test
	void testNoExistingTeam() {
		assertEquals(false, TeamService.existingTeam("Team B"));
	}

	// method checking a team is added
	@Test
	void testAddTeam() {
		assertEquals(false, TeamService.existingTeam("Team C"));
		TeamService.newTeam("Team C");
		assertEquals(true, TeamService.existingTeam("Team C"));
	}

	// a team with the same name cannot be added twice
	@Test
	void testAddFailTeam() {
		assertEquals(false, TeamService.existingTeam("Team C"));
		TeamService.newTeam("Team C");
		assertEquals(true, TeamService.existingTeam("Team C"));
		TeamService.newTeam("Team C");
		assertEquals(2, database.getTeamList().size());
	}

	// an empty (string) can be added as a team name
	@Test
	void testAddEmptyFailTeam() {
		TeamService.newTeam("");
		assertEquals(1, database.getTeamList().size());
	}

	// a null object can be entered to the list of teams
	@Test
	void testAddNullFailTeam() {
		assertEquals(true, TeamService.existingTeam("Team A"));
		TeamService.newTeam(null);
		assertEquals(1, database.getTeamList().size());
	}

	@Test
	void checkTeamExists() {
		assertEquals(true, TeamService.existingTeam("Team A"));
	}

	/**
	 * Player tests
	 */
	@Test
	void testNoExistingPlayerInTeam() {
		assertEquals(false, TeamService.existingPlayer("Team A", "emailA@gmail.com"));
	}

	@Test
	void testExistingPlayerInTeam() {
		TeamService.teamList.get(0).inviteUser(user);
		assertEquals(true, TeamService.existingPlayer("Team A", "emailA@gmail.com"));

	}

	@Test // testing existing user, adding to team
	void testInviteUsertoTeam() {
		TeamService.printTeamPlayers("Team A");
		assertEquals(false, TeamService.existingPlayer("Team A", "emailB@gmail.com"));

		TeamService.inviteUserToTeam("emailB@gmail.com", "Team A");
		TeamService.printTeamPlayers("Team A");
		assertEquals(true, TeamService.existingPlayer("Team A", "emailB@gmail.com"));

	}

	@Test // testing to ensure method doesn't add players twice, teamplayerlist size
			// doesn't increase
	void inviteUserTwiceToTeam() {

		TeamService.inviteUserToTeam("emailB@gmail.com", "Team A");
		assertEquals(true, TeamService.existingPlayer("Team A", "emailB@gmail.com"));

		int current = TeamService.teamList.get(0).getTeamPlayersList().size();

		TeamService.inviteUserToTeam("emailB@gmail.com", "Team A");

		int after = TeamService.teamList.get(0).getTeamPlayersList().size();

		assertEquals(current, after);
	}

	@Test // invite new user to team testing
	void testCreateAndInviteNewUserToTeam() {
		// check user doesn't already exist
		assertEquals(false, TeamService.existingPlayer("Team A", "emailZ@gmail.com"));
		// get current team size
		int current = TeamService.teamList.get(0).getTeamPlayersList().size();
		//
		TeamService.inviteUserToTeam("emailZ@gmail.com", "Team A");

		// check team size has increased
		int after = TeamService.teamList.get(0).getTeamPlayersList().size();
		assertEquals(current, after - 1);
	}

	/**
	 * coach tests
	 */
	@Test
	void testNoExistingCoachInListInTeam() {
		assertEquals(false, TeamService.existingCoach("Team A", "emailB@gmail.com"));
	}

	@Test
	void testExistingCoachInListInTeam() {
		// add user A as a coach
		TeamService.teamList.get(0).getteamCoachList().add(user);
		assertEquals(true, TeamService.existingCoach("Team A", "emailA@gmail.com"));
	}
	// test revealed that when an instance of a team is made using the constructor,
	// the user passed as the manager is not a coach

	@Test
	void testInviteCoachToTeam() {
		assertEquals(false, TeamService.existingCoach("Team A", "emailB@gmail.com"));
		TeamService.inviteCoachToTeam("emailB@gmail.com", "Team A");
		assertEquals(true, TeamService.existingCoach("Team A", "emailB@gmail.com"));
	}

	@Test
	void testInviteCoachTwiceToTeam() {
		initialize();
		// check user doesn't already exist
		assertEquals(false, TeamService.existingCoach("Team A", "emailA@gmail.com"));
		// get current team size
		int current = TeamService.teamList.get(0).getteamCoachList().size();
		//
		TeamService.inviteCoachToTeam("emailA@gmail.com", "Team A");

		// check team size has increased
		int after = TeamService.teamList.get(0).getteamCoachList().size();
		assertEquals(current, after - 1);
	}

	/**
	 * User in database test
	 */
	@Test
	void testValidUser() {
		assertEquals(true, TeamService.validUser("emailA@gmail.com"));
	}

	@Test
	void testNoValidUser() {
		assertEquals(false, TeamService.validUser("emailY@gmail.com"));
	}

	@Test
	void testSuitableEmail() {
		assertEquals(false, TeamService.suitableEmail("emailgmail.com"));
		assertEquals(false, TeamService.suitableEmail("email@gmail"));
		assertEquals(false, TeamService.suitableEmail("emailgmail"));
		assertEquals(true, TeamService.suitableEmail("emailY@gmail.com"));
	}

	@Test
	void testNothing() {

	}
}
