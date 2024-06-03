package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import Model.Database;
import Model.Employee;
import Model.Option;

public class Login extends JFrame implements Option {

    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel statusLabel;
    private Database database;

    public Login(Database database) {
        this.database = database;
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Grocery Store Management - Admin Login");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel emailLabel = new JLabel("Username");
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(emailLabel, gbc);

        emailField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        mainPanel.add(emailField, gbc);

        JLabel passwordLabel = new JLabel("Password");
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(passwordLabel, gbc);

        passwordField = new JPasswordField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        mainPanel.add(passwordField, gbc);

        loginButton = new JButton("Login");
        gbc.gridx = 1;
        gbc.gridy = 2;
        mainPanel.add(loginButton, gbc);

        statusLabel = new JLabel();
        gbc.gridx = 1;
        gbc.gridy = 3;
        mainPanel.add(statusLabel, gbc);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogin();
            }
        });

        add(createBannerPanel(), BorderLayout.WEST);
        add(mainPanel, BorderLayout.CENTER);
        add(createFooterPanel(), BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel createBannerPanel() {
        JPanel bannerPanel = new JPanel();
        bannerPanel.setBackground(new Color(37, 89, 37));
        bannerPanel.setPreferredSize(new Dimension(250, 0));

        JLabel bannerLabel = new JLabel("<html><h1 style='color:white;'>Grocery Store</h1><p style='color:white;'>Online Grocery Shopping</p><p style='color:white;'>Thanksgiving Special</p></html>");
        bannerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        bannerPanel.add(bannerLabel);

        return bannerPanel;
    }

    private JPanel createFooterPanel() {
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(37, 89, 37));
        footerPanel.setPreferredSize(new Dimension(0, 50));

        JLabel footerLabel = new JLabel("© 2019. All rights reserved.");
        footerLabel.setForeground(Color.WHITE);
        footerPanel.add(footerLabel);

        return footerPanel;
    }

    private void handleLogin() {
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());
        Controller.Login login = new Controller.Login(email, password, database);

        if (login.isLoggedIn()) {
            Employee employee = login.getUser();
            statusLabel.setText("Welcome " + employee.getFirstName());
            // Adjust this part for GUI interaction if needed
            employee.showList(new Scanner(System.in), database);
            dispose();
            
            
        } else {
            statusLabel.setText("Wrong Email and Password. Check your credentials and TRY AGAIN.");
        }
    }

    @Override
    public void oper(Employee user, Scanner sc, Database database) {
        // Not needed as we handle initialization in the constructor
    }

    @Override
    public String getOption() {
        return "Login";
    }
}
