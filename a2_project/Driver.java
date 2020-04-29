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

	private double totalWeight;
	private List<LinkedList<LogObject>> farmSorted;
	private List<File> files;
	
	/**
	 * 
	 */
	public Driver(List<File> files) throws Exception {
		farmSorted = new LinkedList<LinkedList<LogObject>>();
		this.files = files;
		for(int i = 0; i < files.size(); i++) {
			Scanner scan = new Scanner(files.get(i));
			while(scan.hasNext()) {
				String info = scan.nextLine();
				String[] seperated = new String[3];
				seperated = info.split(",");
				seperated[1] = seperated[1].substring(5);
				LogObject current = new LogObject(seperated[0], seperated[1], seperated[2]);
				if(!seperated[0].equals("date")) {
					if(!farmSorted.isEmpty()) {
						for(int n = 0; n < farmSorted.size(); n++) {
							if(current != null && current.compare(farmSorted.get(n).get(0))) {
								farmSorted.get(n).add(current);
								current = null;
							}
						}
					}
					if(current != null) {
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
	 * 
	 */
	public boolean containsFarm(String ID, String year) {
		for(int i = 0; i < farmSorted.size(); i++) {
			if(Integer.parseInt(farmSorted.get(i).get(0).getID()) == Integer.parseInt(ID)){
				for(int n = 0; n < farmSorted.get(i).size(); n++) {
					String farm = farmSorted.get(i).get(n).getDate();
					if(Integer.parseInt(farm.substring(0,4)) == Integer.parseInt(year)){
						return true;
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * 
	 */
	public boolean containsYear(int year) {
		for(int i = 0; i < farmSorted.size(); i++) {
			for(int n = 0; n < farmSorted.get(i).size(); n++) {
				String farm = farmSorted.get(i).get(n).getDate();
				if(Integer.parseInt(farm.substring(0,4)) == year) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * 
	 */
	public boolean containsMonth(int month, String year) {
		for(int i = 0; i < farmSorted.size(); i++) {
			for(int n = 0; n < farmSorted.get(i).size(); n++) {
				String farm = farmSorted.get(i).get(n).getDate();
				String[] farmSplit = farm.split("-");
				String farmYear = farmSplit[0];
				String farmMonth = farmSplit[1];
				if(farmYear.equals(year) && farmMonth.equals(month + "")) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean containsDateRangeData(Date dateOne, Date dateTwo) throws ParseException {
		for(int i = 0; i < farmSorted.size(); i++) {
			for(int n = 0; n < farmSorted.get(i).size(); n++) {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				Date current = format.parse(farmSorted.get(i).get(n).getDate());
				if(current.compareTo(dateOne) >= 0 && current.compareTo(dateTwo) <= 0) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * 
	 */
	public List<LogObject> getModifyReport(){
		List<LogObject> list = new LinkedList<LogObject>();
		for(int i = 0; i < farmSorted.size(); i++) {
			for(int n = 0; n < farmSorted.get(i).size(); n++) {
				list.add(farmSorted.get(i).get(n));
			}
		}
		return list;
	}
	
	/**
	 * 
	 */
	public List<LogObject> getSpecificFarm(String ID){
		for(int i = 0; i < farmSorted.size(); i++) {
			if(farmSorted.get(i).get(0).getID().equals(ID)) {
				return farmSorted.get(i);
			}
		}
		return null;	
	}
	
	/**
	 * 
	 */
	public List<YearWeight> getAnnualReport(int year){
		List<LogObject> list = new LinkedList<LogObject>();
		for(int i = 0; i < farmSorted.size(); i++) {
			for(int n = 0; n < farmSorted.get(i).size(); n++) {
				String farm = farmSorted.get(i).get(n).getDate();
				if(Integer.parseInt(farm.substring(0,4)) == year) {
					list.add(farmSorted.get(i).get(n));
				}
			}
		}
		List<YearWeight> listSorted = new LinkedList<YearWeight>();
		for(int i = 0; i < list.size(); i++) {
			YearWeight current = new YearWeight(list.get(i).getID(), list.get(i).getWeight(), list.get(i).getDate().substring(0, 4));
			for(int n = 0; n < listSorted.size(); n++) {
				if(listSorted.get(n).compare(current)) {
					int listWeight = Integer.parseInt(listSorted.get(n).getWeight());
					int currentWeight = Integer.parseInt(current.getWeight());
					listSorted.get(n).setWeight((listWeight + currentWeight) + "");
					current = null;
				}
			}
			if(current != null) {
				listSorted.add(current);
			}
		}
		return listSorted;
	}
	
	/**
	 * 
	 */
	public List<MonthWeight> getMonthlyReport(String year, String month)	{
		List<LogObject> list = new LinkedList<LogObject>();
		for(int i = 0; i < farmSorted.size(); i++) {
			for(int n = 0; n < farmSorted.get(i).size(); n++) {
				String farm = farmSorted.get(i).get(n).getDate();
				String[] farmSplit = farm.split("-");
				String yearFarm = farmSplit[0];
				String monthFarm = farmSplit[1];
				if(yearFarm.equals(year) && monthFarm.equals(month)) {
					list.add(farmSorted.get(i).get(n));
				}
			}
		}
		List<MonthWeight> listSorted = new LinkedList<MonthWeight>();
		for(int i = 0; i < list.size(); i++) {
			MonthWeight current = new MonthWeight(list.get(i).getID(), list.get(i).getWeight());
			for(int n = 0; n < listSorted.size(); n++) {
				if(listSorted.get(n).compare(current)) {
					int listWeight = Integer.parseInt(listSorted.get(n).getWeight());
					int currentWeight = Integer.parseInt(current.getWeight());
					listSorted.get(n).setWeight((listWeight + currentWeight) + "");
					current = null;
				}
			}
			if(current != null) {
				listSorted.add(current);
			}
		}
		return listSorted;
	}
	
	/**
	 * @throws ParseException 
	 * 
	 */
	public List<DayWeight> getDateRangeReport(Date one, Date two) throws ParseException{
		List<LogObject> list = new LinkedList<LogObject>();
		for(int i = 0; i < farmSorted.size(); i++) {
			for(int n = 0; n < farmSorted.get(i).size(); n++) {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				Date current = format.parse(farmSorted.get(i).get(n).getDate());
				if(current.compareTo(one) >= 0 && current.compareTo(two) <= 0) {
					list.add(farmSorted.get(i).get(n));
				}
			}
		}
		List<DayWeight> listSorted = new LinkedList<DayWeight>();
		for(int i = 0; i < list.size(); i++) {
			DayWeight current = new DayWeight(list.get(i).getID(), list.get(i).getWeight());
			for(int n = 0; n < listSorted.size(); n++) {
				if(listSorted.get(n).compare(current)) {
					int listWeight = Integer.parseInt(listSorted.get(n).getWeight());
					int currentWeight = Integer.parseInt(current.getWeight());
					listSorted.get(n).setWeight((listWeight + currentWeight) + "");
					current = null;
				}
			}
			if(current != null) {
				listSorted.add(current);
			}
		}
		return listSorted;
	}
	
	/**
	 * 
	 */
	public String getFileName() {
		return files.get(0).getName().toString();
	}
}
