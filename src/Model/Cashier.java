package Model;

import java.util.Scanner;
import View.ChangePassword;
import View.CreateReciept;

public class Cashier extends Employee{
	
	public Cashier() {
		
	}
	
	public Cashier(int ID, String firstName, String lastName, String email, String phoneNumber, String address, double salary, String password) {
		this.ID = ID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.salary = salary;
		this.password = password;
	}
	
	private void generateList() {
		this.options = new Option[] {
				new CreateReciept(),
				new ChangePassword()
		};
	}
	
	@Override
	public int getDepartment() {
		return 1;
	}

	@Override
	public void showList(Scanner sc, Database database) {
		System.out.println("Cashier List: ");
	}

}
