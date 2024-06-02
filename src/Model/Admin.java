package Model;

import java.util.Iterator;

import java.util.Scanner;

import View.ChangePassword;
import View.CreateEmployee;
import View.CreateProduct;
import View.CreateReciept;
import View.CreateSection;
import View.DeleteEmployee;
import View.DeleteProduct;
import View.DeleteSection;
import View.ReadAllEmployees;
import View.ReadAllProducts;
import View.ReadAllSections;
import View.ReadSectionProducts;
import View.UpdateEmployee;
import View.UpdateProduct;
import View.UpdateSection;

public class Admin extends Employee{
	
	public Admin() {
		generateList();
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
		generateList();
	}
	
	public void generateList() {
		this.options = new Option[] {
				new CreateEmployee(),
				new ReadAllEmployees(), 
				new UpdateEmployee(), 
				new DeleteEmployee(),
				new CreateSection(),
				new ReadAllSections(),
				new UpdateSection(),
				new DeleteSection(),
				new ChangePassword(),
				new CreateProduct(),
				new ReadAllProducts(),
				new ReadSectionProducts(),
				new UpdateProduct(),
				new DeleteProduct(),
				new CreateReciept(),
				
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