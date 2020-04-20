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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
	
	@Override
	public void start(Stage stage) throws Exception {
		Scene start = SceneOne(stage);
		stage.setScene(start);
		stage.show();
	}
	
	private Scene SceneOne(Stage stage) {
		stage.setTitle(Title);
		BorderPane start = new BorderPane();
		start.setPadding(new Insets(20, 20, 20, 20));
		Scene scene = new Scene(start, 900, 650);
		Image startImage = new Image("start.jpeg");
		BackgroundImage backgroundImage = new BackgroundImage(startImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		Background background = new Background(backgroundImage);
		start.setBackground(background);
		Button exit = new Button("Exit");
		exit.setStyle("-fx-font-size: 13pt;");
		Button addData = new Button("Add Data");
		Button removeData = new Button("Remove Data");
		Button browse = new Button("Browse...");
		Button save = new Button("Save");
		Button reports = new Button("Reports");
		Button modify = new Button("Add/Edit/Remove");
		modify.setMinWidth(100);
		reports.setStyle("-fx-font-size: 11pt;");
		reports.setMinWidth(100);
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
		TextField textField = new TextField ();
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
		HBox bottom = new HBox();
		left.getChildren().add(reports);
		left.setAlignment(Pos.CENTER);
		right.getChildren().add(modify);
		right.setAlignment(Pos.CENTER);
		center.setSpacing(20);
		center.getChildren().addAll(selectFile, mid, midTwo, csvError);
		top.getChildren().add(startLabel);
		top.setAlignment(Pos.CENTER_LEFT);
		center.setAlignment(Pos.CENTER.TOP_CENTER);
		bottom.getChildren().add(exit);
		bottom.setAlignment(Pos.BASELINE_RIGHT);
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
	public static void main(String[] args) {
		launch(args);
	}

}
