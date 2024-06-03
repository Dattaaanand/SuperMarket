package View;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controller.ReadAllSections;

import Model.Database;
import Model.Employee;
import Model.Option;

public class DeleteSection implements Option{

	@Override

public void oper(Employee user, Scanner s, Database database) {
    JFrame frame = new JFrame("Delete Section");
    frame.setSize(400, 200);
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frame.setLocationRelativeTo(null);
    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(3, 2, 10, 10));
    panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

    JLabel sectionIdLabel = new JLabel("Section ID (-1 to Display all Sections):");
    panel.add(sectionIdLabel);

    JTextField sectionIdField = new JTextField();
    panel.add(sectionIdField);

    JButton showSectionsButton = new JButton("Show All Sections");
    panel.add(showSectionsButton);

    JButton deleteButton = new JButton("Delete Section");
    panel.add(deleteButton);

    frame.add(panel);
    frame.setVisible(true);

    showSectionsButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            ReadAllSections readSections = new ReadAllSections(database);
            StringBuilder sectionsList = new StringBuilder();
            for (int id : readSections.getIDs()) {
                sectionsList.append("Section ID: ").append(id).append("\n");
            }
            JOptionPane.showMessageDialog(frame, sectionsList.toString(), "All Sections", JOptionPane.INFORMATION_MESSAGE);
        }
    });

    deleteButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int ID = Integer.parseInt(sectionIdField.getText());
                if (ID == -1) {
                    new ReadAllSections(database).print();
                } else {
                    ReadAllSections readSections = new ReadAllSections(database);
                    if (!readSections.getIDs().contains(ID)) {
                        JOptionPane.showMessageDialog(frame, "Invalid section ID", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    int res = JOptionPane.showConfirmDialog(frame, "Are you sure that you want to delete this section with all its products?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
                    if (res == JOptionPane.YES_OPTION) {
                        new Controller.DeleteSection(ID, database);
                        JOptionPane.showMessageDialog(frame, "Section deleted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                        frame.dispose();
                    }
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter a valid section ID", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    });
}

	@Override
	public String getOption() {
		return "Remove Section";
	}
	
}
