package Controller;

import java.sql.SQLException;

import Model.Database;

public class DeleteEmployee {
	
	public DeleteEmployee(int ID, Database database) {
		String delete = " delete from employees where ID = "+ID+"; ";
		try {
			database.getStatement().execute(delete);
			System.out.println("Employee Deleted Successfully");
		}
		catch (SQLException exception) {
			exception.printStackTrace();
		}
	}
}
