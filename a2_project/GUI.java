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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

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

/**
 * GUI - Creates the GUI for milkweights
 * 
 * @author humblet/wirth/bala/chandra/kroes (2020)
 *
 */
public class GUI extends Application {

	private final String Title = "Milk Weights";
	private Image startImage = new Image("start.jpeg");
	private BackgroundImage backgroundImage = new BackgroundImage(startImage, BackgroundRepeat.NO_REPEAT,
			BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
	private Background background = new Background(backgroundImage);
	private Button exit = new Button("Exit");
	private Button menu = new Button("Menu");
	private Button save = new Button("Save");
	private HBox bottom = new HBox();
	private final String MONTHS[] = { "January", "February", "March", "April", "May", "June", "July", "August",
			"September", "October", "November", "December" };
	private Driver currentInfo;
	private List<File> files = new LinkedList<File>();

	/**
	 * This method starts the GUI program creating any GUI wide changes
	 * 
	 * @param stage - the canvas to create the program on
	 */
	@Override
	public void start(Stage stage) throws Exception {
		Scene start = SceneOne(stage);

		// Modifying JavaFX Controls
		bottom.getChildren().addAll(menu, save, exit);
		bottom.setSpacing(327.5);
		bottom.setAlignment(Pos.CENTER);

		// Modifying stage
		stage.setScene(start);
		stage.show();
	}

	/**
	 * This creates the main scene the user will see when opening the program
	 * 
	 * @param stage - the canvas to create the scene on
	 */
	private Scene SceneOne(Stage stage) {
		// Creating JavaFX Controls
		BorderPane start = new BorderPane();
		Scene scene = new Scene(start, 900, 650);
		Button browse = new Button("Browse...");
		Button reports = new Button("Reports");
		Button modify = new Button("Add/Edit/Remove");
		FileChooser file = new FileChooser();
		Label startLabel = new Label("Welcome");
		Label saved = new Label("Saved!");
		Label selectFile = new Label("Select File(s)");
		Label directions = new Label("Only " + "\"Add/Edit/Remove\"" + " one file at a time.");
		Label numberFiles = new Label("Number of Files: 0");
		Label filepath = new Label("Filepath:");
		Label csvError = new Label("No File/invalid file type/invalid file!");
		TextField textField = new TextField();
		HBox mid = new HBox();
		HBox midTwo = new HBox();
		HBox top = new HBox();
		VBox left = new VBox();
		VBox center = new VBox();
		VBox right = new VBox();

		// Modifying JavaFX Controls
		stage.setTitle(Title);
		start.setPadding(new Insets(20, 20, 20, 20));
		start.setBackground(background);
		exit.setStyle("-fx-font-size: 13pt;");
		exit.setMinWidth(70);
		save.setStyle("-fx-font-size: 13pt;");
		save.setMinWidth(70);
		save.setVisible(false);
		menu.setMinWidth(70);
		menu.setStyle("-fx-font-size: 13pt;");
		menu.setVisible(false);
		modify.setDisable(true);
		modify.setMinWidth(120);
		reports.setStyle("-fx-font-size: 11pt;");
		reports.setMinWidth(120);
		reports.setDisable(true);
		selectFile.setStyle("-fx-font-size: 14pt;");
		saved.setTextFill(Color.DARKGREEN);
		saved.setStyle("-fx-font-size: 14pt;");
		saved.setVisible(false);
		startLabel.setStyle("-fx-font-size: 22pt;");
		file.setTitle("Open Milk Weight Data File");
		csvError.setTextFill(Color.RED);
		csvError.setVisible(false);

		// Formating Layout
		mid.getChildren().addAll(filepath, textField, browse);
		mid.setSpacing(10);
		mid.setAlignment(Pos.CENTER);
		midTwo.getChildren().addAll(numberFiles, saved);
		midTwo.setSpacing(10);
		midTwo.setAlignment(Pos.CENTER);
		left.getChildren().add(reports);
		left.setAlignment(Pos.CENTER);
		right.getChildren().add(modify);
		right.setAlignment(Pos.CENTER);
		center.setSpacing(20);
		center.getChildren().addAll(selectFile, mid, directions, midTwo, csvError);
		top.getChildren().add(startLabel);
		top.setAlignment(Pos.CENTER_LEFT);
		center.setAlignment(Pos.TOP_CENTER);

		// Event Handling
		// Event for browse button
		EventHandler<ActionEvent> browsweEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				try {
					files.addAll(file.showOpenMultipleDialog(stage)); // Adds all selected files to files
					String path = files.get(files.size() - 1).getAbsolutePath();
					if (!path.substring(path.length() - 4).equals(".csv")) { // Ensures only .csv files are accepted
						files.clear(); // Removes all files
						throw new Exception();
					}
					csvError.setVisible(false);
					saved.setVisible(true);
					textField.setText(files.get(files.size() - 1).getAbsolutePath());
					numberFiles.setText("Number of Files: " + files.size());
					reports.setDisable(false);
					if (files.size() > 0 && files.size() < 2) { // Only allows modification if one file is selected
						modify.setDisable(false);
					} else {
						modify.setDisable(true);
					}
				} catch (Exception t) {
					csvError.setVisible(true);
				}
			}
		};

