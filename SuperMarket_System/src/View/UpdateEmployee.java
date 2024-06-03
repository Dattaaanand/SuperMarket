package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Controller.ReadAllEmployees;
import Controller.ReadEmployee;
import Model.Database;
import Model.Employee;
import Model.Option;

public class UpdateEmployee implements Option {

    @Override
    public void oper(Employee user, Scanner sc, Database database) {
        JFrame frame = new JFrame("Update Employee");
        frame.setSize(400, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(null);
        
        frame.setLocationRelativeTo(null);
        
        JLabel idLabel = new JLabel("Employee ID:");
        idLabel.setBounds(10, 20, 100, 25);
        frame.add(idLabel);

        JTextField idField = new JTextField(20);
        idField.setBounds(120, 20, 165, 25);
        frame.add(idField);

        JButton showAllButton = new JButton("Show All Employees");
        showAllButton.setBounds(120, 60, 165, 25);
        frame.add(showAllButton);

        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setBounds(10, 100, 100, 25);
        frame.add(firstNameLabel);

        JTextField firstNameField = new JTextField(20);
        firstNameField.setBounds(120, 100, 165, 25);
        frame.add(firstNameField);

        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setBounds(10, 140, 100, 25);
        frame.add(lastNameLabel);

        JTextField lastNameField = new JTextField(20);
        lastNameField.setBounds(120, 140, 165, 25);
        frame.add(lastNameField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(10, 180, 100, 25);
        frame.add(emailLabel);

        JTextField emailField = new JTextField(20);
        emailField.setBounds(120, 180, 165, 25);
        frame.add(emailField);

        JLabel phoneLabel = new JLabel("Phone Number:");
        phoneLabel.setBounds(10, 220, 100, 25);
        frame.add(phoneLabel);

        JTextField phoneField = new JTextField(20);
        phoneField.setBounds(120, 220, 165, 25);
        frame.add(phoneField);

        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setBounds(10, 260, 100, 25);
        frame.add(addressLabel);

        JTextField addressField = new JTextField(20);
        addressField.setBounds(120, 260, 165, 25);
        frame.add(addressField);

        JLabel salaryLabel = new JLabel("Salary:");
        salaryLabel.setBounds(10, 300, 100, 25);
        frame.add(salaryLabel);

        JTextField salaryField = new JTextField(20);
        salaryField.setBounds(120, 300, 165, 25);
        frame.add(salaryField);

        JButton updateButton = new JButton("Update Employee");
        updateButton.setBounds(120, 340, 165, 25);
        frame.add(updateButton);

        showAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ReadAllEmployees(database).print();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int ID = Integer.parseInt(idField.getText());
                    Employee emp = new ReadEmployee(ID, database).getEmployee();
                    if (emp == null) {
                        JOptionPane.showMessageDialog(null, "Employee not found.");
                        return;
                    }

                    String firstName = firstNameField.getText();
                    if (!firstName.isEmpty()) {
                        emp.setFirstName(firstName);
                    }

                    String lastName = lastNameField.getText();
                    if (!lastName.isEmpty()) {
                        emp.setLastName(lastName);
                    }

                    String email = emailField.getText();
                    if (!email.isEmpty()) {
                        emp.setEmail(email);
                    }

                    String phoneNumber = phoneField.getText();
                    if (!phoneNumber.isEmpty()) {
                        emp.setPhoneNumber(phoneNumber);
                    }

                    String address = addressField.getText();
                    if (!address.isEmpty()) {
                        emp.setAddress(address);
                    }

                    String salaryStr = salaryField.getText();
                    if (!salaryStr.isEmpty()) {
                        double salary = Double.parseDouble(salaryStr);
                        emp.setSalary(salary);
                    }

                    new Controller.UpdateEmployee(emp, database);
                    JOptionPane.showMessageDialog(null, "Employee Updated Successfully!");
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
        return "Update Employee's data";
    }
}
