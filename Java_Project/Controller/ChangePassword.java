package Controller;
public class ChangePassword{
	public ChangePassword(String password, Employee user, Database database) {
		String update="UPDATE 'employees' SET 'Password' ='"+password+"'WHERE 'ID' = '"+user.getID()+"';";
		try {
			database.getStatement().execute(update);
			System.out.println("Password changed successfully");
		}catch (SQLException exception) {
			exception.printStrackTrace();
		}
	}
}