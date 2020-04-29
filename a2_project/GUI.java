/**
 * GUI.java created in a2_project
 * 
 * Author:	 Brock Humblet (bhumblet@wisc.edu)
 * 			 John Wirth (jjwirth2@wisc.edu)
 * 			 Shashank Bala (sbala2@wisc.edu)
 * 			 Saurav Chandra (schandra8@wisc.edu)
 * 			 Logan Kroes (lkroes@wisc.edu)
 * 
 * Date:	 Apr 19, 2020
 * 
 * Course:	 CS400
 * Semester: Spring 2020
 * Lecture:	 001
 * 
 * List Collaborators: none
 * 
 * Other Credits: none
 * 
 * Known Bugs: none
 */
package a2_project;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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
	private Driver currentInfo;
	private List<File> files = new LinkedList<File>();
	
	/**
	 * 
	 */
	@Override
	public void start(Stage stage) throws Exception {
		Scene start = SceneOne(stage);
		bottom.getChildren().addAll(menu, save, exit);
		bottom.setSpacing(327.5);
		bottom.setAlignment(Pos.CENTER);
		stage.setScene(start);
		stage.show();
	}
	
	/**
	 * 
	 */
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
		Label directions = new Label("Only " +"\"Add/Edit/Remove\"" + " one file at a time.");
		Label numberFiles = new Label("Number of Files: 0");
		selectFile.setStyle("-fx-font-size: 14pt;");
		saved.setTextFill(Color.DARKGREEN);
		saved.setStyle("-fx-font-size: 14pt;");
		saved.setVisible(false);
		startLabel.setStyle("-fx-font-size: 22pt;");
		FileChooser file = new FileChooser();
		file.setTitle("Open Milk Weight Data File");
		Label filepath = new Label("Filepath:");
		Label csvError = new Label("No File/invalid file type/invalid file!");
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
		center.getChildren().addAll(selectFile, mid, directions, midTwo, csvError);
		top.getChildren().add(startLabel);
		top.setAlignment(Pos.CENTER_LEFT);
		center.setAlignment(Pos.CENTER.TOP_CENTER);
		EventHandler<ActionEvent> browsweEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				files.addAll(file.showOpenMultipleDialog(stage));
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
					files.clear();
					csvError.setVisible(true);
				}
			}
		};
		EventHandler<ActionEvent> modifyEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				try {
					stage.setScene(modify(stage));
				} catch (Exception e1) {
					files.clear();
					csvError.setVisible(true);
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
	
	/**
	 * 
	 */
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
		TextField yearAnnual = new TextField();
		TextField yearMonthly = new TextField();
		yearMonthly.setPrefWidth(112);
		yearAnnual.setPrefWidth(240);
		Label annualLabel = new Label("Year:");
		Label monthlyLabel = new Label("Year:");
		Label to = new Label("to");
		Label dateLabel = new Label("Range:");
		Label farmError = new Label("No matching ID/Year or invalid input!");
		Label annualError = new Label("No matching Year or invalid input!");
		Label monthError = new Label("No matching Month/Year or invalid input!");
		Label rangeError = new Label("No info between dates or invalid input!");
		ComboBox comboMonths = new ComboBox(FXCollections.observableArrayList(MONTHS));
		farmError.setVisible(false);
		farmError.setTextFill(Color.RED);
		farmID.setPrefWidth(110);
		annualError.setVisible(false);
		annualError.setTextFill(Color.RED);
		monthError.setVisible(false);
		monthError.setTextFill(Color.RED);
		rangeError.setVisible(false);
		rangeError.setTextFill(Color.RED);
		menu.setVisible(true);
		comboMonths.setPrefWidth(112);
		comboMonths.setPromptText("Month");
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
				files.clear();
				farmData.clear();
				stage.setScene(SceneOne(stage));
			}
		};
		EventHandler<ActionEvent> annualEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String year = yearAnnual.getText() ;
				try {
					int yearInt = Integer.parseInt(year);
					if(currentInfo.containsYear(yearInt)) {
						stage.setScene(annualReport(stage, yearInt));
					}
					else {
						annualError.setVisible(true);
					}
				} catch(Exception t) {
					annualError.setVisible(true);
				}
			}
			
		};
		EventHandler<ActionEvent> rangeEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
				try {
					Date one = format.parse(startDate.getEditor().getText());
					Date two = format.parse(endDate.getEditor().getText());
					if(currentInfo.containsDateRangeData(one, two)) {
						stage.setScene(rangeReport(stage, one, two));
					}
					else {
						rangeError.setVisible(true);
					}
				} catch (Exception e1) {
					rangeError.setVisible(true);
				}
				
			}
			
		};
		EventHandler<ActionEvent> monthEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String year = yearMonthly.getText();
				String month = (String)comboMonths.getValue();
				int monthInt = -1;
				for(int i = 0; i < MONTHS.length; i++) {
					if(MONTHS[i].equals(month)) {
						monthInt = i + 1;
					}
				}
				try {
					if(currentInfo.containsMonth(monthInt, year)) {
						stage.setScene(monthReport(stage, monthInt, year));
					}
					else {
					monthError.setVisible(true);
					}
				} catch(Exception t) {
					monthError.setVisible(true);
				}
			}
			
		};
		EventHandler<ActionEvent> farmEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String farmString = farmID.getText();
				String yearString = yearFarm.getText();
				try {
					if(currentInfo.containsFarm(farmString, yearString)) {
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
		dateButton.setOnAction(rangeEvent);
		monthlyButton.setOnAction(monthEvent);
		annualButton.setOnAction(annualEvent);
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
		annual.getChildren().addAll(annualLabel, yearAnnual, annualButton);
		annual.setSpacing(10);
		annualRep.getChildren().addAll(annualReport, annual, annualError);
		annualRep.setSpacing(20);
		VBox monthlyRep = new VBox();
		HBox monthly = new HBox();
		monthly.getChildren().addAll(monthlyLabel, yearMonthly, comboMonths, monthlyButton);
		monthly.setSpacing(10);
		monthlyRep.getChildren().addAll(monthlyReport, monthly, monthError);
		monthlyRep.setSpacing(20);
		VBox dateRep = new VBox();
		HBox date = new HBox();
		date.getChildren().addAll(dateLabel, startDate, to, endDate, dateButton);
		date.setSpacing(10);
		dateRep.getChildren().addAll(dateReport, date, rangeError);
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
	
	/**
	 * 
	 */
	private Scene annualReport(Stage stage, int year) throws Exception {
		List<YearWeight> list = currentInfo.getAnnualReport(year);
		int totalWeight = 0;
		for(int i = 0; i < list.size(); i++) {
			totalWeight += Integer.parseInt(list.get(i).getWeight());
		}
		for(int i = 0; i < list.size(); i++) {
			String weight = list.get(i).getWeight();
			int weightInt = Integer.parseInt(weight);
			annualData.add(new annualTable(list.get(i).getFarmID(), weight, (double)(Math.round(((double)(Integer.parseInt(weight)) / totalWeight) * 10000)) / 100 + "" ));
		}
		stage.setTitle(Title + "-Annual Report");
		BorderPane start = new BorderPane();
		start.setBackground(background);
		start.setPadding(new Insets(20, 20, 20, 20));
		Scene scene = new Scene(start, 900, 650);
		Button reports = new Button("Reports");
		reports.setStyle("-fx-font-size: 11pt;");
		Label label = new Label("Annual Report");
		label.setStyle("-fx-font-size: 22pt;");
		Label annual = new Label("Year: " + year);
		annual.setStyle("-fx-font-size: 12pt;");
		TableView table = new TableView();
		TableColumn firstCol = new TableColumn("Farm");
	    TableColumn secondCol = new TableColumn("Total Weight");
	    TableColumn lastCol = new TableColumn("Year %");
	    firstCol.setPrefWidth(250);
	    secondCol.setPrefWidth(250);
	    lastCol.setPrefWidth(250);
	    firstCol.setSortable(false);
	    lastCol.setSortable(false);
	    table.getColumns().addAll(firstCol, secondCol, lastCol);
	    table.setMaxWidth(770);
	    firstCol.setCellValueFactory(
	    		new PropertyValueFactory<farmTable,String>("farm")
	    );
	    secondCol.setCellValueFactory(
	    		new PropertyValueFactory<farmTable,String>("weight")
	    );
	    lastCol.setCellValueFactory(
	    	    new PropertyValueFactory<farmTable,String>("percentage")
	    );
	    EventHandler<ActionEvent> reportEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				try {
					stage.setScene(sceneReport(stage));
					annualData.clear();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		};
	    reports.setOnAction(reportEvent);
	    table.setItems(annualData);
		HBox top = new HBox();
		VBox center = new VBox();
		center.getChildren().addAll(annual, table,reports);
		center.setPrefWidth(600);
		center.setSpacing(20);
		center.setAlignment(Pos.TOP_CENTER);
		top.getChildren().add(label);
		top.setAlignment(Pos.CENTER_LEFT);
		start.setTop(top);
		start.setCenter(center);
		start.setBottom(bottom);
		return scene;
	}
	
	/**
	 * 
	 */
	private Scene monthReport(Stage stage, int month, String year) throws Exception {
		List<MonthWeight> list = currentInfo.getMonthlyReport(year, month + "");
		int totalWeight = 0;
		for(int i = 0; i < list.size(); i++) {
			totalWeight += Integer.parseInt(list.get(i).getWeight());
		}
		for(int i = 0; i < list.size(); i++) {
			String weight = list.get(i).getWeight();
			int weightInt = Integer.parseInt(weight);
			monthData.add(new monthTable(list.get(i).getFarmID(), weight, (double)(Math.round(((double)(Integer.parseInt(weight)) / totalWeight) * 10000)) / 100 + "" ));
		}
		stage.setTitle(Title + "-Month Report");
		BorderPane start = new BorderPane();
		start.setBackground(background);
		start.setPadding(new Insets(20, 20, 20, 20));
		Scene scene = new Scene(start, 900, 650);
		Button reports = new Button("Reports");
		reports.setStyle("-fx-font-size: 11pt;");
		Label label = new Label("Monthly Report");
		label.setStyle("-fx-font-size: 22pt;");
		Label monthly = new Label("Year: " + year);
		monthly.setStyle("-fx-font-size: 12pt;");
		TableView table = new TableView();
		TableColumn firstCol = new TableColumn("Farm");
	    TableColumn secondCol = new TableColumn("Total Weight");
	    TableColumn lastCol = new TableColumn("Year %");
	    firstCol.setPrefWidth(250);
	    secondCol.setPrefWidth(250);
	    lastCol.setPrefWidth(250);
	    firstCol.setSortable(false);
	    lastCol.setSortable(false);
	    table.getColumns().addAll(firstCol, secondCol, lastCol);
	    table.setMaxWidth(770);
	    firstCol.setCellValueFactory(
	    		new PropertyValueFactory<farmTable,String>("farm")
	    );
	    secondCol.setCellValueFactory(
	    		new PropertyValueFactory<farmTable,String>("weight")
	    );
	    lastCol.setCellValueFactory(
	    	    new PropertyValueFactory<farmTable,String>("percentage")
	    );
	    EventHandler<ActionEvent> reportEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				try {
					stage.setScene(sceneReport(stage));
					monthData.clear();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		};
	    reports.setOnAction(reportEvent);
	    table.setItems(monthData);
		HBox top = new HBox();
		VBox center = new VBox();
		center.getChildren().addAll(monthly, table,reports);
		center.setPrefWidth(600);
		center.setSpacing(20);
		center.setAlignment(Pos.TOP_CENTER);
		top.getChildren().add(label);
		top.setAlignment(Pos.CENTER_LEFT);
		start.setTop(top);
		start.setCenter(center);
		start.setBottom(bottom);
		return scene;
	}
	
	/**
	 * 
	 */
	private Scene rangeReport(Stage stage, Date one, Date two) throws Exception {
		List<DayWeight> list = currentInfo.getDateRangeReport(one, two);
		int totalWeight = 0;
		for(int i = 0; i < list.size(); i++) {
			totalWeight += Integer.parseInt(list.get(i).getWeight());
		}
		for(int i = 0; i < list.size(); i++) {
			String weight = list.get(i).getWeight();
			int weightInt = Integer.parseInt(weight);
			rangeData.add(new rangeTable(list.get(i).getFarmID(), weight, (double)(Math.round(((double)(Integer.parseInt(weight)) / totalWeight) * 10000)) / 100 + "" ));
		}
		stage.setTitle(Title + "-Date Range Report");
		BorderPane start = new BorderPane();
		start.setBackground(background);
		start.setPadding(new Insets(20, 20, 20, 20));
		Scene scene = new Scene(start, 900, 650);
		Button reports = new Button("Reports");
		reports.setStyle("-fx-font-size: 11pt;");
		Label label = new Label("Date Range Report");
		label.setStyle("-fx-font-size: 22pt;");
		String[] dateOne = one.toString().split(" ");
		String[] dateTwo = two.toString().split(" ");
		String dateOneFormated = dateOne[0] + " " + dateOne[1] + " " + dateOne[2]+ " " + dateOne[5];
		String dateTwoFormated = dateTwo[0] + " " + dateTwo[1] + " " + dateTwo[2]+ " " + dateTwo[5];
		Label range = new Label(dateOneFormated + " to " + dateTwoFormated);
		range.setStyle("-fx-font-size: 12pt;");
		TableView table = new TableView();
		TableColumn firstCol = new TableColumn("Farm");
	    TableColumn secondCol = new TableColumn("Total Weight");
	    TableColumn lastCol = new TableColumn("Year %");
	    firstCol.setPrefWidth(250);
	    secondCol.setPrefWidth(250);
	    lastCol.setPrefWidth(250);
	    firstCol.setSortable(false);
	    lastCol.setSortable(false);
	    table.getColumns().addAll(firstCol, secondCol, lastCol);
	    table.setMaxWidth(770);
	    firstCol.setCellValueFactory(
	    		new PropertyValueFactory<farmTable,String>("farm")
	    );
	    secondCol.setCellValueFactory(
	    		new PropertyValueFactory<farmTable,String>("weight")
	    );
	    lastCol.setCellValueFactory(
	    	    new PropertyValueFactory<farmTable,String>("percentage")
	    );
	    EventHandler<ActionEvent> reportEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				try {
					stage.setScene(sceneReport(stage));
					rangeData.clear();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		};
	    reports.setOnAction(reportEvent);
	    table.setItems(rangeData);
		HBox top = new HBox();
		VBox center = new VBox();
		center.getChildren().addAll(range, table,reports);
		center.setPrefWidth(600);
		center.setSpacing(20);
		center.setAlignment(Pos.TOP_CENTER);
		top.getChildren().add(label);
		top.setAlignment(Pos.CENTER_LEFT);
		start.setTop(top);
		start.setCenter(center);
		start.setBottom(bottom);
		return scene;
	}
	
	/**
	 * 
	 */
	private Scene farmReport(Stage stage, String farmID, String year) throws Exception {
		Farm farmList = new Farm(currentInfo.getSpecificFarm(farmID));
		double[][] data = farmList.getFarmYearData(year);
		for(int i = 0; i < 12; i++) {
			farmData.add(new farmTable(MONTHS[i], (int)data[i][0] + "", (double)Math.round((data[i][1] * 100)) / 100 + ""));
		}
		stage.setTitle(Title + "-Farm Report");
		BorderPane start = new BorderPane();
		start.setBackground(background);
		start.setPadding(new Insets(20, 20, 20, 20));
		Scene scene = new Scene(start, 900, 650);
		Button reports = new Button("Reports");
		reports.setStyle("-fx-font-size: 11pt;");
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
	    firstCol.setSortable(false);
	    lastCol.setSortable(false);
	    EventHandler<ActionEvent> reportEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				try {
					stage.setScene(sceneReport(stage));
					farmData.clear();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		};
	    reports.setOnAction(reportEvent);
	    table.setItems(farmData);
	    table.setMaxHeight(330);
		HBox top = new HBox();
		VBox center = new VBox();
		center.getChildren().addAll(farm, table,reports);
		center.setPrefWidth(600);
		center.setSpacing(20);
		center.setAlignment(Pos.TOP_CENTER);
		top.getChildren().add(label);
		top.setAlignment(Pos.CENTER_LEFT);
		start.setTop(top);
		start.setCenter(center);
		start.setBottom(bottom);
		return scene;
	}
	
	/**
	 * 
	 */
	private Scene modify(Stage stage) throws Exception {
		currentInfo = new Driver(files);
		List<LogObject> data = currentInfo.getModifyReport();
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
	
	// 
	private final ObservableList<farmTable> farmData =
	        FXCollections.observableArrayList(
	        );
	
	/**
	 * 
	 */
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
 
        public void setPercentage(String percentage) {
            this.percentage.set(percentage);
        }
    }
	
	// 
	private final ObservableList<annualTable> annualData =
	        FXCollections.observableArrayList(
	        );
	
	/**
	 * 
	 */
	public static class annualTable {
		 
        private final SimpleStringProperty farm;
        private final SimpleStringProperty weight;
        private final SimpleStringProperty percentage;
 
        private annualTable(String farm, String weight, String percentage) {
            this.farm = new SimpleStringProperty(farm);
            this.weight= new SimpleStringProperty(weight);
            this.percentage = new SimpleStringProperty(percentage);
        }
 
        public String getFarm() {
            return farm.get();
        }
 
        public void setFarm(String farm) {
            this.farm.set(farm);
        }
 
        public String getWeight() {
            return weight.get();
        }
 
        public void setWeight(String weight) {
            this.weight.set(weight);
        }
 
        public String getPercentage() {
            return percentage.get();
        }
 
        public void setPercentage(String percentage) {
            this.percentage.set(percentage);
        }
    }
	
	// 
	private final ObservableList<monthTable> monthData =
	        FXCollections.observableArrayList(
	        );
	
	/**
	 * 
	 */
	public static class monthTable {
		 
        private final SimpleStringProperty farm;
        private final SimpleStringProperty weight;
        private final SimpleStringProperty percentage;
 
        private monthTable(String farm, String weight, String percentage) {
            this.farm = new SimpleStringProperty(farm);
            this.weight= new SimpleStringProperty(weight);
            this.percentage = new SimpleStringProperty(percentage);
        }
 
        public String getFarm() {
            return farm.get();
        }
 
        public void setFarm(String farm) {
            this.farm.set(farm);
        }
 
        public String getWeight() {
            return weight.get();
        }
 
        public void setWeight(String weight) {
            this.weight.set(weight);
        }
 
        public String getPercentage() {
            return percentage.get();
        }
 
        public void setPercentage(String percentage) {
            this.percentage.set(percentage);
        }
    }
	
	// 
		private final ObservableList<rangeTable> rangeData =
		        FXCollections.observableArrayList(
		        );
		
		/**
		 * 
		 */
		public static class rangeTable {
			 
	        private final SimpleStringProperty farm;
	        private final SimpleStringProperty weight;
	        private final SimpleStringProperty percentage;
	 
	        private rangeTable(String farm, String weight, String percentage) {
	            this.farm = new SimpleStringProperty(farm);
	            this.weight= new SimpleStringProperty(weight);
	            this.percentage = new SimpleStringProperty(percentage);
	        }
	 
	        public String getFarm() {
	            return farm.get();
	        }
	 
	        public void setFarm(String farm) {
	            this.farm.set(farm);
	        }
	 
	        public String getWeight() {
	            return weight.get();
	        }
	 
	        public void setWeight(String weight) {
	            this.weight.set(weight);
	        }
	 
	        public String getPercentage() {
	            return percentage.get();
	        }
	 
	        public void setPercentage(String percentage) {
	            this.percentage.set(percentage);
	        }
	    }
	
	// 
	private final ObservableList<modifyTable> modifyData =
	        FXCollections.observableArrayList( 
	        );
	
	/**
	 * 
	 */
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
