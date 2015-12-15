package project;

import javax.swing.*;
import javax.swing.border.*;
import java.sql.SQLException;
import java.awt.*;

public class Secretary extends JFrame{
	

	private JPanel mainPanel;
	private Menubar menuBar;
	private Description d;
	private CalendarPane cp;

	public Secretary() throws SQLException
	{
		setTitle("Dental System");
        setSize(1280, 720);
        setLocationRelativeTo(null);
        
        // Menu bar
        menuBar = new Menubar();   
        setJMenuBar(menuBar);
        
        // Calendar Pane
        d = new Description(null);
        this.cp = new CalendarPane(d);
        d.setCalendar(this.cp);
        this.cp.setVisible(true);
        
    	Border padding = new EmptyBorder(0, 1, 0, 1);
        Border blackBorder = BorderFactory.createLineBorder(Color.black);
        this.cp.setBorder(new CompoundBorder(padding, blackBorder));
        this.cp.setPreferredSize(new Dimension(900,300));
        
        // Description
      
       
        //Main Panel        
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(this.cp, BorderLayout.WEST);
        mainPanel.add(d, BorderLayout.CENTER);	
        

        add(mainPanel);
     
	}
	
	public static void main(String[] args) throws SQLException {
		Secretary me = new Secretary();
        me.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        me.setVisible(true);
	}
	
	public JPanel getMainPanel(){
		return mainPanel;
	}
	
	public CalendarPane getCalendar(){
		return cp;
	}
	
	public Description getDescription(){
		return d;
	}
	
	public Menubar getMenubar(){
		return menuBar;
	}

	public void setMenubar(Menubar m){
		menuBar = m;
	}

	

		

}
