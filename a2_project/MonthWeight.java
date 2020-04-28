/**
 * MonthWeight.java created in a2_project
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
 * MonthWeight - Used to represent and store milk weight data for a single month
 */
public class MonthWeight {

	// Value for a farm ID of a single farm
	private String farmID;
	// Value of total milk weight for a given month
	private String weight;

	/**
	 * Only constructor for MonthWeight object that uses farm ID and total milk
	 * weight as parameters
	 * 
	 * @param farmID - the farm ID for a given farm
	 * @param weight - the total weight for a given month
	 */
	public MonthWeight(String farmID, String weight) {
		this.setFarmID(farmID);
		this.setWeight(weight);
	}

	/**
	 * Getter for the farm ID of a farm for the given month
	 *
	 * @return the farm ID of the given farm
	 */
	public String getFarmID() {
		return farmID;
	}

	/**
	 * Setter for the farm ID of a farm for the given month
	 * 
	 * @param farmID - the new farm ID of the farm
	 */
	public void setFarmID(String farmID) {
		this.farmID = farmID;
	}

	/**
	 * Getter for the total milk weight for the given month
	 * 
	 * @return the total milk weight for the given month
	 */
	public String getWeight() {
		return weight;
	}

	/**
	 * Setter for the total milk weight for the given month
	 * 
	 * @param weight - the new milk weight for the given month
	 */
	public void setWeight(String weight) {
		this.weight = weight;
	}

	/**
	 * Compares two farm IDs for a given month and returns true if they are the same
	 * ID
	 * 
	 * @param two - the second farmID you want to compare to the original for the
	 *            given month
	 * @return true if the farm IDs are the same
	 */
	public boolean compare(MonthWeight two) {
		if (Integer.parseInt(this.getFarmID()) == Integer.parseInt(two.getFarmID())) {
			return true;
		}
		return false;
	}
}
