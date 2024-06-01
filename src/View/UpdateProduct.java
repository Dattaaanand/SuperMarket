package View;

import java.util.Scanner;

import Controller.ReadAllProducts;
import Controller.ReadProduct;
import Model.Database;
import Model.Employee;
import Model.Option;
import Model.Product;

public class UpdateProduct implements Option {

    @Override
    public void oper(Employee user, Scanner s, Database database) {
        System.out.println("Enter product ID(-1 to show all products):");
        int ID = s.nextInt();
        while (ID<0) {
            new ReadAllProducts(database).print();
            System.out.println("Enter product ID(-1 to show all products):");
            ID=s.nextInt();

        }

        Product p=new ReadProduct(ID, database).getProduct();
        System.out.println("Enter product name(-1 to keep"+p.getName()+"):");
        String name=s.next();
        if(!name.equals("-1")){
            p.setName(name);
        }

        System.out.println("Enter description (-1 to keep" +p.getDescription()+"):");
        String description=s.next();

        if(!description.equals(-1)){
            p.setDescription(description);
        }

        System.out.println("Enter Price (-1 to keep" +p.getPrice()+"$):");
        double price=s.nextDouble();

        if(price!=-1){
            p.setPrice(price);
        }

        System.out.println("Enter qty (-1 to keep" +p.getQty()+"):");
        int qty=s.nextInt();

        if(qty!=-1){
            p.setQty(qty);
        }
        
        new Controller.UpdateProduct(p, database);
    }

    @Override
    public String getOption() {
        return "Update Product's Data";
    }

}
