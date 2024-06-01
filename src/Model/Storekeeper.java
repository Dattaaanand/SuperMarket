package Model;

import java.util.Scanner;

import Controller.ReadSectionProducts;
import View.ChangePassword;
import View.CreateProduct;
import View.DeleteProduct;
import View.ReadAllProducts;
import View.UpdateProduct;

public class Storekeeper extends Employee{
	
	public Storekeeper() {
		generateList();
	}
	
	public Storekeeper(int ID, String firstName, String lastName, String email, String phoneNumber, String address, double salary, String password) {
		this.ID = ID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.salary = salary;
		this.password = password;
		generateList();
	}
	
	private void generateList() {
		this.options = new Option[] {
				new ChangePassword(),
				new CreateProduct(),
				new ReadAllProducts(),
				new View.ReadSectionProducts(),
				new UpdateProduct(),
				new DeleteProduct(),
		};
	}
	
	@Override
	public int getDepartment() {
		return 2;
	}
}
