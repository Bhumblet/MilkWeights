/**
 * HelloFX.java created by brock on Macbook Pro in a2
 *
 * Author:	 Brock Humblet (bhumblet@wisc.edu)
 * Date:	 4-19-20
 * 
 * Course:	 CS400
 * Semester: Spring 2020
 * Lecture:  001
 *
 * IDE:		 Eclipse IDE for Java Developers
 * Version:  2019-12 (4.14.0)
 * Build id: 20191212-1212
 * 
 * Device:	 Brock's Macbook Pro
 * OS:		 macOS Mojave
 * Version:	 10.14.6
 *
 * List Collaborators: Name, email@wisc.edu, lecture number
 *
 * Other Credits: describe other source (website or people)
 *
 * Known Bugs: None
 */
package a2_project;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class GUI extends Application {

	private final String Title = "Milk Weights";
	private Image startImage = new Image("start.jpeg");
	private BackgroundImage backgroundImage = new BackgroundImage(startImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
	private Background background = new Background(backgroundImage);
	private Button exit = new Button("Exit");
	private Button menu = new Button("Menu");
	private HBox bottom = new HBox();
	private final String MONTHS[] = { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };
	private final ComboBox COMBO_MONTHS = new ComboBox(FXCollections.observableArrayList(MONTHS));
	
	@Override
	public void start(Stage stage) throws Exception {
		Scene start = SceneOne(stage);
		bottom.getChildren().addAll(menu,exit);
		bottom.setSpacing(725);
		bottom.setAlignment(Pos.CENTER);
		COMBO_MONTHS.setPromptText("Month");
		stage.setScene(start);
		stage.show();
	}
	
	private Scene SceneOne(Stage stage) {
		stage.setTitle(Title);
		BorderPane start = new BorderPane();
		start.setPadding(new Insets(20, 20, 20, 20));
		Scene scene = new Scene(start, 900, 650);
		start.setBackground(background);
		exit.setStyle("-fx-font-size: 13pt;");
		menu.setStyle("-fx-font-size: 13pt;");
		exit.setMinWidth(70);
		menu.setMinWidth(70);
		menu.setVisible(false);
		Button browse = new Button("Browse...");
		Button save = new Button("Save");
		Button reports = new Button("Reports");
		Button modify = new Button("Add/Edit/Remove");
		modify.setMinWidth(120);
		reports.setStyle("-fx-font-size: 11pt;");
		reports.setMinWidth(120);
		reports.setDisable(true);
		modify.setDisable(true);
		Label startLabel = new Label("Welcome");
		Label saved = new Label("Saved!");
		Label selectFile = new Label("Select Input");
		selectFile.setStyle("-fx-font-size: 14pt;");
		saved.setTextFill(Color.GREEN);
		saved.setStyle("-fx-font-size: 14pt;");
		saved.setVisible(false);
		startLabel.setStyle("-fx-font-size: 22pt;");
		FileChooser file = new FileChooser();
		file.setTitle("Open Milk Weight Data File");
		Label filepath = new Label("Filepath:");
		Label csvError = new Label("No File or invalid file type!");
		csvError.setTextFill(Color.RED);
		csvError.setVisible(false);
		TextField textField = new TextField();
		HBox mid = new HBox();
		mid.getChildren().addAll(filepath, textField, browse);
		mid.setSpacing(10);
		mid.setAlignment(Pos.CENTER);
		HBox midTwo = new HBox();
		midTwo.getChildren().addAll(save, saved);
		midTwo.setSpacing(10);
		midTwo.setAlignment(Pos.CENTER);
		HBox top = new HBox();
		VBox left = new VBox();
		VBox center = new VBox();
		VBox right = new VBox();
		left.getChildren().add(reports);
		left.setAlignment(Pos.CENTER);
		right.getChildren().add(modify);
		right.setAlignment(Pos.CENTER);
		center.setSpacing(20);
		center.getChildren().addAll(selectFile, mid, midTwo, csvError);
		top.getChildren().add(startLabel);
		top.setAlignment(Pos.CENTER_LEFT);
		center.setAlignment(Pos.CENTER.TOP_CENTER);
		EventHandler<ActionEvent> browsweEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				textField.setText(file.showOpenDialog(stage).toString());
				save.fire();
			}
		};
		EventHandler<ActionEvent> saveEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				/*if(invalid file) {
					csvError.setVisible(true);*/
				saved.setVisible(true);
				csvError.setVisible(false);
				reports.setDisable(false);
				modify.setDisable(false);
			}
		};
		EventHandler<ActionEvent> exitEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				stage.close();
			}
		};
		EventHandler<ActionEvent> reportsEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				stage.setScene(sceneReport(stage));
			}
		};
		reports.setOnAction(reportsEvent);
		save.setOnAction(saveEvent);
		browse.setOnAction(browsweEvent);
		exit.setOnAction(exitEvent);
		start.setTop(top);
		start.setLeft(left);
		start.setCenter(center);
		start.setRight(right);
		start.setBottom(bottom);
		return scene;
	}
	
	private Scene sceneReport(Stage stage) {
		stage.setTitle(Title + "-Reports");
		BorderPane start = new BorderPane();
		start.setBackground(background);
		start.setPadding(new Insets(20, 20, 20, 20));
		Scene scene = new Scene(start, 900, 650);
		Label farmReport = new Label("Farm Report");
		farmReport.setStyle("-fx-font-size: 14pt;");
		Label annualReport = new Label("Annual Report");
		annualReport.setStyle("-fx-font-size: 14pt;");
		Label monthlyReport = new Label("Montly Report");
		monthlyReport.setStyle("-fx-font-size: 14pt;");
		Label dateReport = new Label("Date Range Report");
		dateReport.setStyle("-fx-font-size: 14pt;");
		Label farmLabel = new Label("Farm ID:");
		TextField farmID = new TextField();
		TextField yearFarm = new TextField();
		yearFarm.setPrefWidth(100);
		yearFarm.setPromptText("Year");
		TextField year = new TextField();
		TextField yearMonthly = new TextField();
		yearMonthly.setPrefWidth(112);
		year.setPrefWidth(240);
		Label annualLabel = new Label("Year:");
		Label monthlyLabel = new Label("Year:");
		Label to = new Label("to");
		Label dateLabel = new Label("Range:");
		farmID.setPrefWidth(110);
		menu.setVisible(true);
		COMBO_MONTHS.setPrefWidth(112);
		Label label = new Label("Reports");
		label.setStyle("-fx-font-size: 22pt;");
		Button farmButton = new Button("Farm Report");
		Button annualButton = new Button("Annual Report");
		Button monthlyButton = new Button("Monthly Report");
		Button dateButton = new Button("Range Report");
		DatePicker startDate = new DatePicker();
		DatePicker endDate = new DatePicker();
		startDate.setPrefWidth(101);
		endDate.setPrefWidth(101);
		annualButton.setPrefWidth(105);
		farmButton.setPrefWidth(105);
		EventHandler<ActionEvent> menuEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				stage.setScene(SceneOne(stage));
			}
		};
		menu.setOnAction(menuEvent);
		VBox farmRep = new VBox();
		HBox farm = new  HBox();
		farm.getChildren().addAll(farmLabel, farmID, yearFarm, farmButton);
		farm.setSpacing(10);
		farmRep.getChildren().addAll(farmReport, farm);
		farmRep.setSpacing(20);
		VBox annualRep = new VBox();
		HBox annual = new HBox();
		annual.getChildren().addAll(annualLabel, year, annualButton);
		annual.setSpacing(10);
		annualRep.getChildren().addAll(annualReport, annual);
		annualRep.setSpacing(20);
		VBox monthlyRep = new VBox();
		HBox monthly = new HBox();
		monthly.getChildren().addAll(monthlyLabel, yearMonthly, COMBO_MONTHS, monthlyButton);
		monthly.setSpacing(10);
		monthlyRep.getChildren().addAll(monthlyReport, monthly);
		monthlyRep.setSpacing(20);
		VBox dateRep = new VBox();
		HBox date = new HBox();
		date.getChildren().addAll(dateLabel, startDate, to, endDate, dateButton);
		date.setSpacing(10);
		dateRep.getChildren().addAll(dateReport, date);
		dateRep.setSpacing(20);
		HBox top = new HBox();
		VBox left = new VBox();
		VBox right = new VBox();
		top.getChildren().add(label);
		left.getChildren().addAll(farmRep, annualRep);
		left.setAlignment(Pos.TOP_LEFT);
		right.getChildren().addAll(monthlyRep, dateRep);
		right.setAlignment(Pos.TOP_LEFT);
		right.setSpacing(150);
		left.setSpacing(150);
		top.setAlignment(Pos.CENTER_LEFT);
		start.setTop(top);
		start.setLeft(left);
		start.setRight(right);
		start.setBottom(bottom);
		return scene;
	}
	public static void main(String[] args) {
		launch(args);
	}

}
