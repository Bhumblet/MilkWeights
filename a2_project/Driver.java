/**
 * Driver.java created by johnw on Surface Pro 6 in a2_project
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

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Map.Entry;

/**
 * Driver - TODO Describe purpose of this user-defined type
 * @author wirth (2020)
 *
 */
public class Driver {

	private double totalWeight;
	private HashMap<String,Farm> data;
	private File csv;
	
	public Driver() {
		totalWeight = 0;
		data = new HashMap<>();
	}
	
	public Driver(String filepath) throws Exception {
		importData(filepath);
	}
	
	public void importData(String filepath) throws Exception {
		if(!filepath.substring(filepath.length()-4).equals(".csv")) {
			throw new Exception();
		}
		csv = new File(filepath);
		Scanner scan=new Scanner(csv);
		scan.useDelimiter(",");
		while(scan.hasNext()) {
			ArrayList<String> points=new ArrayList<String>();
			points.add(scan.next());
			if(points.size()==3) {
				int year=Integer.parseInt(points.get(0).substring(0,4));
				int month=Integer.parseInt(points.get(0).substring(5,5));
				String day=points.get(0).substring(7,7);
				String id=points.get(1).substring(5);
				if(!data.containsValue(id))
					data.put(id,new Farm(id));
				double weight=Double.parseDouble(points.get(2));
				Farm f=null;
				for(Entry<String,Farm> entry:data.entrySet()) {
					if(entry.getValue().getID().equals(id))
						f=entry.getValue();
				}
				
				data.replace(id,f);
			}
		}
		scan.close();
	}
	
	public List<String> exportData() {
		return null;
	}
	
	public List<String> getFarmReport(){
		return null;
	}
	
	public List<String> getAnnualReport(){
		return null;
	}
	
	public List<String> getMonthlyReport(){
		return null;
	}
	
	public List<String> getDateRangeReport(){
		return null;
	}
	
	public String getFileName() {
		return csv.getName();
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		

	}

}
