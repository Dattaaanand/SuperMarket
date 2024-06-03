package Model;

import java.util.Scanner;

public interface Option {
	abstract void oper(Employee user, Scanner sc, Database database);
	
	abstract String getOption();
}
