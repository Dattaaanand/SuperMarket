package View;

import java.util.Scanner;

import Model.Database;
import Model.Employee;
import Model.Option;
import Controller.ReadAllSections;

public class ReadSectionProducts implements Option {

    @Override 
    public void oper(Employee user, Scanner s, Database database) {

        System.out.println("Enter section ID(-1 to show all sections):");
        int section=s.nextInt();
        while (section<0) {
            new ReadAllSections(database).print();
            System.out.println("Enter section ID(-1 to show all sections):");
            section=s.nextInt();
        }
        new Controller.ReadSectionProducts(section,database).print();
        
    }

    @Override
    public String getOption() {
        return "View Section Products";
    }



}




