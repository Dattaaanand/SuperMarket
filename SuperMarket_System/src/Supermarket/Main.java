package Supermarket;

import javax.swing.SwingUtilities;

import Model.Database;
import View.Login;

public class Main {

    public static void main(String[] args) {
        Database database = new Database();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Login(database);
            }
        });
    }
}
