import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.awt.*;

public class Secretary extends JFrame{

	private JPanel mainPanel, calendar, description;
	public Secretary()
	{
		setTitle("Application Name");
        setSize(1280, 720);
         
        // Menu bar
        Menubar menuBar = new Menubar();   
        setJMenuBar(menuBar);
        
        //Main Panel        
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        
        // Calendar
        calendar = new Calendar();
        
        
        // Brief description of an appointment
        description = new Description();
        
        // add mainPanel to 
        mainPanel.add(calendar, BorderLayout.WEST);
        mainPanel.add(description, BorderLayout.CENTER);
        add(mainPanel);
        
        
        
	}
	public static void main(String[] args) {
		Secretary me = new Secretary();
        me.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        me.setVisible(true);
	}
	
	public JPanel getMainPanel(){
		return mainPanel;
	}
	
	public JPanel getCalendar(){
		return calendar;
	}
		

}
