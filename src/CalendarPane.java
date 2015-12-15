

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.Calendar;
import java.sql.*;
import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 * A Class to represent a weekly calendar of appointments
 * @author Aaron Ayres
 */
public class CalendarPane extends JPanel{
	
	public static int MILLISECONDS_IN_MINUITE = 1000 * 60;
	public static int MILLISECONDS_IN_HOUR = MILLISECONDS_IN_MINUITE * 60;
	
	private Description description;
	private JTabbedPane tabbedPane;
	private JButton btnPrevWeek,btnNextWeek,btnPrevMonth,btnNextMonth,btnPrevYear,btnNextYear;
	private JTable dMon,dTue,dWen,dThu,dFri,hMon,hTue,hWen,hThu,hFri;
	private JPanel navigation, navigationButtons,dentistPanel,hygienistPanel,dentistMonday,dentistTuesday,dentistWednesday,dentistThursday,dentistFriday,hygienistMonday,hygienistTuesday,hygienistWednesday,hygienistThursday,hygienistFriday;
	private Calendar startDate,endDate;
	private JLabel date,lblDMonday,lblDTuesday,lblDWednesday,lblDThursday,lblDFriday,lblHMonday,lblHTuesday,lblHWednesday,lblHThursday,lblHFriday;

	private String[] titles = {"Start Time","End Time","Appoinment Type"};
	
	private ArrayList<Appointment> app;
	
	/**
	 * A method to run the calendarPane on its own
	 * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CalendarPane j = new CalendarPane(null);
					j.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * A method to get the Calendar date for the start of the calendar
	 * @return Calendar - The start date of the calendar panel
	 */
	public Calendar getStartDate(){
		return this.startDate;
	}
	
	/**
	 * A method to get the Calendar date for the end of the calendar
	 * @return Calendar - The end date of the calendar panel
	 */
	public Calendar getEndDate(){
		return this.endDate;
	}

	/**
	 * Create the application.
	 * @throws SQLException 
	 */
	public CalendarPane(Description d) throws SQLException {
		this.description = d;
		Calendar c = Calendar.getInstance();
		startDate = (Calendar) c.clone();
		endDate = (Calendar) c.clone();
		initialize(startDate,endDate);
	}

