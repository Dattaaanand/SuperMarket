package View;

import java.util.Scanner;

import Controller.ReadAllEmployees;
import Model.Database;
import Model.Employee;
import Model.Option;

public class DeleteEmployee implements Option{

	@Override
	public void oper(Employee user, Scanner sc, Database database) {
		System.out.println("Enter employee ID (-1 to show all employees):");
		int ID = sc.nextInt();
		while (ID<0) {
			new ReadAllEmployees(database).print();
			System.out.println("Enter employee ID (-1 to show all employees):");
			ID = sc.nextInt();
		}
		new Controller.DeleteEmployee(ID, database);
	}

	@Override
	public String getOption() {
		return "Fire Employee";
	}

}
