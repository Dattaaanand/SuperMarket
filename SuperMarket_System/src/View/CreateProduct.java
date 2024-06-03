package View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Controller.ReadAllSections;
import Model.Admin;
import Model.Cashier;
import Model.Database;
import Model.Employee;
import Model.Option;
import Model.Product;
import Model.Storekeeper;

public class CreateProduct implements Option {

    @Override
    public void oper(Employee user, Scanner s, Database database) {
        JFrame frame = new JFrame("Add Product");
        frame.setSize(400, 500);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(null);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(10, 20, 100, 25);
        frame.add(nameLabel);

        JTextField nameField = new JTextField(20);
        nameField.setBounds(120, 20, 165, 25);
        frame.add(nameField);

        JLabel descriptionLabel = new JLabel("Description:");
        descriptionLabel.setBounds(10, 60, 100, 25);
        frame.add(descriptionLabel);

        JTextField descriptionField = new JTextField(20);
        descriptionField.setBounds(120, 60, 165, 25);
        frame.add(descriptionField);

        JLabel priceLabel = new JLabel("Price (double):");
        priceLabel.setBounds(10, 100, 100, 25);
        frame.add(priceLabel);

        JTextField priceField = new JTextField(20);
        priceField.setBounds(120, 100, 165, 25);
        frame.add(priceField);

        JLabel qtyLabel = new JLabel("Quantity (int):");
        qtyLabel.setBounds(10, 140, 100, 25);
        frame.add(qtyLabel);

        JTextField qtyField = new JTextField(20);
        qtyField.setBounds(120, 140, 165, 25);
        frame.add(qtyField);

        JLabel sectionLabel = new JLabel("Section ID:");
        sectionLabel.setBounds(10, 180, 100, 25);
        frame.add(sectionLabel);

        JTextField sectionField = new JTextField(20);
        sectionField.setBounds(120, 180, 165, 25);
        frame.add(sectionField);

        JButton showSectionsButton = new JButton("Show All Sections");
        showSectionsButton.setBounds(120, 220, 165, 25);
        frame.add(showSectionsButton);

        showSectionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ReadAllSections sections = new ReadAllSections(database);
                sections.print();
            }
        });

        JButton addButton = new JButton("Add Product");
        addButton.setBounds(120, 260, 165, 25);
        frame.add(addButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = nameField.getText();
                    String description = descriptionField.getText();
                    double price = Double.parseDouble(priceField.getText());
                    int qty = Integer.parseInt(qtyField.getText());
                    int sectionID = Integer.parseInt(sectionField.getText());

                    ReadAllSections sections = new ReadAllSections(database);
                    if (!sections.getIDs().contains(sectionID)) {
                        JOptionPane.showMessageDialog(null, "Section doesn't exist");
                        return;
                    }

                    Product product = new Product();
                    product.setName(name);
                    product.setDescription(description);
                    product.setPrice(price);
                    product.setQty(qty);

                    new Controller.CreateProduct(product, sectionID, database);
                    JOptionPane.showMessageDialog(null, "Product Added Successfully!");
                    frame.dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid input, please check your entries.");
                }
            }
        });

        frame.setVisible(true);
    }

	
	public String getOption() {
		return "Add New Product";
	}
	
}
