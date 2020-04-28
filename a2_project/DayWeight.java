/**
 * DayWeight.java created in a2_project
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

/**
 * DayWeight - Used to represent data for milk weight of a single day
 *
 */
public class DayWeight {

	// Represents the milk weight value for a given day
	private double totalDayWeight;

	/**
	 * Only constructor for a DayWeight object that takes total weight for a day as
	 * a parameter
	 * 
	 * @param totalDayWeight - the total milk weight for that given day
	 */
	public DayWeight(double totalDayWeight) {
		this.totalDayWeight = totalDayWeight;
	}

	/**
	 * Setter for milk weight for a given day
	 * 
	 * @param totalDayWeight - the milk weight we want to change the current milk
	 *                       weight to for a given day
	 */
	public void setTotalDayWeight(double totalDayWeight) {
		this.totalDayWeight = totalDayWeight;
	}

	/**
	 * Getter for milk weight for a given day
	 * 
	 * @return the value of milk weight for a given day
	 */
	public double getTotalDayWeight() {
		return this.totalDayWeight;
	}

}
