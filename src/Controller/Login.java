package Controller;

import Model.Employee;

public class Login {
	
	private String email;
	private String password;
	private Employee employee;
	
	public Login(String email, String password) {
		this.email = email;
		this.password = password;
	}
	
	public boolean isLoggedIn() {
		return false;
	}
	
	public Employee getUser() {
		return null;
	}
	
}
