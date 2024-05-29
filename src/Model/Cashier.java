package Model;

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
	
	@Override
	public int getDepartment() {
		return 1;
	}

	@Override
	public void showList() {
		System.out.println("Cashier List: ");
	}

}
