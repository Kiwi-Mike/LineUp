package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

public class LoginScreenUI extends GridPane implements UI {
	CreateAccountScreenUI createAccountScreenUI;
	HomeScreenUI homeScreenUI;
	Main main;
	UserService userService;

	public LoginScreenUI(Main main) {
		this.main = main;
		userService = new UserService(main.databaseLineUp);
		printCurrentUsers();
		createLoginGridPane();
	}

	private void printCurrentUsers() {
		userService.getAllUsers();
		int userCount = 1;
		for (User user : userService.getAllUsers()) {
			System.out.println("User-" + userCount + "  " + "Name - " + user.getUserName() + "  " + "Date of birth - "
					+ user.getUsersAge() + "  " + "Gender - " + user.getUsersGender() + "  " + "Email - "
					+ user.getUserEmail() + "  " + "Password - " + user.getUserPassword());
			System.out.println();
			userCount++;
		}

	}

	@Override
	public void show(Stage stageName) {
	}

	/**
	 * This method creates the gridPane that will hold all of the login UI elements.
	 * It is responsible for setting the constraints of each element in the grid.
	 * 
	 * @return GridPane holding all of the Login UI elements.
	 */
	public GridPane createLoginGridPane() {

		GridPane loginScreen = this;// new GridPane();

		Text lineUpLogoText = lineUpLogoText();
		GridPane.setMargin(lineUpLogoText, new Insets(50, 5, 35, 5)); // the parameters buffer (Top, Left, Bottom,
																		// Right)
		GridPane.setConstraints(lineUpLogoText, 0, 0);
		GridPane.setHalignment(lineUpLogoText, HPos.CENTER); // sets horizontal position on GridPane

		Text signInText = signInText();
		GridPane.setMargin(signInText, new Insets(50, 5, 10, 5));
		GridPane.setConstraints(signInText, 0, 1);
		GridPane.setHalignment(signInText, HPos.CENTER);

		Text emailText = emailText();
		GridPane.setConstraints(emailText, 0, 2);
		GridPane.setHalignment(emailText, HPos.LEFT);

		TextField userNameTextField = userNameTextField();
		GridPane.setMargin(userNameTextField, new Insets(0, 0, 15, 0));
		GridPane.setConstraints(userNameTextField, 0, 3);

		Text passwordText = passwordText();
		GridPane.setConstraints(passwordText, 0, 4);
		GridPane.setHalignment(passwordText, HPos.LEFT);

		TextField userPasswordTextField = userPasswordTextField();
		GridPane.setMargin(userPasswordTextField, new Insets(0, 0, 10, 0));
		GridPane.setConstraints(userPasswordTextField, 0, 5);

		Text forgotPasswordText = forgotPasswordText();
		GridPane.setConstraints(forgotPasswordText, 0, 6);
		GridPane.setHalignment(forgotPasswordText, HPos.RIGHT);

		Button logInButton = logInButton();
		GridPane.setMargin(logInButton, new Insets(30, 0, 15, 0));
		GridPane.setConstraints(logInButton, 0, 7);
		GridPane.setHalignment(logInButton, HPos.CENTER);

		FlowPane dontHaveAccountFlowPane = dontHaveAccountTextFlow();
		dontHaveAccountFlowPane.setMaxWidth(200);
		GridPane.setMargin(dontHaveAccountFlowPane, new Insets(100, 20, 15, 20));
		GridPane.setConstraints(dontHaveAccountFlowPane, 0, 8);
		GridPane.setHalignment(dontHaveAccountFlowPane, HPos.CENTER);

		Background logInScreenBackground = logInScreenBackground();

		loginScreen.setAlignment(Pos.TOP_CENTER);
		loginScreen.setBackground(logInScreenBackground);
		loginScreen.setVgap(5);
		loginScreen.setHgap(5);

		loginScreen.getChildren().addAll(lineUpLogoText, signInText, emailText, passwordText, userNameTextField,
				userPasswordTextField, forgotPasswordText, logInButton, dontHaveAccountFlowPane);

		return loginScreen;
	}

	public Background logInScreenBackground() {
		Image backgroundImage = new Image("blueBackground.png");

		BackgroundSize backgroundSize = new BackgroundSize(1, 1, true, true, false, false);

		Background logInScreenBackground = new Background(new BackgroundImage(backgroundImage,
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize));

		return logInScreenBackground;

	}

	public Text lineUpLogoText() {
		Text lineUpLogoText = new Text("LineUp");
		lineUpLogoText.setFont(Font.font("Helvetica", FontWeight.BOLD, 50));
		lineUpLogoText.setFill(Color.WHITE);

		return lineUpLogoText;
	}

	/**
	 * Creates the text displayed at the very top of the login screen.
	 * 
	 * @return a text object
	 */
	public Text signInText() {
		Text signInText = new Text("Sign In");
		signInText.setFont(Font.font("Helvetica", FontWeight.BOLD, 15));
		signInText.setFill(Color.WHITE);

		return signInText;

	}

	/**
	 * Text above the email input text field, signifying what is is to the user
	 * 
	 * @return a text object
	 */
	public Text emailText() {
		Text emailText = new Text("Email");
		emailText.setFont(Font.font("Helvetica", FontWeight.BOLD, 10));
		emailText.setFill(Color.WHITE);

		return emailText;

	}