	/**
	 * Initialises the calendar to the given start date and end date
	 * @param startOfWeek - The start of the week for the calendar
	 * @param endOfWeek - The end of the week for the calendar
	 * @throws SQLException
	 */
	public void initialize(Calendar startOfWeek, Calendar endOfWeek) throws SQLException {
		
		this.startDate = startOfWeek;
		this.endDate = endOfWeek;
	    setStartDate();
		setEndDate();
		
		this.app = new ArrayList<Appointment>();
		
		// Layout
	    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		
		// Navigation Panel
		
		
		navigation = new JPanel();
		navigation.setLayout(new GridLayout(2,1,0,0));
		
	    date = new JLabel(getDateString());
	    date.setFont(new Font("Tahoma", Font.BOLD, 13));
	    date.setHorizontalAlignment(SwingConstants.CENTER);
	    
	    
	    navigationButtons = new JPanel();
	    navigationButtons.setLayout(new GridLayout(1,6,0,10));
	    
	    btnNextWeek = new JButton("Next Week >");
	    btnNextWeek.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		try {
					nextWeek();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	    		date.setText(getDateString());
	    	}
	    });
	    
	    btnPrevWeek = new JButton("< Previous Week");
	    btnPrevWeek.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		try {
					prevWeek();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	    		date.setText(getDateString());
	    	}
	    });
	    
	    btnNextMonth = new JButton("Next Month >>");
	    btnNextMonth.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		try {
					nextMonth();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	    		date.setText(getDateString());
	    	}
	    });
	    
	    btnPrevMonth = new JButton("<< Previous Month");
	    btnPrevMonth.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		try {
					prevMonth();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	    		date.setText(getDateString());
	    	}
	    });
	    
	    btnNextYear = new JButton("Next Year >>>");
	    btnNextYear.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		try {
					nextYear();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	    		date.setText(getDateString());
	    	}
	    });
	    
	    btnPrevYear = new JButton("<<< Previous Year");
	    btnPrevYear.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		try {
					prevYear();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	    		date.setText(getDateString());
	    	}
	    });
	    
	    btnNextWeek.setBackground(Color.orange);
	    btnNextWeek.setBorderPainted(true);
	    btnNextMonth.setBackground(Color.orange);
	    btnNextMonth.setBorderPainted(true);
	    btnNextYear.setBackground(Color.orange);
	    btnNextYear.setBorderPainted(true);
	    
	    btnPrevWeek.setBackground(Color.orange);
	    btnPrevWeek.setBorderPainted(true);
	    btnPrevMonth.setBackground(Color.orange);
	    btnPrevMonth.setBorderPainted(true);
	    btnPrevYear.setBackground(Color.orange);
	    btnPrevYear.setBorderPainted(true);
	    
	    
	    navigationButtons.add(btnPrevYear);
	    navigationButtons.add(btnPrevMonth);
	    navigationButtons.add(btnPrevWeek);
	    navigationButtons.add(btnNextWeek);
	    navigationButtons.add(btnNextMonth);
	    navigationButtons.add(btnNextYear);
	    
	    
	    
	    navigation.add(date);
	    navigation.add(navigationButtons);
	    
	    this.add(navigation);
	    
	    this.setVisible(true);
		
		// Calendars
		
	    tabbedPane = new JTabbedPane();
	    
	    dentistPanel = new JPanel();
        dentistPanel.setLayout(new GridLayout(5,1,0,10));
        dentistPanel.setBounds(0, 0, 400, 900);
        

	    hygienistPanel = new JPanel();
	    hygienistPanel.setLayout(new GridLayout(5,1,0,10));
	    hygienistPanel.setBounds(0, 0, 400, 900);
        
        dentistMonday = new JPanel();
        dentistTuesday = new JPanel();
        dentistWednesday = new JPanel();
        dentistThursday = new JPanel();
        dentistFriday = new JPanel();
        hygienistMonday = new JPanel();
        hygienistTuesday = new JPanel();
        hygienistWednesday = new JPanel();
        hygienistThursday = new JPanel();
        hygienistFriday = new JPanel();
        
        lblDMonday = new JLabel("Monday");	   
        lblDMonday.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblDMonday.setHorizontalAlignment(SwingConstants.CENTER);
        lblDMonday.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
	    
        lblHMonday = new JLabel("Monday");
        lblHMonday.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblHMonday.setHorizontalAlignment(SwingConstants.CENTER);
        lblHMonday.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
	    
        lblDTuesday = new JLabel("Tuesday");
        lblDTuesday.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblDTuesday.setHorizontalAlignment(SwingConstants.CENTER);
        lblDTuesday.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
	    
        lblHTuesday = new JLabel("Tuesday");
        lblHTuesday.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblHTuesday.setHorizontalAlignment(SwingConstants.CENTER);
        lblHTuesday.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
	    
        lblDWednesday = new JLabel("Wednesday");
        lblDWednesday.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblDWednesday.setHorizontalAlignment(SwingConstants.CENTER);
        lblDWednesday.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
	    
        lblHWednesday = new JLabel("Wednesday");
        lblHWednesday.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblHWednesday.setHorizontalAlignment(SwingConstants.CENTER);
        lblHWednesday.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
	    
        lblDThursday = new JLabel("Thursday");
        lblDThursday.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblDThursday.setHorizontalAlignment(SwingConstants.CENTER);
        lblDThursday.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
	    
        lblHThursday = new JLabel("Thursday");
        lblHThursday.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblHThursday.setHorizontalAlignment(SwingConstants.CENTER);
        lblHThursday.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
	    
        lblDFriday = new JLabel("Friday");
        lblDFriday.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblDFriday.setHorizontalAlignment(SwingConstants.CENTER);
        lblDFriday.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
	    
        lblHFriday  = new JLabel("Friday");
        lblHFriday.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblHFriday.setHorizontalAlignment(SwingConstants.CENTER);
        lblHFriday.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        
        
        
        // Mon
        
        DefaultTableModel model = new DefaultTableModel(0, titles.length) {
         	public boolean isCellEditable(int rowIndex, int colIndex) {
        		return false; //Disallow the editing of any cell
        	}
         	
        };
        
        
        model.setColumnIdentifiers(titles);
        this.dMon = new JTable();
        this.dMon.setModel(model);
        
	    JTableHeader header = this.dMon.getTableHeader();
        header.setBackground(Color.PINK);
        
        
        this.dMon.addMouseListener(new MouseAdapter() {
        	  public void mouseClicked(MouseEvent e) {
        	    		  int row=dMon.rowAtPoint(e.getPoint());
        	    		  int col= dMon.columnAtPoint(e.getPoint());
        	    	      dMon.getSelectionModel().clearSelection();
        	    	      try {
							appointmentDescription((String)dMon.getValueAt(row, 0),2,"Dentist");
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
        	    	      
        	    	     
        	  }});
        
        // Tues
        DefaultTableModel model2 = new DefaultTableModel(0, titles.length) {
         	public boolean isCellEditable(int rowIndex, int colIndex) {
        		return false; //Disallow the editing of any cell
        	}
        };
        
       
        model2.setColumnIdentifiers(titles);
        this.dTue = new JTable();
        this.dTue.setModel(model2);
        
	    JTableHeader header2 = this.dTue.getTableHeader();
        header2.setBackground(Color.PINK);
        
        
        this.dTue.addMouseListener(new MouseAdapter() {
        	  public void mouseClicked(MouseEvent e) {
        	    		  int row=dTue.rowAtPoint(e.getPoint());
        	    		  int col= dTue.columnAtPoint(e.getPoint());
        	    		  try {
							appointmentDescription((String)dTue.getValueAt(row, 0),3,"Dentist");
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
        	    	      
        	    	      // Code to display appointment details
        	  }});
       
        // Wen
        
        DefaultTableModel model3 = new DefaultTableModel(0, titles.length) {
         	public boolean isCellEditable(int rowIndex, int colIndex) {
        		return false; //Disallow the editing of any cell
        	}
        };
        
       
        model3.setColumnIdentifiers(titles);
        this.dWen = new JTable();
        this.dWen.setModel(model3);
        
	    JTableHeader header3 = this.dWen.getTableHeader();
        header3.setBackground(Color.PINK);
        
        
        this.dWen.addMouseListener(new MouseAdapter() {
        	  public void mouseClicked(MouseEvent e) {
        	    		  int row=dWen.rowAtPoint(e.getPoint());
        	    		  int col= dWen.columnAtPoint(e.getPoint());
        	    		  try {
							appointmentDescription((String)dWen.getValueAt(row, 0),4,"Dentist");
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
        	    	      
        	    	      // Code to display appointment details
        	  }});
        
        // Thu
        
        DefaultTableModel model4 = new DefaultTableModel(0, titles.length) {
         	public boolean isCellEditable(int rowIndex, int colIndex) {
        		return false; //Disallow the editing of any cell
        	}
        };
        
       
        model4.setColumnIdentifiers(titles);
        this.dThu = new JTable();
        this.dThu.setModel(model4);
        
	    JTableHeader header4 = this.dThu.getTableHeader();
        header4.setBackground(Color.PINK);
        
        
        this.dThu.addMouseListener(new MouseAdapter() {
        	  public void mouseClicked(MouseEvent e) {
        	    		  int row=dThu.rowAtPoint(e.getPoint());
        	    		  int col= dThu.columnAtPoint(e.getPoint());
        	    	       dThu.getSelectionModel().clearSelection();
        	    	       try {
							appointmentDescription((String)dThu.getValueAt(row, 0),5,"Dentist");
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
        	    	      
        	    	      // Code to display appointment details
        	  }});
        
        // Fri
        
        DefaultTableModel model5 = new DefaultTableModel(0, titles.length) {
         	public boolean isCellEditable(int rowIndex, int colIndex) {
        		return false; //Disallow the editing of any cell
        	}
        };
        
       
        model5.setColumnIdentifiers(titles);
        this.dFri = new JTable();
        this.dFri.setModel(model5);
        
	    JTableHeader header5 = this.dFri.getTableHeader();
        header5.setBackground(Color.PINK);
        
        
        this.dFri.addMouseListener(new MouseAdapter() {
        	  public void mouseClicked(MouseEvent e) {
        	    		  int row=dFri.rowAtPoint(e.getPoint());
        	    		  int col= dFri.columnAtPoint(e.getPoint());
        	    		  dFri.getSelectionModel().clearSelection();
        	    		  try {
							appointmentDescription((String)dFri.getValueAt(row, 0),6,"Dentist");
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
        	    	      
        	    	      // Code to display appointment details
        	  }});
        
        //
        
        JScrollPane scr1 = new JScrollPane(this.dMon);
        //scr1.setBounds(this.dMon.getBounds());
        JScrollPane scr2 = new JScrollPane(dTue);
       // scr2.setBounds(dTue.getBounds());
        JScrollPane scr3 = new JScrollPane(dWen);
        //scr3.setBounds(dWen.getBounds());
        JScrollPane scr4 = new JScrollPane(dThu);
       // scr4.setBounds(dThu.getBounds());
        JScrollPane scr5 = new JScrollPane(dFri);
        //scr5.setBounds(dFri.getBounds());
        
        
        
        dentistMonday.add(lblDMonday);
        dentistMonday.add(scr1);
        dentistMonday.setLayout(new BoxLayout(dentistMonday,BoxLayout.Y_AXIS));
        
        dentistTuesday.add(lblDTuesday);
        dentistTuesday.add(scr2);
        dentistTuesday.setLayout(new BoxLayout(dentistTuesday,BoxLayout.Y_AXIS));
        
        dentistWednesday.add(lblDWednesday);
        dentistWednesday.add(scr3);
        dentistWednesday.setLayout(new BoxLayout(dentistWednesday,BoxLayout.Y_AXIS));
        
        dentistThursday.add(lblDThursday);
        dentistThursday.add(scr4);
        dentistThursday.setLayout(new BoxLayout(dentistThursday,BoxLayout.Y_AXIS));
        
        dentistFriday.add(lblDFriday);
        dentistFriday.add(scr5);
        dentistFriday.setLayout(new BoxLayout(dentistFriday,BoxLayout.Y_AXIS));
        
  
        
        dentistPanel.add(dentistMonday);
        dentistPanel.add(dentistTuesday);
        dentistPanel.add(dentistWednesday);
        dentistPanel.add(dentistThursday);
        dentistPanel.add(dentistFriday);
        
        

        
        // Hygienist
        
        DefaultTableModel model6 = new DefaultTableModel(0, titles.length) {
         	public boolean isCellEditable(int rowIndex, int colIndex) {
        		return false; //Disallow the editing of any cell
        	}
        };
        
        
        model6.setColumnIdentifiers(titles);
        this.hMon = new JTable();
        this.hMon.setModel(model6);
        
	    JTableHeader header6 = this.hMon.getTableHeader();
	    header6.setBackground(Color.LIGHT_GRAY);
        
        
        this.hMon.addMouseListener(new MouseAdapter() {
        	  public void mouseClicked(MouseEvent e) {
        	    		  int row=hMon.rowAtPoint(e.getPoint());
        	    		  int col= hMon.columnAtPoint(e.getPoint());
        	    	      hMon.getSelectionModel().clearSelection();
          	    		  try {
							appointmentDescription((String)hMon.getValueAt(row, 0),2,"Hygienist");
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
        	    	      
        	    	      // Code to display appointment details
        	  }});
        
        // Tues
        DefaultTableModel model7 = new DefaultTableModel(0, titles.length) {
         	public boolean isCellEditable(int rowIndex, int colIndex) {
        		return false; //Disallow the editing of any cell
        	}
        };
        
       
        model7.setColumnIdentifiers(titles);
        this.hTue = new JTable();
        this.hTue.setModel(model7);
        
	    JTableHeader header7 = this.hTue.getTableHeader();
	    header7.setBackground(Color.LIGHT_GRAY);
        
        
        this.hTue.addMouseListener(new MouseAdapter() {
        	  public void mouseClicked(MouseEvent e) {
        	    		  int row=hTue.rowAtPoint(e.getPoint());
        	    		  int col= hTue.columnAtPoint(e.getPoint());
        	    	      hTue.getSelectionModel().clearSelection();
            	          try {
							appointmentDescription((String)hTue.getValueAt(row, 0),3,"Hygienist");
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
        	    	      
        	    	      // Code to display appointment details
        	  }});
       
        // Wen
        
        DefaultTableModel model8 = new DefaultTableModel(0, titles.length) {
         	public boolean isCellEditable(int rowIndex, int colIndex) {
        		return false; //Disallow the editing of any cell
        	}
        };
        
       
        model8.setColumnIdentifiers(titles);
        this.hWen = new JTable();
        this.hWen.setModel(model8);
        
	    JTableHeader header8 = this.hWen.getTableHeader();
	    header8.setBackground(Color.LIGHT_GRAY);
        
        
        this.hWen.addMouseListener(new MouseAdapter() {
        	  public void mouseClicked(MouseEvent e) {
        	    		  int row=hWen.rowAtPoint(e.getPoint());
        	    		  int col= hWen.columnAtPoint(e.getPoint());
        	    		  hWen.getSelectionModel().clearSelection();
            	          try {
							appointmentDescription((String)hWen.getValueAt(row, 0),4,"Hygienist");
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
        	    	      
        	    	      // Code to display appointment details
        	  }});
        
        // Thu
        
        DefaultTableModel model9 = new DefaultTableModel(0, titles.length) {
         	public boolean isCellEditable(int rowIndex, int colIndex) {
        		return false; //Disallow the editing of any cell
        	}
        };
        
       
        model9.setColumnIdentifiers(titles);
        this.hThu = new JTable();
        this.hThu.setModel(model9);
        
	    JTableHeader header9 = this.hThu.getTableHeader();
	    header9.setBackground(Color.LIGHT_GRAY);
        
        
        this.hThu.addMouseListener(new MouseAdapter() {
        	  public void mouseClicked(MouseEvent e) {
        	    		  int row=hThu.rowAtPoint(e.getPoint());
        	    		  int col= hThu.columnAtPoint(e.getPoint());
        	    	        hThu.getSelectionModel().clearSelection();
        	    	      try {
							appointmentDescription((String)hThu.getValueAt(row, 0),5,"Hygienist");
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
        	    	      
        	    	      // Code to display appointment details
        	  }});
        
        // Fri
        
        DefaultTableModel model10 = new DefaultTableModel(0, titles.length) {
         	public boolean isCellEditable(int rowIndex, int colIndex) {
        		return false; //Disallow the editing of any cell
        	}
        };
        
       
        model10.setColumnIdentifiers(titles);
        this.hFri = new JTable();
        this.hFri.setModel(model10);
        
	    JTableHeader header10 = this.hFri.getTableHeader();
        header10.setBackground(Color.LIGHT_GRAY);
        
        
        this.hFri.addMouseListener(new MouseAdapter() {
        	  public void mouseClicked(MouseEvent e) {
        	    		  int row=hFri.rowAtPoint(e.getPoint());
        	    		  int col= hFri.columnAtPoint(e.getPoint());
        	    		  hFri.getSelectionModel().clearSelection();
            	          try {
							appointmentDescription((String)hFri.getValueAt(row, 0),6,"Hygienist");
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
        	    	      
        	    	      // Code to display appointment details
        	  }});
        
        //
        
        JScrollPane scr6 = new JScrollPane(hMon);
        JScrollPane scr7 = new JScrollPane(hTue);
        JScrollPane scr8 = new JScrollPane(hWen);
        JScrollPane scr9 = new JScrollPane(hThu);
        JScrollPane scr10 = new JScrollPane(hFri);
        
        
        
        hygienistMonday.add(lblHMonday);
        hygienistMonday.add(scr6);
        hygienistMonday.setLayout(new BoxLayout(hygienistMonday,BoxLayout.Y_AXIS));
        
        hygienistTuesday.add(lblHTuesday);
        hygienistTuesday.add(scr7);
        hygienistTuesday.setLayout(new BoxLayout(hygienistTuesday,BoxLayout.Y_AXIS));
        
        hygienistWednesday.add(lblHWednesday);
        hygienistWednesday.add(scr8);
        hygienistWednesday.setLayout(new BoxLayout(hygienistWednesday,BoxLayout.Y_AXIS));
        
        hygienistThursday.add(lblHThursday);
        hygienistThursday.add(scr9);
        hygienistThursday.setLayout(new BoxLayout(hygienistThursday,BoxLayout.Y_AXIS));
        
        hygienistFriday.add(lblHFriday);
        hygienistFriday.add(scr10);
        hygienistFriday.setLayout(new BoxLayout(hygienistFriday,BoxLayout.Y_AXIS));
        
        
        hygienistPanel.add(hygienistMonday);
        hygienistPanel.add(hygienistTuesday);
        hygienistPanel.add(hygienistWednesday);
        hygienistPanel.add(hygienistThursday);
        hygienistPanel.add(hygienistFriday);
        

        tabbedPane.addTab("Dentist",dentistPanel);
        tabbedPane.addTab("Hygienist", hygienistPanel);
        
        
		this.add(tabbedPane);
		this.setVisible(true);
		
		getAppointments();
		addAppointments();
		
	
	}
	
	/**
	 * resets the contents of the JTables for the calendar
	 */
	public void resetCalendar(){
		
		this.app = new ArrayList<Appointment>();
		
		DefaultTableModel model = (DefaultTableModel)dMon.getModel(); 
		int rows = model.getRowCount(); 
		for(int i = rows - 1; i >=0; i--)
		{
		   model.removeRow(i); 
		}
		
		model = (DefaultTableModel)dTue.getModel(); 
	    rows = model.getRowCount(); 
		for(int i = rows - 1; i >=0; i--)
		{
		   model.removeRow(i); 
		}
		
		model = (DefaultTableModel)dWen.getModel(); 
	    rows = model.getRowCount(); 
		for(int i = rows - 1; i >=0; i--)
		{
		   model.removeRow(i); 
		}
		
		
		model = (DefaultTableModel)dThu.getModel(); 
	    rows = model.getRowCount(); 
		for(int i = rows - 1; i >=0; i--)
		{
		   model.removeRow(i); 
		}
		
		model = (DefaultTableModel)dFri.getModel(); 
	    rows = model.getRowCount(); 
		for(int i = rows - 1; i >=0; i--)
		{
		   model.removeRow(i); 
		}
		
		
		model = (DefaultTableModel)hMon.getModel(); 
	    rows = model.getRowCount(); 
		for(int i = rows - 1; i >=0; i--)
		{
		   model.removeRow(i); 
		}
		
		
		model = (DefaultTableModel)hTue.getModel(); 
	    rows = model.getRowCount(); 
		for(int i = rows - 1; i >=0; i--)
		{
		   model.removeRow(i); 
		}
		
		model = (DefaultTableModel)hWen.getModel(); 
	    rows = model.getRowCount(); 
		for(int i = rows - 1; i >=0; i--)
		{
		   model.removeRow(i); 
		}
		model = (DefaultTableModel)hThu.getModel(); 
	    rows = model.getRowCount(); 
		for(int i = rows - 1; i >=0; i--)
		{
		   model.removeRow(i); 
		}
		
		model = (DefaultTableModel)hFri.getModel(); 
	    rows = model.getRowCount(); 
		for(int i = rows - 1; i >=0; i--)
		{
		   model.removeRow(i); 
		}
		
		
		
	}
	/**
	 * Set the startDate of the calendar week to the nearest Monday
	 */
	private void setStartDate(){
	    // Get the start date of the week
	    if (this.startDate.get(Calendar.DAY_OF_WEEK) > 2){
	    	while (this.startDate.get(Calendar.DAY_OF_WEEK) > 2){
	    		this.startDate.add(Calendar.DATE, -1);
	    	}
	    }
	    else if(this.startDate.get(Calendar.DAY_OF_WEEK) == 1){
	    	this.startDate.add(Calendar.DATE, +1);
	    }
	    
	}
	
	/**
	 * Set the endDate of the calendar week to the nearest Friday
	 */
	private void setEndDate(){
		  if (this.endDate.get(Calendar.DAY_OF_WEEK) < 6){
		    	while(this.endDate.get(Calendar.DAY_OF_WEEK) < 6){
		    		this.endDate.add(Calendar.DATE, +1);
		    	}
		    }else if(this.endDate.get(Calendar.DAY_OF_WEEK) == 7){
		    	this.endDate.add(Calendar.DATE, -1);
		 }
	}
	
	/**
	 * A method that gets the current calendar date range in a easy to read format
	 * @return String - The date range as a String
	 */
	private String getDateString(){
			    
	    java.util.Date startDate =  this.startDate.getTime();      
	    java.util.Date endDate =  this.endDate.getTime();
	    
	    SimpleDateFormat format = new SimpleDateFormat("dd MMMM YYYY");     

	    String s = "Monday " + format.format(startDate);
	    String e = "Friday " + format.format(endDate);
	    
	    return s + " - " + e;
		
	}
	
	/**
	 * A method which sets the startDate and EndDate of the Calendar week to the next week
	 * And then call other methods to reset the Calendar to display the appointments for the new
	 * date range.
	 * @throws SQLException
	 */
	
	private void nextWeek() throws SQLException{
		this.startDate.add(Calendar.DATE, + 7);
		this.endDate.add(Calendar.DATE, + 7);
		resetCalendar();
		getAppointments();
		addAppointments();
	}
	
	/**
	 * A method which sets the startDate and EndDate of the Calendar week to the previous week
	 * And then call other methods to reset the Calendar to display the appointments for the new
	 * date range.
	 * @throws SQLException
	 */
	private void prevWeek() throws SQLException{
		this.startDate.add(Calendar.DATE, - 7);
		this.endDate.add(Calendar.DATE, - 7);
		resetCalendar();
		getAppointments();
		addAppointments();
		
	}
	
	/**
	 * Sets the calendar start and end dates for the frame to the first in the month
	 */
	private void setClosestInFuture(){
		while(this.startDate.get(Calendar.DAY_OF_WEEK) != 2){
			this.startDate.add(Calendar.DATE, + 1);
			this.endDate.add(Calendar.DATE, + 1);
		}
		while(this.endDate.get(Calendar.DAY_OF_WEEK) != 6){
			this.endDate.add(Calendar.DATE, + 1);
		}
	}
	
	/**
	 * A method which sets the startDate and EndDate of the Calendar week to the next month
	 * And then call other methods to reset the Calendar to display the appointments for the new
	 * date range.
	 * @throws SQLException
	 */
	
	private void nextMonth() throws SQLException{
		
		if(endDate.get(Calendar.MONTH) > startDate.get(Calendar.MONTH)){
			this.startDate.add(Calendar.MONTH, + 1);
			this.startDate.set(Calendar.DAY_OF_MONTH,1);
			this.endDate.set(Calendar.DAY_OF_MONTH,1);
			setClosestInFuture();

		}
		else{

			this.startDate.add(Calendar.MONTH, + 1);
			this.startDate.set(Calendar.DAY_OF_MONTH,1);
			this.endDate.add(Calendar.MONTH, + 1);
			this.endDate.set(Calendar.DAY_OF_MONTH,1);
			setClosestInFuture();
		}
		
		resetCalendar();
		getAppointments();
		addAppointments();
		
	}
	
	/**
	 * A method which sets the startDate and EndDate of the Calendar week to the previous month
	 * And then call other methods to reset the Calendar to display the appointments for the new
	 * date range.
	 * @throws SQLException
	 */
	
	private void prevMonth() throws SQLException{
		
		if(endDate.get(Calendar.MONTH) > startDate.get(Calendar.MONTH)){
			this.startDate.add(Calendar.MONTH, - 1);
			this.startDate.set(Calendar.DAY_OF_MONTH,1);
			this.endDate.add(Calendar.MONTH, - 2);
			this.endDate.set(Calendar.DAY_OF_MONTH,1);
		}
		else{
			this.startDate.add(Calendar.MONTH, - 1);
			this.startDate.set(Calendar.DAY_OF_MONTH,1);
			this.endDate.add(Calendar.MONTH, - 1);
			this.endDate.set(Calendar.DAY_OF_MONTH,1);
		}
		setClosestInFuture();
		resetCalendar();
		getAppointments();
		addAppointments();
	}
	
	/**
	 * A method which sets the startDate and EndDate of the Calendar week to the next year
	 * And then call other methods to reset the Calendar to display the appointments for the new
	 * date range.
	 * @throws SQLException
	 */
	private void nextYear() throws SQLException{
		
		if(endDate.get(Calendar.YEAR) > startDate.get(Calendar.YEAR)){
			this.startDate.add(Calendar.YEAR, + 1);
			this.startDate.set(Calendar.DAY_OF_MONTH,1);
			this.endDate.add(Calendar.MONTH, - 1);
			this.endDate.add(Calendar.YEAR, + 1);
			this.endDate.set(Calendar.DAY_OF_MONTH,1);
		}else{
			
			if(endDate.get(Calendar.MONTH) > startDate.get(Calendar.MONTH)){
				this.startDate.add(Calendar.YEAR, + 1);
				this.startDate.set(Calendar.DAY_OF_MONTH,1);
				this.endDate.add(Calendar.MONTH ,-1);
				this.endDate.add(Calendar.YEAR, + 1);
				this.endDate.set(Calendar.DAY_OF_MONTH,1);
			}else{
				this.startDate.add(Calendar.YEAR, + 1);
				this.startDate.set(Calendar.DAY_OF_MONTH,1);
				this.endDate.add(Calendar.YEAR, + 1);
				this.endDate.set(Calendar.DAY_OF_MONTH,1);
			}
			
		}
		setClosestInFuture();
		resetCalendar();
		getAppointments();
		addAppointments();
	
	}
	
	/**
	 * A method which sets the startDate and EndDate of the Calendar week to the previous year
	 * And then call other methods to reset the Calendar to display the appointments for the new
	 * date range.
	 * @throws SQLException
	 */
	private void prevYear() throws SQLException{
		
		
		
		if(endDate.get(Calendar.YEAR) > startDate.get(Calendar.YEAR)){
			this.startDate.add(Calendar.YEAR, - 1);
			this.startDate.set(Calendar.DAY_OF_MONTH,1);
			this.endDate.add(Calendar.YEAR, - 1);
			this.endDate.add(Calendar.MONTH, - 1);
			this.endDate.set(Calendar.DAY_OF_MONTH,1);
		}else{
			if(endDate.get(Calendar.MONTH) > startDate.get(Calendar.MONTH)){
				this.startDate.add(Calendar.YEAR, - 1);
				this.startDate.set(Calendar.DAY_OF_MONTH,1);
				this.endDate.add(Calendar.MONTH ,-1);
				this.endDate.add(Calendar.YEAR, - 1);
				this.endDate.set(Calendar.DAY_OF_MONTH,1);
			}else{
				this.startDate.add(Calendar.YEAR, - 1);
				this.startDate.set(Calendar.DAY_OF_MONTH,1);
				this.endDate.add(Calendar.YEAR, - 1);
				this.endDate.set(Calendar.DAY_OF_MONTH,1);
			}
		}
		

		
		
		setClosestInFuture();
		resetCalendar();
		getAppointments();
		addAppointments();
		
	}
	
	/**
	 * A method to get all the appointments for this week
	 * @throws SQLException
	 */
	public void getAppointments() throws SQLException{
		this.app = new ArrayList<Appointment>();
		this.app = Appointment.getAppointments(this.startDate, this.endDate);
		
	}
	 
   /**
    * A method to addAppointments in the given week to the calendar JTables
    */
   public void addAppointments(){
	    Iterator<Appointment> itr = this.app.iterator();
	    
	    //use hasNext() and next() methods of Iterator to iterate through the elements
	    while(itr.hasNext()){
	      Appointment a = itr.next();
	      Date d = a.getDate();
	      Calendar c = Calendar.getInstance();
	      c.setTime(d);
	      int day = c.get(Calendar.DAY_OF_WEEK);
	      addAppointment(a,day);
	   
	  }
	}
	
    /**
     * A method to add an appointment to one of the day JTables based on the appointment date and partner
     * @param a Appointment - the appointment to be added to a Table
     * @param dayOfWeek int - The day of the week ( 1 - Sunday, 7- Saturday )
     */
	private void addAppointment(Appointment a, int dayOfWeek){
		String strSTime = a.getStartTime().toString().substring(0, 5);
		String strETime = a.getEndTime().toString().substring(0, 5);
		String type = "";
		
		if(a.getType() == null){
			type = "Holiday";
		}else{
			type = a.getType();
		}

		if (a.getPartner().equals("Dentist")){
			if( dayOfWeek == 2){
				DefaultTableModel model =  (DefaultTableModel) this.dMon.getModel();
				model.addRow(new Object[]{strSTime,strETime,type});
				model.fireTableDataChanged();
			}
			else if(dayOfWeek == 3){
				DefaultTableModel model = (DefaultTableModel) dTue.getModel();
				model.addRow(new Object[]{strSTime,strETime,type});
				
			}
			else if(dayOfWeek == 4){
				DefaultTableModel model = (DefaultTableModel) dWen.getModel();
				model.addRow(new Object[]{strSTime,strETime,type});
			}
			else if(dayOfWeek == 5){
				DefaultTableModel model = (DefaultTableModel) dThu.getModel();
				model.addRow(new Object[]{strSTime,strETime,type});
			}else{
				DefaultTableModel model = (DefaultTableModel) dFri.getModel();
				model.addRow(new Object[]{strSTime,strETime,type});
			}
		}else{
			if( dayOfWeek == 2){
				DefaultTableModel model = (DefaultTableModel) hMon.getModel();
				model.addRow(new Object[]{strSTime,strETime,type});
			}
			else if(dayOfWeek == 3){
				DefaultTableModel model = (DefaultTableModel) hTue.getModel();
				model.addRow(new Object[]{strSTime,strETime,type});
			}
			else if(dayOfWeek == 4){
				DefaultTableModel model = (DefaultTableModel) hWen.getModel();
				model.addRow(new Object[]{strSTime,strETime,type});
			}
			else if(dayOfWeek == 5){
				DefaultTableModel model = (DefaultTableModel) hThu.getModel();
				model.addRow(new Object[]{strSTime,strETime,type});
			}else{
				DefaultTableModel model = (DefaultTableModel) hFri.getModel();
				model.addRow(new Object[]{strSTime,strETime,type});
			}
		}
	}
	
	/**
	 * A method that displays more information a given appointment from the calendar table on the description panel
	 * @param time String - The start time of the appointment from the table in format HH:MM:SS
	 * @param dayOfWeek int - The day of the week (1 = Sunday 7 = Saturday)
	 * @param partner String - The type of partner the appointment is for
	 * @throws SQLException - The method contains methods that access the database and therefore must throw and Exception
	 */
	private void appointmentDescription(String time,int dayOfWeek,String partner) throws SQLException{
		int hours = Integer.parseInt(time.substring(0,2));
		int minuites = Integer.parseInt(time.substring(3,5));
		
		Time startTime = new Time((hours - 1) * MILLISECONDS_IN_HOUR + minuites * MILLISECONDS_IN_MINUITE );
		Calendar c = (Calendar)startDate.clone();
		c.set(Calendar.MILLISECOND, 0);
		
		c.add(Calendar.DATE, dayOfWeek - 2);
		
		java.util.Date d1 = c.getTime();
		java.sql.Date d = new java.sql.Date(d1.getTime());
		
		Appointment a = getAppointment(startTime,d);
		
		if (a == null){
			System.out.println("ERROR");
		}else{
			// GET APPOINTMENT DETAILS FROM SQL QUERY AND FILL IN DESCRIPTION
			this.description.describeAppointment(a);
		}
	}
	/**
	 * A method that give a time and date will iterate through all appointments and return the appointment with the same start time and date
	 * @param t Time - the start time of the appointment
	 * @param d Date - the date of the appointment
	 * @return Appointment - The appointment on that date at the given time
	 */
	private Appointment getAppointment(Time t, Date d){
		  Iterator<Appointment> itr = this.app.iterator();
		  while(itr.hasNext()){
		      Appointment a = itr.next();
		      Date date = a.getDate();
		      Time start = a.getStartTime();
		      	      
		      if(sameDay(date,d) && start.equals(t)){
		    	  return a;
		      }
		   
		  }
		  return null;
	}

	/**
	 * A method which determines if two sql dates are the same (excluding milliseconds of the dates)
	 * @param d1 Date - The first date to be compared
	 * @param d2 Date - The seconds date to be compared
	 * @return boolean - If the dates are on the same day
	 */
	public static boolean sameDay(Date d1, Date d2){
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.setTime(d1);
		cal2.setTime(d2);
		return( cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
		                  cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR));
	}
}
