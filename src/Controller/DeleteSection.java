package Controller;

import java.sql.SQLException;

import Model.Database;

public class DeleteSection {
	public DeleteSection(int ID, Database database) {
		String delete = "delete from Sections where ID = "+ID+";  ";
		try {
			database.getStatement().execute(delete);
			System.out.println("Section Removed Successfully.");
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
}
