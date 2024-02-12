package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HomeScreenUI extends BorderPane implements UI {
	Main main;
	CreateTeamScreenUI createTeamScreenUI;
	CreateEventScreenUI createEventScreenUI;

	public HomeScreenUI(Main main) {
		this.main = main;
		createHomeScreenBorderPane();
	}

	@Override
	public void show(Stage stageName) {
		BorderPane HomeScreenBorderPane = createHomeScreenBorderPane();

		Scene scene = new Scene(HomeScreenBorderPane, 400, 700);
		stageName.setScene(scene);

	}

	public BorderPane createHomeScreenBorderPane() {

		BorderPane homeScreen = this;

		homeScreen.setBackground(homepageBackground());
		homeScreen.setCenter(createHomeGridPane());

		// LineUp title for homepage is placed within a Vbox
		// in order to center the title and place it slightly
		// down the screen like other UI (can't be done by adding to
		// gridPane because you cannot centre title between the 2 columns.
		VBox homeScreenTopVBox = new VBox();
		homeScreenTopVBox.setPrefHeight(150);

		Text homePagetitle = homePagetitle();
		homeScreenTopVBox.setAlignment(Pos.BOTTOM_CENTER);

		homeScreenTopVBox.getChildren().add(homePagetitle);

		homeScreen.setTop(homeScreenTopVBox);

		// homeScreen.setAlignment(homePagetitle, Pos.CENTER);

		return homeScreen;

	}

	public GridPane createHomeGridPane() {
		GridPane homeGridPane = new GridPane();

		// the following block of code allows the button to be on the right.
		ColumnConstraints column1 = new ColumnConstraints();
		column1.setPercentWidth(20);
		ColumnConstraints column2 = new ColumnConstraints();
		column2.setPercentWidth(20);
		ColumnConstraints column3 = new ColumnConstraints();
		column3.setPercentWidth(20);
		ColumnConstraints column4 = new ColumnConstraints();
		column4.setPercentWidth(20);
		ColumnConstraints column5 = new ColumnConstraints();
		column5.setPercentWidth(20);
		homeGridPane.getColumnConstraints().addAll(column1, column2, column3, column4, column5); // each get 50% of
																									// width

		// initialize My team title object and set it's location on the grid Pane
		Text MyTeamsTitle = MyTeamsTitle();
		GridPane.setMargin(MyTeamsTitle, new Insets(50, 5, 35, 5)); // the parameters buffer (Top, Left, Bottom,
		// Right)
		GridPane.setConstraints(MyTeamsTitle, 0, 2);
		GridPane.setHalignment(MyTeamsTitle, HPos.LEFT);

		// Initialize Create Team Button object and set it's location on the grid Pane
		Button CreateTeamsBtn = CreateTeamsBtn();
		GridPane.setMargin(CreateTeamsBtn, new Insets(50, 5, 35, 5)); // the parameters buffer (Top, Left, Bottom,
		// Right)
		GridPane.setConstraints(CreateTeamsBtn, 1, 2);
		GridPane.setHalignment(CreateTeamsBtn, HPos.RIGHT);
		GridPane.setValignment(CreateTeamsBtn, VPos.CENTER);

		// set GridPane to transparent?? didn't work
//		homeGridPane.setStyle("-fx-background-color: rgba(0, 100, 100, 1);-fx-background-redius:0;");

		// does not source the image (probably about where it's stored in the folders)
		ImageView teamImageView = teamImageView();

		GridPane.setMargin(teamImageView, new Insets(1, 5, 35, 5)); // the parameters buffer (Top, Left, Bottom,
		// Right

		GridPane.setConstraints(teamImageView, 0, 3);
		GridPane.setHalignment(teamImageView, HPos.LEFT);
		GridPane.setValignment(teamImageView, VPos.CENTER);

		ImageView teamImageView2 = teamImageView();

		GridPane.setMargin(teamImageView2, new Insets(1, 5, 35, 5)); // the parameters buffer (Top, Left, Bottom,
		// Right

		GridPane.setConstraints(teamImageView2, 1, 3);
		GridPane.setHalignment(teamImageView2, HPos.LEFT);
		GridPane.setValignment(teamImageView2, VPos.CENTER);
		homeGridPane.getChildren().addAll(teamImageView, teamImageView2);

		// Initialize My Event title and set it's location
		Text MyEventsTitle = MyEventsTitle();
		GridPane.setMargin(MyEventsTitle, new Insets(50, 5, 35, 5)); // the parameters buffer (Top, Left, Bottom,
		// Right)
		GridPane.setConstraints(MyEventsTitle, 0, 4);
		GridPane.setHalignment(MyEventsTitle, HPos.LEFT);

		// Initialize Create Event button and set it's location
		Button CreateEventsBtn = CreateEventsBtn();
		GridPane.setMargin(CreateEventsBtn, new Insets(50, 5, 35, 5)); // the parameters buffer (Top, Left, Bottom,
		// Right)
		GridPane.setConstraints(CreateEventsBtn, 1, 4);
		GridPane.setHalignment(CreateEventsBtn, HPos.RIGHT);
		GridPane.setValignment(CreateEventsBtn, VPos.CENTER);

		// set size of row to the width of the screen
		homeGridPane.setMaxWidth(1000);
		homeGridPane.setVgap(5);
		homeGridPane.setHgap(5);

		// add all objects to the GridPane
		homeGridPane.getChildren().addAll(MyTeamsTitle, CreateTeamsBtn, MyEventsTitle, CreateEventsBtn);
		return homeGridPane;
	}

	public Text MyTeamsTitle() {
		Text MyTeamsTitletext = new Text();

		MyTeamsTitletext.setText("My Teams");
		MyTeamsTitletext.setFont(Font.font("Helvetica", FontWeight.BOLD, 20));
		MyTeamsTitletext.setFill(Color.WHITE);

		return MyTeamsTitletext;
	}

	public Button CreateTeamsBtn() {

		Rectangle buttonShape = new Rectangle();
		buttonShape.setWidth(600);
		buttonShape.setHeight(100);
		buttonShape.setArcWidth(50);
		buttonShape.setArcHeight(100);

		Button CreateTeamsButton = new Button("Create Team");
		CreateTeamsButton.setFont(Font.font("Helvetica", FontWeight.BOLD, 10));
		CreateTeamsButton.setStyle("-fx-background-color: white; -fx-text-fill: black;");
		CreateTeamsButton.setShape(buttonShape);
		CreateTeamsButton.setPrefWidth(100);
		CreateTeamsButton.setPrefHeight(35);

		DropShadow dropShadow = new DropShadow();
		dropShadow.setOffsetX(3.0f);
		dropShadow.setOffsetY(5.0f);
		dropShadow.setColor(Color.rgb(150, 50, 50, .688));
		CreateTeamsButton.setEffect(dropShadow);

		CreateTeamsButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				createTeamScreenUI = new CreateTeamScreenUI(main);
				double sceneWidth = main.primaryStage.getScene().getWidth();
				double sceneHeight = main.primaryStage.getScene().getHeight();
				Scene createTeamScreenScene = new Scene(createTeamScreenUI, sceneWidth, sceneHeight);
				main.primaryStage.setScene(createTeamScreenScene);

			}
		});

		return CreateTeamsButton;
	}

	public ImageView teamImageView() {
		Image sampleteamimage = new Image("team.png");
		ImageView teamlogo = new ImageView();
		teamlogo.setImage(sampleteamimage);
		teamlogo.setFitWidth(100);
		teamlogo.setPreserveRatio(true);
		teamlogo.setSmooth(true);
		teamlogo.setCache(true);
		return teamlogo;
	}

	public Text MyEventsTitle() {
		Text MyEventsTitletext = new Text();

		MyEventsTitletext.setText("My Events");
		MyEventsTitletext.setFont(Font.font("Helvetica", FontWeight.BOLD, 20));
		MyEventsTitletext.setFill(Color.WHITE);

		return MyEventsTitletext;
	}

	public Button CreateEventsBtn() {

		Rectangle buttonShape = new Rectangle();
		buttonShape.setWidth(600);
		buttonShape.setHeight(100);
		buttonShape.setArcWidth(50);
		buttonShape.setArcHeight(100);

		Button CreateEventsButton = new Button("Create Event");
		CreateEventsButton.setFont(Font.font("Helvetica", FontWeight.BOLD, 10));
		CreateEventsButton.setStyle("-fx-background-color: white; -fx-text-fill: black;");
		CreateEventsButton.setShape(buttonShape);
		CreateEventsButton.setPrefWidth(100);
		CreateEventsButton.setPrefHeight(35);

		DropShadow dropShadow = new DropShadow();
		dropShadow.setOffsetX(3.0f);
		dropShadow.setOffsetY(5.0f);
		dropShadow.setColor(Color.rgb(150, 50, 50, .688));
		CreateEventsButton.setEffect(dropShadow);
		
		CreateEventsButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				createEventScreenUI = new CreateEventScreenUI(main);
				double sceneWidth = main.primaryStage.getScene().getWidth();
				double sceneHeight = main.primaryStage.getScene().getHeight();
				Scene createEventScreenScene = new Scene(createEventScreenUI, sceneWidth, sceneHeight);
				main.primaryStage.setScene(createEventScreenScene);

			}
		});

		return CreateEventsButton;
	}

	public Text homePagetitle() {
		Text homePageText = new Text();

		homePageText.setText("LineUp");
		homePageText.setFont(Font.font("Helvetica", FontWeight.BOLD, 50));
		homePageText.setFill(Color.WHITE);

		return homePageText;

	}

	public Background homepageBackground() {
		Image backgroundImage = new Image("blueBackground.png");

		BackgroundSize backgroundSize = new BackgroundSize(1, 1, true, true, false, false);

		Background logInScreenBackground = new Background(new BackgroundImage(backgroundImage,
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize));

		return logInScreenBackground;

	}

}
