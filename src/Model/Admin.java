package Model;

import java.util.Iterator;
import java.util.Scanner;

import View.CreateEmployee;

public class Admin extends Employee{
	
	public Admin() {
		this.options = new Option[] {
			new CreateEmployee()
		};
	}
	
	public Admin(int ID, String firstName, String lastName, String email, String phoneNumber, String address, double salary, String password) {
		this.ID = ID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.salary = salary;
		this.password = password;
		this.options = new Option[] {
				new CreateEmployee()
		};
	}

	@Override
	public int getDepartment(){
		return 0;
	}

	@Override
	public void showList(Scanner sc, Database database) {
		System.out.println();
		for (int i=1; i<=options.length; i++) {
			System.out.println(i+"."+ options[i-1].getOption());
		}
		System.out.println();
		
		int selected = sc.nextInt();
		options[selected -1].oper(this, sc, database);
	}
	
	
}
