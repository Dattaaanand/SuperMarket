package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Controller.ReadAllSections;
import Controller.ReadSection;
import Model.Database;
import Model.Employee;
import Model.Option;
import Model.Section;

public class UpdateSection implements Option {

    @Override
    public void oper(Employee user, Scanner sc, Database database) {
        JFrame frame = new JFrame("Update Section");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);

        JLabel idLabel = new JLabel("Section ID:");
        idLabel.setBounds(10, 20, 100, 25);
        frame.add(idLabel);

        JTextField idField = new JTextField(20);
        idField.setBounds(120, 20, 165, 25);
        frame.add(idField);

        JButton viewAllButton = new JButton("View All Sections");
        viewAllButton.setBounds(120, 60, 165, 25);
        frame.add(viewAllButton);

        viewAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ReadAllSections(database).print();
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

        JButton updateButton = new JButton("Update Section");
        updateButton.setBounds(120, 180, 165, 25);
        frame.add(updateButton);

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(idField.getText());
                    String name = nameField.getText();
                    String description = descriptionField.getText();

                    Section section = new ReadSection(id, database).getSection();
                    if (section == null) {
                        JOptionPane.showMessageDialog(null, "Section ID not found");
                        return;
                    }

                    if (!name.isEmpty()) {
                        section.setName(name);
                    }

                    if (!description.isEmpty()) {
                        section.setDescription(description);
                    }

                    new Controller.UpdateSection(section, database);
                    JOptionPane.showMessageDialog(null, "Section Updated Successfully!");
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
        return "Update Data from Section";
    }
}
