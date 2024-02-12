package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

	public Pane createAccountScreenUI;
	public Pane loginScreenUI;
	public UI createEventScreenUI;
	public Pane homeScreenUI;
	public Pane createTeamScreenUI;
	public Database databaseLineUp;
	public Stage primaryStage;
	public Scene mainScene;

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		try {
			databaseLineUp = new Database();
			loginScreenUI = new LoginScreenUI(this);
			mainScene = new Scene(loginScreenUI, 700, 600);
			primaryStage.setScene(mainScene);

			primaryStage.show();

//			createTeamScreenUI = new CreateTeamScreenUI();

			User user1 = new User("Mark Zuckerberg", "mark@gmail.com", "zuck");
			User user2 = new User("Elon Musk", "elon@gmail.com", "tesla");
			User user3 = new User("Donald Trump", "trump@gmail.com", "trump");
			databaseLineUp.addUserToMap("mark@gmail.com", user1);
			databaseLineUp.addUserToMap("elon@gmail.com", user2);
			databaseLineUp.teamList.add(new Team("Facebook", user1));

//			 Scanner sc = new Scanner(System.in);
//			 System.out.println("Email of valid user: ");
//	         String userEmail = sc.next();
//	         while (!databaseLineUp.getUserList().containsKey(userEmail)) {
//	         	System.out.println("Enter valid user email in database");
//	         	userEmail = sc.next();
//	         }
//	         TeamService userLogged = new TeamService(databaseLineUp, new User(userEmail));
//			 sc.close();
//			testing ends

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}