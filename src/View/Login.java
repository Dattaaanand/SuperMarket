package View;

import java.util.Scanner;
import Model.Database;
import Model.Employee;
import Model.Option;

public class Login implements Option{

	@Override
	public void oper(Employee user, Scanner s, Database database) {
		
		System.out.println("Welcome to SuperMarket Management System");
		System.out.print("Enter You Email: ");
		String email = s.next();
		System.out.print("Enter Your Password: ");
		String password = s.next();
		Controller.Login login = new Controller.Login(email, password);
		
		if (login.isLoggedIn()) {
			Employee employee = login.getUser();
			System.out.println("Welcome "+employee.getFirstName());
			employee.showList();
		}
		else {
			System.out.println("Wrong Email and Password");
			System.out.println("Check your Credentials and TRY AGAIN.");
		}
	}
	
}
