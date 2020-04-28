/**
 * LogObject.java created in a2_project
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
 * YearWeight - Represents total weight for a given year and data for milk
 * weights for that given year
 */
public class YearWeight {

	// Value of farm ID for the given farm
	private String farmID;
	// Value of milk weight for the given year of the given farm
	private String weight;
	// Value of the year to store data
	private String year;

	/**
	 * Only constructor that takes farm ID, total milk weight, and year as
	 * parameters
	 * 
	 * @param farmID - the farm ID used for the milk weight data for the given year
	 * @param weight - the total milk weight for the given year
	 * @param year   - the year to store the milk weight data under
	 */
	public YearWeight(String farmID, String weight, String year) {
		this.setFarmID(farmID);
		this.setWeight(weight);
		this.setYear(year);
	}

	/**
	 * Getter for the farm ID
	 * 
	 * @return the farm ID for this data
	 */
	public String getFarmID() {
		return farmID;
	}

	/**
	 * Setter for the farm ID
	 * 
	 * @param farmID - the new farmID for the data
	 */
	public void setFarmID(String farmID) {
		this.farmID = farmID;
	}

	/**
	 * Getter for the total milk weight
	 * 
	 * @return total milk weight for a given year
	 */
	public String getWeight() {
		return weight;
	}

	/**
	 * Setter for the total milk weight
	 * 
	 * @param weight - the new total milk weight for the given year
	 */
	public void setWeight(String weight) {
		this.weight = weight;
	}

	/**
	 * Compares two farm IDs for a given year and returns true if they are the same
	 * ID
	 * 
	 * @param two - the second farmID you want to compare to the original for the
	 *            given year
	 * @return true if the farm IDs are the same
	 */
	public boolean compare(YearWeight two) {
		if (Integer.parseInt(this.getFarmID()) == Integer.parseInt(two.getFarmID())) {
			return true;
		}
		return false;
	}

	/**
	 * Getter for the year
	 * 
	 * @return the year of the data
	 */
	public String getYear() {
		return year;
	}

	/**
	 * Setter for the year
	 * 
	 * @param year - the new year this data should represent
	 */
	public void setYear(String year) {
		this.year = year;
	}

}
