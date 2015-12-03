import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Menubar extends JMenuBar {
	
	private JMenu newMenu, toolsMenu, aboutMenu;
	public Menubar(){
        // Define and add two drop down menu to the menubar
        newMenu = new JMenu("New");
        toolsMenu = new JMenu("Tools");
        aboutMenu = new JMenu("About");

        // Create and add simple menu item to one of the drop down menu
        JMenuItem newPatient = new JMenuItem("New Patient");
        JMenuItem newAppointment = new JMenuItem("New Appointment");
         
        // Add sub-menu
        newMenu.add(newPatient);
        newMenu.add(newAppointment);
        
 		// Add menu
		this.add(newMenu);
		this.add(toolsMenu);
		this.add(aboutMenu);
        // Add a listener to the New menu item. actionPerformed() method will
        // invoked, if user triggred this menu item
        newPatient.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open new window to add patient
            	
            	
            	// Open new window to add new appointment
            }
        });
        
	}
}
