package View;

import java.util.Scanner;

import Controller.ReadAllSections;
import Controller.ReadSection;
import Model.Database;
import Model.Employee;
import Model.Option;
import Model.Section;

public class UpdateSection implements Option{

	@Override
	public void oper(Employee user, Scanner sc, Database database) {
		System.out.print("Enter Section ID (-1 to show all sections):");
		int ID = sc.nextInt();
		while(ID<0) {
			new ReadAllSections(database).print();
			System.out.println("Enter Section ID(-1 to show all sections):");
			ID = sc.nextInt();
		}
		
		Section section = new ReadSection(ID, database).getSection();
		System.out.println("Enter Section Name(-1 to keep "+section.getName()+"): ");
		String name = sc.next();
		if (!name.equals("-1")) {
			section.setName(name);
		}
		
		System.out.println("Enter Section Description(-1 to keep "+section.getDescription()+"):");
		String description = sc.next();
		if (!description.equals("-1")) {
			section.setDescription(description);
		}
		
		new Controller.UpdateSection(section, database);
		
	}

	@Override
	public String getOption() {
		return "Update Data from Section";
	}

}
