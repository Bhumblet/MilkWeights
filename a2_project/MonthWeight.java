/**
 * MonthWeight.java created by johnw on Surface Pro 6 in a2_project
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
 * MonthWeight - TODO Describe purpose of this user-defined type
 * @author wirth (2020)
 *
 */
public class MonthWeight {

	 private double totalMonthWeight;
	 private DayWeight[] dailyWeight;
	
	 public MonthWeight(double totalMonthWeight) {
		 this.totalMonthWeight = totalMonthWeight;
		 dailyWeight = new DayWeight[31];
	 }
	 
	/**
	 * @param args
	 */
	public void setTotalMonthWeight(double totalMonthWeight) {
		this.totalMonthWeight = totalMonthWeight;
	}
	
	public double getTotalMonthWeight() {
		totalMonthWeight=0;
		for(int i=0;i<dailyWeight.length;i++) {
			if(dailyWeight[i]!=null)
				totalMonthWeight+=dailyWeight[i].getTotalDayWeight();
		}
		return totalMonthWeight;
	}

	public void setDailyWeight(int index, double value) {
		dailyWeight[index] = new DayWeight(value);
	}
	
	public double getDailyWeight(int index) {
		return dailyWeight[index].getTotalDayWeight();
	}
	
}
