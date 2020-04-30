/**
 * Driver.java created in a2_project
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

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Driver - Class used to drive the rest of the program and GUI
 */
public class Driver {

	private double totalWeight; // Creates a weight variable for the farm
	private List<LinkedList<LogObject>> farmSorted; // A list of the farm's milk weight data
	private List<File> files; // Lists the

	/**
	 * Driver Class Constructor: Reads the files and fills the farmSorted list
	 * 
	 * @param files - List of files to be read
	 * @throws Exception if an exception is found
	 */
	public Driver(List<File> files) throws Exception {
		farmSorted = new LinkedList<LinkedList<LogObject>>(); // ins
		this.files = files;
		for (int i = 0; i < files.size(); i++) { // Loops through the files list
			Scanner scan = new Scanner(files.get(i)); // Creates a scanner object
			while (scan.hasNext()) { // Makes sure there is more data to be read
				String info = scan.nextLine();
				String[] seperated = new String[3];
				seperated = info.split(",");
				seperated[1] = seperated[1].substring(5);
				LogObject current = new LogObject(seperated[0], seperated[1], seperated[2]);
				if (!seperated[0].equals("date")) {
					if (!farmSorted.isEmpty()) {
						for (int n = 0; n < farmSorted.size(); n++) {
							if (current != null && current.compare(farmSorted.get(n).get(0))) {
								farmSorted.get(n).add(current);
								current = null;
							}
						}
					}
					if (current != null) { // Adds current data to the farmSorted list if it is not null
						LinkedList<LogObject> temp = new LinkedList<LogObject>();
						temp.add(current);
						farmSorted.add(temp);
					}
				}
			}
			scan.close();
		}
	}

