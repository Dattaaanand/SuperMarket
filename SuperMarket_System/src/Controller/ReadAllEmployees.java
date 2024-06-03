package Controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import Model.Admin;
import Model.Cashier;
import Model.Employee;
import Model.Storekeeper;
import Supermarket.CommonFrame;
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
        JFrame frame = new JFrame("Employees List");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 600);

        String[] columnNames = {"ID", "First Name", "Last Name", "Email", "Phone Number", "Address", "Salary","Department"};
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

        for (Employee e : employees) {
            Object[] row = {
                    e.getID(),
                    e.getFirstName(),
                    e.getLastName(),
                    e.getEmail(),
                    e.getPhoneNumber(),
                    e.getAddress(),
                    String.format("%.2f$", e.getSalary()),
                    e.getDepartmentToString()
            };
            model.addRow(row);
        }

        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}