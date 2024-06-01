package Controller;

import Model.Product;
import Model.Database;
import java.sql.SQLException;

public class CreateProduct {
	
	
	public CreateProduct(Product p ,int section , Database database) {
		String insert="INSERT INTO "products" ("Name", "Description", "Price", Qty",
		+" "Section") VALUES ("+p.getName()+"','"+p.getDescription()+"',
		p.getPrice()+"", '" + p.getQty()+"", value-5]', '[value-6]');
		
		try {
			database.getStatement().execute(insert);
			System.out.println("Product created successfully");
		}
		catch (SQLException exception){
			exception.printStackTrace();
		}
	}
}
