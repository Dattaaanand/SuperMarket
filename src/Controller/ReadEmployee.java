package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import Model.Admin;
import Model.Cashier;
import Model.Database;
import Model.Employee;
import Model.Storekeeper;

public class ReadEmployee {
	private Employee e;
	public ReadEmployee(int ID, Database database) {
		String select = "SELECT * FROM employees WHERE ID = "+ID+" ;";
		try {
			ResultSet rs = database.getStatement().executeQuery(select);
			rs.next();
			int id = rs.getInt("ID");
			String firstName = rs.getString("FirstName");
			String lastName = rs.getString("LastName");
			String phoneNumber = rs.getString("PhoneNumber");
			String email = rs.getString("Email");
			String password = rs.getString("Password");
			String address = rs.getString("Address");
			double salary = rs.getDouble("Salary");
			int department = rs.getInt("Department");
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
			e.setID(id);
			e.setFirstName(firstName);
			e.setLastName(lastName);
			e.setEmail(email);
			e.setAddress(address);
			e.setPassword(password);
			e.setSalary(salary);
			e.setPhoneNumber(phoneNumber);
		}
		catch (SQLException exception) {
			exception.printStackTrace();
		}
	}
	public Employee getEmployee() {
		return e;
	}
	public void printEmployee() {
		e.print();
	}
}