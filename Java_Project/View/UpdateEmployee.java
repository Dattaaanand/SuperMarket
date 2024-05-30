package View;
import java.util.Scanner;
import Model.Option;
import Model.Employee;
import Model.Database;
public class UpdateEmployee implements Option{
	@Override
	public void oper(Employee user, Scanner s, Database database) {
		System.out.println("Enter employee ID (-1 to show all employees):");
		int ID = s.next();
		while (ID<0) {
			new ReadAllEmployees(database).print();
			System.out.println("Enter employee ID (-1 to show all employees):");
			ID = s.next();
		}
		Employee emp = new ReadEmployee(ID, database).getEmployee();
		System.out.println("Enter first name (-1 to keep "+emp.getFirstName()+"):");
		String firstName = s.next();
		if (!firstName.equals("-1")) {
			emp.setFirstName(firstName);
		}
		System.out.println("Enter last name (-1 to keep "+emp.getLastName()+"):");
		String lastName = s.next();
		if (!lastName.equals("-1")) {
			emp.setLastName(lastName);
		}
		System.out.println("Enter email (-1 to keep "+emp.getEmail()+"):");
		String email = s.next();
		if (!email.equals("-1")) {
			emp.setEmail(email);
		}
		System.out.println("Enter phone number (-1 to keep "+emp.getPhoneNumber()+"):");
		String phoneNumber = s.next();
		if (!phoneNumber.equals("-1")) {
			emp.setPhoneNumber(phoneNumber);
		}
		System.out.println("Enter address (-1 to keep "+emp.getAddress()+"):");
		String address = s.next();
		if (!address.equals("-1")) {
			emp.setAddress(address);
		}
		System.out.println("Enter salary (-1 to keep "+emp.getSalary()+"):");
		double salary = s.nextDouble();
		if (salary != -1) {
			emp.setSalary(salary);
		}
		
		new Controller.UpdateEmployee(emp, database);
	}
	@Override
	public String getOption() {
		return "Update Employee's data";
	}
}
