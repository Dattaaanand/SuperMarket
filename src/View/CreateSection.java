package View;

import java.util.Scanner;

import Model.Database;
import Model.Employee;
import Model.Option;
import Model.Section;

public class CreateSection implements Option{

	@Override
	public void oper(Employee user, Scanner sc, Database database) {
		System.out.print("Enter Section Name:");
		String name = sc.next();
		System.out.print("Enter Description:");
		String description = sc.next();
		Section section = new Section();
		section.setName(name);
		section.setDescription(description);
		new Controller.CreateSection(section, database);
	}

	@Override
	public String getOption() {
		return "Add New Section";
	}

}
