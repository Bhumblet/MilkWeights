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
 * LogObject - Represents the milk weight data in a general sense to help
 * display in the GUI easier
 * 
 * @author wirth (2020)
 *
 */
public class LogObject {

	// Value of farm ID for the LogObject
	private String ID;
	// Value of date of milk weight data for the LogObject
	private String date;
	// Value of the milk weight for the LogObject
	private String weight;

	/**
	 * Only constructor for the LogObject that uses three parameters
	 * 
	 * @param date   - the date this data will represent
	 * @param ID     - the farm ID connected to this data
	 * @param weight - the total milk weight of this data
	 */
	public LogObject(String date, String ID, String weight) {
		this.setID(ID);
		this.setDate(date);
		this.setWeight(weight);
	}

	/**
	 * Getter for the total milk weight of the LogObject
	 * 
	 * @return total milk weight
	 */
	public String getWeight() {
		return weight;
	}

	/**
	 * Setter for the total milk weight of the LogObject
	 * 
	 * @param weight - the new milk weight
	 */
	public void setWeight(String weight) {
		this.weight = weight;
	}

	/**
	 * Getter for the ID of the given LogObject
	 * 
	 * @return the ID of the given LogObject
	 */
	public String getID() {
		return ID;
	}

	/**
	 * Setter for the ID of the given LogObject
	 * 
	 * @param iD - the new ID for the given LogObject
	 */
	public void setID(String iD) {
		ID = iD;
	}

	/**
	 * Getter for the data of the given LogObject
	 * 
	 * @return the data of the given LogObject
	 */
	public String getDate() {
		return date;
	}

	/**
	 * Setter for the date of the given LogObject
	 * 
	 * @param date - the new date for the given LogObject
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * Compares two farm IDs for a given farm and returns true if they are the same
	 * ID
	 * 
	 * @param two - the second farmID you want to compare to the original for the
	 *            given farm
	 * @return true if the farm IDs are the same
	 */
	public boolean compare(LogObject two) {
		if (Integer.parseInt(this.getID()) == Integer.parseInt(two.getID())) {
			return true;
		}
		return false;
	}

}
