package Supermarket;


import java.util.Scanner;
import Model.Admin;
import Model.Database;
import Model.Employee;
import View.Login;

public class Main {
	
	public static void main(String[] args) {
		new Login().oper(null, new Scanner(System.in), new Database());
	}

}
