package Model;
import java.util.Scanner;
import View.ChangePassword;
public class Storekeeper extends Employee {
	public Storekeeper() {
		generateList();
	}
	public Storekeeper(int ID, String firstName, String lastName, String email, String phoneNumber, String address, double salary, String password ) {
		this.ID = ID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.salary = salary;
		this.passsword = password;
		generateList();
	}
	private void generateList() {
		this.options = new Option[] {
				new ChangePassword()
		};
	}
	public int getDepartment() {
		return 2;
	}
}
