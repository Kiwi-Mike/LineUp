package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class AccountCreatedUI extends GridPane {
	Main main;
	UserService userService;
	CreateAccountScreenUI createAccountScreenUI;

	private Text lineUpLogoText;
	private Text welcomeToLineUpText;
	private Text signUpToStartText;
	private Button signInButton;

	public AccountCreatedUI(Main main) {
		this.main = main;
		userService = new UserService(main.databaseLineUp);
		accountCreatedScreenGridPane();
	}

	public GridPane accountCreatedScreenGridPane() {
		initLineUpLogoText();
		initWelcomeToLineUpText();
		initSignUpToGetStartedText();
		initSigninButton();

		Background newAccountScreenBackground = newAccountScreenBackground();
		this.setAlignment(Pos.TOP_CENTER);
		this.setBackground(newAccountScreenBackground);
		this.setVgap(5);
		this.setHgap(5);
		return this;

	}

	public Background newAccountScreenBackground() {
		Image backgroundImage = new Image("blueBackground.png");

		BackgroundSize backgroundSize = new BackgroundSize(1, 1, true, true, false, false);

		Background newAccountScreenBackground = new Background(new BackgroundImage(backgroundImage,
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize));

		return newAccountScreenBackground;

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

	public void initWelcomeToLineUpText() {

		welcomeToLineUpText = new Text("WELCOME TO LINE-UP!");
		welcomeToLineUpText.setFont(Font.font("Helvetica", FontWeight.BOLD, 30));
		welcomeToLineUpText.setFill(Color.WHITE);

		GridPane.setMargin(welcomeToLineUpText, new Insets(50, 5, 30, 5));
		GridPane.setConstraints(welcomeToLineUpText, 0, 1);
		GridPane.setHalignment(welcomeToLineUpText, HPos.CENTER);
		GridPane.setColumnSpan(welcomeToLineUpText, 2);

		this.getChildren().add(welcomeToLineUpText);

	}

	public void initSignUpToGetStartedText() {

		signUpToStartText = new Text("Sign in to get started");
		signUpToStartText.setFont(Font.font("Helvetica", FontWeight.NORMAL, 25));
		signUpToStartText.setFill(Color.WHITE);

		GridPane.setMargin(signUpToStartText, new Insets(50, 5, 30, 5));
		GridPane.setConstraints(signUpToStartText, 0, 2);
		GridPane.setHalignment(signUpToStartText, HPos.CENTER);
		GridPane.setColumnSpan(signUpToStartText, 2);

		this.getChildren().add(signUpToStartText);

	}

	public void initSigninButton() {

		Rectangle buttonShape = new Rectangle();
		buttonShape.setWidth(600);
		buttonShape.setHeight(100);
		buttonShape.setArcWidth(50);
		buttonShape.setArcHeight(100);

		signInButton = new Button("Return to Sign In");
		signInButton.setFont(Font.font("Helvetica", FontWeight.BOLD, 15));
		signInButton.setStyle("-fx-background-color: white; -fx-text-fill: black;");
		signInButton.setShape(buttonShape);
		signInButton.setPrefWidth(300);
		signInButton.setPrefHeight(35);

		DropShadow dropShadow = new DropShadow();
		dropShadow.setOffsetX(3.0f);
		dropShadow.setOffsetY(5.0f);
		dropShadow.setColor(Color.rgb(150, 50, 50, .688));
		signInButton.setEffect(dropShadow);

		GridPane.setMargin(signInButton, new Insets(30, 0, 15, 0));
		GridPane.setConstraints(signInButton, 0, 3);
		GridPane.setHalignment(signInButton, HPos.CENTER);
		GridPane.setColumnSpan(signInButton, 2);

		signInButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {

				LoginScreenUI loginScreenUI = new LoginScreenUI(main);
				double sceneWidth = main.primaryStage.getScene().getWidth();
				double sceneHeight = main.primaryStage.getScene().getHeight();
				Scene loginScreenScene = new Scene(loginScreenUI, sceneWidth, sceneHeight);
				main.primaryStage.setScene(loginScreenScene);

			}
		});

		this.getChildren().add(signInButton);
	}
}
