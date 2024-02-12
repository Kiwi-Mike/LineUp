package application;

import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class CreateEventScreenUI extends GridPane implements UI {
	// Tracks columns and rows used in the invite list grid panes
	int invitedPlayerRow = 1;
	int invitedPlayerColumn = 0;
	int invitedCoachRow = 1;
	int invitedCoachColumn = 0;
	Main main;

	public CreateEventScreenUI(Main main) {
		this.main = main;
		createEventGridPane();
	};

	public void show(Stage createEventScreen) {
		GridPane createTeamScreenGridPane = createEventGridPane();
		Scene scene = new Scene(createTeamScreenGridPane, 700, 700);
		createEventScreen.setScene(scene);
	}

	public GridPane createEventGridPane() {

		// Creating GridPane and setting background
		GridPane createEventScreen = this;
		Background screenBackground = screenBackground();
		createEventScreen.setBackground(screenBackground);

		// Logo Text
		Text lineUpLogoText = lineUpLogoText();
		GridPane.setMargin(lineUpLogoText, new Insets(50, 5, 10, 5)); // the parameters buffer (Top, Left, Bottom,Right)
		GridPane.setColumnSpan(lineUpLogoText, 3); // Setting to span two columns to center
		GridPane.setConstraints(lineUpLogoText, 0, 0); // sets position in grid pane
		GridPane.setHalignment(lineUpLogoText, HPos.CENTER); // sets horizontal position on GridPane

		// Create Event Text
		Text createEventTitle = createEventTitleText();
		GridPane.setMargin(createEventTitle, new Insets(20, 0, 10, 0)); // the parameters buffer (Top, Left, Bottom,
																		// Right)
		GridPane.setColumnSpan(createEventTitle, 3); // Setting to spawn two columns to center
		GridPane.setConstraints(createEventTitle, 0, 1); // sets position in grid pane
		GridPane.setHalignment(createEventTitle, HPos.CENTER); // sets horizontal position on GridPane

		// Title above where event name is input
		Text eventNameText = eventNameText();
		GridPane.setConstraints(eventNameText, 0, 2); // sets position in grid pane
		GridPane.setHalignment(eventNameText, HPos.LEFT);

		// Text field where team names are input
		TextField eventNameTextField = eventNameTextField();
		GridPane.setMargin(eventNameTextField, new Insets(0, 0, 15, 0));
		GridPane.setConstraints(eventNameTextField, 0, 3); // sets position in grid pane

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
		invitedPlayersBackground.setMaxHeight(110);
		
		GridPane.setConstraints(invitedPlayersBackground, 0, 8); // sets position in grid pane
		GridPane.setHalignment(invitedPlayersBackground, HPos.LEFT);

		// Pane that serves as the background for the invited coach names
		Pane invitedCoachBackground = new Pane();
		invitedCoachBackground.setBackground(transparentBackground());
		invitedCoachBackground.getChildren().add(invitedCoachPane);
		invitedCoachBackground.setMinWidth(300);
		invitedCoachBackground.setMinHeight(110);
		invitedCoachBackground.setMaxHeight(110);
		GridPane.setConstraints(invitedCoachBackground, 1, 8); // sets position in grid pane
		GridPane.setHalignment(invitedCoachBackground, HPos.LEFT);
		
		TextArea eventDescriptionTextArea = eventDescriptionTextArea();
		eventDescriptionTextArea.setMinWidth(300);
		eventDescriptionTextArea.setMinHeight(110);
		eventDescriptionTextArea.setMaxHeight(110);
		GridPane.setConstraints(eventDescriptionTextArea, 2, 8); // sets position in grid pane
		GridPane.setHalignment(eventDescriptionTextArea, HPos.LEFT);

		// The text used as the title above the invited players list
		Text invitedPlayerTitle = invitedPlayerTitleText();
		GridPane.setConstraints(invitedPlayerTitle, 0, 7); // sets position in grid pane
		GridPane.setHalignment(invitedPlayerTitle, HPos.LEFT);

		// The text used as the title above the invited coach list
		Text invitedCoachTitle = invitedCoachTitleText();
		GridPane.setConstraints(invitedCoachTitle, 1, 7); // sets position in grid pane
		GridPane.setHalignment(invitedCoachTitle, HPos.LEFT);
		
		Text eventDescriptionTitle = eventDescriptionTitleText();
		GridPane.setConstraints(eventDescriptionTitle, 2, 7); // sets position in grid pane
		GridPane.setHalignment(eventDescriptionTitle, HPos.LEFT);
		
		Text eventTypeTitle = eventTypeTitleText();
		GridPane.setConstraints(eventTypeTitle, 1, 2); // sets position in grid pane
		GridPane.setHalignment(eventTypeTitle, HPos.LEFT);
		
		Text dateSelectionTitle = dateSelectionTitleText();
		GridPane.setConstraints(dateSelectionTitle, 2, 2); // sets position in grid pane
		GridPane.setHalignment(dateSelectionTitle, HPos.LEFT);
		
		Text timeSelectionTitle = timeSelectionTitleText();
		GridPane.setConstraints(timeSelectionTitle, 2, 4); // sets position in grid pane
		GridPane.setHalignment(timeSelectionTitle, HPos.LEFT);
		
		DatePicker eventDatePicker = eventDatePicker();
		GridPane.setConstraints(eventDatePicker, 2, 3); // sets position in grid pane
		GridPane.setHalignment(eventDatePicker, HPos.LEFT);
		GridPane.setValignment(eventDatePicker, VPos.TOP);
		eventDatePicker.setMinWidth(300);
		GridPane.setMargin(eventDatePicker, new Insets(5, 0, 0, 0));

		// Drop down menu to select event type
		ComboBox<?> eventTypeSelection = eventTypeSelectionDropDown();
		GridPane.setConstraints(eventTypeSelection, 1, 3); // sets position in grid pane
		GridPane.setHalignment(eventTypeSelection, HPos.LEFT);
		GridPane.setValignment(eventTypeSelection, VPos.TOP);
		
		ComboBox<?> eventTimeSelection = eventTimeSelectionDropDown();
		GridPane.setConstraints(eventTimeSelection, 2, 5); // sets position in grid pane
		GridPane.setHalignment(eventTimeSelection, HPos.LEFT);
		GridPane.setValignment(eventTimeSelection, VPos.TOP);
		
		Button createEventButton = createEventButton();
		Button cancelEventButton = cancelEventButton();
		
		createEventButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				main.homeScreenUI = new HomeScreenUI(main);
				double sceneWidth = main.primaryStage.getScene().getWidth();
				double sceneHeight = main.primaryStage.getScene().getHeight();
				Scene homeScreenScene = new Scene(main.homeScreenUI, sceneWidth, sceneHeight);
				main.primaryStage.setScene(homeScreenScene);

			}
		});
		
		cancelEventButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				main.homeScreenUI = new HomeScreenUI(main);
				double sceneWidth = main.primaryStage.getScene().getWidth();
				double sceneHeight = main.primaryStage.getScene().getHeight();
				Scene homeScreenScene = new Scene(main.homeScreenUI, sceneWidth, sceneHeight);
				main.primaryStage.setScene(homeScreenScene);
			}
		});
		
		
		HBox bottomButtonBox = new HBox();
		bottomButtonBox.setAlignment(Pos.CENTER);
		bottomButtonBox.getChildren().addAll(createEventButton, cancelEventButton);
		bottomButtonBox.setSpacing(20);
		GridPane.setConstraints(bottomButtonBox, 0, 9); // sets position in grid pane
		GridPane.setHalignment(bottomButtonBox, HPos.CENTER);
		GridPane.setColumnSpan(bottomButtonBox, 3);
		GridPane.setMargin(bottomButtonBox, new Insets(30, 0, 0, 0));

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
		createEventScreen.getChildren().addAll(lineUpLogoText, createEventTitle, eventNameText, eventNameTextField,
				playerNameText, playerNameTextField, invitePlayerButton, invitedPlayersBackground, invitedPlayerTitle,
				eventTypeSelection, coachNameText, coachNameTextField, inviteCoachButton, invitedCoachBackground,
				invitedCoachTitle,eventTypeTitle,eventDatePicker,dateSelectionTitle,
				eventTimeSelection,timeSelectionTitle,bottomButtonBox,eventDescriptionTitle,eventDescriptionTextArea);

		// setting alignment of GridPane
		createEventScreen.setAlignment(Pos.TOP_CENTER);
		createEventScreen.setVgap(5);
		createEventScreen.setHgap(30);

		return createEventScreen;
	}

	/**
	 * Creates Text Object of the LineUp Logo
	 * 
	 * @return Text Object of LineUp Logo
	 */

	public DatePicker eventDatePicker() {
		DatePicker eventDatePicker = new DatePicker();
		eventDatePicker.setValue(LocalDate.now());
		
		return eventDatePicker;
	}
	
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
	public Text createEventTitleText() {
		Text createEventTitleText = new Text("Create a New Event"); // sets the text
		createEventTitleText.setFont(Font.font("Helvetica", FontWeight.BOLD, 15)); // sets font Style, Weight and Size
		createEventTitleText.setFill(Color.WHITE);

		return createEventTitleText;

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
	public Text eventNameText() {
		Text eventNameText = new Text("Event Name*");
		eventNameText.setFont(Font.font("Helvetica", FontWeight.BOLD, 10)); // sets font Style, Weight and Size
		eventNameText.setFill(Color.WHITE);

		return eventNameText;
	}
	
	public Text eventTypeTitleText() {
		Text eventType = new Text("Event Type");
		eventType.setFont(Font.font("Helvetica", FontWeight.BOLD, 10)); // sets font Style, Weight and Size
		eventType.setFill(Color.WHITE);
		return eventType;
	}
	
	public Text dateSelectionTitleText() {
		Text dateSelectionTitle = new Text("Choose Date*");
		dateSelectionTitle.setFont(Font.font("Helvetica", FontWeight.BOLD, 10)); // sets font Style, Weight and Size
		dateSelectionTitle.setFill(Color.WHITE);
		return dateSelectionTitle;
	}
	
	public Text timeSelectionTitleText() {
		Text timeSelectionTitle = new Text("Choose Time*");
		timeSelectionTitle.setFont(Font.font("Helvetica", FontWeight.BOLD, 10)); // sets font Style, Weight and Size
		timeSelectionTitle.setFill(Color.WHITE);
		return timeSelectionTitle;
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
	public Button createEventButton() {
		String buttonText = "Create Event";

		Button createEvent = new Button();
		createEvent.setText(buttonText);

		Rectangle buttonShape = new Rectangle();
		buttonShape.setWidth(400);
		buttonShape.setHeight(100);
		buttonShape.setArcWidth(50);
		buttonShape.setArcHeight(100);

		createEvent.setFont(Font.font("Helvetica", FontWeight.BOLD, 15));
		createEvent.setStyle("-fx-background-color: white; -fx-text-fill: black;");
		createEvent.setShape(buttonShape);
		createEvent.setPrefWidth(200);
		createEvent.setPrefHeight(35);

		DropShadow dropShadow = new DropShadow();
		dropShadow.setOffsetX(3.0f);
		dropShadow.setOffsetY(5.0f);
		dropShadow.setColor(Color.rgb(150, 50, 50, .688));
		createEvent.setEffect(dropShadow);

		return createEvent;
	}

	/**
	 * Creates a button used to cancel the create team process
	 * 
	 * @return Button
	 */
	public Button cancelEventButton() {
		String buttonText = "Cancel";

		Button cancelEvent = new Button();
		cancelEvent.setText(buttonText);

		Rectangle buttonShape = new Rectangle();
		buttonShape.setWidth(400);
		buttonShape.setHeight(100);
		buttonShape.setArcWidth(50);
		buttonShape.setArcHeight(100);

		cancelEvent.setFont(Font.font("Helvetica", FontWeight.BOLD, 15));
		cancelEvent.setStyle("-fx-background-color: white; -fx-text-fill: black;");
		cancelEvent.setShape(buttonShape);
		cancelEvent.setPrefWidth(200);
		cancelEvent.setPrefHeight(35);

		DropShadow dropShadow = new DropShadow();
		dropShadow.setOffsetX(3.0f);
		dropShadow.setOffsetY(5.0f);
		dropShadow.setColor(Color.rgb(150, 50, 50, .688));
		cancelEvent.setEffect(dropShadow);

		return cancelEvent;
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
	
	public Text eventDescriptionTitleText() {
		Text eventDescription = new Text("Event Description*");
		eventDescription.setFont(Font.font("Helvetica", FontWeight.BOLD, 10)); // sets font Style, Weight and Size
		eventDescription.setFill(Color.WHITE);
		return eventDescription;
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
	
	public TextArea eventDescriptionTextArea() {
		TextArea eventDescriptionTextArea = new TextArea();

		eventDescriptionTextArea.setPromptText("Enter event description: ");

		eventDescriptionTextArea.setMinWidth(300);
		eventDescriptionTextArea.setMaxWidth(300);
		
		eventDescriptionTextArea.setBackground(transparentBackground());
		
		eventDescriptionTextArea.setWrapText(true);

		DropShadow dropShadow = new DropShadow();
		dropShadow.setOffsetX(3.0f);
		dropShadow.setOffsetY(5.0f);
		dropShadow.setColor(Color.rgb(150, 50, 50, .688));
		eventDescriptionTextArea.setEffect(dropShadow);

		return eventDescriptionTextArea;

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
	public TextField eventNameTextField() {
		TextField eventNameTextField = new TextField();

		eventNameTextField.setPromptText("Enter the name for you Event: ");

		eventNameTextField.setMinWidth(300);
		eventNameTextField.setMaxWidth(300);

		eventNameTextField.setMinHeight(35);

		eventNameTextField.getText();

		DropShadow dropShadow = new DropShadow();
		dropShadow.setOffsetX(3.0f);
		dropShadow.setOffsetY(5.0f);
		dropShadow.setColor(Color.rgb(150, 50, 50, .688));
		eventNameTextField.setEffect(dropShadow);

		return eventNameTextField;

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
	
	public ComboBox<?> eventTypeSelectionDropDown() {
		final ComboBox<String> eventSelectionDropDown = new ComboBox<String>();
		
		eventSelectionDropDown.getItems().addAll("Match", "Tournament", "League", "Class");

		eventSelectionDropDown.setPromptText("Select event type: ");
		eventSelectionDropDown.setEditable(true);

		eventSelectionDropDown.setMinWidth(300);
		eventSelectionDropDown.setMaxWidth(300);

		eventSelectionDropDown.setMinHeight(35);
		
		DropShadow dropShadow = new DropShadow();
		dropShadow.setOffsetX(3.0f);
		dropShadow.setOffsetY(5.0f);
		dropShadow.setColor(Color.rgb(150, 50, 50, .688));
		eventSelectionDropDown.setEffect(dropShadow);

		return eventSelectionDropDown;

	}
	
	public ComboBox<?> eventTimeSelectionDropDown() {
		final ComboBox<String> eventTimeDropDown = new ComboBox<String>();
		
		for (int hour = 0; hour < 24; hour++) {
	           for (int minute = 0; minute < 60; minute += 15) {
	           String time = String.format("%02d:%02d", hour, minute);
	           eventTimeDropDown.getItems().add(time);
	           }
	    }

		eventTimeDropDown.setPromptText("Select event time: ");
		eventTimeDropDown.setEditable(false);

		eventTimeDropDown.setMinWidth(300);
		eventTimeDropDown.setMaxWidth(300);

		eventTimeDropDown.setMinHeight(35);
		
		DropShadow dropShadow = new DropShadow();
		dropShadow.setOffsetX(3.0f);
		dropShadow.setOffsetY(5.0f);
		dropShadow.setColor(Color.rgb(150, 50, 50, .688));
		eventTimeDropDown.setEffect(dropShadow);

		return eventTimeDropDown;

	}

}

