/**
 * Farm.java created by johnw on Surface Pro 6 in a2_project
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

import java.util.HashMap;
import java.util.List;

/**
 * Farm - TODO Describe purpose of this user-defined type
 * @author wirth (2020)
 *
 */
public class Farm {

	private String ID;
	private List<LogObject> farmData; 
	/**
	 * @param args
	 */
	public Farm(List<LogObject> farmData) {
		this.ID = farmData.get(0).getID();
		this.farmData = farmData;
	}
	
	public double[][] getFarmYearData(String year) {
		double[][] months = new double[12][2];
		int yearWeight = 0;
		for(int i = 0; i < months.length; i++) {
			for(int n = 0; n < months[i].length; n++) {
				months[i][n] = 0;
			}
		}
		for(int i = 0; i < farmData.size(); i++) {
			String farmDate = farmData.get(i).getDate();
			String[] date = farmDate.split("-");
			int month = Integer.parseInt(date[1]);
			if(Integer.parseInt(farmDate.substring(0,4)) == Integer.parseInt(year)) {
				months[month - 1][0] += Integer.parseInt(farmData.get(i).getWeight());
			}
		}
		for(int i = 0; i < months.length; i++) {
			yearWeight += months[i][0];
		}
		for(int i = 0; i < 12; i++) {
			months[i][1] = (months[i][0]/yearWeight) * 100;
		}
		return months;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}
}
