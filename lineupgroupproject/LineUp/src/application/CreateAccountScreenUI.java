package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CreateAccountScreenUI extends GridPane implements UI {
	Main main;
	LoginScreenUI loginScreenUI;
	private UserService userService;

	// UI Components
	private TextField userNameInput;
	private Text lineUpLogoText;
	private Text createAccountText;
	private Text UserNameTextFieldTitleText;
	private Text dateOfBirthText;
	private DatePicker dateOfBirthDropDownMenu;
	private Text selectGenderText;
	private ComboBox<String> genderDropDownMenuBox;
	private Text emailText;
	private TextField emailInput;
	private Text passwordText;
	private TextField passwordInput;
	private Button signUpButton;
	private FlowPane alreadyHaveAccountFlowPane;
	private Text errorMessage;

	public CreateAccountScreenUI(Main main) {
		userService = new UserService(main.databaseLineUp);
		this.main = main;
		getAccountGridPane();
	}

	@Override
	public void show(Stage createAccountStage) {

//		createAccountSceneView = new Scene(getAccountGridPane(), 1000, 800);
//		createAccountStage.setScene(createAccountSceneView);
	}

	public GridPane getAccountGridPane() {
		initLineUpLogoText();
		initCreateAccountText();
		initNameTextTitle();
		initNameInput();
		initDateOfBirthText();
		initDateOfBirthDropDownMenu();
		initSelectGenderText();
		initGenderDropDownMenu();
		initEmailText();
		initEmailInput();
		initPasswordText();
		initPasswordInput();
		initSignUpButton();
		errorMessageText();
		initAlreadyHaveAccountTextFlow();

		Background createAccountScreenBackground = createAccountScreenBackground();
		this.setAlignment(Pos.TOP_CENTER);
		this.setBackground(createAccountScreenBackground);
		this.setVgap(5);
		this.setHgap(5);

		return this;

	}

	public Background createAccountScreenBackground() {
		Image backgroundImage = new Image("blueBackground.png");

		BackgroundSize backgroundSize = new BackgroundSize(1, 1, true, true, false, false);

		Background logInScreenBackground = new Background(new BackgroundImage(backgroundImage,
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize));

		return logInScreenBackground;

	}

	public void initLineUpLogoText() {
		// create element
		lineUpLogoText = new Text("LineUp");
		lineUpLogoText.setFont(Font.font("Helvetica", FontWeight.BOLD, 50));
		lineUpLogoText.setFill(Color.WHITE);

		// deal with layout
		GridPane.setMargin(lineUpLogoText, new Insets(50, 5, 35, 5));
		GridPane.setConstraints(lineUpLogoText, 0, 0);
		GridPane.setHalignment(lineUpLogoText, HPos.CENTER);
		GridPane.setColumnSpan(lineUpLogoText, 2);

		// add grid pane
		this.getChildren().add(lineUpLogoText);
	}

	public void initCreateAccountText() {

		createAccountText = new Text("Create Account");
		createAccountText.setFont(Font.font("Helvetica", FontWeight.BOLD, 25));
		createAccountText.setFill(Color.WHITE);

		GridPane.setMargin(createAccountText, new Insets(50, 5, 30, 5));
		GridPane.setConstraints(createAccountText, 0, 1);
		GridPane.setHalignment(createAccountText, HPos.CENTER);
		GridPane.setColumnSpan(createAccountText, 2);

		this.getChildren().add(createAccountText);

	}

	public void initNameTextTitle() {

		UserNameTextFieldTitleText = new Text("Name");
		UserNameTextFieldTitleText.setFont(Font.font("Helvetica", FontWeight.BOLD, 10));
		UserNameTextFieldTitleText.setFill(Color.WHITE);

		GridPane.setConstraints(UserNameTextFieldTitleText, 0, 2);
		GridPane.setHalignment(UserNameTextFieldTitleText, HPos.LEFT);

		this.getChildren().add(UserNameTextFieldTitleText);

	}

	public void initNameInput() {
		userNameInput = new TextField();
		userNameInput.setPromptText("Enter your name");
		userNameInput.setStyle("");

		GridPane.setMargin(userNameInput, new Insets(0, 0, 15, 0));
		GridPane.setConstraints(userNameInput, 0, 3);
		GridPane.setHalignment(userNameInput, HPos.CENTER);

		userNameInput.setMinWidth(300);
		userNameInput.setMaxWidth(300);
		userNameInput.setMinHeight(35);
		userNameInput.getText();
		DropShadow dropShadow = createDropShadow();
		userNameInput.setEffect(dropShadow);

		this.getChildren().add(userNameInput);
	}

	public String getUserNameInputValue() {
		return userNameInput.getText();
	}

	public DropShadow createDropShadow() {
		DropShadow dropShadow = new DropShadow();
		dropShadow.setOffsetX(3.0f);
		dropShadow.setOffsetY(5.0f);
		dropShadow.setColor(Color.rgb(150, 50, 50, .688));
		return dropShadow;
	}

	public void initDateOfBirthText() {
		dateOfBirthText = new Text("Date of Birth");
		dateOfBirthText.setFont(Font.font("Helvetica", FontWeight.BOLD, 10));
		dateOfBirthText.setFill(Color.WHITE);
		GridPane.setConstraints(dateOfBirthText, 0, 4);
		GridPane.setHalignment(dateOfBirthText, HPos.LEFT);

		this.getChildren().addAll(dateOfBirthText);
	}

	public void initDateOfBirthDropDownMenu() {
		dateOfBirthDropDownMenu = new DatePicker();

		dateOfBirthDropDownMenu.setPromptText("Select date of birth");
		dateOfBirthDropDownMenu.setEditable(true);
		dateOfBirthDropDownMenu.setMinWidth(300);
		dateOfBirthDropDownMenu.setMaxWidth(300);
		dateOfBirthDropDownMenu.setMinHeight(35);
//		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//		Date date = new SimpleDateFormat("yyyy-mm-dd");
		dateOfBirthDropDownMenu.setValue(LocalDate.now());

		GridPane.setMargin(dateOfBirthDropDownMenu, new Insets(0, 0, 15, 0));
		GridPane.setConstraints(dateOfBirthDropDownMenu, 0, 5);
		GridPane.setHalignment(dateOfBirthDropDownMenu, HPos.CENTER);

		DropShadow dropShadow = createDropShadow();
		dateOfBirthDropDownMenu.setEffect(dropShadow);

		this.getChildren().addAll(dateOfBirthDropDownMenu);
	}

	public String getUserDateOfBirthValue() {
		return dateOfBirthDropDownMenu.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
	}

	public void initSelectGenderText() {
		selectGenderText = new Text("Gender");
		GridPane.setConstraints(selectGenderText, 0, 6);
		GridPane.setHalignment(selectGenderText, HPos.LEFT);
		selectGenderText.setFont(Font.font("Helvetica", FontWeight.BOLD, 10));
		selectGenderText.setFill(Color.WHITE);

		this.getChildren().addAll(selectGenderText);
	}

	public void initGenderDropDownMenu() {
		genderDropDownMenuBox = new ComboBox<String>();

		GridPane.setMargin(genderDropDownMenuBox, new Insets(0, 0, 15, 0));
		GridPane.setConstraints(genderDropDownMenuBox, 0, 7);
		GridPane.setHalignment(genderDropDownMenuBox, HPos.CENTER);

		genderDropDownMenuBox.getItems().addAll("Male", "Female", "Other");
		genderDropDownMenuBox.setPromptText("Select gender");
		genderDropDownMenuBox.setEditable(true);
		genderDropDownMenuBox.setMinWidth(300);
		genderDropDownMenuBox.setMaxWidth(300);
		genderDropDownMenuBox.setMinHeight(35);

		DropShadow dropShadow = createDropShadow();
		genderDropDownMenuBox.setEffect(dropShadow);

		this.getChildren().addAll(genderDropDownMenuBox);
	}

	public String getUserGenderChoiceValue() {
		return genderDropDownMenuBox.getValue();
	}

	public void initEmailText() {
		emailText = new Text("Email");
		emailText.setFont(Font.font("Helvetica", FontWeight.BOLD, 10));
		emailText.setFill(Color.WHITE);

		GridPane.setMargin(emailText, new Insets(0, 0, 0, 20));
		GridPane.setConstraints(emailText, 1, 2);
		GridPane.setHalignment(emailText, HPos.LEFT);

		this.getChildren().addAll(emailText);
	}

	public void initEmailInput() {
		// asks for users email address
		emailInput = new TextField();
		emailInput.setPromptText("Enter your email");

		emailInput.setMinWidth(300);
		emailInput.setMaxWidth(300);
		emailInput.setMinHeight(35);
		emailInput.getText();

		GridPane.setMargin(emailInput, new Insets(0, 0, 15, 20));
		GridPane.setConstraints(emailInput, 1, 3);
		GridPane.setHalignment(emailInput, HPos.CENTER);

		DropShadow dropShadow = createDropShadow();
		emailInput.setEffect(dropShadow);

		this.getChildren().addAll(emailInput);
	}

	public String getUserEmailInputValue() {
		return emailInput.getText();
	}

	public void initPasswordText() {
		passwordText = new Text("Password");
		passwordText.setFont(Font.font("Helvetica", FontWeight.BOLD, 10));
		passwordText.setFill(Color.WHITE);

		GridPane.setMargin(passwordText, new Insets(0, 0, 0, 20));
		GridPane.setConstraints(passwordText, 1, 4);
		GridPane.setHalignment(passwordText, HPos.LEFT);

		this.getChildren().addAll(passwordText);
	}

	public void initPasswordInput() {
		// asks for users password.
		passwordInput = new TextField();
		passwordInput.setPromptText("Enter your password");
		passwordInput.setMinWidth(300);
		passwordInput.setMaxWidth(300);
		passwordInput.setMinHeight(35);
		passwordInput.getText();

		GridPane.setMargin(passwordInput, new Insets(0, 0, 15, 20));
		GridPane.setConstraints(passwordInput, 1, 5);
		GridPane.setHalignment(passwordInput, HPos.CENTER);

		DropShadow dropShadow = createDropShadow();
		passwordInput.setEffect(dropShadow);

		this.getChildren().addAll(passwordInput);
	}

	public String getUserPasswordInputValue() {
		return passwordInput.getText();
	}

	public void initSignUpButton() {

		Rectangle buttonShape = new Rectangle();
		buttonShape.setWidth(600);
		buttonShape.setHeight(100);
		buttonShape.setArcWidth(50);
		buttonShape.setArcHeight(100);

		signUpButton = new Button("Sign Up");
		signUpButton.setFont(Font.font("Helvetica", FontWeight.BOLD, 15));
		signUpButton.setStyle("-fx-background-color: white; -fx-text-fill: black;");
		signUpButton.setShape(buttonShape);
		signUpButton.setPrefWidth(300);
		signUpButton.setPrefHeight(35);

		DropShadow dropShadow = new DropShadow();
		dropShadow.setOffsetX(3.0f);
		dropShadow.setOffsetY(5.0f);
		dropShadow.setColor(Color.rgb(150, 50, 50, .688));
		signUpButton.setEffect(dropShadow);

		GridPane.setMargin(signUpButton, new Insets(30, 0, 15, 0));
		GridPane.setConstraints(signUpButton, 0, 12);
		GridPane.setHalignment(signUpButton, HPos.CENTER);
		GridPane.setColumnSpan(signUpButton, 2);

		signUpButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {

				if (validateUserName(getUserNameInputValue()) == true
						&& validateUserDateOfBirth(getUserDateOfBirthValue()) == true
						&& validateUsersGender(getUserGenderChoiceValue()) == true
						&& validateUserEmail(getUserEmailInputValue()) == true
						&& validateUserPassword(getUserPasswordInputValue()) == true) {
					userService.createUser(getUserNameInputValue(), getUserDateOfBirthValue(),
							getUserGenderChoiceValue(), getUserEmailInputValue(), getUserPasswordInputValue());

					AccountCreatedUI accountCreatedUI = new AccountCreatedUI(main);
					double sceneWidth = main.primaryStage.getScene().getWidth();
					double sceneHeight = main.primaryStage.getScene().getHeight();
					Scene loginScreenScene = new Scene(accountCreatedUI, sceneWidth, sceneHeight);
					main.primaryStage.setScene(loginScreenScene);
				}
			}
		});

		this.getChildren().add(signUpButton);
	}

	public boolean validateUserName(String userName) {
		// Check if the string input by the user only uses valid characters.
		if (userName.equals("")) {
			errorMessage.setText("*Enter username*");
			userNameInput.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
			return false;
		}
		userNameInput.setStyle("");
		return true;
	}

	public boolean validateUserDateOfBirth(String dateOfBirth) {
		// check if the date chosen by the users is in the past.
		// check if the date chosen by the user set them at an appropriate age... 13+
		// maybe?

		if (dateOfBirth.equals("08-11-2023")) {
			errorMessage.setText("*Enter your date of birth*");
			dateOfBirthDropDownMenu.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
			return false;
		}
		dateOfBirthDropDownMenu.setStyle("");
		return true;
	}

	public boolean validateUsersGender(String userGenderChoice) {
		// check if the gender entered by the users matches one of the 3 given choices.
		if (userGenderChoice == null) {
			errorMessage.setText("*Enter your gender*");
			genderDropDownMenuBox.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
			return false;
		}
		genderDropDownMenuBox.setStyle("");
		return true;
	}

	public boolean validateUserEmail(String email) {
		// Check if a valid email address has been entered by the user.
		// Check the system data base to see if the email is already tide to a
		// pre-existing user.

		for (User user : main.databaseLineUp.getAllUsers()) {
			if (email.equals(user.getUserEmail())) {
				errorMessage.setText("*Email already in use*");
				emailInput.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
				return false;
			}
		}
		String emailRegex = "^.+@.+$";
		if (email.contains("@") && email.contains(".") && email.matches(emailRegex)) {
			emailInput.setStyle("");
			return true;
		}

		if (email.equals("")) {
			errorMessage.setText("*Enter your email*");
			emailInput.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
			return false;
		}
		emailInput.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
		errorMessage.setText("*Invalid email format*");
		return false;
	}

	public boolean validateUserPassword(String password) {
		if (password.equals("")) {
			errorMessage.setText("*Enter a password*");
			passwordInput.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
			return false;
		}
		passwordInput.setStyle("");
		return true;
	}

	public void errorMessageText() {
		errorMessage = new Text("");
		errorMessage.setFont(Font.font("Helvetica", FontWeight.BOLD, 14));
		errorMessage.setFill(Color.MAROON);

		GridPane.setMargin(errorMessage, new Insets(0, 0, 0, 0));
		GridPane.setConstraints(errorMessage, 0, 11);
		GridPane.setHalignment(errorMessage, HPos.CENTER);
		GridPane.setColumnSpan(errorMessage, 2);

		this.getChildren().addAll(errorMessage);
	}

	public void initAlreadyHaveAccountTextFlow() {
		Text flowPaneText = new Text("Already have an account? ");
		flowPaneText.setFont(Font.font("Helvetica", FontWeight.NORMAL, 11));
		flowPaneText.setFill(Color.WHITE);

		Hyperlink flowPaneHyperlink = new Hyperlink("LOGIN");
		flowPaneHyperlink.setFont(Font.font("Helvetica", FontWeight.BOLD, 12));
		flowPaneHyperlink.setTextFill(Color.WHITE);

		flowPaneHyperlink.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				loginScreenUI = new LoginScreenUI(main);
				double sceneWidth = main.primaryStage.getScene().getWidth();
				double sceneHeight = main.primaryStage.getScene().getHeight();
				Scene loginScreenScene = new Scene(loginScreenUI, sceneWidth, sceneHeight);
				main.primaryStage.setScene(loginScreenScene);

			}
		});

		alreadyHaveAccountFlowPane = new FlowPane(flowPaneText, flowPaneHyperlink);
		alreadyHaveAccountFlowPane.setMaxWidth(200);
		GridPane.setMargin(alreadyHaveAccountFlowPane, new Insets(100, 20, 15, 20));
		GridPane.setConstraints(alreadyHaveAccountFlowPane, 0, 13);
		GridPane.setHalignment(alreadyHaveAccountFlowPane, HPos.CENTER);
		GridPane.setColumnSpan(alreadyHaveAccountFlowPane, 2);

		this.getChildren().add(alreadyHaveAccountFlowPane);

	}

}