	/**
	 * Creates a text field for the user to input either their user name or email.
	 * It is responsible for setting the attributes of the given text field. For
	 * Example the text prompt and the text width.
	 * 
	 * @return the user name text field
	 */
	public TextField userNameTextField() {
		TextField userNameTextField = new TextField();

		userNameTextField.setPromptText("Enter your email");

		userNameTextField.setMinWidth(300);
		userNameTextField.setMaxWidth(300);

		userNameTextField.setMinHeight(35);

		userNameTextField.getText();

		DropShadow dropShadow = new DropShadow();
		dropShadow.setOffsetX(3.0f);
		dropShadow.setOffsetY(5.0f);
		dropShadow.setColor(Color.rgb(150, 50, 50, .688));
		userNameTextField.setEffect(dropShadow);

		return userNameTextField;

	}

	/**
	 * Text above the password input text field, signifying what is is to the user
	 * 
	 * @return a text object
	 */
	public Text passwordText() {
		Text passwordText = new Text("Password");
		passwordText.setFont(Font.font("Helvetica", FontWeight.BOLD, 10));
		passwordText.setFill(Color.WHITE);

		return passwordText;

	}

	/**
	 * Creates a text field for the user to input either their password. It is
	 * responsible for setting the attributes of the given text field. For Example
	 * the text prompt and the text width.
	 * 
	 * @return the password text field
	 */
	public TextField userPasswordTextField() {
		TextField userPasswordTextField = new TextField();

		userPasswordTextField.setPromptText("Enter your password");

		userPasswordTextField.setMinWidth(300);
		userPasswordTextField.setMaxWidth(300);

		userPasswordTextField.setMinHeight(35);

		userPasswordTextField.getText();

		DropShadow dropShadow = new DropShadow();
		dropShadow.setOffsetX(3.0f);
		dropShadow.setOffsetY(5.0f);
		dropShadow.setColor(Color.rgb(150, 50, 50, .688));
		userPasswordTextField.setEffect(dropShadow);

		return userPasswordTextField;

	}

	/**
	 * Text bellow the password input text field, asking the user if they have
	 * forgotten their password.
	 * 
	 * @return a text object
	 */
	public Text forgotPasswordText() {
		Text forgotPasswordText = new Text("Forgot Password?");
		forgotPasswordText.setFont(Font.font("Helvetica", FontWeight.BOLD, 7));
		forgotPasswordText.setFill(Color.WHITE);

		return forgotPasswordText;

	}

	/**
	 * The button the user will press to submit their user name and password and
	 * attempt to log into the system.
	 * 
	 * @return a button object
	 */
	public Button logInButton() {

		Rectangle buttonShape = new Rectangle();
		buttonShape.setWidth(600);
		buttonShape.setHeight(100);
		buttonShape.setArcWidth(50);
		buttonShape.setArcHeight(100);

		Button logInButton = new Button("Login");
		logInButton.setFont(Font.font("Helvetica", FontWeight.BOLD, 15));
		logInButton.setStyle("-fx-background-color: white; -fx-text-fill: black;");
		logInButton.setShape(buttonShape);
		logInButton.setPrefWidth(300);
		logInButton.setPrefHeight(35);

		DropShadow dropShadow = new DropShadow();
		dropShadow.setOffsetX(3.0f);
		dropShadow.setOffsetY(5.0f);
		dropShadow.setColor(Color.rgb(150, 50, 50, .688));
		logInButton.setEffect(dropShadow);

		logInButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				homeScreenUI = new HomeScreenUI(main);
				double sceneWidth = main.primaryStage.getScene().getWidth();
				double sceneHeight = main.primaryStage.getScene().getHeight();
				Scene createAccountScene = new Scene(homeScreenUI, sceneWidth, sceneHeight);
				main.primaryStage.setScene(createAccountScene);

			}
		});

		return logInButton;

	}

	/**
	 * A flow pane displaying text and a hyperlink at the very bottom of the log in
	 * screen. Instructing the user to create a new account if they don't have one
	 * already.
	 * 
	 * @return A flow pane displaying a text and hyperlink object.
	 */
	public FlowPane dontHaveAccountTextFlow() {
		Text flowPaneText = new Text("Don't have an account? ");
		flowPaneText.setFont(Font.font("Helvetica", FontWeight.NORMAL, 11));
		flowPaneText.setFill(Color.WHITE);

		Hyperlink flowPaneHyperlink = new Hyperlink("SIGN UP");
		flowPaneHyperlink.setFont(Font.font("Helvetica", FontWeight.BOLD, 12));
		flowPaneHyperlink.setTextFill(Color.WHITE);

		flowPaneHyperlink.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				createAccountScreenUI = new CreateAccountScreenUI(main);
				double sceneWidth = main.primaryStage.getScene().getWidth();
				double sceneHeight = main.primaryStage.getScene().getHeight();
				Scene createAccountScene = new Scene(createAccountScreenUI, sceneWidth, sceneHeight);
				main.primaryStage.setScene(createAccountScene);

			}
		});

		FlowPane dontHaveAccountTextFlow = new FlowPane(flowPaneText, flowPaneHyperlink);

		return dontHaveAccountTextFlow;

	}

}
