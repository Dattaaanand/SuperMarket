package View;

import java.util.Scanner;
import Controller.ReadAllSections;

import Model.Database;
import Model.Employee;
import Model.Option;

public class DeleteSection implements Option{

	@Override
	public void oper(Employee user, Scanner s, Database database) {
		System.out.println("Enter Section ID (-1 to Display all Sections):");
		ReadAllSections readSections = new ReadAllSections(database);


		int ID = s.nextInt();
		while(ID<0) {
			readSections.print();
			System.out.println("Enter Section ID (-1 to show all Sections):");
			ID = s.nextInt();
		}


		if (!readSections.getIDs().contains(ID)) {
			System.out.println("Invalid section ID");
			return;
		}

		System.out.println("Are you sure that you want to delete "+
		"this section with all its products? \n1.Yes\n2. No");

		int res=s.nextInt();
		if(res==1){
			new Controller.DeleteSection(ID, database);
		}
		
		}

	@Override
	public String getOption() {
		return "Remove Section";
	}
	
}