		// Event for exit button
		EventHandler<ActionEvent> exitEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				stage.close();
			}
		};

		// Event for reports button
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

		// Event for Add/Edit/Remove button
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

		// Button Triggers
		modify.setOnAction(modifyEvent);
		reports.setOnAction(reportsEvent);
		browse.setOnAction(browsweEvent);
		exit.setOnAction(exitEvent);

		// Sets BorderPane arguments
		start.setTop(top);
		start.setLeft(left);
		start.setCenter(center);
		start.setRight(right);
		start.setBottom(bottom);
		return scene;
	}

	/**
	 * This creates the reports scene the user will use to get reports
	 * 
	 * @param stage - the canvas to create the scene on
	 */
	private Scene sceneReport(Stage stage) throws Exception {
		currentInfo = new Driver(files);

		// Creating JavaFX Controls
		BorderPane start = new BorderPane();
		Scene scene = new Scene(start, 900, 650);
		Label label = new Label("Reports");
		Label farmReport = new Label("Farm Report");
		Label annualReport = new Label("Annual Report");
		Label monthlyReport = new Label("Montly Report");
		Label dateReport = new Label("Date Range Report");
		Label farmLabel = new Label("Farm ID:");
		Label dateLabel = new Label("Range:");
		Label annualLabel = new Label("Year:");
		Label monthlyLabel = new Label("Year:");
		Label to = new Label("to");
		Label farmError = new Label("No matching ID/Year or invalid input!");
		Label annualError = new Label("No matching Year or invalid input!");
		Label monthError = new Label("No matching Month/Year or invalid input!");
		Label rangeError = new Label("No info between dates or invalid input!");
		ComboBox<String> comboMonths = new ComboBox<String>(FXCollections.observableArrayList(MONTHS));
		TextField farmID = new TextField();
		TextField yearFarm = new TextField();
		TextField yearAnnual = new TextField();
		TextField yearMonthly = new TextField();
		Button farmButton = new Button("Farm Report");
		Button annualButton = new Button("Annual Report");
		Button monthlyButton = new Button("Monthly Report");
		Button dateButton = new Button("Range Report");
		DatePicker startDate = new DatePicker();
		DatePicker endDate = new DatePicker();
		VBox farmRep = new VBox();
		VBox annualRep = new VBox();
		VBox monthlyRep = new VBox();
		VBox dateRep = new VBox();
		VBox left = new VBox();
		VBox right = new VBox();
		HBox date = new HBox();
		HBox monthly = new HBox();
		HBox annual = new HBox();
		HBox farm = new HBox();
		HBox top = new HBox();

		// Modifying JavaFx Controls
		menu.setVisible(true);
		stage.setTitle(Title + "-Reports");
		start.setBackground(background);
		start.setPadding(new Insets(20, 20, 20, 20));
		farmReport.setStyle("-fx-font-size: 14pt;");
		annualReport.setStyle("-fx-font-size: 14pt;");
		monthlyReport.setStyle("-fx-font-size: 14pt;");
		dateReport.setStyle("-fx-font-size: 14pt;");
		yearFarm.setPrefWidth(100);
		yearFarm.setPromptText("Year");
		yearMonthly.setPrefWidth(112);
		yearAnnual.setPrefWidth(240);
		farmError.setVisible(false);
		farmError.setTextFill(Color.RED);
		farmID.setPrefWidth(110);
		annualError.setVisible(false);
		annualError.setTextFill(Color.RED);
		monthError.setVisible(false);
		monthError.setTextFill(Color.RED);
		rangeError.setVisible(false);
		rangeError.setTextFill(Color.RED);
		comboMonths.setPrefWidth(112);
		comboMonths.setPromptText("Month");
		label.setStyle("-fx-font-size: 22pt;");
		startDate.setPrefWidth(101);
		endDate.setPrefWidth(101);
		annualButton.setPrefWidth(105);
		farmButton.setPrefWidth(105);

		// Event Handling
		// Event for menu button
		EventHandler<ActionEvent> menuEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				currentInfo = null;
				files.clear();
				farmData.clear();
				stage.setScene(SceneOne(stage));
			}
		};

		// Event for annual report button
		EventHandler<ActionEvent> annualEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String year = yearAnnual.getText();
				try {
					int yearInt = Integer.parseInt(year);
					if (currentInfo.containsYear(yearInt)) { // If the files selected contain info from that year
						stage.setScene(annualReport(stage, yearInt));
					} else {
						annualError.setVisible(true);
					}
				} catch (Exception t) {
					annualError.setVisible(true);
				}
			}

		};

		// Event for date range report button
		EventHandler<ActionEvent> rangeEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
				try {
					Date one = format.parse(startDate.getEditor().getText());
					Date two = format.parse(endDate.getEditor().getText());
					if (currentInfo.containsDateRangeData(one, two)) { // if the files selected have data in the
																		// selected range
						stage.setScene(rangeReport(stage, one, two));
					} else {
						rangeError.setVisible(true);
					}
				} catch (Exception e1) {
					rangeError.setVisible(true);
				}

			}

		};

		// Event for month report button
		EventHandler<ActionEvent> monthEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String year = yearMonthly.getText();
				String month = (String) comboMonths.getValue();
				int monthInt = -1;
				for (int i = 0; i < MONTHS.length; i++) { // Gets the month index
					if (MONTHS[i].equals(month)) {
						monthInt = i + 1;
					}
				}
				try {
					if (currentInfo.containsMonth(monthInt, year)) { // if the selected files contain the month and year
																		// combo
						stage.setScene(monthReport(stage, monthInt, year));
					} else {
						monthError.setVisible(true);
					}
				} catch (Exception t) {
					monthError.setVisible(true);
				}
			}

		};

		// Event for farm report button
		EventHandler<ActionEvent> farmEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String farmString = farmID.getText();
				String yearString = yearFarm.getText();
				try {
					if (currentInfo.containsFarm(farmString, yearString)) { // if the slected files contain the farm and
																			// year data
						stage.setScene(farmReport(stage, farmString, yearString));
					} else {
						farmError.setVisible(true);
					}
				} catch (Exception t) {
					farmError.setVisible(true);
				}
			}
		};

		// Button triggers
		menu.setOnAction(menuEvent);
		dateButton.setOnAction(rangeEvent);
		monthlyButton.setOnAction(monthEvent);
		annualButton.setOnAction(annualEvent);
		farmButton.setOnAction(farmEvent);

		// Formating Layout
		farm.getChildren().addAll(farmLabel, farmID, yearFarm, farmButton);
		farm.setSpacing(10);
		farmRep.getChildren().addAll(farmReport, farm, farmError);
		farmRep.setSpacing(20);
		annual.getChildren().addAll(annualLabel, yearAnnual, annualButton);
		annual.setSpacing(10);
		annualRep.getChildren().addAll(annualReport, annual, annualError);
		annualRep.setSpacing(20);
		monthly.getChildren().addAll(monthlyLabel, yearMonthly, comboMonths, monthlyButton);
		monthly.setSpacing(10);
		monthlyRep.getChildren().addAll(monthlyReport, monthly, monthError);
		monthlyRep.setSpacing(20);
		date.getChildren().addAll(dateLabel, startDate, to, endDate, dateButton);
		date.setSpacing(10);
		dateRep.getChildren().addAll(dateReport, date, rangeError);
		dateRep.setSpacing(20);
		top.getChildren().add(label);
		top.setAlignment(Pos.CENTER_LEFT);
		left.getChildren().addAll(farmRep, annualRep);
		left.setAlignment(Pos.TOP_LEFT);
		left.setSpacing(150);
		right.getChildren().addAll(monthlyRep, dateRep);
		right.setAlignment(Pos.TOP_LEFT);
		right.setSpacing(150);

		// Set BorderPane arguments
		start.setTop(top);
		start.setLeft(left);
		start.setRight(right);
		start.setBottom(bottom);
		return scene;
	}

	/**
	 * This creates the annual report scene
	 * 
	 * @param stage - the canvas to create the scene on
	 */
	private Scene annualReport(Stage stage, int year) throws Exception {
		// Gets the data from the driver class and formats it into the table
		List<YearWeight> list = currentInfo.getAnnualReport(year);
		int totalWeight = 0;
		for (int i = 0; i < list.size(); i++) {
			totalWeight += Integer.parseInt(list.get(i).getWeight());
		}
		for (int i = 0; i < list.size(); i++) {
			String weight = list.get(i).getWeight();
			tableData.add(new dataTable(list.get(i).getFarmID(), weight,
					(double) (Math.round(((double) (Integer.parseInt(weight)) / totalWeight) * 10000)) / 100 + ""));
		}

		// Creating JavaFX Controls
		BorderPane start = new BorderPane();
		Scene scene = new Scene(start, 900, 650);
		Button reports = new Button("Reports");
		Label label = new Label("Annual Report");
		Label annual = new Label("Year: " + year);
		TableView table = new TableView();
		TableColumn firstCol = new TableColumn("Farm");
		TableColumn secondCol = new TableColumn("Total Weight");
		TableColumn lastCol = new TableColumn("Year %");
		HBox top = new HBox();
		VBox center = new VBox();

		// Modifying JavaFX Controls
		stage.setTitle(Title + "-Annual Report");
		start.setBackground(background);
		start.setPadding(new Insets(20, 20, 20, 20));
		reports.setStyle("-fx-font-size: 11pt;");
		label.setStyle("-fx-font-size: 22pt;");
		annual.setStyle("-fx-font-size: 12pt;");
		firstCol.setPrefWidth(250);
		secondCol.setPrefWidth(250);
		lastCol.setPrefWidth(250);
		firstCol.setSortable(false);
		lastCol.setSortable(false);
		table.setMaxWidth(770);

		// Modifying Table
		table.getColumns().addAll(firstCol, secondCol, lastCol);
		firstCol.setCellValueFactory(new PropertyValueFactory<farmTable, String>("farm"));
		secondCol.setCellValueFactory(new PropertyValueFactory<farmTable, String>("weight"));
		lastCol.setCellValueFactory(new PropertyValueFactory<farmTable, String>("percentage"));
		table.setItems(tableData);

		// Event Handling
		// Event for report button
		EventHandler<ActionEvent> reportEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				try {
					stage.setScene(sceneReport(stage));
					tableData.clear();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		};

		// Button triggers
		reports.setOnAction(reportEvent);

		// Formating Layout
		center.getChildren().addAll(annual, table, reports);
		center.setPrefWidth(600);
		center.setSpacing(20);
		center.setAlignment(Pos.TOP_CENTER);
		top.getChildren().add(label);
		top.setAlignment(Pos.CENTER_LEFT);

		// Set BorderPane arguments
		start.setTop(top);
		start.setCenter(center);
		start.setBottom(bottom);
		return scene;
	}

	/**
	 * This creates the month report scene
	 * 
	 * @param stage - the canvas to create the scene on
	 */
	private Scene monthReport(Stage stage, int month, String year) throws Exception {
		// Gets the data from the driver class and formats it into the table
		List<MonthWeight> list = currentInfo.getMonthlyReport(year, month + "");
		int totalWeight = 0;
		for (int i = 0; i < list.size(); i++) {
			totalWeight += Integer.parseInt(list.get(i).getWeight());
		}
		for (int i = 0; i < list.size(); i++) { // Adds the data to table
			String weight = list.get(i).getWeight();
			tableData.add(new dataTable(list.get(i).getFarmID(), weight,
					(double) (Math.round(((double) (Integer.parseInt(weight)) / totalWeight) * 10000)) / 100 + ""));
		}

		// Creating JavaFX Controls
		BorderPane start = new BorderPane();
		Scene scene = new Scene(start, 900, 650);
		Button reports = new Button("Reports");
		Label label = new Label("Monthly Report");
		Label monthly = new Label("Year: " + year);
		TableView table = new TableView();
		TableColumn firstCol = new TableColumn("Farm");
		TableColumn secondCol = new TableColumn("Total Weight");
		TableColumn lastCol = new TableColumn("Year %");
		HBox top = new HBox();
		VBox center = new VBox();

		// Modifying JavaFX Controls
		stage.setTitle(Title + "-Month Report");
		start.setBackground(background);
		start.setPadding(new Insets(20, 20, 20, 20));
		reports.setStyle("-fx-font-size: 11pt;");
		label.setStyle("-fx-font-size: 22pt;");
		monthly.setStyle("-fx-font-size: 12pt;");
		firstCol.setPrefWidth(250);
		secondCol.setPrefWidth(250);
		lastCol.setPrefWidth(250);
		firstCol.setSortable(false);
		lastCol.setSortable(false);
		table.setMaxWidth(770);

		// Modifying table
		table.getColumns().addAll(firstCol, secondCol, lastCol);
		firstCol.setCellValueFactory(new PropertyValueFactory<farmTable, String>("farm"));
		secondCol.setCellValueFactory(new PropertyValueFactory<farmTable, String>("weight"));
		lastCol.setCellValueFactory(new PropertyValueFactory<farmTable, String>("percentage"));
		table.setItems(tableData);

		// Event handling
		// Event for report button
		EventHandler<ActionEvent> reportEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				try {
					stage.setScene(sceneReport(stage)); // returns to reports scene
					tableData.clear();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		};

		// Button triggers
		reports.setOnAction(reportEvent);

		// Formating Layout
		center.getChildren().addAll(monthly, table, reports);
		center.setPrefWidth(600);
		center.setSpacing(20);
		center.setAlignment(Pos.TOP_CENTER);
		top.getChildren().add(label);
		top.setAlignment(Pos.CENTER_LEFT);

		// Set BorderPane arguments
		start.setTop(top);
		start.setCenter(center);
		start.setBottom(bottom);
		return scene;
	}

	/**
	 * This creates the range report scene
	 * 
	 * @param stage - the canvas to create the scene on
	 */
	private Scene rangeReport(Stage stage, Date one, Date two) throws Exception {
		// Gets the data from the driver class and formats it into the table
		List<DayWeight> list = currentInfo.getDateRangeReport(one, two);
		int totalWeight = 0;
		for (int i = 0; i < list.size(); i++) {
			totalWeight += Integer.parseInt(list.get(i).getWeight());
		}
		for (int i = 0; i < list.size(); i++) {
			String weight = list.get(i).getWeight();
			tableData.add(new dataTable(list.get(i).getFarmID(), weight,
					(double) (Math.round(((double) (Integer.parseInt(weight)) / totalWeight) * 10000)) / 100 + ""));
		}

		// Data formating
		String[] dateOne = one.toString().split(" ");
		String[] dateTwo = two.toString().split(" ");
		String dateOneFormated = dateOne[0] + " " + dateOne[1] + " " + dateOne[2] + " " + dateOne[5];
		String dateTwoFormated = dateTwo[0] + " " + dateTwo[1] + " " + dateTwo[2] + " " + dateTwo[5];

		// Creating JavaFX Controls
		BorderPane start = new BorderPane();
		Scene scene = new Scene(start, 900, 650);
		Button reports = new Button("Reports");
		Label label = new Label("Date Range Report");
		Label range = new Label(dateOneFormated + " to " + dateTwoFormated);
		TableView table = new TableView();
		TableColumn firstCol = new TableColumn("Farm");
		TableColumn secondCol = new TableColumn("Total Weight");
		TableColumn lastCol = new TableColumn("Year %");
		HBox top = new HBox();
		VBox center = new VBox();

		// Modifying JavaFX Controls
		stage.setTitle(Title + "-Date Range Report");
		start.setBackground(background);
		start.setPadding(new Insets(20, 20, 20, 20));
		reports.setStyle("-fx-font-size: 11pt;");
		label.setStyle("-fx-font-size: 22pt;");
		range.setStyle("-fx-font-size: 12pt;");
		firstCol.setPrefWidth(250);
		secondCol.setPrefWidth(250);
		lastCol.setPrefWidth(250);
		firstCol.setSortable(false);
		lastCol.setSortable(false);
		table.setMaxWidth(770);

		// Modifying table
		table.getColumns().addAll(firstCol, secondCol, lastCol);
		firstCol.setCellValueFactory(new PropertyValueFactory<farmTable, String>("farm"));
		secondCol.setCellValueFactory(new PropertyValueFactory<farmTable, String>("weight"));
		lastCol.setCellValueFactory(new PropertyValueFactory<farmTable, String>("percentage"));
		table.setItems(tableData);

		// Event handling
		// Event for report button
		EventHandler<ActionEvent> reportEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				try {
					stage.setScene(sceneReport(stage)); // returns to report scene
					tableData.clear();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		};

		// Button triggers
		reports.setOnAction(reportEvent);

		// Formating layout
		center.getChildren().addAll(range, table, reports);
		center.setPrefWidth(600);
		center.setSpacing(20);
		center.setAlignment(Pos.TOP_CENTER);
		top.getChildren().add(label);
		top.setAlignment(Pos.CENTER_LEFT);

		// Set BorderPane arguments
		start.setTop(top);
		start.setCenter(center);
		start.setBottom(bottom);
		return scene;
	}

	/**
	 * This creates the farm report scene
	 * 
	 * @param stage - the canvas to create the scene on
	 */
	private Scene farmReport(Stage stage, String farmID, String year) throws Exception {
		// Gets the data from the driver class and formats it into the table
		Farm farmList = new Farm(currentInfo.getSpecificFarm(farmID));
		double[][] data = farmList.getFarmYearData(year);
		for (int i = 0; i < 12; i++) {
			farmData.add(new farmTable(MONTHS[i], (int) data[i][0] + "",
					(double) Math.round((data[i][1] * 100)) / 100 + ""));
		}

		// Create JavaFX Controls
		BorderPane start = new BorderPane();
		Scene scene = new Scene(start, 900, 650);
		Button reports = new Button("Reports");
		Label label = new Label("Farm Report");
		Label farm = new Label("Farm ID: " + farmID + " Year: " + year);
		TableView table = new TableView();
		TableColumn firstCol = new TableColumn("Month");
		TableColumn secondCol = new TableColumn("Milk Weight");
		TableColumn lastCol = new TableColumn("Month %");
		HBox top = new HBox();
		VBox center = new VBox();

		// Modifying JavaFX controls
		stage.setTitle(Title + "-Farm Report");
		start.setBackground(background);
		start.setPadding(new Insets(20, 20, 20, 20));
		reports.setStyle("-fx-font-size: 11pt;");
		label.setStyle("-fx-font-size: 22pt;");
		farm.setStyle("-fx-font-size: 12pt;");
		firstCol.setPrefWidth(250);
		firstCol.setSortable(false);
		secondCol.setPrefWidth(250);
		lastCol.setPrefWidth(250);
		lastCol.setSortable(false);
		table.setMaxWidth(752);
		table.setMaxHeight(330);

		// Modifying table
		table.getColumns().addAll(firstCol, secondCol, lastCol);
		firstCol.setCellValueFactory(new PropertyValueFactory<farmTable, String>("month"));
		secondCol.setCellValueFactory(new PropertyValueFactory<farmTable, String>("milkWeight"));
		lastCol.setCellValueFactory(new PropertyValueFactory<farmTable, String>("percentage"));
		table.setItems(farmData);

		// Event handling
		// Event for report butoon
		EventHandler<ActionEvent> reportEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				try {
					stage.setScene(sceneReport(stage)); // sets scene back to reports
					farmData.clear();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		};

		// Button triggers
		reports.setOnAction(reportEvent);

		// Formatting Layout
		center.getChildren().addAll(farm, table, reports);
		center.setPrefWidth(600);
		center.setSpacing(20);
		center.setAlignment(Pos.TOP_CENTER);
		top.getChildren().add(label);
		top.setAlignment(Pos.CENTER_LEFT);

		// Set BorderPane arguments
		start.setTop(top);
		start.setCenter(center);
		start.setBottom(bottom);
		return scene;
	}

	/**
	 * This creates the modify scene
	 * 
	 * @param stage - the canvas to create the scene on
	 */
	private Scene modify(Stage stage) throws Exception {
		currentInfo = new Driver(files);

		// Gets the data from the driver class and formats it into the table
		List<LogObject> data = currentInfo.getModifyReport();
		for (int i = 0; i < data.size(); i++) {
			LogObject current = data.get(i);
			String[] date = current.getDate().split("-");
			String dateFormated = date[1] + "/" + date[2] + "/" + date[0];
			modifyData.add(new modifyTable(dateFormated, current.getID(), current.getWeight()));
		}

		// Create JavaFX controls
		BorderPane start = new BorderPane();
		Scene scene = new Scene(start, 900, 650);
		Label label = new Label("Add/Edit/Remove");
		Label saved = new Label("Saved!");
		Label file = new Label("File: " + currentInfo.getFileName());
		Label error = new Label("No input or invalid input!");
		TableView<modifyTable> table = new TableView<modifyTable>();
		TableColumn firstCol = new TableColumn("Date");
		TableColumn secondCol = new TableColumn("Farm ID");
		TableColumn lastCol = new TableColumn("Weight");
		DatePicker date = new DatePicker();
		Button add = new Button("Add");
		Button remove = new Button("Remove");
		TextField ID = new TextField();
		TextField weight = new TextField();
		HBox top = new HBox();
		HBox mod = new HBox();

		// Moidfying JavaFX Controls
		menu.setVisible(true);
		save.setVisible(true);
		stage.setTitle(Title + "-Modify");
		start.setBackground(background);
		start.setPadding(new Insets(20, 20, 20, 20));
		saved.setVisible(false);
		saved.setTextFill(Color.LIGHTGREEN);
		label.setStyle("-fx-font-size: 22pt;");
		file.setStyle("-fx-font-size: 12pt;");
		firstCol.setPrefWidth(250);
		secondCol.setPrefWidth(250);
		lastCol.setPrefWidth(250);
		table.setEditable(true);
		table.setMaxWidth(770);
		firstCol.setSortable(false);
		secondCol.setSortable(false);
		lastCol.setSortable(false);
		date.setPromptText("Date");
		ID.setPromptText("Farm ID");
		error.setTextFill(Color.RED);
		error.setVisible(false);
		weight.setPromptText("Weight");
		add.setPrefWidth(101);
		remove.setPrefWidth(101);

		// Modifying table
		table.getColumns().addAll(firstCol, secondCol, lastCol);
		firstCol.setCellValueFactory(new PropertyValueFactory<farmTable, String>("date"));
		secondCol.setCellValueFactory(new PropertyValueFactory<farmTable, String>("farmID"));
		lastCol.setCellValueFactory(new PropertyValueFactory<farmTable, String>("weight"));
		table.setItems(modifyData);
		firstCol.setCellFactory(TextFieldTableCell.forTableColumn());
		secondCol.setCellFactory(TextFieldTableCell.forTableColumn());
		lastCol.setCellFactory(TextFieldTableCell.forTableColumn());

		// Event Handling
		// Event for menu button
		EventHandler<ActionEvent> menuEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				currentInfo = null;
				files.clear();
				modifyData.clear();
				stage.setScene(SceneOne(stage)); // returns to start scene
			}
		};

		// Event for add button
		EventHandler<ActionEvent> addEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				if (date.getValue() != null && !ID.getText().equals("") && !weight.getText().equals("")) { // if data
																											// fields
																											// aren't
																											// blank
					error.setVisible(false);
					String dateText = date.getEditor().getText();
					modifyData.add(new modifyTable(dateText, ID.getText(), weight.getText())); // add new data
					date.setValue(null);
					ID.clear();
					weight.clear();
				} else {
					error.setVisible(true);
				}
			}
		};

		// Event for remove button
		EventHandler<ActionEvent> removeEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				modifyTable selected = table.getSelectionModel().getSelectedItem();
				table.getItems().remove(selected); // remove selected item
			}
		};

		// Event for save button
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
					for (int i = 0; i < modifyData.size(); i++) { // Writes each item to file
						bWriter.newLine();
						String[] date = modifyData.get(i).getDate().split("/");
						String ID = modifyData.get(i).getFarmID();
						String weight = modifyData.get(i).getWeight();
						String dateFormat = date[2] + "-" + date[0] + "-" + date[1];
						bWriter.write(dateFormat + ",Farm " + ID + "," + weight);
					}
					bWriter.close();
					writer.close();
					saved.setVisible(true);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		};

		// Button triggers
		menu.setOnAction(menuEvent);
		save.setOnAction(saveEvent);
		remove.setOnAction(removeEvent);
		add.setOnAction(addEvent);

		// Formating layout
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

		// Set BorderPane arguments
		start.setTop(top);
		start.setCenter(center);
		start.setBottom(bottom);
		return scene;

	}

	// Data for farm table
	private final ObservableList<farmTable> farmData = FXCollections.observableArrayList();
	// Data for modify table
	private final ObservableList<modifyTable> modifyData = FXCollections.observableArrayList();
	// Data for range table
	private final ObservableList<dataTable> tableData = FXCollections.observableArrayList();

	/**
	 * This class creates a table for the farm report
	 */
	public static class farmTable {

		private final SimpleStringProperty month;
		private final SimpleStringProperty milkWeight;
		private final SimpleStringProperty percentage;

		/**
		 * Constructor for farm table
		 * 
		 * @param month      - String containing month
		 * @param milkweight - String containing weight
		 * @param percentage - String containing percentage
		 */
		private farmTable(String month, String milkweight, String percentage) {
			this.month = new SimpleStringProperty(month);
			this.milkWeight = new SimpleStringProperty(milkweight);
			this.percentage = new SimpleStringProperty(percentage);
		}

		/**
		 * Gets the month
		 * 
		 * @return - month
		 */
		public String getMonth() {
			return month.get();
		}

		/**
		 * Sets the month
		 * 
		 * @param month - String month
		 */
		public void setMonth(String month) {
			this.month.set(month);
		}

		/**
		 * Gets the weight
		 * 
		 * @return - weight
		 */
		public String getMilkWeight() {
			return milkWeight.get();
		}

		/**
		 * Sets the weight
		 * 
		 * @param milkWeight - String weight
		 */
		public void setMilkWeight(String milkWeight) {
			this.milkWeight.set(milkWeight);
		}

		/**
		 * Gets the percentage
		 * 
		 * @return - percentage
		 */
		public String getPercentage() {
			return percentage.get();
		}

		/**
		 * Sets the percentage
		 * 
		 * @param percentage - String percentage
		 */
		public void setPercentage(String percentage) {
			this.percentage.set(percentage);
		}
	}

	/**
	 * This class creates a table for the annual report
	 */
	public static class dataTable {

		private final SimpleStringProperty farm;
		private final SimpleStringProperty weight;
		private final SimpleStringProperty percentage;

		/**
		 * Constructor for annual table
		 * 
		 * @param farm       - String containing farm id
		 * @param weight     - String containing weight
		 * @param percentage - String containing percentage
		 */
		private dataTable(String farm, String weight, String percentage) {
			this.farm = new SimpleStringProperty(farm);
			this.weight = new SimpleStringProperty(weight);
			this.percentage = new SimpleStringProperty(percentage);
		}

		/**
		 * Gets farmID
		 * 
		 * @return - farmID
		 */
		public String getFarm() {
			return farm.get();
		}

		/**
		 * Sets farmID
		 * 
		 * @param farm - String id
		 */
		public void setFarm(String farm) {
			this.farm.set(farm);
		}

		/**
		 * Gets weight
		 * 
		 * @return - weight
		 */
		public String getWeight() {
			return weight.get();
		}

		/**
		 * Sets weight
		 * 
		 * @param weight - String weight
		 */
		public void setWeight(String weight) {
			this.weight.set(weight);
		}

		/**
		 * Gets percentage
		 * 
		 * @return - percentage
		 */
		public String getPercentage() {
			return percentage.get();
		}

		/**
		 * Sets percentage
		 * 
		 * @param percentage - String percentage
		 */
		public void setPercentage(String percentage) {
			this.percentage.set(percentage);
		}
	}

	/**
	 * This class creates a table for modification
	 */
	public static class modifyTable {

		private final SimpleStringProperty date;
		private final SimpleStringProperty farmID;
		private final SimpleStringProperty weight;

		/**
		 * Constructor for modify table
		 * 
		 * @param date   - String containing date
		 * @param farmID - String containing farmID
		 * @param weight - String containing weight
		 */
		public modifyTable(String date, String farmID, String weight) {
			this.date = new SimpleStringProperty(date);
			this.farmID = new SimpleStringProperty(farmID);
			this.weight = new SimpleStringProperty(weight);
		}

		/**
		 * Gets the date
		 * 
		 * @return - date
		 */
		public String getDate() {
			return date.get();
		}

		/**
		 * Sets the date
		 * 
		 * @param date - String date
		 */
		public void setDate(String date) {
			this.date.set(date);
		}

		/**
		 * Gets the id
		 * 
		 * @return - id
		 */
		public String getFarmID() {
			return farmID.get();
		}

		/**
		 * Sets the id
		 * 
		 * @param farmID - String id
		 */
		public void setFarmID(String farmID) {
			this.farmID.set(farmID);
		}

		/**
		 * Gets the weight
		 * 
		 * @return - weight
		 */
		public String getWeight() {
			return weight.get();
		}

		/**
		 * Sets the weight
		 * 
		 * @param weight - String weight
		 */
		public void setWeight(String weight) {
			this.weight.set(weight);
		}
	}

	/**
	 * Main method to run GUI made from JavaFX
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);

	}
}
