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
import java.util.LinkedList;
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
	private List<LinkedList<LogObject>> farmSorted;
	private List<File> files;
	
	public Driver(List<File> files) throws Exception {
		farmSorted = new LinkedList<LinkedList<LogObject>>();
		this.files = files;
		for(int i = 0; i < files.size(); i++) {
			Scanner scan = new Scanner(files.get(i));
			while(scan.hasNext()) {
				String info = scan.nextLine();
				String[] seperated = new String[3];
				seperated = info.split(",");
				seperated[1] = seperated[1].substring(5);
				LogObject current = new LogObject(seperated[0], seperated[1], seperated[2]);
				if(!seperated[0].equals("date")) {
					if(!farmSorted.isEmpty()) {
						for(int n = 0; n < farmSorted.size(); n++) {
							if(current != null && current.compare(farmSorted.get(n).get(0))) {
								farmSorted.get(n).add(current);
								current = null;
							}
						}
					}
					if(current != null) {
						LinkedList<LogObject> temp = new LinkedList<LogObject>();
						temp.add(current);
						farmSorted.add(temp);
					}
				}
			}
			scan.close();
		}
	}
	
	public boolean contains(String ID) {
		for(int i = 0; i < farmSorted.size(); i++) {
			if(Integer.parseInt(farmSorted.get(i).get(0).getID()) == Integer.parseInt(ID)){
				return true;
			}
		}
		return false;
	}
	/*public void importData(String filepath) throws Exception {
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
				String year=points.get(0).substring(0,4);
				String month=points.get(0).substring(5,5);
				String day=points.get(0).substring(7,7);
				String id=points.get(1).substring(5);
				if(!data.containsKey(id))
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
	}*/
	
	public List<String> exportData() {
		return null;
	}
	public List<LogObject> getModifyData(){
		List<LogObject> list = new LinkedList<LogObject>();
		for(int i = 0; i < farmSorted.size(); i++) {
			for(int n = 0; n < farmSorted.get(i).size(); n++) {
				list.add(farmSorted.get(i).get(n));
			}
		}
		return list;
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
		return files.get(0).getName().toString();
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		

	}

}