	/**
	 * Checks if the farm exists based on the ID and year
	 * 
	 * @param ID   - ID of the farm
	 * @param year - year wanted to check
	 * @return true if it contains the farm, false otherwise
	 */
	public boolean containsFarm(String ID, String year) {
		for (int i = 0; i < farmSorted.size(); i++) {
			if (Integer.parseInt(farmSorted.get(i).get(0).getID()) == Integer.parseInt(ID)) { // Checks if the inputed ID matches with any of the farm ID's in the farm sorted list
				for (int n = 0; n < farmSorted.get(i).size(); n++) {
					String farm = farmSorted.get(i).get(n).getDate();
					if (Integer.parseInt(farm.substring(0, 4)) == Integer.parseInt(year)) { // Checks if the inputed year matches with any of the years for the milk weight data
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * Sees if the year exists in the farm data for the milk weight
	 * 
	 * @param year - checks if this year has milk weight data associated to it
	 * @return true if the year exists
	 */
	public boolean containsYear(int year) {
		for (int i = 0; i < farmSorted.size(); i++) {
			for (int n = 0; n < farmSorted.get(i).size(); n++) {
				String farm = farmSorted.get(i).get(n).getDate();
				if (Integer.parseInt(farm.substring(0, 4)) == year) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Sees if the month based on the year has milk weight data
	 * 
	 * @param month - checks if this month has milk weight data associated to it
	 * @param year  - checks if this year has milk weight data associated to it
	 * @return true if the month has data based on the year
	 */
	public boolean containsMonth(int month, String year) {
		for (int i = 0; i < farmSorted.size(); i++) {
			for (int n = 0; n < farmSorted.get(i).size(); n++) {
				String farm = farmSorted.get(i).get(n).getDate();
				String[] farmSplit = farm.split("-");
				String farmYear = farmSplit[0];
				String farmMonth = farmSplit[1];
				if (farmYear.equals(year) && farmMonth.equals(month + "")) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Sees if the date range based on the year has milk weight data
	 * 
	 * @param dateOne - minimum date for data
	 * @param dateTwo - maximum date for the data
	 * @return true if the date range has data based on the year
	 */
	public boolean containsDateRangeData(Date dateOne, Date dateTwo) throws ParseException {
		for (int i = 0; i < farmSorted.size(); i++) {
			for (int n = 0; n < farmSorted.get(i).size(); n++) {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				Date current = format.parse(farmSorted.get(i).get(n).getDate());
				if (current.compareTo(dateOne) >= 0 && current.compareTo(dateTwo) <= 0) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Gets the modify report
	 * 
	 * @return the Modified report
	 */
	public List<LogObject> getModifyReport() {
		List<LogObject> list = new LinkedList<LogObject>();
		for (int i = 0; i < farmSorted.size(); i++) {
			for (int n = 0; n < farmSorted.get(i).size(); n++) {
				list.add(farmSorted.get(i).get(n));
			}
		}
		return list;
	}

	/**
	 * Gets a specified farm based on ID of the farm
	 * 
	 * @param ID - ID of the specified farm
	 * @return the data of the farm wanted
	 */
	public List<LogObject> getSpecificFarm(String ID) {
		for (int i = 0; i < farmSorted.size(); i++) {
			if (farmSorted.get(i).get(0).getID().equals(ID)) {
				return farmSorted.get(i);
			}
		}
		return null;
	}

	/**
	 * gets the annual report of milk weight data
	 * 
	 * @param year - the specified year wanted for the annual report
	 * @return the annual report
	 */
	public List<YearWeight> getAnnualReport(int year) {
		List<LogObject> list = new LinkedList<LogObject>();
		for (int i = 0; i < farmSorted.size(); i++) {
			for (int n = 0; n < farmSorted.get(i).size(); n++) {
				String farm = farmSorted.get(i).get(n).getDate();
				if (Integer.parseInt(farm.substring(0, 4)) == year) {
					list.add(farmSorted.get(i).get(n));
				}
			}
		}
		List<YearWeight> listSorted = new LinkedList<YearWeight>();
		for (int i = 0; i < list.size(); i++) {
			YearWeight current = new YearWeight(list.get(i).getID(), list.get(i).getWeight(),
					list.get(i).getDate().substring(0, 4));
			for (int n = 0; n < listSorted.size(); n++) {
				if (listSorted.get(n).compare(current)) {
					int listWeight = Integer.parseInt(listSorted.get(n).getWeight());
					int currentWeight = Integer.parseInt(current.getWeight());
					listSorted.get(n).setWeight((listWeight + currentWeight) + "");
					current = null;
				}
			}
			if (current != null) {
				listSorted.add(current);
			}
		}
		return listSorted;
	}

	/**
	 * gets the monthly report of milk weight data
	 * 
	 * @param year  - the specified year wanted for the monthly report
	 * @param month - the specified month wanted for the monthly report
	 * @return the monthly report
	 */
	public List<MonthWeight> getMonthlyReport(String year, String month) {
		List<LogObject> list = new LinkedList<LogObject>();
		for (int i = 0; i < farmSorted.size(); i++) {
			for (int n = 0; n < farmSorted.get(i).size(); n++) {
				String farm = farmSorted.get(i).get(n).getDate();
				String[] farmSplit = farm.split("-");
				String yearFarm = farmSplit[0];
				String monthFarm = farmSplit[1];
				if (yearFarm.equals(year) && monthFarm.equals(month)) {
					list.add(farmSorted.get(i).get(n)); // fills list if the farm matches the month and year
				}
			}
		}
		List<MonthWeight> listSorted = new LinkedList<MonthWeight>();
		for (int i = 0; i < list.size(); i++) { // sorts the milk weight data in the list
			MonthWeight current = new MonthWeight(list.get(i).getID(), list.get(i).getWeight());
			for (int n = 0; n < listSorted.size(); n++) {
				if (listSorted.get(n).compare(current)) {
					int listWeight = Integer.parseInt(listSorted.get(n).getWeight());
					int currentWeight = Integer.parseInt(current.getWeight());
					listSorted.get(n).setWeight((listWeight + currentWeight) + "");
					current = null;
				}
			}
			if (current != null) {
				listSorted.add(current);
			}
		}
		return listSorted;
	}

	/**
	 * Gets the date range report
	 * 
	 * @param one - minimum date for the data wanted
	 * @param two - maximum date for the data wanted
	 * @return the date range report
	 * @throws ParseException
	 */
	public List<DayWeight> getDateRangeReport(Date one, Date two) throws ParseException {
		List<LogObject> list = new LinkedList<LogObject>();
		for (int i = 0; i < farmSorted.size(); i++) {
			for (int n = 0; n < farmSorted.get(i).size(); n++) {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				Date current = format.parse(farmSorted.get(i).get(n).getDate());
				if (current.compareTo(one) >= 0 && current.compareTo(two) <= 0) {
					list.add(farmSorted.get(i).get(n)); // adds the milk weight data if it falls under the date range
				}
			}
		}
		List<DayWeight> listSorted = new LinkedList<DayWeight>();
		for (int i = 0; i < list.size(); i++) { // sorts the milk weight data in the list
			DayWeight current = new DayWeight(list.get(i).getID(), list.get(i).getWeight());
			for (int n = 0; n < listSorted.size(); n++) {
				if (listSorted.get(n).compare(current)) {
					int listWeight = Integer.parseInt(listSorted.get(n).getWeight());
					int currentWeight = Integer.parseInt(current.getWeight());
					listSorted.get(n).setWeight((listWeight + currentWeight) + "");
					current = null;
				}
			}
			if (current != null) {
				listSorted.add(current);
			}
		}
		return listSorted;
	}

	/**
	 * Gets the file name
	 * 
	 * @return the file name
	 */
	public String getFileName() {
		return files.get(0).getName().toString();
	}
}
