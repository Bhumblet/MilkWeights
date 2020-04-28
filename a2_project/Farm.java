/**
 * Farm.java created in a2_project
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

import java.util.List;

/**
 * Farm - Represents milk weight data for a given Farm
 */
public class Farm {

	// Value of ID for the given farm
	private String ID;
	// List of LogObjects used for storing data for a given farm
	private List<LogObject> farmData;

	/**
	 * Only constructor that takes a list of LogObjects as the parameter
	 * 
	 * @param farmData - the list of LogObjects used to store data for a given farm
	 */
	public Farm(List<LogObject> farmData) {
		this.ID = farmData.get(0).getID();
		this.farmData = farmData;
	}

	/**
	 * 
	 */
	public double[][] getFarmYearData(String year) {
		double[][] months = new double[12][2];
		int yearWeight = 0;
		for (int i = 0; i < months.length; i++) {
			for (int n = 0; n < months[i].length; n++) {
				months[i][n] = 0;
			}
		}
		for (int i = 0; i < farmData.size(); i++) {
			String farmDate = farmData.get(i).getDate();
			String[] date = farmDate.split("-");
			int month = Integer.parseInt(date[1]);
			if (Integer.parseInt(farmDate.substring(0, 4)) == Integer.parseInt(year)) {
				months[month - 1][0] += Integer.parseInt(farmData.get(i).getWeight());
			}
		}
		for (int i = 0; i < months.length; i++) {
			yearWeight += months[i][0];
		}
		for (int i = 0; i < 12; i++) {
			months[i][1] = (months[i][0] / yearWeight) * 100;
		}
		return months;
	}

	/**
	 * Getter for the farm ID of the given farm
	 * 
	 * @return ID of the farm
	 */
	public String getID() {
		return ID;
	}

	/**
	 * Setter for the farm ID of the given farm
	 * 
	 * @param ID - the new ID for the farm
	 */
	public void setID(String iD) {
		ID = iD;
	}
}
