
import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.awt.*;

public class Menubar extends JMenuBar {
	
	private JMenu newMenu, toolsMenu, aboutMenu;
	public Menubar(){
		JFrame f = new JFrame("New");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(200,200);
		
        // Define and add two drop down menu to the menu bar
        newMenu = new JMenu("New");
        
        toolsMenu = new JMenu("Tools");
        aboutMenu = new JMenu("About");

        // Create and add simple menu item to one of the drop down menu
        JMenuItem newPatient = new JMenuItem("New Patient");
        JMenuItem newAppointment = new JMenuItem("New Appointment");       
        JMenuItem viewTreatment = new JMenuItem("View Treatments");
        JMenuItem viewPatients = new JMenuItem("View Patients");
        
        newPatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Registration();				
			}
		});
        
        newAppointment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new BookAppointment();				
			}
		});
        
        viewPatients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					   new ViewPatients(null);
				} catch (SQLException e1) {
					System.out.println("Here3");
				}				
			}
		});
        
        
        
        // Add sub-menu
        newMenu.add(newPatient);
        newMenu.add(newAppointment);
        
        toolsMenu.add(viewTreatment);
        toolsMenu.add(viewPatients);
        
 		// Add menu
		this.add(newMenu);
		this.add(toolsMenu);
		this.add(aboutMenu); 	
        
	}
	
	public static void main(String[] args) {
		JFrame test = new JFrame("test");
		test.setSize(300,300);
		Menubar test1 = new Menubar();
		test.setJMenuBar(test1);
		test.setVisible(true);
	
		
	}
}