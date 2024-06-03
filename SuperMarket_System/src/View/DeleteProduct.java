package View;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controller.ReadAllEmployees;
import Controller.ReadAllProducts;
import Controller.ReadProduct;
import Model.Database;
import Model.Employee;
import Model.Option;
import Model.Product;

public class DeleteProduct implements Option {
    private JFrame frame;
    private JTextField idField;

    @Override
	public void oper(Employee user, Scanner sc, Database database) {
		frame = new JFrame("Delete Product");
        frame.setSize(300, 150);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));

        JLabel idLabel = new JLabel("Product ID:");
        panel.add(idLabel);

        idField = new JTextField();
        panel.add(idField);

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener((ActionListener) new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int ID = Integer.parseInt(idField.getText());
                if (ID == -1) {
                    new ReadAllProducts(database).print();
                    frame.dispose();
                } else {
                    new Controller.DeleteProduct(ID, database);
                    JOptionPane.showMessageDialog(null, "Product deleted successfully");
                    frame.dispose();
                }
            }
        });
        panel.add(deleteButton);

        frame.add(panel);
        frame.setVisible(true);
	}

    @Override
    public String getOption() {
        return "Remove Product";
    }


}
