package View;
import java.util.Scanner;
import Controller.ReadAllSections;
import Model.Database;
import Model.Employee;
import Model.Option;
import Model.Product;

public class CreateProduct implements Option {
	
	@Override
	public void oper(Employee user, Scanners, Database database) {
			Product product = new Product();
			
			System.out.println("Enter name:");
			product.setName(s.next()); I
			
			System.out.println("Enter description:");
			product.setDescription (s.next());
			
			System.out.println("Enter price (double):");
			product.setPrice(s.nextDouble());
			
			System.out.println("Enter qty (int):");
			product.setQty(s.nextInt());
			
			System.out.println("Enter section ID( -1 to show all sections");
			int sectionID=s.nextInt();
			ReadAllSections sections= new ReadAllSections(database);
			
			while(sectionID<0) {
				sections.print();
				System.out.println("Enter section ID( -1 to show all sections");
				int sectionID=s.nextInt();
			}
			
			if(!sections.getIDs().contains(sectionID)) {
				System.out.println("Section doesn't exist");
				return;
			}
			
			new Controller.CreateProduct(product,sectionID, sectionID, database);
			
	}
	
	public String getOption() {
		return "Add New Product";
	}
	
}
