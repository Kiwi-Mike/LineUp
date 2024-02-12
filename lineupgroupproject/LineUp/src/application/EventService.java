package application;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class EventService {
	
	/** Assume the console tester is already a valid user and logged in */
	User testUser;
	
	public EventService() {
		
	}
	
	/** Hardcodes user, coach and team into a database */
	public void populateEventTest(Database data) {
		
			// Hardcoding user, coach and team
			testUser = new User("titustest", "titus@gmail.com", "titus123");
			User coach = new User("testCOACH", "coach@gmail.com", "coach123");
			Team testTeam = new Team("TheTests", testUser);
			
			// Populate database with test users, players, coaches and team
			data.userList.put(testUser.getUserEmail(), testUser);
			data.userList.put(coach.getUserEmail(), coach);
			data.teamList.add(testTeam);
	}
	
	/** Populates database with test user, players, coaches and team.
	 * Tests event creation fields, creates event object with inputs and adds to database */
	public void eventTester() {
		
		Database data = new Database();
		populateEventTest(data);
		
		// Test questions		
		Scanner scan = new Scanner(System.in);
	
		String eventName = nameCheck(scan);
		String eventDate = dateCheck(scan);
		int eventTime = timeCheck(scan);
		String coachName = coachCheck(scan, data);
	//	ArrayList<String> invitedPlayers = playersCheck(scan);  needs work
		Team teamName = teamCheck(scan, data);
		
		try {
			
			// Creates event from inputs, adds event to database and user's events list. 
			Event createdEvent = new Event(eventName, eventDate, eventTime, testUser, coachName);
			createdEvent.eventTeamList.add(teamName);
		//	createdEvent.eventUserList.addAll(players);
			
			data.eventList.add(createdEvent);
		//	System.out.println(data.eventList);
		//	testUser.userEventsList.add(createdEvent);
			
		}
		  catch(NullPointerException e)
	        {
	            System.out.print("NullPointerException caught " + e);
	        }
		
		scan.close(); 
	}

	
	/** Returns event name input (if less than 10 characters) */
	public String nameCheck(Scanner scan) {
		
		String nameInput;
		
		while (true) {
			
			System.out.println("Enter event name: ");
			nameInput = scan.nextLine();
			
			if (nameInput.length() >= 30) {
				System.out.println("Invalid. Please enter a name less than 10 characters.");
			}
			
			else {
				System.out.println("Thank you, " + nameInput + " added.");
				break;
			}	
		}
		return nameInput;
	}
	
	/** Checks if valid date*/
	public String dateCheck(Scanner scan) {
	
		// Date formatter (capital MM represents month, mm represents minutes in hour)
		SimpleDateFormat formatter = new SimpleDateFormat("yyy/MM/dd");
		// Current date
		Date currentDate = new Date();
		
		String userInput;
		
		while (true) {
			System.out.println("Enter event date in following format: yyyy/mm/dd: ");
			userInput = scan.nextLine();
			
			// Converts user input into date object (unless formatted incorrectly)
			Date userInputDate;
			
			try {
				// Converts into valid date
				 userInputDate = formatter.parse(userInput); 
				
				// If the current date is after what the user entered, return incorrect. 
				if (userInputDate.before(currentDate)) {
					System.out.println("Invalid. Please enter a future date.");
				}
				else if (userInputDate.after(currentDate)) {
					System.out.println("Thank you, " + userInputDate + " added.");
					break;
				}
				else {
					System.out.println("Invalid. Please enter the event date in the following format: yyyy/mm/dd.");
				}
					
			} catch (ParseException e) {
				System.out.println("Catch error" + e.toString());
			} 
		}
			
		return userInput;
	}
	
	/** Returns event time (12 30 pm format) */
	public int timeCheck(Scanner scan) {
		
		String userInput;

        while (true) {
            System.out.print("Enter a time in 24-hour format (12 00): ");
            userInput = scan.nextLine();
            
            // Splits string into integer parts, hrs & mins
            int hours = Integer.parseInt(userInput.split(" ")[0]);
            int mins = Integer.parseInt(userInput.split(" ")[1]);
            
            // Checks within bounds and converts input to int, returns new int
            if (hours >= 00 && hours <= 23 && mins >= 00 && mins <= 59) {
         
            	// Converts ints back to strings to be combined into 1200 format
            	String combine1 = String.format("%02d", hours); // ensures leading zeros are kept (eg 12 05 == 1205)
            	String combine2 = String.format("%02d", mins);
            	
            	String combinedTime = combine1 + combine2;
            	int newTime = Integer.parseInt(combinedTime);
            	
            	System.out.println("Thank you, " + newTime + " added.");
            	return newTime;
            }
            else {
            	System.out.println("Invalid. Please enter in '12 30' format: ");
            }
        }
    }
	
	
	/** Returns coach (if in system or user is coach) */
	public String coachCheck(Scanner scan, Database data) {
		
		String coachInput;
		
		while (true) {
		
			System.out.println("Invite manager by email, if you are the coach, please enter 'I am the manager': ");
			coachInput = scan.nextLine();
			
			// If coach is in system or invite by email
			if (data.userList.containsKey(coachInput) || coachInput.endsWith("@gmail.com")) {
				System.out.println("Thank you," + coachInput + " added.");
				break;
			}
			else if (coachInput.equalsIgnoreCase("I am the coach")) {
				coachInput = testUser.getUserEmail();
				System.out.println("Thank you, you have been added as the manager.");
				break;
			}
			else {
				System.out.println("Invalid, no coach found. Please enter a valid manager");
			}
		}
		
		return coachInput;
	}
	
	/** Checks if there is a team in database from name, returns team object */
	public Team teamCheck(Scanner scan, Database data) {
	
	String teamDetails;
	Team team;
		
		outerLoop: while (true) {
			
			System.out.println("Invite team by name: ");
			teamDetails = scan.nextLine();
			
			for (Team t : data.teamList) {
				if (teamDetails.equalsIgnoreCase(t.getTeamName())) {
					System.out.println("Thank you, " + t.getTeamName() + " added.");
					team = t;
					break outerLoop;
				}
				
				else {
					System.out.println("Invalid. No team found.");
				}
			}
		}
		return team;
	}
	
	
	public ArrayList<String> playersCheck(Scanner scan){
		
		ArrayList<String> emails = new ArrayList<String>();
		
		// Inviting players
	 outerLoop:	while (true) {
			System.out.println("Invite Players by email (eg ben@gmail.com titus@gmail.com): ");
			String players;
			
			while (scan.hasNext()) {
			players  = scan.next();
			
			if (players.endsWith("@gmail.com")) {
				emails.add(players);
				break outerLoop;
				}
			
			else {
				System.out.println("Invalid.");
				break;
			}
			}
		}
		
		return emails;
	}
}
