package Controller;

import java.sql.SQLException;

import Model.Database;

public class DeleteProduct {

    public DeleteProduct(int ID,Database database){
        String delete=" Delete from `products` where `id` = "+ID+";";

        try {
			database.getStatement().execute(delete);
			System.out.println("Product Deleted Successfully");
		}
		catch (SQLException exception) {
			exception.printStackTrace();
		}



    }
}
