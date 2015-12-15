import javax.swing.*;
import java.awt.event.*;

/**
 * Menu bar for Secretary interface
 *
 */
public class Menubar extends JMenuBar {
	
	// Components
	private JMenu newMenu, aboutMenu;
	
	public Menubar(){
		JFrame f = new JFrame("New");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(200,200);
		
        // Define and add two drop down menu to the menu bar
        newMenu = new JMenu("New");
        aboutMenu = new JMenu("About");
        // Create and add simple menu item to one of the drop down menu
        JMenuItem newPatient = new JMenuItem("New Patient");
        JMenuItem newAppointment = new JMenuItem("New Appointment");       
        
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
        // Add sub-menu
        newMenu.add(newPatient);
        newMenu.add(newAppointment);
       	
        // Add JMenu to JMenubar
		this.add(newMenu);
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
