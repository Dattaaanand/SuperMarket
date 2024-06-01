package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Database;
import Model.Section;

public class ReadAllSections {
	
	private ArrayList<Section> sections;
	
	public ReadAllSections(Database database) {
		String select = "  select * from Sections;  ";
		sections = new ArrayList<>();
		try {
			ResultSet rs = database.getStatement().executeQuery(select);
			while (rs.next()) {
				Section section = new Section();
				section.setID(rs.getInt("ID"));
				section.setName(rs.getString("Name"));
				section.setDescription(rs.getString("Description"));
				sections.add(section);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void print() {
		for (Section s : sections) {
			s.print();
		}
		System.out.println("-------------------------");
	}
}
