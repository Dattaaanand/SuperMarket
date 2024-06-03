package Controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import Model.Database;
import Model.Product;
import Model.Section;

public class ReadAllSections {
	
	private ArrayList<Section> sections;
	private Database database;

	public ReadAllSections(Database database) {
		this.database=database;

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
        JFrame frame = new JFrame("Section List");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 600);

        String[] columnNames = {"ID", "Name", "Description"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(model);

        table.setRowHeight(30); 
        table.setFont(new Font("Arial", Font.PLAIN, 14)); 
        table.setBackground(Color.WHITE); 
        table.setForeground(Color.BLACK); 
        table.setSelectionBackground(new Color(173, 216, 230)); 
        table.setSelectionForeground(Color.BLACK); 
        table.setGridColor(Color.LIGHT_GRAY); 

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 16));
        header.setBackground(new Color(0, 153, 0));
        header.setForeground(Color.WHITE); 
        header.setReorderingAllowed(false); 

        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) header.getDefaultRenderer();
        renderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);

        for (Section s : sections) {
            Object[] row = {
                    s.getID(),
                    s.getName(),
                    s.getDescription(),

            };
            model.addRow(row);
        }

        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

		public void printWithProducts() {
		for (Section s:sections) {
			s.setProducts(new ReadSectionProducts(s.getID(),database).getProducts());
		}
		
		for(Section s:sections) {
			System.out.println(s.getName());
			for (int i=0;i<s.getName().length();i++) {
				System.out.print("-");
			}
			System.out.println();
			
			for(Product p : s.getProducts()) {
				System.out.println(p.getID()+". "+p.getName()+"\t"+p.getPrice()+"$");
			}
			
			System.out.println("-----------------------");
		}
	}

	public ArrayList<Integer> getIDs(){
		ArrayList<Integer> ids=new ArrayList<>();
		for(Section s:sections) {
			ids.add(s.getID());
			
		}
		return ids;
	}

	public ArrayList<Section> getList() {
		return sections;
	}

}
