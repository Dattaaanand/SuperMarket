package View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import Model.Admin;
import Model.Cashier;
import Model.Database;
import Model.Employee;
import Model.Option;
import Model.Storekeeper;

public class CreateEmployee implements Option {

    @Override
    public void oper(Employee user, Scanner sc, Database database) {
        JFrame frame = new JFrame("Register Employee");
        frame.setSize(400, 500);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(null);

        JLabel idLabel = new JLabel("ID:");
        idLabel.setBounds(10, 20, 100, 25);
        frame.add(idLabel);

        JTextField idField = new JTextField(20);
        idField.setBounds(120, 20, 165, 25);
        frame.add(idField);

        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setBounds(10, 60, 100, 25);
        frame.add(firstNameLabel);

        JTextField firstNameField = new JTextField(20);
        firstNameField.setBounds(120, 60, 165, 25);
        frame.add(firstNameField);

        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setBounds(10, 100, 100, 25);
        frame.add(lastNameLabel);

        JTextField lastNameField = new JTextField(20);
        lastNameField.setBounds(120, 100, 165, 25);
        frame.add(lastNameField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(10, 140, 100, 25);
        frame.add(emailLabel);

        JTextField emailField = new JTextField(20);
        emailField.setBounds(120, 140, 165, 25);
        frame.add(emailField);

        JLabel phoneLabel = new JLabel("Phone (optional):");
        phoneLabel.setBounds(10, 180, 100, 25);
        frame.add(phoneLabel);

        JTextField phoneField = new JTextField(20);
        phoneField.setBounds(120, 180, 165, 25);
        frame.add(phoneField);

        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setBounds(10, 220, 100, 25);
        frame.add(addressLabel);

        JTextField addressField = new JTextField(20);
        addressField.setBounds(120, 220, 165, 25);
        frame.add(addressField);

        JLabel salaryLabel = new JLabel("Salary:");
        salaryLabel.setBounds(10, 260, 100, 25);
        frame.add(salaryLabel);

        JTextField salaryField = new JTextField(20);
        salaryField.setBounds(120, 260, 165, 25);
        frame.add(salaryField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 300, 100, 25);
        frame.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setBounds(120, 300, 165, 25);
        frame.add(passwordField);

        JLabel departmentLabel = new JLabel("Department:");
        departmentLabel.setBounds(10, 340, 100, 25);
        frame.add(departmentLabel);

        String[] departments = {"Admin", "Cashier", "Storekeeper"};
        JComboBox<String> departmentComboBox = new JComboBox<>(departments);
        departmentComboBox.setBounds(120, 340, 165, 25);
        frame.add(departmentComboBox);

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(120, 380, 165, 25);
        frame.add(registerButton);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent eac) {
                try {
                    int ID = Integer.parseInt(idField.getText());
                    String firstName = firstNameField.getText();
                    String lastName = lastNameField.getText();
                    String email = emailField.getText();
                    String phoneNumber = phoneField.getText();
                    String address = addressField.getText();
                    double salary = Double.parseDouble(salaryField.getText());
                    String password = new String(passwordField.getPassword());
                    int department = departmentComboBox.getSelectedIndex();

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
                            JOptionPane.showMessageDialog(null, "Invalid Department");
                            return;
                    }
                    e.setID(ID);
                    e.setFirstName(firstName);
                    e.setLastName(lastName);
                    e.setEmail(email);
                    e.setAddress(address);
                    e.setPassword(password);
                    e.setSalary(salary);
                    e.setPhoneNumber(phoneNumber);

                    new Controller.CreateEmployee(e, database);
                    JOptionPane.showMessageDialog(null, "Employee Registered Successfully!");
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
        return "Add New Employee";
    }
}
