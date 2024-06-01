package View;

import java.util.Scanner;
import Controller.ReadAllProducts;
import Controller.ReadProduct;
import Model.Database;
import Model.Employee;
import Model.Option;
import Model.Product;

public class DeleteProduct implements Option {

    @Override
    public void oper(Employee user, Scanner s, Database database) {
        System.out.println("Enter product ID(-1 to show all products):");
        int ID = s.nextInt();
        ReadAllProducts readProducts = new ReadAllProducts(database);
        while (ID<0) {
            readProducts.print();
            System.out.println("Enter product ID(-1 to show all products):");
            ID=s.nextInt();
        }
        if (readProducts.getIDs().contains(ID)){
            System.out.println("Invalid product ID");
            return;
        }
        new Controller.DeleteProduct(ID, database);


    }

    @Override
    public String getOption() {
        return "Remove Product";
    }


}
