package View;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import Model.Database;
import Model.Employee;
import Model.Option;

public class ChangePassword implements Option {

    @Override
    public void oper(Employee user, Scanner sc, Database database) {
        // Create a JFrame for the password change UI
        JFrame frame = new JFrame("Change Password ");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        // Create and arrange UI components
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        panel.setBackground(new Color(240, 240, 240));

        JLabel titleLabel = new JLabel("Change Password for: " + user.getFirstName());
        titleLabel.setFont(new Font(titleLabel.getFont().getName(), Font.BOLD, 16));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel currentPasswordLabel = new JLabel("Current Password:");
        JPasswordField currentPasswordField = new JPasswordField(20);
        currentPasswordField.setEchoChar('*');

        JLabel newPasswordLabel = new JLabel("New Password:");
        JPasswordField newPasswordField = new JPasswordField(20);
        newPasswordField.setEchoChar('*');

        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        JPasswordField confirmPasswordField = new JPasswordField(20);
        confirmPasswordField.setEchoChar('*');

        JLabel minCharLabel = new JLabel("Minimum number of characters: 4");
        minCharLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton saveButton = new JButton("Save");
        saveButton.setBackground(new Color(70, 130, 180));
        saveButton.setForeground(Color.WHITE);
        saveButton.setFocusPainted(false);
        saveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        saveButton.addActionListener(e -> {
            String currentPass = new String(currentPasswordField.getPassword());
            String newPass = new String(newPasswordField.getPassword());
            String confirmPass = new String(confirmPasswordField.getPassword());

            if (!currentPass.equals(user.getPassword())) {
                JOptionPane.showMessageDialog(frame, "Incorrect password. Please try again.");
                return;
            }

            if (!newPass.equals(confirmPass)) {
                JOptionPane.showMessageDialog(frame, "Passwords don't match. Please try again.");
                return;
            }

            if (newPass.length() < 4) {
                JOptionPane.showMessageDialog(frame, "Password must be at least 4 characters long.");
                return;
            }

            new Controller.ChangePassword(confirmPass, user, database);
            frame.dispose();
        });

        // Add components to the panel with spacing
        panel.add(titleLabel);
        panel.add(Box.createVerticalStrut(20));
        panel.add(currentPasswordLabel);
        panel.add(currentPasswordField);
        panel.add(Box.createVerticalStrut(10));
        panel.add(newPasswordLabel);
        panel.add(newPasswordField);
        panel.add(Box.createVerticalStrut(10));
        panel.add(confirmPasswordLabel);
        panel.add(confirmPasswordField);
        panel.add(Box.createVerticalStrut(10));
        panel.add(minCharLabel);
        panel.add(Box.createVerticalStrut(20));
        panel.add(saveButton);

        // Add panel to the frame and display
        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null); // Center the frame on screen
        frame.setVisible(true);
    }

    @Override
    public String getOption() {
        return "Change Password";
    }
}
