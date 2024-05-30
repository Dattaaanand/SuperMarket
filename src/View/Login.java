package View;

import java.util.Scanner;
import Model.Database;
import Model.Employee;
import Model.Option;

public class Login implements Option{

	@Override
	public void oper(Employee user, Scanner sc, Database database) {
		
		System.out.println("Welcome to SuperMarket Management System");
		System.out.print("Enter You Email: ");
		String email = sc.next();
		System.out.print("Enter Your Password: ");
		String password = sc.next();
		Controller.Login login = new Controller.Login(email, password, database);
		
		if (login.isLoggedIn()) {
			Employee employee = login.getUser();
			System.out.println("Welcome "+employee.getFirstName());
			employee.showList(sc, database);
		}
		else {
			System.out.println("Wrong Email and Password");
			System.out.println("Check your Credentials and TRY AGAIN.");
		}
	}
	
	@Override
	public String getOption() {
		return "Login";
	}
	
}
