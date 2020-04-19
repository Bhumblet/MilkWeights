/**
 * DayWeight.java created by johnw on Surface Pro 6 in a2_project
 * 
 * Author:	 John Wirth (jjwirth2@wisc.edu)
 * Date:	 Apr 19, 2020
 * 
 * Course:	 CS400
 * Semester: Spring 2020
 * Lecture:	 001
 * 
 * IDE:		 Eclipse IDE for Java Developers
 * Version:	 2019-12 (4.14.0)
 * Build id: 20191212-1212
 * 
 * Device:	 J-Surface
 * OS:		 Windows 10 Home
 * Version:	 1903
 * OS Build: 18362.592
 *
 * List Collaborators: Name, email@wisc.edu, lecture number
 * 
 * Other Credits: describe other sources (web sites or people)
 * 
 * Known Bugs: describe known unresolved bugs here
 */
package a2_project;

/**
 * DayWeight - TODO Describe purpose of this user-defined type
 * @author wirth (2020)
 *
 */
public class DayWeight {

	private double totalDayWeight;
	
	public DayWeight(double totalDayWeight) {
		this.totalDayWeight = totalDayWeight;
	}
	
	/**
	 * @param args
	 */
	public void setTotalDayWeight(double totalDayWeight) {
		this.totalDayWeight = totalDayWeight;
	}

	public double getTotalDayWeight() {
		return this.totalDayWeight;
	}
	
}
