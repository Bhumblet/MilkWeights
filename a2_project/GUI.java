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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

import a2_project.GUI.modifyTable;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
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
	private Button save = new Button("Save");
	private HBox bottom = new HBox();
	private final String MONTHS[] = { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };
	private final ComboBox COMBO_MONTHS = new ComboBox(FXCollections.observableArrayList(MONTHS));
	private Driver currentInfo;
	private List<File> files = new LinkedList<File>();
	
	@Override
	public void start(Stage stage) throws Exception {
		Scene start = SceneOne(stage);
		bottom.getChildren().addAll(menu, save, exit);
		bottom.setSpacing(327.5);
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
		save.setStyle("-fx-font-size: 13pt;");
		exit.setMinWidth(70);
		save.setMinWidth(70);
		menu.setMinWidth(70);
		menu.setVisible(false);
		save.setVisible(false);
		Button browse = new Button("Browse...");
		Button reports = new Button("Reports");
		Button modify = new Button("Add/Edit/Remove");
		modify.setMinWidth(120);
		reports.setStyle("-fx-font-size: 11pt;");
		reports.setMinWidth(120);
		reports.setDisable(true);
		modify.setDisable(true);
		Label startLabel = new Label("Welcome");
		Label saved = new Label("Saved!");
		Label selectFile = new Label("Select File(s)");
		Label numberFiles = new Label("Number of Files: 0");
		selectFile.setStyle("-fx-font-size: 14pt;");
		saved.setTextFill(Color.DARKGREEN);
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
		midTwo.getChildren().addAll(numberFiles, saved);
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
				files.add(file.showOpenDialog(stage));
				String path = files.get(files.size() - 1).getAbsolutePath();
				try {
					if(!path.substring(path.length()-4).equals(".csv")) {
						files.remove(files.size()-1);
						throw new Exception();
					}
					csvError.setVisible(false);
					saved.setVisible(true);
					textField.setText(files.get(files.size()-1).getAbsolutePath());
					numberFiles.setText("Number of Files: " + files.size());
					reports.setDisable(false);
					if(files.size() > 0 && files.size() < 2) {
						modify.setDisable(false);
					}
					else {
						modify.setDisable(true);
					}
				} catch(Exception t) {
					csvError.setVisible(true);
				}
			}
		};
		
		EventHandler<ActionEvent> exitEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				stage.close();
			}
		};
		EventHandler<ActionEvent> reportsEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				try {
					stage.setScene(sceneReport(stage));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		};
		EventHandler<ActionEvent> modifyEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				try {
					stage.setScene(modify(stage));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		};
		modify.setOnAction(modifyEvent);
		reports.setOnAction(reportsEvent);
		browse.setOnAction(browsweEvent);
		exit.setOnAction(exitEvent);
		start.setTop(top);
		start.setLeft(left);
		start.setCenter(center);
		start.setRight(right);
		start.setBottom(bottom);
		return scene;
	}
	
	private Scene sceneReport(Stage stage) throws Exception {
		currentInfo = new Driver(files);
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
		Label farmError = new Label("No matching ID/Year or invalid input!");
		farmError.setVisible(false);
		farmError.setTextFill(Color.RED);
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
				currentInfo = null;
				files = null;
				stage.setScene(SceneOne(stage));
			}
		};
		EventHandler<ActionEvent> farmEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String farmString = farmID.getText();
				String yearString = yearFarm.getText();
				try {
					int yearInt = Integer.parseInt(yearString);
					if(currentInfo.contains(farmString)) {
						stage.setScene(farmReport(stage, farmString, yearString));
					}
					else {
						farmError.setVisible(true);
					}
				} catch(Exception t) {
					farmError.setVisible(true);
				}
			}
		};
		menu.setOnAction(menuEvent);
		farmButton.setOnAction(farmEvent);
		VBox farmRep = new VBox();
		HBox farm = new  HBox();
		farm.getChildren().addAll(farmLabel, farmID, yearFarm, farmButton);
		farm.setSpacing(10);
		farmRep.getChildren().addAll(farmReport, farm, farmError);
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
	
	private Scene farmReport(Stage stage, String farmID, String year) throws Exception {
		stage.setTitle(Title + "-Farm Report");
		BorderPane start = new BorderPane();
		start.setBackground(background);
		start.setPadding(new Insets(20, 20, 20, 20));
		Scene scene = new Scene(start, 900, 650);
		Label label = new Label("Farm Report");
		label.setStyle("-fx-font-size: 22pt;");
		Label farm = new Label("Farm ID: " + farmID + " Year: " + year);
		farm.setStyle("-fx-font-size: 12pt;");
		TableView table = new TableView();
		TableColumn firstCol = new TableColumn("Month");
	    TableColumn secondCol = new TableColumn("Milk Weight");
	    TableColumn lastCol = new TableColumn("Month %");
	    firstCol.setPrefWidth(250);
	    secondCol.setPrefWidth(250);
	    lastCol.setPrefWidth(250);
	    table.getColumns().addAll(firstCol, secondCol, lastCol);
	    table.setMaxWidth(752);
	    firstCol.setCellValueFactory(
	    	 new PropertyValueFactory<farmTable,String>("month")
	    );
	    	secondCol.setCellValueFactory(
	    	    new PropertyValueFactory<farmTable,String>("milkWeight")
	    	);
	    	lastCol.setCellValueFactory(
	    	    new PropertyValueFactory<farmTable,String>("percentage")
	    	);
	    table.setItems(farmData);
	    table.setEditable(true);
	    table.setMaxHeight(330);
		HBox top = new HBox();
		VBox center = new VBox();
		center.getChildren().addAll(farm, table);
		center.setPrefWidth(600);
		center.setAlignment(Pos.TOP_CENTER);
		top.getChildren().add(label);
		top.setAlignment(Pos.CENTER_LEFT);
		start.setTop(top);
		start.setCenter(center);
		start.setBottom(bottom);
		return scene;
		
	}
	
	private Scene modify(Stage stage) throws Exception {
		currentInfo = new Driver(files);
		List<LogObject> data = currentInfo.getModifyData();
		for(int i = 0; i < data.size(); i++) {
			LogObject current = data.get(i);
			String[] date = current.getDate().split("-");
			String dateFormated = date[1] + "/" + date[2] + "/" + date[0];
			modifyData.add(new modifyTable(dateFormated, current.getID(), current.getWeight()));
		}
		stage.setTitle(Title + "-Modify");
		BorderPane start = new BorderPane();
		start.setBackground(background);
		start.setPadding(new Insets(20, 20, 20, 20));
		Scene scene = new Scene(start, 900, 650);
		Label label = new Label("Add/Edit/Remove");
		Label saved = new Label("Saved!");
		saved.setVisible(false);;
		saved.setTextFill(Color.LIGHTGREEN);
		label.setStyle("-fx-font-size: 22pt;");
		Label file = new Label("File: " + currentInfo.getFileName());
		file.setStyle("-fx-font-size: 12pt;");
		menu.setVisible(true);
		save.setVisible(true);
		EventHandler<ActionEvent> menuEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				currentInfo = null;
				files.clear();
				modifyData.clear();
				stage.setScene(SceneOne(stage));
			}
		};
		menu.setOnAction(menuEvent);
		TableView<modifyTable> table = new TableView<modifyTable>();
		TableColumn firstCol = new TableColumn("Date");
	    TableColumn secondCol = new TableColumn("Farm ID");
	    TableColumn lastCol = new TableColumn("Weight");
	    firstCol.setPrefWidth(250);
	    secondCol.setPrefWidth(250);
	    lastCol.setPrefWidth(250);
	    table.getColumns().addAll(firstCol, secondCol, lastCol);
	    table.setEditable(true);
	    table.setMaxWidth(770);
	    firstCol.setSortable(false);
	    secondCol.setSortable(false);
	    lastCol.setSortable(false);
	    firstCol.setCellValueFactory(
		    	 new PropertyValueFactory<farmTable,String>("date")
		    );
		    	secondCol.setCellValueFactory(
		    	    new PropertyValueFactory<farmTable,String>("farmID")
		    	);
		    	lastCol.setCellValueFactory(
		    	    new PropertyValueFactory<farmTable,String>("weight")
		    	);
	    table.setItems(modifyData);
	    DatePicker date = new DatePicker();
	    date.setPromptText("Date");
	    TextField ID = new TextField();
	    ID.setPromptText("Farm ID");
	    TextField weight = new TextField();
	    Label error = new Label("No input or invalid input!");
	    error.setTextFill(Color.RED);
	    error.setVisible(false);
	    weight.setPromptText("Weight");
	    Button add = new Button("Add");
	    Button remove = new Button("Remove");
	    add.setPrefWidth(101);
	    remove.setPrefWidth(101);
	    EventHandler<ActionEvent> addEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				if(date.getValue() != null && !ID.getText().equals("") && !weight.getText().equals("")) {
					error.setVisible(false);
					String dateText = date.getEditor().getText();
					//dateText = (dateText.substring(0, dateText.length()-4)) + dateText.substring(dateText.length()-2);
					modifyData.add(new modifyTable(dateText,ID.getText(),weight.getText()));
					date.setValue(null);
					ID.clear();
					weight.clear();
				}else {
					error.setVisible(true);
				}
			}
		};
		EventHandler<ActionEvent> removeEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				modifyTable selected = table.getSelectionModel().getSelectedItem();
				table.getItems().remove(selected);
			}
		};
		EventHandler<ActionEvent> saveEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				File file = files.get(0);
				try {
					PrintWriter print = new PrintWriter(file.getAbsolutePath());
					print.print("");
					print.close();
					FileWriter writer = new FileWriter(file.getAbsoluteFile(), false);
					BufferedWriter bWriter = new BufferedWriter(writer);
					bWriter.write("date,farm_id,weight");
					for(int i = 0; i < modifyData.size(); i++) {
						bWriter.newLine();
						String[] date = modifyData.get(i).getDate().split("/");
						String ID = modifyData.get(i).getFarmID();
						String weight = modifyData.get(i).getWeight();
						String dateFormat = date[2] + "-" + date[0] + "-" + date[1];
						bWriter.write(dateFormat + ",Farm " + ID + "," + weight);
					}
					bWriter.close();
					saved.setVisible(true);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		};
		firstCol.setCellFactory(TextFieldTableCell.forTableColumn());
		secondCol.setCellFactory(TextFieldTableCell.forTableColumn());
		lastCol.setCellFactory(TextFieldTableCell.forTableColumn());
		save.setOnAction(saveEvent);
		remove.setOnAction(removeEvent);
		add.setOnAction(addEvent);
		HBox top = new HBox();
		HBox mod = new HBox();
		mod.getChildren().addAll(date, ID, weight, add, remove);
		mod.setSpacing(10);
		mod.setAlignment(Pos.CENTER);
		VBox center = new VBox();
		center.getChildren().addAll(file, table, mod, error, saved);
		center.setPrefWidth(600);
		center.setAlignment(Pos.TOP_CENTER);
		center.setSpacing(20);
		top.getChildren().add(label);
		top.setAlignment(Pos.CENTER_LEFT);
		start.setTop(top);
		start.setCenter(center);
		start.setBottom(bottom);
		return scene;
		
	}
	private final ObservableList<farmTable> farmData =
	        FXCollections.observableArrayList(
	            new farmTable("January", "996", "6.6"),
	            new farmTable("February", "1375", "9.0"),
	            new farmTable("March", "1131", "7.4"),
	            new farmTable("April", "662", "4.4"),
	            new farmTable("May", "1147", "7.6"),
	            new farmTable("June", "1886", "12.4"),
	            new farmTable("July", "1081", "7.1"),
	            new farmTable("August", "1492", "9.8"),
	            new farmTable("September", "768", "5.0"),
	            new farmTable("October", "1593", "10.5"),
	            new farmTable("November", "1346", "8.9"),
	            new farmTable("December", "1713", "11.3")
	        );
	public static class farmTable {
		 
        private final SimpleStringProperty month;
        private final SimpleStringProperty milkWeight;
        private final SimpleStringProperty percentage;
 
        private farmTable(String month, String milkweight, String percentage) {
            this.month = new SimpleStringProperty(month);
            this.milkWeight = new SimpleStringProperty(milkweight);
            this.percentage = new SimpleStringProperty(percentage);
        }
 
        public String getMonth() {
            return month.get();
        }
 
        public void setMonth(String month) {
            this.month.set(month);
        }
 
        public String getMilkWeight() {
            return milkWeight.get();
        }
 
        public void setMilkWeight(String milkWeight) {
            this.milkWeight.set(milkWeight);
        }
 
        public String getPercentage() {
            return percentage.get();
        }
 
        public void setEmail(String percentage) {
            this.percentage.set(percentage);
        }
    }
	private final ObservableList<modifyTable> modifyData =
	        FXCollections.observableArrayList(
	            
	        );
	public static class modifyTable {
		 
        private final SimpleStringProperty date;
        private final SimpleStringProperty farmID;
        private final SimpleStringProperty weight;
 
        public modifyTable(String date, String farmID, String weight) {
            this.date = new SimpleStringProperty(date);
            this.farmID = new SimpleStringProperty(farmID);
            this.weight = new SimpleStringProperty(weight);
        }
 
        public String getDate() {
            return date.get();
        }
 
        public void setDate(String date) {
            this.date.set(date);
        }
 
        public String getFarmID() {
            return farmID.get();
        }
 
        public void setFarmID(String farmID) {
            this.farmID.set(farmID);
        }
 
        public String getWeight() {
            return weight.get();
        }
 
        public void setWeight(String weight) {
            this.weight.set(weight);
        }
    }
	
	/**
	 * Main method to run gui made from JavaFX
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);

	}
}
