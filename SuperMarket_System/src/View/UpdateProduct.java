package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Controller.ReadAllProducts;
import Controller.ReadProduct;
import Model.Database;
import Model.Employee;
import Model.Option;
import Model.Product;

public class UpdateProduct implements Option {

    @Override
    public void oper(Employee user, Scanner s, Database database) {
        JFrame frame = new JFrame("Update Product");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        JLabel idLabel = new JLabel("Product ID:");
        idLabel.setBounds(10, 20, 100, 25);
        frame.add(idLabel);

        JTextField idField = new JTextField(20);
        idField.setBounds(120, 20, 165, 25);
        frame.add(idField);

        JButton viewAllButton = new JButton("View All Products");
        viewAllButton.setBounds(120, 60, 165, 25);
        frame.add(viewAllButton);

        viewAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ReadAllProducts(database).print();
            }
        });

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(10, 100, 100, 25);
        frame.add(nameLabel);

        JTextField nameField = new JTextField(20);
        nameField.setBounds(120, 100, 165, 25);
        frame.add(nameField);

        JLabel descriptionLabel = new JLabel("Description:");
        descriptionLabel.setBounds(10, 140, 100, 25);
        frame.add(descriptionLabel);

        JTextField descriptionField = new JTextField(20);
        descriptionField.setBounds(120, 140, 165, 25);
        frame.add(descriptionField);

        JLabel priceLabel = new JLabel("Price (double):");
        priceLabel.setBounds(10, 180, 100, 25);
        frame.add(priceLabel);

        JTextField priceField = new JTextField(20);
        priceField.setBounds(120, 180, 165, 25);
        frame.add(priceField);

        JLabel qtyLabel = new JLabel("Quantity (int):");
        qtyLabel.setBounds(10, 220, 100, 25);
        frame.add(qtyLabel);

        JTextField qtyField = new JTextField(20);
        qtyField.setBounds(120, 220, 165, 25);
        frame.add(qtyField);

        JButton updateButton = new JButton("Update Product");
        updateButton.setBounds(120, 260, 165, 25);
        frame.add(updateButton);

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(idField.getText());
                    String name = nameField.getText();
                    String description = descriptionField.getText();
                    double price = Double.parseDouble(priceField.getText());
                    int qty = Integer.parseInt(qtyField.getText());

                    Product product = new ReadProduct(id, database).getProduct();
                    if (product == null) {
                        JOptionPane.showMessageDialog(null, "Product ID not found");
                        return;
                    }

                    if (!name.isEmpty()) {
                        product.setName(name);
                    }

                    if (!description.isEmpty()) {
                        product.setDescription(description);
                    }

                    if (price != 0) {
                        product.setPrice(price);
                    }

                    if (qty != 0) {
                        product.setQty(qty);
                    }

                    new Controller.UpdateProduct(product, database);
                    JOptionPane.showMessageDialog(null, "Product Updated Successfully!");
                    frame.dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid input, please check your entries.");
                }
            }
        });

        frame.setVisible(true);
    }

    @Override
    public String getOption() {
        return "Update Product's Data";
    }
}
