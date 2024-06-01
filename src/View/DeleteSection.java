package View;

import java.util.Scanner;
import Controller.ReadAllSections;

import Model.Database;
import Model.Employee;
import Model.Option;

public class DeleteSection implements Option{

	@Override
	public void oper(Employee user, Scanner sc, Database database) {
		System.out.println("Enter Section ID (-1 to Display all Sections):");
		int ID = sc.nextInt();
		while(ID<0) {
			new ReadAllSections(database).print();
			System.out.println("Enter Section ID (-1 to show all Sections):");
			ID = sc.nextInt();
		}
		new Controller.DeleteSection(ID, database);
	}

	@Override
	public String getOption() {
		return "Remove Section";
	}

}
