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

	// Value of farm ID for the given farm
	private String farmID;
	// Represents the milk weight value for a given day
	private String weight;

	/**
	 * Only constructor for a DayWeight object that takes total weight for a day as
	 * a parameter
	 * 
	 * @param farmID - farmID
	 * @param weight - the total milk weight
	 */
	public DayWeight(String farmID, String weight) {
		this.setFarmID(farmID);
		this.setWeight(weight);
	}

	/**
	 * Getter for farm ID
	 * 
	 * @return the FarmID
	 */
	public String getFarmID() {
		return farmID;
	}

	/**
	 * Setter for farmID
	 * 
	 * @param farmId - farmId representing the farm
	 */
	public void setFarmID(String farmID) {
		this.farmID = farmID;
	}

	/**
	 * Getter for milk weight for a given day
	 * 
	 * @return the value of milk weight for a given day
	 */
	public String getWeight() {
		return weight;
	}

	/**
	 * Setter for milk weight for a given day
	 * 
	 * @param totalDayWeight - the milk weight we want to change the current milk
	 *                       weight to for a given day
	 */
	public void setWeight(String weight) {
		this.weight = weight;
	}
	
	/**
	 * Compares two farm IDs for a given Day and returns true if they are the same
	 * ID
	 * 
	 * @param day - the second farmID you want to compare to the original for the
	 *            given month
	 * @return true if the farm IDs are the same
	 */
	public boolean compare(DayWeight two) {
		if (Integer.parseInt(this.getFarmID()) == Integer.parseInt(two.getFarmID())) {
			return true;
		}
		return false;
	}
}
