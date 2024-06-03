package Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import View.ChangePassword;
import View.CreateSection;
import View.DeleteSection;
import View.ReadAllSections;
import View.UpdateSection;

public class Storekeeper extends Employee {

    public Storekeeper() {
        generateList();
    }

    public Storekeeper(int ID, String firstName, String lastName, String email, String phoneNumber, String address, double salary, String password) {
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.salary = salary;
        this.password = password;
        generateList();
    }

    private void generateList() {
        this.options = new Option[]{
                new CreateSection(),
                new ReadAllSections(),
                new UpdateSection(),
                new DeleteSection(),
                new ChangePassword()
        };
    }

    @Override
    public int getDepartment() {
        return 2;
    }

    @Override
    public void showList(Scanner sc, Database database) {
        if (frame == null) {
            frame = new JFrame("StoreKeeper Dashboard");
            frame.setSize(600, 800);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        } else {
            frame.getContentPane().removeAll(); // Remove existing components
        }

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(204, 255, 204)); // Light green background

        JLabel welcomeLabel = new JLabel("Welcome, " + getFirstName() + "!", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        mainPanel.add(welcomeLabel, BorderLayout.NORTH);

        JPanel optionsPanel = new JPanel(new GridLayout(0, 2, 20, 20));
        optionsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        optionsPanel.setBackground(new Color(204, 255, 204)); // Light green background

        for (Option option : options) {
            JButton button = new JButton(option.getOption());
            button.setFont(new Font("Arial", Font.PLAIN, 16));
            button.setBackground(new Color(0, 153, 0)); // Green background
            button.setForeground(Color.WHITE); // White text color
            button.setFocusPainted(false); // Remove focus border
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    option.oper(Storekeeper.this, sc, database);
                }
            });
            optionsPanel.add(button);
        }

        JScrollPane scrollPane = new JScrollPane(optionsPanel);
        scrollPane.getViewport().setBackground(new Color(204, 255, 204)); // Light green background for scroll pane
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        frame.add(mainPanel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
