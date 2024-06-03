package Controller;

import java.sql.SQLException;

import Model.Database;
import Model.Section;

public class UpdateSection {
	public UpdateSection(Section s, Database database) {
		String update = "  update Sections set Name = '"+s.getName()+"', Description = '"+s.getDescription()+"' where ID = '"+s.getID()+"'  ";
		
		try {
			database.getStatement().execute(update);
			System.out.println("Section Successfully Updated.");
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
}