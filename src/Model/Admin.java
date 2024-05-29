package Model;

public class Admin extends Employee{
	
	public Admin() {
		this.options = new Option[] {};
	}
	
	public Admin(int ID, String firstName, String lastName, String email, String phoneNumber, String address, double salary, String password) {
		this.ID = ID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.salary = salary;
		this.options = new Option[] {};
		this.password = password;
	}

	@Override
	public int getDepartment() {
		return 0;
	}

	@Override
	public void showList() {
		System.out.println("Admin List: ");
	}
	
	
}
