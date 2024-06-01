
package Controller;

import java.sql.ResultSet;
import java.util.ArrayList;

import Model.Database;
import Model.Product;

public class ReadSectionProducts {
        private ArrayList<Product> products;

        public ReadSectionProducts(int section,Database database){
            String select="Select * from `products` where `Section` = "+section+";";
            products=new ArrayList<>();
            
            try{
                ResultSet rs=database.getStatement().executeQuery(select);

                while (rs.next()) {
                    Product p= new Product();
                    p.setID(rs.getInt("ID"));
                    p.setName(rs.getString("Name"));
                    p.setDescription(rs.getString("Description"));
                    p.setPrice(rs.getDouble("Price"));
                    p.setQty(rs.getInt("Qty"));
                    products.add(p);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        public void print(){
            for(Product p: products){
                p.print();
            }
            System.out.println("-------------------");
        }

        public ArrayList<Product> getProducts(){
            return products;
            
        }

}



