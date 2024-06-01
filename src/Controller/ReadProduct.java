package Controller;

import java.sql.ResultSet;

import Model.Database;
import Model.Product;

public class ReadProduct {

    private Product p;

    
    public ReadProduct(int ID,Database database) {
        String select="Select * from `products` where `ID` = "+ID+";";

        try{
                ResultSet rs=database.getStatement().executeQuery(select);

                while (rs.next()) {
                    p= new Product();
                    p.setID(rs.getInt("ID"));
                    p.setName(rs.getString("Name"));
                    p.setDescription(rs.getString("Description"));
                    p.setPrice(rs.getDouble("Price"));
                    p.setQty(rs.getInt("Qty"));
                }
        }catch (Exception e){
                e.printStackTrace();
            }
    }

    public Product getProduct(){
        return p;
    }

}
