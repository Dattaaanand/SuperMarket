package Controller;

import java.sql.SQLException;

import Model.Database;
import Model.Section;

public class CreateSection {
	
	public CreateSection(Section s, Database database) {
		String insert = "  insert into sections(Name, Description) values('"+s.getName()+"', '"+s.getDescription()+"');  ";
		try {
			database.getStatement().execute(insert);
			System.out.println("Section Created Successfully");
		}
		catch (SQLException exception) {
			exception.printStackTrace();
		}
	}
	
}
