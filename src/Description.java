package project;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.Calendar;
import java.awt.*;

public class Description extends JPanel {
	
	private JLabel lblPartner,lblForename,lblSurname,lblStartTime,lblEndTime,lblDate,lblType,lblTreatments,lblTotalCost;
	private JTextField txtPartner,txtForename,txtSurname,txtStartTime,txtEndTime,txtDate,txtType;
	private JTextArea txtAreaTreatments;
	private JPanel panelPartner, panelForename, panelSurname, panelStartTime, panelEndTime, panelDate, panelType, panelTreatments, panalTotalCost;
	private JButton btnCancel;
	private Appointment currentAppointment;
	private CalendarPane cp;
	
	public Description(CalendarPane cp) throws SQLException{
		this.cp = null;
		
		this.currentAppointment = null;
        Border padding = new EmptyBorder(0, 0, 0, 1);
        Border blackBorder = BorderFactory.createLineBorder(Color.black);
        this.setBorder(new CompoundBorder(padding, blackBorder));
        
        
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        panelPartner = new JPanel();
        panelForename = new JPanel();
        panelSurname = new JPanel();
        panelStartTime = new JPanel();
        panelEndTime = new JPanel();
        panelDate = new JPanel();
        panelType = new JPanel();
        panelTreatments = new JPanel();
        		
        		
        int height = this.getHeight();
        int width = this.getWidth();
    	
        lblPartner = new JLabel("Partner:");
        lblPartner.setHorizontalAlignment(0);
        txtPartner = new JTextField();
        txtPartner.setPreferredSize(new Dimension(100,20));
        panelPartner.add(lblPartner);
        panelPartner.add(txtPartner);
        
        lblForename = new JLabel("Forename");
        txtForename = new JTextField();
        txtForename.setPreferredSize(new Dimension(100,20));
        panelForename.add(lblForename);
        panelForename.add(txtForename);
        
        lblSurname = new JLabel("Surname");
        txtSurname = new JTextField();
        txtSurname.setPreferredSize(new Dimension(100,20));
        panelSurname.add(lblSurname);
        panelSurname.add(txtSurname);
        
        
        lblType = new JLabel("Appointment Type");
        txtType = new JTextField();
        txtType.setPreferredSize(new Dimension(100,20));
        panelType.add(lblType);
        panelType.add(txtType);
        
        lblDate = new JLabel("Date");
        txtDate = new JTextField();
        txtDate.setPreferredSize(new Dimension(100,20));
        panelDate.add(lblDate);
        panelDate.add(txtDate);
        
        lblStartTime = new JLabel("Start Time");
        txtStartTime = new JTextField();
        txtStartTime.setPreferredSize(new Dimension(100,20));
        panelStartTime.add(lblStartTime);
        panelStartTime.add(txtStartTime);
        
        
        lblEndTime = new JLabel("End Time");
        txtEndTime = new JTextField();
        txtEndTime.setPreferredSize(new Dimension(100,20));
        panelEndTime.add(lblEndTime);
        panelEndTime.add(txtEndTime);
        
        panelPartner.setLayout(new FlowLayout());
        
        btnCancel = new JButton("Cancel Appointment");
        btnCancel.setPreferredSize(new Dimension(100,40));
        btnCancel.addMouseListener(new MouseAdapter() {
        	  public void mouseClicked(MouseEvent e)  {
        		  
        		  	if(!(currentAppointment == null)){
        		  	  int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to cancel the appointment?", "Cancel Appointment?", JOptionPane.YES_NO_OPTION);

        		  	  if (JOptionPane.YES_OPTION == result) {
        		  		 try {
							Appointment.cancelAppointment(currentAppointment);
						} catch (SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
        		  		 currentAppointment = null;
        		  		 
        		  		 Calendar start = getCalendar().getStartDate();
        		  		 Calendar end = getCalendar().getEndDate();
        		  		 getCalendar().resetCalendar();
        		  		 try {
							getCalendar().getAppointments();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
        		  		 getCalendar().addAppointments();
        		  		 
        		  		 clearEntries(); 
        		  		 	
        		  	  } else if (JOptionPane.NO_OPTION == result) {
          		                System.out.println("No");
        		  	  }else{
          		            System.out.println("Nothing");
        		  	  }
        		  	}
        		  
        		 
        	  }
        });
        
        this.add(panelPartner);
        this.add(panelForename);
        this.add(panelSurname);
        this.add(panelType);
        this.add(panelDate);
        this.add(panelStartTime);
        this.add(panelEndTime);

        this.add(btnCancel);
   
        // Do something
	}
		
	private void clearEntries(){
		txtPartner.setText("");
		txtForename.setText("");
		txtSurname.setText("");
		txtType.setText("");
		txtDate.setText("");
		txtStartTime.setText("");
		txtEndTime.setText("");
	}
	
	
	public void describeAppointment(Appointment a) throws SQLException{
		this.currentAppointment = a;
		Patient p = a.getPatient();
		
		if (p == null){
			txtPartner.setText(a.getPartner());
			txtForename.setText("N/A");
			txtSurname.setText("N/A");
			txtType.setText("Holiday");
			txtDate.setText(a.getDate().toString());
			txtStartTime.setText(a.getStartTime().toString());
			txtEndTime.setText(a.getEndTime().toString());
		}else{
			txtPartner.setText(a.getPartner());
			txtForename.setText(p.getForename());
			txtSurname.setText(p.getSurname());
			txtType.setText(a.getType());
			txtDate.setText(a.getDate().toString());
			txtStartTime.setText(a.getStartTime().toString());
			txtEndTime.setText(a.getEndTime().toString());
		}
	
	}
	
	public Appointment getAppointment(){
		return this.currentAppointment;
	}
	
	public CalendarPane getCalendar(){
		return this.cp;
	}
	
	public JButton getBtnCancel(){
		return btnCancel;
	}
	
	public void setCalendar(CalendarPane c){
		this.cp = c;
	}
	
}