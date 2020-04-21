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

/**
 * Farm - TODO Describe purpose of this user-defined type
 * @author wirth (2020)
 *
 */
public class Farm {

	private String ID;
	private double totalFarmWeight;
	private HashMap<Integer,YearWeight> yearlyWeight;
	/**
	 * @param args
	 */
	public Farm(String ID) {
		this.ID = ID;
		totalFarmWeight = 0;
		yearlyWeight = new HashMap<>();
	}

	public void setTotalFarmWeight(double totalFarmWeight) {
		this.totalFarmWeight = totalFarmWeight;
	}
	
	public double getTotalFarmWeight() {
		totalFarmWeight=0;
		for(int i=0;i<yearlyWeight.size();i++) {
			if(yearlyWeight.get(i)!=null)
				totalFarmWeight+=yearlyWeight.get(i).getTotalYearWeight();
		}
		return totalFarmWeight;
	}
	
	public void setYearlyWeight(int year, double value) {
		yearlyWeight.get(year).setTotalYearWeight(value);
	}
	
	public double getYearlyWeight(int year) {
		return this.yearlyWeight.get(year).getTotalYearWeight();
	}
	
	public void setID(String ID) {
		this.ID = ID;
	}
	
	public String getID() {
		return this.ID;
	}
	
}
