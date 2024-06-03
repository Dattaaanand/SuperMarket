package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Model.Database;
import Model.Employee;
import Model.Option;

public class ReadSectionProducts implements Option {

    @Override
    public void oper(Employee user, Scanner s, Database database) {
        JFrame frame = new JFrame("View Section Products");
        frame.setSize(300, 150);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(null);

        JLabel label = new JLabel("Enter Section ID:");
        label.setBounds(10, 20, 150, 25);
        frame.add(label);

        JTextField textField = new JTextField();
        textField.setBounds(140, 20, 120, 25);
        frame.add(textField);

        JButton button = new JButton("View Products");
        button.setBounds(80, 60, 140, 25);
        frame.add(button);

        button.addActionListener(e -> {
            try {
                int sectionID = Integer.parseInt(textField.getText());
                new Controller.ReadSectionProducts(sectionID, database).print();
                frame.dispose();
            } catch (NumberFormatException ex) {
                // Handle invalid input
            }
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public String getOption() {
        return "View Section Products";
    }
}
