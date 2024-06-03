
package Controller;

import java.sql.ResultSet;
import java.util.ArrayList;

import Model.Database;
import Model.Product;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;



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


        public void print() {
            JFrame frame = new JFrame("Section Products");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setSize(800, 600);

            String[] columnNames = {"ID", "Name", "Price"};
            DefaultTableModel model = new DefaultTableModel(columnNames, 0);
            JTable table = new JTable(model);

            table.setRowHeight(30);
            table.setFont(new Font("Arial", Font.PLAIN, 14));
            table.setBackground(Color.WHITE);
            table.setForeground(Color.BLACK);
            table.setSelectionBackground(new Color(173, 216, 230));
            table.setSelectionForeground(Color.BLACK);
            table.setGridColor(Color.LIGHT_GRAY);

            JTableHeader header = table.getTableHeader();
            header.setFont(new Font("Arial", Font.BOLD, 16));
            header.setBackground(new Color(0, 153, 0));
            header.setForeground(Color.WHITE);
            header.setReorderingAllowed(false);

            DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) header.getDefaultRenderer();
            renderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);


             for (Product product : products) {
                 Object[] row = {product.getID(), product.getName(), product.getPrice()};
                 model.addRow(row);
             }

            JScrollPane scrollPane = new JScrollPane(table);
            frame.add(scrollPane, BorderLayout.CENTER);

            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        }

        public ArrayList<Product> getProducts(){
            return products;
            
        }

}



