package View;

import java.util.Scanner;

import Model.Admin;
import Model.Cashier;
import Model.Database;
import Model.Employee;
import Model.Option;
import Model.Storekeeper;

public class CreateEmployee implements Option{

	@Override
	public void oper(Employee user, Scanner sc, Database database) {
		
		System.out.print("Enter First Name: ");
		String firstName = sc.next();
		System.out.print("Enter Last Name: ");
		String lastName = sc.next();
		System.out.print("Enter Email: ");
		String email = sc.next();
		System.out.print("Enter Phone Number: ");
		String phoneNumber = sc.next();
		System.out.print("Enter Address: ");
		String address = sc.next();
		System.out.print("Enter Salary: ");
		double salary = sc.nextDouble();
		System.out.print("Enter Password: ");
		String password = sc.next();
		System.out.println("Department Options:");
		System.out.println(" 0 - Admin");
		System.out.println(" 1 - Cashier");
		System.out.println(" 2 - Storekeeper");
		System.out.print("Enter Department: ");
		int department = sc.nextInt();
		
		Employee e;
		switch (department) {
		case 0:
			e = new Admin();
			break;
		case 1:
			e = new Cashier();
			break;
		case 2:
			e = new Storekeeper();
			break;
		default:
			System.out.println("Invalid Department");
			e = new Cashier();
			break;
		}
		
		e.setFirstName(firstName);
		e.setLastName(lastName);
		e.setEmail(email);
		e.setAddress(address);
		e.setPassword(password);
		e.setSalary(salary);
		e.setPhoneNumber(phoneNumber);
		
		new Controller.CreateEmployee(e, database);
		
	}

	@Override
	public String getOption() {
		return "Add New Employee";
	}

}
