package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class CreateTeamScreenUI extends GridPane implements UI {
	// Tracks columns and rows used in the invite list grid panes
	int invitedPlayerRow = 1;
	int invitedPlayerColumn = 0;
	int invitedCoachRow = 1;
	int invitedCoachColumn = 0;
	Main main;

	public CreateTeamScreenUI(Main main) {
		this.main = main;
		createTeamGridPane();
	};

	public void show(Stage createTeamScreen) {
		GridPane createTeamScreenGridPane = createTeamGridPane();
		Scene scene = new Scene(createTeamScreenGridPane, 700, 700);
		createTeamScreen.setScene(scene);
	}

	public GridPane createTeamGridPane() {

		// Creating GridPane and setting background
		GridPane createTeamScreen = this;
		Background screenBackground = screenBackground();
		createTeamScreen.setBackground(screenBackground);

		// Logo Text
		Text lineUpLogoText = lineUpLogoText();
		GridPane.setMargin(lineUpLogoText, new Insets(50, 5, 10, 5)); // the parameters buffer (Top, Left, Bottom,Right)
		GridPane.setColumnSpan(lineUpLogoText, 2); // Setting to span two columns to center
		GridPane.setConstraints(lineUpLogoText, 0, 0); // sets position in grid pane
		GridPane.setHalignment(lineUpLogoText, HPos.CENTER); // sets horizontal position on GridPane

		// Create Team Text
		Text createTeamTitle = createTeamTitleText();
		GridPane.setMargin(createTeamTitle, new Insets(20, 0, 10, 0)); // the parameters buffer (Top, Left, Bottom,
																		// Right)
		GridPane.setColumnSpan(createTeamTitle, 2); // Setting to spawn two columns to center
		GridPane.setConstraints(createTeamTitle, 0, 1); // sets position in grid pane
		GridPane.setHalignment(createTeamTitle, HPos.CENTER); // sets horizontal position on GridPane

		// Title above where team names are input
		Text teamNameText = teamNameText();
		GridPane.setConstraints(teamNameText, 0, 2); // sets position in grid pane
		GridPane.setHalignment(teamNameText, HPos.LEFT);

		// Text field where team names are input
		TextField teamNameTextField = teamNameTextField();
		GridPane.setMargin(teamNameTextField, new Insets(0, 0, 15, 0));
		GridPane.setConstraints(teamNameTextField, 0, 3); // sets position in grid pane

		// Title above where player names are input
		Text playerNameText = addPlayerText();
		GridPane.setConstraints(playerNameText, 0, 4); // sets position in grid pane

		// Title above where coach names are input
		Text coachNameText = addCoachText();
		GridPane.setConstraints(coachNameText, 1, 4); // sets position in grid pane

		// Text Field where player names are input
		TextField playerNameTextField = playerNameTextField();
		GridPane.setMargin(playerNameTextField, new Insets(0, 0, 15, 0));
		GridPane.setConstraints(playerNameTextField, 0, 5); // sets position in grid pane

		// Text field where coach names are input
		TextField coachNameTextField = coachNameTextField();
		GridPane.setMargin(coachNameTextField, new Insets(0, 0, 15, 0));
		GridPane.setConstraints(coachNameTextField, 1, 5);

		// Invite Player button, adds players to invite list
		Button invitePlayerButton = invitePlayerButton();
		GridPane.setMargin(invitePlayerButton, new Insets(0, 0, 15, 0));
		GridPane.setConstraints(invitePlayerButton, 0, 6);
		GridPane.setHalignment(invitePlayerButton, HPos.RIGHT);

		// Invite coach button, adds coaches to invite list
		Button inviteCoachButton = inviteCoachButton();
		GridPane.setMargin(inviteCoachButton, new Insets(0, 0, 15, 0));
		GridPane.setConstraints(inviteCoachButton, 1, 6);
		GridPane.setHalignment(inviteCoachButton, HPos.RIGHT);

		// GridPane that contains all the names entered into the player name TextField
		GridPane invitedPlayersPane = new GridPane();
		invitedPlayersPane.setVgap(5);
		invitedPlayersPane.setHgap(5);
		invitedPlayersPane.setPadding(new Insets(0, 0, 0, 5));

		// GridPane that contains all the names entered into the coach name TextField
		GridPane invitedCoachPane = new GridPane();
		invitedCoachPane.setVgap(5);
		invitedCoachPane.setHgap(5);
		invitedCoachPane.setPadding(new Insets(0, 0, 0, 5));

		// Pane that serves as the background for the invited player names
		Pane invitedPlayersBackground = new Pane();
		invitedPlayersBackground.setBackground(transparentBackground());
		invitedPlayersBackground.getChildren().add(invitedPlayersPane);
		invitedPlayersBackground.setMinWidth(300);
		invitedPlayersBackground.setMinHeight(110);
		GridPane.setConstraints(invitedPlayersBackground, 0, 8); // sets position in grid pane
		GridPane.setHalignment(invitedPlayersBackground, HPos.LEFT);

		// Pane that serves as the background for the invited coach names
		Pane invitedCoachBackground = new Pane();
		invitedCoachBackground.setBackground(transparentBackground());
		invitedCoachBackground.getChildren().add(invitedCoachPane);
		invitedCoachBackground.setMinWidth(300);
		invitedCoachBackground.setMinHeight(110);
		GridPane.setConstraints(invitedCoachBackground, 1, 8); // sets position in grid pane
		GridPane.setHalignment(invitedCoachBackground, HPos.LEFT);

		// The text used as the title above the invited players list
		Text invitedPlayerTitle = invitedPlayerTitleText();
		GridPane.setConstraints(invitedPlayerTitle, 0, 7); // sets position in grid pane
		GridPane.setHalignment(invitedPlayerTitle, HPos.LEFT);

		// The text used as the title above the invited coach list
		Text invitedCoachTitle = invitedCoachTitleText();
		GridPane.setConstraints(invitedCoachTitle, 1, 7); // sets position in grid pane
		GridPane.setHalignment(invitedCoachTitle, HPos.LEFT);

		// Button that finalises all input and loads home screen
		Button createTeamButton = createTeamButton();
		GridPane.setConstraints(createTeamButton, 0, 9); // sets position in grid pane
		GridPane.setHalignment(createTeamButton, HPos.RIGHT);
		GridPane.setMargin(createTeamButton, new Insets(60, 0, 0, 0));

		// Button that cancels team creation
		Button cancelTeamButton = cancelTeamButton();
		GridPane.setConstraints(cancelTeamButton, 1, 9); // sets position in grid pane
		GridPane.setHalignment(cancelTeamButton, HPos.LEFT);
		GridPane.setMargin(cancelTeamButton, new Insets(60, 0, 0, 0));
		
		createTeamButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				main.homeScreenUI = new HomeScreenUI(main);
				double sceneWidth = main.primaryStage.getScene().getWidth();
				double sceneHeight = main.primaryStage.getScene().getHeight();
				Scene homeScreenScene = new Scene(main.homeScreenUI, sceneWidth, sceneHeight);
				main.primaryStage.setScene(homeScreenScene);

			}
		});
		
		cancelTeamButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				main.homeScreenUI = new HomeScreenUI(main);
				double sceneWidth = main.primaryStage.getScene().getWidth();
				double sceneHeight = main.primaryStage.getScene().getHeight();
				Scene homeScreenScene = new Scene(main.homeScreenUI, sceneWidth, sceneHeight);
				main.primaryStage.setScene(homeScreenScene);
			}
		});

		// Drop down menu to select sport the team plays
		ComboBox<?> sportSelection = sportSelectionDropDown();
		GridPane.setConstraints(sportSelection, 1, 3); // sets position in grid pane
		GridPane.setHalignment(sportSelection, HPos.LEFT);
		GridPane.setValignment(sportSelection, VPos.TOP);
		
		
		Text teamTypeTitle = teamTypeTitleText();
		GridPane.setConstraints(teamTypeTitle, 1, 2); // sets position in grid pane
		GridPane.setHalignment(teamTypeTitle, HPos.LEFT);

		// Action listener for the invite player button
		invitePlayerButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {

				String enteredText = playerNameTextField.getText() + ","; // gets text from input field and adds a comma

				Text invitedPlayer = new Text(enteredText);
				invitedPlayer.setFont(Font.font("Helvetica", 15)); // sets font Style, Weight and Size
				invitedPlayer.setFill(Color.WHITE);

				// Limits the number of columns to 5 by incrementing invitedPlayerColumn each
				// input
				// When invitedPlayerColumn reaches 5, is reset to 0 and moves to next row

				if (invitedPlayerColumn < 5) {
					GridPane.setConstraints(invitedPlayer, invitedPlayerColumn, invitedPlayerRow);
					invitedPlayerColumn++;
				} else if (invitedPlayerColumn >= 5) {
					invitedPlayerColumn = 0;
					invitedPlayerRow++;
					GridPane.setConstraints(invitedPlayer, invitedPlayerColumn, invitedPlayerRow);
				}
				invitedPlayersPane.getChildren().add(invitedPlayer); // adding player to the grid pane
				playerNameTextField.setText(""); // clears the input field of text
			}
		});

		// Invite coach button action listener
		inviteCoachButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {

				String enteredText = coachNameTextField.getText() + ","; // gets text from input and adds comma

				Text invitedCoach = new Text(enteredText);
				invitedCoach.setFont(Font.font("Helvetica", 15)); // sets font Style, Weight and Size
				invitedCoach.setFill(Color.WHITE);

				// Limits the number of columns to 5 by incrementing invitedCoachColumn each
				// input
				// When invitedCoachColumn reaches 5, is reset to 0 and moves to next row

				if (invitedCoachColumn < 5) {
					GridPane.setConstraints(invitedCoach, invitedCoachColumn, invitedCoachRow);
					invitedCoachColumn++;
				} else if (invitedCoachColumn >= 5) {
					invitedCoachColumn = 0;
					invitedCoachRow++;
					GridPane.setConstraints(invitedCoach, invitedCoachColumn, invitedCoachRow);
				}
				invitedCoachPane.getChildren().add(invitedCoach); // adds text to the grid pane
				coachNameTextField.setText(""); // clears the text input
			}
		});

		// Player Name TextField input action listener
		playerNameTextField.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {

				String enteredText = playerNameTextField.getText() + ","; // gets the text and adds a comma

				Text invitedPlayer = new Text(enteredText);
				invitedPlayer.setFont(Font.font("Helvetica", 15)); // sets font Style, Weight and Size
				invitedPlayer.setFill(Color.WHITE);

				// Limits the number of columns to 5 by incrementing invitedPlayerColumn each
				// input
				// When invitedPlayerColumn reaches 5, is reset to 0 and moves to next row

				if (invitedPlayerColumn < 5) {
					GridPane.setConstraints(invitedPlayer, invitedPlayerColumn, invitedPlayerRow);
					invitedPlayerColumn++;
				} else if (invitedPlayerColumn >= 5) {
					invitedPlayerColumn = 0;
					invitedPlayerRow++;
					GridPane.setConstraints(invitedPlayer, invitedPlayerColumn, invitedPlayerRow);
				}
				invitedPlayersPane.getChildren().add(invitedPlayer); // adds text to grid pane
				playerNameTextField.setText(""); // clears the input field
			}
		});

		// Action Listener for the coach text field
		coachNameTextField.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {

				String enteredText = coachNameTextField.getText() + ","; // gets the input and adds a comma

				Text invitedCoach = new Text(enteredText);
				invitedCoach.setFont(Font.font("Helvetica", 15)); // sets font Style, Weight and Size
				invitedCoach.setFill(Color.WHITE);

				// Limits the number of columns to 5 by incrementing invitedCoachColumn each
				// input
				// When invitedCoachColumn reaches 5, is reset to 0 and moves to next row
				if (invitedCoachColumn < 5) {
					GridPane.setConstraints(invitedCoach, invitedCoachColumn, invitedCoachRow);
					invitedCoachColumn++;
				} else if (invitedCoachColumn >= 5) {
					invitedCoachColumn = 0;
					invitedCoachRow++;
					GridPane.setConstraints(invitedCoach, invitedCoachColumn, invitedCoachRow);
				}
				invitedCoachPane.getChildren().add(invitedCoach); // adds the text to grid pane
				coachNameTextField.setText(""); // clears the input
			}
		});

		// adding all children to pane
		createTeamScreen.getChildren().addAll(lineUpLogoText, createTeamTitle, teamNameText, teamNameTextField,
				playerNameText, playerNameTextField, invitePlayerButton, invitedPlayersBackground, invitedPlayerTitle,
				sportSelection, coachNameText, coachNameTextField, inviteCoachButton, invitedCoachBackground,
				invitedCoachTitle, createTeamButton, cancelTeamButton,teamTypeTitle);

		// setting alignment of GridPane
		createTeamScreen.setAlignment(Pos.TOP_CENTER);
		createTeamScreen.setVgap(5);
		createTeamScreen.setHgap(30);

		return createTeamScreen;
	}

	/**
	 * Creates Text Object of the LineUp Logo
	 * 
	 * @return Text Object of LineUp Logo
	 */

	public Text lineUpLogoText() {
		Text lineUpLogoText = new Text("LineUp"); // setting the text of the object
		lineUpLogoText.setFont(Font.font("Helvetica", FontWeight.BOLD, 50)); // sets font Style, Weight and Size
		lineUpLogoText.setFill(Color.WHITE); // sets color

		return lineUpLogoText;

	}

	/**
	 * Creates Text Object that says Create a New Team using our default formatting
	 * 
	 * @return Text Object saying Create a New Team
	 */
	public Text createTeamTitleText() {
		Text createTeamTitleText = new Text("Create a New Team"); // sets the text
		createTeamTitleText.setFont(Font.font("Helvetica", FontWeight.BOLD, 15)); // sets font Style, Weight and Size
		createTeamTitleText.setFill(Color.WHITE);

		return createTeamTitleText;

	}

	/**
	 * Creates Text Object saying Player Name
	 * 
	 * @return the Text Object saying Player Name
	 */
	public Text addPlayerText() {
		Text addPlayerText = new Text("Player Email*");
		addPlayerText.setFont(Font.font("Helvetica", FontWeight.BOLD, 10)); // sets font Style, Weight and Size
		addPlayerText.setFill(Color.WHITE);

		return addPlayerText;

	}

	/**
	 * Creates Text Object saying Coach Name
	 * 
	 * @return Text Object saying Coach Name
	 */
	public Text addCoachText() {
		Text addPlayerText = new Text("Coach Email*");
		addPlayerText.setFont(Font.font("Helvetica", FontWeight.BOLD, 10)); // sets font Style, Weight and Size
		addPlayerText.setFill(Color.WHITE);

		return addPlayerText;

	}

	/**
	 * Creates a Text Object that says Team Name with our default formatting
	 * 
	 * @return Text Object that says Team Name
	 */
	public Text teamNameText() {
		Text teamNameText = new Text("Team Name*");
		teamNameText.setFont(Font.font("Helvetica", FontWeight.BOLD, 10)); // sets font Style, Weight and Size
		teamNameText.setFill(Color.WHITE);

		return teamNameText;

	}

	/**
	 * Creates a the add player button
	 * 
	 * @return a button object
	 */
	public Button invitePlayerButton() {
		String buttonText = "Add Player to Invite List";

		Button invitePlayerButton = new Button();
		invitePlayerButton.setText(buttonText);

		Rectangle buttonShape = new Rectangle();
		buttonShape.setWidth(400);
		buttonShape.setHeight(100);
		buttonShape.setArcWidth(50);
		buttonShape.setArcHeight(100);

		invitePlayerButton.setFont(Font.font("Helvetica", FontWeight.BOLD, 15));
		invitePlayerButton.setStyle("-fx-background-color: white; -fx-text-fill: black;");
		invitePlayerButton.setShape(buttonShape);
		invitePlayerButton.setPrefWidth(200);
		invitePlayerButton.setPrefHeight(35);

		DropShadow dropShadow = new DropShadow();
		dropShadow.setOffsetX(3.0f);
		dropShadow.setOffsetY(5.0f);
		dropShadow.setColor(Color.rgb(150, 50, 50, .688));
		invitePlayerButton.setEffect(dropShadow);

		return invitePlayerButton;

	}

	/**
	 * Creates the invite coach button with our formatting that adds the text input
	 * to the invite list
	 * 
	 * @return button that says "Add Coach to Invite List"
	 */
	public Button inviteCoachButton() {
		String buttonText = "Add Coach to Invite List";

		Button inviteCoachButton = new Button();
		inviteCoachButton.setText(buttonText);

		Rectangle buttonShape = new Rectangle();
		buttonShape.setWidth(400);
		buttonShape.setHeight(100);
		buttonShape.setArcWidth(50);
		buttonShape.setArcHeight(100);

		inviteCoachButton.setFont(Font.font("Helvetica", FontWeight.BOLD, 15));
		inviteCoachButton.setStyle("-fx-background-color: white; -fx-text-fill: black;");
		inviteCoachButton.setShape(buttonShape);
		inviteCoachButton.setPrefWidth(200);
		inviteCoachButton.setPrefHeight(35);

		DropShadow dropShadow = new DropShadow();
		dropShadow.setOffsetX(3.0f);
		dropShadow.setOffsetY(5.0f);
		dropShadow.setColor(Color.rgb(150, 50, 50, .688));
		inviteCoachButton.setEffect(dropShadow);

		return inviteCoachButton;

	}

	/**
	 * Creates the Create Team Button that finalises all inputs and creates the team
	 * in the system
	 * 
	 * @return Button
	 */
	public Button createTeamButton() {
		String buttonText = "Create Team";

		Button createTeam = new Button();
		createTeam.setText(buttonText);

		Rectangle buttonShape = new Rectangle();
		buttonShape.setWidth(400);
		buttonShape.setHeight(100);
		buttonShape.setArcWidth(50);
		buttonShape.setArcHeight(100);

		createTeam.setFont(Font.font("Helvetica", FontWeight.BOLD, 15));
		createTeam.setStyle("-fx-background-color: white; -fx-text-fill: black;");
		createTeam.setShape(buttonShape);
		createTeam.setPrefWidth(200);
		createTeam.setPrefHeight(35);

		DropShadow dropShadow = new DropShadow();
		dropShadow.setOffsetX(3.0f);
		dropShadow.setOffsetY(5.0f);
		dropShadow.setColor(Color.rgb(150, 50, 50, .688));
		createTeam.setEffect(dropShadow);

		return createTeam;
	}

	/**
	 * Creates a button used to cancel the create team process
	 * 
	 * @return Button
	 */
	public Button cancelTeamButton() {
		String buttonText = "Cancel";

		Button cancelTeam = new Button();
		cancelTeam.setText(buttonText);

		Rectangle buttonShape = new Rectangle();
		buttonShape.setWidth(400);
		buttonShape.setHeight(100);
		buttonShape.setArcWidth(50);
		buttonShape.setArcHeight(100);

		cancelTeam.setFont(Font.font("Helvetica", FontWeight.BOLD, 15));
		cancelTeam.setStyle("-fx-background-color: white; -fx-text-fill: black;");
		cancelTeam.setShape(buttonShape);
		cancelTeam.setPrefWidth(200);
		cancelTeam.setPrefHeight(35);

		DropShadow dropShadow = new DropShadow();
		dropShadow.setOffsetX(3.0f);
		dropShadow.setOffsetY(5.0f);
		dropShadow.setColor(Color.rgb(150, 50, 50, .688));
		cancelTeam.setEffect(dropShadow);

		return cancelTeam;
	}

	/**
	 * Creates Text that says Invite Players
	 * 
	 * @return Text Object
	 */
	public Text invitedPlayerTitleText() {
		Text invitedCoaches = new Text("Invited Players");
		invitedCoaches.setFont(Font.font("Helvetica", FontWeight.BOLD, 10)); // sets font Style, Weight and Size
		invitedCoaches.setFill(Color.WHITE);
		return invitedCoaches;
	}

	/**
	 * Creates Text that says Invited Coaches
	 * 
	 * @return Text Object
	 */
	public Text invitedCoachTitleText() {
		Text invitedPlayers = new Text("Invited Coaches");
		invitedPlayers.setFont(Font.font("Helvetica", FontWeight.BOLD, 10)); // sets font Style, Weight and Size
		invitedPlayers.setFill(Color.WHITE);
		return invitedPlayers;
	}
	
	public Text teamTypeTitleText() {
		Text teamType = new Text("Team Type");
		teamType.setFont(Font.font("Helvetica", FontWeight.BOLD, 10)); // sets font Style, Weight and Size
		teamType.setFill(Color.WHITE);
		return teamType;
	}

	/**
	 * Creates the Text Field where the user will enter Player Names
	 * 
	 * @return Text Field
	 */
	public TextField playerNameTextField() {
		TextField playerNameTextField = new TextField();

		playerNameTextField.setPromptText("Enter player email: ");

		playerNameTextField.setMinWidth(300);
		playerNameTextField.setMaxWidth(300);

		playerNameTextField.setMinHeight(35);

		playerNameTextField.getText();

		DropShadow dropShadow = new DropShadow();
		dropShadow.setOffsetX(3.0f);
		dropShadow.setOffsetY(5.0f);
		dropShadow.setColor(Color.rgb(150, 50, 50, .688));
		playerNameTextField.setEffect(dropShadow);

		return playerNameTextField;

	}

	/**
	 * Creates the text field where coach names are input to be added to invite list
	 * 
	 * @return TextField
	 */
	public TextField coachNameTextField() {
		TextField coachNameTextField = new TextField();

		coachNameTextField.setPromptText("Enter coach email: ");

		coachNameTextField.setMinWidth(300);
		coachNameTextField.setMaxWidth(300);

		coachNameTextField.setMinHeight(35);

		coachNameTextField.getText();

		DropShadow dropShadow = new DropShadow();
		dropShadow.setOffsetX(3.0f);
		dropShadow.setOffsetY(5.0f);
		dropShadow.setColor(Color.rgb(150, 50, 50, .688));
		coachNameTextField.setEffect(dropShadow);

		return coachNameTextField;

	}

	/**
	 * Creates a Text Field where the user will input the team name
	 * 
	 * @return
	 */
	public TextField teamNameTextField() {
		TextField teamNameTextField = new TextField();

		teamNameTextField.setPromptText("Enter your desired Team Name: ");

		teamNameTextField.setMinWidth(300);
		teamNameTextField.setMaxWidth(300);

		teamNameTextField.setMinHeight(35);

		teamNameTextField.getText();

		DropShadow dropShadow = new DropShadow();
		dropShadow.setOffsetX(3.0f);
		dropShadow.setOffsetY(5.0f);
		dropShadow.setColor(Color.rgb(150, 50, 50, .688));
		teamNameTextField.setEffect(dropShadow);

		return teamNameTextField;

	}

	/**
	 * Creates a background object that will be set as the background of a pane
	 * using the blueBackground.png
	 * 
	 * @return Background Object
	 */
	public Background screenBackground() {
		Image backgroundImage = new Image("blueBackground.png");
		BackgroundSize backgroundSize = new BackgroundSize(1, 1, true, true, false, false);
		Background screenBackground = new Background(new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize));

		return screenBackground;

	}

	/**
	 * Creates a background object that is partially transparent to be used as the
	 * background of the invite lists
	 * 
	 * @return Background
	 */
	
	public Background transparentBackground() {
		Color backgroundColor = new Color(1.0, 1.0, 1.0, 0.7); // RGBA: White with 70% opacity
		BackgroundFill backgroundFill = new BackgroundFill(backgroundColor, null, null);
		Background background = new Background(backgroundFill);
		return background;
	}
	
	/**
	 * Creation of the drop down Menu used for selecting what sport the team plays
	 * 
	 * @return ComboBox
	 */
	
	public ComboBox<?> sportSelectionDropDown() {
		final ComboBox<String> sportSelectionDropDown = new ComboBox<String>();
		
		sportSelectionDropDown.getItems().addAll("Football", "Rugby", "Cricket", "Netball");

		sportSelectionDropDown.setPromptText("Select sport");
		sportSelectionDropDown.setEditable(true);

		sportSelectionDropDown.setMinWidth(300);
		sportSelectionDropDown.setMaxWidth(300);

		sportSelectionDropDown.setMinHeight(35);
		
		DropShadow dropShadow = new DropShadow();
		dropShadow.setOffsetX(3.0f);
		dropShadow.setOffsetY(5.0f);
		dropShadow.setColor(Color.rgb(150, 50, 50, .688));
		sportSelectionDropDown.setEffect(dropShadow);

		return sportSelectionDropDown;

	}

}
