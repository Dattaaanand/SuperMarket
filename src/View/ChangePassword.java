package View;

import java.util.Scanner;
import Model.Database;
import Model.Employee;
import Model.Option;
public class ChangePassword implements Option{
	@Override
	public void oper(Employee user, Scanner sc, Database database) {
		System.out.println("Enter old password:");
		String oldPass = sc.next();
		if (!oldPass.equals(user.getPassword())) {
			System.out.println("Incorrect password\n Try again later");
			return;
		}
		System.out.println("Enter new password:");
		String newPass = sc.next();
		System.out.println("Confirm password:");
		String confirmPass = sc.next();
		if (!newPass.equals(confirmPass)) {
			System.out.println("Password doesn't match\n Try again later");
			return;
		}
		new Controller.ChangePassword(confirmPass, user, database);
	}
	@Override
	public String getOption() {
		return "Change Password";
	}
}