package Controller;

import java.sql.ResultSet;
import java.util.ArrayList;

import Model.Admin;
import Model.Cashier;
import Model.Employee;
import Model.Storekeeper;
import Model.Database;
import java.sql.SQLException;

public class ReadAllEmployees {
	private ArrayList <Employee> employees;
	public ReadAllEmployees(Database database) {
		String select = "SELECT * FROM employees;";
		employees = new ArrayList<>();
		try {
		ResultSet rs = database.getStatement().executeQuery(select);
		while(rs.next()) {
			int ID = rs.getInt("ID");
			String firstName = rs.getString("FirstName");
			String lastName = rs.getString("LastName");
			String phoneNumber = rs.getString("PhoneNumber");
			String email = rs.getString("Email");
			String password = rs.getString("Password");
			String address = rs.getString("Address");
			double salary = rs.getDouble("Salary");
			int department = rs.getInt("Department");
			Employee e;
			switch (department) {
			case 0:
				e = new Admin();
				break;
			case 1:
				e = new Cashier();
				break;
			case 2:
				e = new Storekeeper();
				break;
			default:
				System.out.println("Invalid department");
				e = new Cashier();
				break;
				}
			e.setID(ID);
			e.setFirstName(firstName);
			e.setLastName(lastName);
			e.setEmail(email);
			e.setAddress(address);
			e.setPassword(password);
			e.setSalary(salary);
			e.setPhoneNumber(phoneNumber);
			e.setEmail(email);
			e.setPassword(password);
			employees.add(e);
		}
	}catch(SQLException exception) {
		exception.printStackTrace();
	}
	}
	public void print() {
		for (Employee e : employees) {
			System.out.println("----------------------------------------");
			System.out.println("ID:\t \t"+e.getID());
			System.out.println("First Name: \t"+e.getFirstName());
			System.out.println("Last Name: \t"+e.getLastName());
			System.out.println("Email: \t"+e.getEmail());
			System.out.println("Phone Number: \t"+e.getPhoneNumber());
			System.out.println("Address: \t"+e.getAddress());
			System.out.println("Salary: \t"+e.getSalary()+"$");
			System.out.println("Department: \t"+e.getDepartment());
			System.out.println("---------------------------------------");
		}
	}
}