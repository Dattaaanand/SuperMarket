package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Model.Database;
import Model.Employee;
import Model.Option;
import Model.Section;

public class CreateSection implements Option {

    @Override
    public void oper(Employee user, Scanner sc, Database database) {
        JFrame frame = new JFrame("Add Section");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
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

        JButton addButton = new JButton("Add Section");
        addButton.setBounds(120, 100, 165, 25);
        frame.add(addButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = nameField.getText();
                    String description = descriptionField.getText();

                    if (name.isEmpty() || description.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Please fill all fields");
                        return;
                    }

                    Section section = new Section();
                    section.setName(name);
                    section.setDescription(description);

                    new Controller.CreateSection(section, database);
                    JOptionPane.showMessageDialog(null, "Section Added Successfully!");
                    frame.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error adding section: " + ex.getMessage());
                }
            }
        });

        frame.setVisible(true);
    }

    @Override
    public String getOption() {
        return "Add New Section";
    }
}
