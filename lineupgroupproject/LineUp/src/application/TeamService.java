package application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

//Logic for creating teams and adding users/coaches
public class TeamService {
	private static Database database;
	private static User loggedInUser;
	public static ArrayList<Team> teamList; // teamList from database
	private static HashMap<String, User> userList; // userList from database
	private Scanner sc;

	public TeamService(Database database, User loggedInUser) {
//		System.out.println("Testing of creating teams. ");
		this.database = database;
		this.loggedInUser = loggedInUser;

		teamList = database.getTeamList();
		userList = database.getUserList();

//		test();
	}

	// Console Testing
	public void test() {
		sc = new Scanner(System.in);
		System.out.println("Option 1: Create new team,     2: invite player to team,      3: invite coach to team.");
		System.out.println("Option 4: Print teams list     5: Show team players & coaches");
		while (sc.hasNext()) {
			if (sc.hasNextInt()) {
				int option = sc.nextInt();
				if (option >= 1 & option <= 5) {
					if (option == 1) {
						newTeamOption();
					} else if (option == 2) {
						teamInvitePlayer();
					} else if (option == 3) {
						teamInviteCoach();
					} else if (option == 4) {
						printTeams();
					} else if (option == 5) {
						teamPlayersCoaches();
					}
				} else
					System.out.println("Enter valid option");
			} else {
				String invalidInput = sc.next(); // Consume the invalid input
				System.out.println("Invalid input. Please enter a valid option.");
			}
		}
	}

	// User creates new team if not already existing
	public static void newTeam(String teamName) {
		Team newTeam = new Team(teamName, loggedInUser);
		if (!existingTeam(teamName)) {
			teamList.add(newTeam);
			System.out.println("Team: " + teamName + " created.");
		} else
			System.out.println("Team: '" + teamName + "' is already taken.");
	}

	// check if team exists in database, case sensitive
	public static boolean existingTeam(String teamName) {
		for (Team team : teamList) {
			if (team.getTeamName().equals(teamName)) {
				return true;
			}
		}
		return false;
	}

	// checks if player is in team
	public static boolean existingPlayer(String teamName, String playerEmail) {
		Team team = findTeam(teamName);
		for (User user : team.getTeamPlayersList()) {
			if (user.getUserEmail().equalsIgnoreCase(playerEmail)) {
				return true;
			}
		}
		return false;
	}

	// checks if coach is in team
	public static boolean existingCoach(String teamName, String coachEmail) {
		Team team = findTeam(teamName);
		for (User coach : team.getteamCoachList()) {
			if (coach.getUserEmail().equalsIgnoreCase(coachEmail)) {
				return true;
			}
		}
		return false;
	}

	// Print teams List
	public static void printTeams() {
		System.out.print("Team List: ");
		for (Team team : teamList) {
			System.out.print(team.getTeamName() + ", ");
		}
		System.out.println();
	}

	// Console print out the list of players in team.
	public static void printTeamPlayers(String teamName) {
		Team team = findTeam(teamName);
		System.out.print("Player List: ");
		for (User user : team.getTeamPlayersList()) {
			System.out.print(user.getUserEmail());
		}
		System.out.println();
	}

	// Console print out coaches in team.
	public void printCoaches(String teamName) {
		Team team = findTeam(teamName);
		System.out.print("Coach List: ");
		for (User coach : team.getteamCoachList()) {
			System.out.print(coach.getUserEmail());
		}
		System.out.println();
	}

	// check if user in database
	public static boolean validUser(String email) {
		if (userList.containsKey(email)) {
			return true;
		} else
			return false;
	}

	// Add user to team,
	// If email not in team's player list, Creates user through email and adds to
	// teamlist if not in userList
	public static void inviteUserToTeam(String email, String teamName) {
		Team teamToAdd = findTeam(teamName);
		if (!existingPlayer(teamName, email)) {
			if (validUser(email)) {
				teamToAdd.inviteUser(userList.get(email));
				System.out.println("User with email " + email + " found, added as player.");
			} else {
				User invitedUser = new User(email);
				teamToAdd.inviteUser(invitedUser);
				database.addUserToMap(email, invitedUser);
				System.out.println("New user " + email + " created and added.");
			}
		} else {
			System.out.println("Email " + email + " is already a part of the team, " + teamName);
		}
	}

	// Add coach to Team, has to be a valid User to be added
	public static void inviteCoachToTeam(String coachEmail, String teamName) {
		Team teamToAdd = findTeam(teamName);
		if (!existingCoach(teamName, coachEmail)) {
			if (validUser(coachEmail)) {
				teamToAdd.inviteCoach(userList.get(coachEmail));
				System.out.println("User with email" + coachEmail + " found, added.");
			} else {
//				User invitedUser = new User(coachEmail);
//				teamToAdd.inviteUser(invitedUser);
//				database.addUserToMap(coachEmail, invitedUser);
				System.out.println("The coach, " + coachEmail + " must have an account to be added.");
			}
		} else {
			System.out.println("Email " + coachEmail + " is already a coach of " + teamName);
		}
	}

	// finds Team object from name, case sensitive
	public static Team findTeam(String teamName) {
		for (Team team : teamList) {
			if (team.getTeamName().equals(teamName)) {
				return team;
			}
		}
		return null;
	}

	public void newTeamOption() {
//		Scanner sc = new Scanner(System.in);
		System.out.println("Enter new team name: ");
		String userTeam = sc.next();
		newTeam(userTeam);
		askSports(userTeam);
	}

	public void teamInvitePlayer() {
//		Scanner sc = new Scanner(System.in);
		String userTeam = "";
		while (!existingTeam(userTeam)) {
			System.out.println("Existing Team name: ");
			userTeam = sc.next();
		}
		String userPlayer = "";
		while (!suitableEmail(userPlayer)) {
			System.out.println("Enter player email (requires @, char before & after, and full stop): ");
			userPlayer = sc.next();
		}
		inviteUserToTeam(userPlayer, userTeam);
//		sc.close();
	}

	// Checks emails to be suitable, requires at least 1 char before and after @,
	// and full stop
	public static boolean suitableEmail(String email) {
		String emailRegex = "^.+@.+$";
		if (email.contains("@") && email.contains(".") && email.matches(emailRegex)) {
			return true;
		}
		return false;
	}

	public void teamInviteCoach() {
		String userTeam = "";
		while (!existingTeam(userTeam)) {
			System.out.println("Existing Team name: ");
			userTeam = sc.next();
		}
		String userCoach = "";
		while (!suitableEmail(userCoach)) {
			System.out.println("Invtie coach email: ");
			userCoach = sc.next();
		}
		inviteUserToTeam(userCoach, userTeam);
	}

	// print team players and coaches
	public void teamPlayersCoaches() {
		String userTeam = "";
		while (!existingTeam(userTeam)) {
			System.out.println("Team name to show players and coaches: ");
			userTeam = sc.next();
		}
		Team team = findTeam(userTeam);
		System.out.println("Team: " + userTeam + ", Sport: " + team.getSport());
		printCoaches(userTeam);
		System.out.println();
		printTeamPlayers(userTeam);
	}

	// ask user for sports team
	public void askSports(String teamName) {
		Team team = findTeam(teamName);
		System.out.println("What kind of sports does this team play?");
		String sports = sc.next();
		team.setSport(sports);
		System.out.println("Team " + team.getTeamName() + " sports set as " + sports);
	}
}
