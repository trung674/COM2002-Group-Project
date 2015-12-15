package project;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BookAppointment extends JFrame {
	private JTextField txtPatientID, txtStartHour, txtStartMin, txtEndHour, txtEndMin;
	private JLabel lblMessage, lblPatientID, lblStartTime, lblEndTime, lblDate;
	private JButton btnConfirm;
	private static JComboBox<String> appointmentTypeList, partnerTypeList;
	private JSpinner jpDay, jpMonth, jpYear;
	private JCheckBox noPatient;
	
	static Calendar now = Calendar.getInstance();
	static ConnectDB db = new ConnectDB();
	static Connection con = db.getCon();
	
	public BookAppointment() {
		initialize();
	}
	
	/**
	 * Create form for booking new appointment
	 * @throws SQLException 
	 */
	private void initialize(){
		new JFrame();
		setTitle("Book new appointment");
		setSize(400, 500);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		setResizable(false);	
		
		// close connection to database on close
		addWindowListener(new WindowAdapter()
	       {
	           @Override
	           public void windowClosing(WindowEvent e)
	           {	       		
	       			try {
	       				con.close();
	       			} catch (SQLException ex) { } //ignored
	               e.getWindow().dispose();
	           }
	       });
		
		// Checkbox for no-patient appointment
		noPatient = new JCheckBox("Vacation");
		noPatient.setBounds(140, 30, 200, 20);
		noPatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AbstractButton abstractButton = (AbstractButton) e.getSource();
				if(abstractButton.getModel().isSelected()){
					appointmentTypeList.setEnabled(false);
					txtPatientID.setText("");
					txtPatientID.setEnabled(false);
					add(lblMessage);
					
				} else {
					appointmentTypeList.setEnabled(true);
					txtPatientID.setEnabled(true);
					remove(lblMessage);
				}
				
				validate();
				repaint();
			}
		});
		
		
		// Type of visit
		String[] appointmentType = {"Check up", "Treatment"};
		appointmentTypeList = new JComboBox<String>(appointmentType);
		appointmentTypeList.setBounds(20, 100, 150, 20);
		
		// Type of partner
		String[] partnerType = {"Dentist", "Hygienist"};
		partnerTypeList = new JComboBox<String>(partnerType);
		partnerTypeList.setBounds(230, 100, 150, 20);
	
		lblPatientID = new JLabel("Patient ID");
		lblPatientID.setBounds(20, 150, 130, 20);
		txtPatientID = new JTextField(4);
		txtPatientID.setBounds(100, 150, 200, 20);
		
		
		// this message will appear when noPatient check box is selected
		lblMessage = new JLabel("Please enter absence date and time period");
		lblMessage.setBounds(20, 200, 400, 20);
		
		// Time of the appointment
		lblStartTime = new JLabel("Start Time");
		lblStartTime.setBounds(20, 250, 100, 20);
		txtStartHour = new JTextField(2);
		txtStartHour.setBounds(130, 250, 30, 30);
		txtStartMin = new JTextField(2);
		txtStartMin.setBounds(155, 250, 30, 30 );
		lblEndTime = new JLabel("End Time");
		lblEndTime.setBounds(20, 280, 100, 20);
		txtEndHour = new JTextField(2);
		txtEndHour.setBounds(130, 280, 30, 30);
		txtEndMin = new JTextField(2);
		txtEndMin.setBounds(155, 280, 30, 30);
			
		// Date of the appointment
		lblDate = new JLabel("Date");
		lblDate.setBounds(20, 320, 100, 20);
		jpDay = new JSpinner(new SpinnerNumberModel(now.get(Calendar.DATE), 1, now.getActualMaximum(Calendar.DAY_OF_MONTH), 1));
		jpDay.setBounds(130, 320, 50, 20);
		jpMonth = new JSpinner(new SpinnerNumberModel(now.get(Calendar.MONTH) + 1, 1, 12, 1));
		jpMonth.setBounds(175, 320, 50, 20);
		jpYear = new JSpinner(new SpinnerNumberModel(now.get(Calendar.YEAR), now.get(Calendar.YEAR), now.get(Calendar.YEAR) + 20, 1));
		jpYear.setBounds(220, 320, 75, 20);
		
		btnConfirm = new JButton("Confirm");
		btnConfirm.setBounds(150, 400, 100, 40);
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
							try {
								confirm();
							} catch (SQLException e1) { } //ignored
			}
		});
		
		add(noPatient);
		add(appointmentTypeList);
		add(partnerTypeList);
		add(lblPatientID);
		add(txtPatientID);
		add(lblStartTime);
		add(txtStartHour);
		add(txtStartMin);
		add(lblEndTime);
		add(txtEndHour);
		add(txtEndMin);
		add(lblDate);
		add(jpDay);
		add(jpMonth);
		add(jpYear);
		add(btnConfirm);
		setVisible(true);
		
	}
	
	/**
	 * A function that validates the inputs for the form
	 * @param partner String - the value representing the partner of the appointment
	 * @param visitType String - the value representing the type of the appointment
	 * @param fname String - the patients firstname
	 * @param sname String - the patients surname
	 * @param day String - the value representing the day value of their date of birth
	 * @param month String - the value representing the month value of their date of birth
	 * @param year String - the value representing the year value of their date of birth
     * @param startHour String - the value representing the "start hour" value of the appointment
     * @param startMin String - the value representing the "start minute" value of the appointment
     * @param endHour String - the value representing the "end hour" value of the appointment
     * @param endMin String - the value representing the "end minute" value of the appointment
	 * @return boolean - If all the inputs are valid
	 * @throws SQLException 
	 */

	
	private  boolean validateInputs(String patientID, String day, String month, String year, String startHour, String startMin, String endHour, String endMin ) throws SQLException{
		
		boolean validInputs = true; // Inputs valid until proven otherwise
		String[] msg = {"@","@","@","@"}; // Message string for each of the patients parameters, @ denotes that there is no error
		
	
		// Validate each of the inputs, creating an error message when invalid and resetting the text field.
		
		// if noPatient check box is selected, no need to validate patient ID
		if(!noPatient.isSelected()){
			if(!validateID(patientID)){
				validInputs = false;
				msg[0] = "Input Error: patient ID is invalid.";
				txtPatientID.setText("");
			}
		}
		if(!validateDate(day,month,year)){
			validInputs = false;
			msg[2] = "Input Error: Appointment date is invalid.";
		
		}
		if(!validateTime(startHour, startMin, endHour, endMin)){
			validInputs = false;
			msg[3] = "Input Error: Appointment time is invalid.";
			txtStartHour.setText("");
			txtStartMin.setText("");
			txtEndHour.setText("");
			txtEndMin.setText("");
		}
		// If there are some invalid inputs then display the error message
		if(!validInputs){
			showErrorMessage(msg);
			return false;			
		} 

		return true;

	}
	
	
	
	/**
	 * A method to display a series of error messages
	* @param msgs String[] - The error messages
	*/
	private void showErrorMessage(String[] msgs){
			
		String errorMessage = "";
			
		// If the array value is not "@" then it is an error so display its message on a new line.
		for( int i = 0; i <= msgs.length - 1; i++){	
				   if(!msgs[i].equals("@")){
				    errorMessage = errorMessage + "\n" + msgs[i];
				   }
		}
				
		// Create the JOptionPane to show the error messages
			JOptionPane.showMessageDialog(this,errorMessage, "Input Error!",
					   JOptionPane.ERROR_MESSAGE);
			
	}
	
	/**
	 * A method to validate a patient ID
	 * @param patientID String - unique ID of the patient
	 * @return boolean - If it is valid
	 */
	private static boolean validateID(String patientID){
		return patientID.matches("[0-9]{4}"); // 4 digits, between 0 - 9
		
	}
	
	/**
	 * Validation on a date represented by 3 Strings
	 * @param day String - The day of the date
	 * @param month String - The month of the date
	 * @param year String - the year of the date
	 * @return boolean - If the date is a valid Date of Birth
	 */
	public static boolean validateDate(String day, String month, String year){
		// If either entry is null then return false
		if(day.equals(null) || month.equals(null) || year.equals(null)){
			return false;
		}

		// If either entry is empty then return false
		if(day.isEmpty() || month.isEmpty() || year.isEmpty()){
			return false;
		}

		// If the day does not contain only digits and not in the format DD then return false
		if(!(day.matches("[0-9]{2}"))){
			return false;
		}
		else{
			// If the day integer is out of range return false
			if(Integer.parseInt(day) > 31 || Integer.parseInt(day) < 1){
				return false;
			}
		
		}
		
		// If month does not contain only digits and not in the format MM then return false
		if(!(month.matches("[0-9]{2}"))){
			return false;
		}else{
			// If the month integer is not in the range then return false
			if(Integer.parseInt(month) > 12 || Integer.parseInt(month) < 1){
					return false;
			}
		}
		
		// If the year does not contain only digits and not in the format YYYYY then return false
		if(!(year.matches("[0-9]{4}"))){
			return false;
		}else if(Integer.parseInt(year) < now.get(Calendar.YEAR)){
			// If the year integer is not in range then return false
					return false;			
		}
		
		// Try to create a date object from the inputs given
		java.util.Date bDate = StringToDate(year + "-" + month + "-" + day);
		if(bDate == null){
			// if it is an invalid date then return false
			return false;
		}
		
		java.util.Date today = StringToDate(now.get(Calendar.YEAR) + "-" + (now.get(Calendar.MONTH) + 1) + "-" + now.get(Calendar.DATE));
		// If date given occurs before today then it is not valid
		if (bDate.before(today)){
			return false;
		}
		
		return true;	
		
	}
	
	/**
	 * Validation on a start time and end time 
	 * @param startHour String - The hour of the start time of the appointment
	 * @param startMin String - The minute of the start time of the appointment
	 * @param endHour String - The hour of the end time of the appointment
	 * @param endMin String - The minute of the end time of the appointment
	 * @return boolean - If the time is a valid 
	 * @throws SQLException 
	 */
	
	public static boolean validateTime(String startHour, String startMin, String endHour, String endMin) {
		// If either entry is null then return false
		
		if(startHour.equals(null) || startMin.equals(null) || endHour.equals(null) || endMin.equals(null)) {
			return false;
		}
		// If either entry is empty then return false
		if(startHour.isEmpty() || startMin.isEmpty() || endHour.isEmpty() || endMin.isEmpty()) {
			return false;
		}
		
		// Hour must be between 9 and 17
		if(Integer.parseInt(startHour) < 9 || Integer.parseInt(startHour) > 17 || Integer.parseInt(endHour) < 9 || Integer.parseInt(endHour) > 17){
			return false;
		}
		
		// Minute must be between 0 and 59
		if(Integer.parseInt(startMin) < 0 || Integer.parseInt(startMin) > 59 || Integer.parseInt(endMin) < 0 || Integer.parseInt(endMin) > 59){
			return false;
		}
		
		return true;
				
	}
	

	/**
	 * Attempts to return a Date object
	 * @param date String - The String that is to be converted into a date
	 * @return Date - The Date from the given String
	 */
	public static java.sql.Date StringToDate(String date) {
		try {
			java.util.Date uDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
			java.sql.Date sDate = new Date(uDate.getTime());
			return sDate;
		} 
		catch (ParseException e) {
			// If the string cannot be converted into a date
			return null;
		}
		

	}
	
	
	/**
	 * A method that will confirm and save an appointment to database using the inputs on the form
	 * @throws SQLException 
	 */
	private void confirm() throws SQLException{
		// Get the inputs from the form
		String partner = (String) partnerTypeList.getSelectedItem();
		String visitType = (String) appointmentTypeList.getSelectedItem();
		int patientID = 0;
		String patientID_str = txtPatientID.getText();
		if(!noPatient.isSelected()){			
			patientID = Integer.parseInt(patientID_str);
		}
		
		String day = String.valueOf(jpDay.getValue());
		String month = String.valueOf(jpMonth.getValue());
		String year = String.valueOf(jpYear.getValue());
		String startHour = txtStartHour.getText();
		String startMin = txtStartMin.getText();
		String endHour = txtEndHour.getText();
		String endMin = txtEndMin.getText();
		
		// If all inputs are valid then create a Date object to represent Date of birth and convert the house number input to integer
		if(validateInputs(patientID_str ,day,month,year, startHour, startMin, endHour, endMin)){
			java.sql.Date appointmentDate = StringToDate(year + "-" + month + "-" + day);
			Time startTime = Time.valueOf(startHour + ":" + startMin + ":00");
			Time endTime = Time.valueOf(endHour + ":" + endMin + ":00");
			createAppointment(partner, visitType, patientID, appointmentDate, startTime, endTime);
			
		}
	}
	
	/**
	 * A method that creates a new appointment in the database using the validated inputs from the form
	 * @param patientID String - the ID of the patient
	 * @param dob Date - the date of birth of the patient
	 * @param pNumber String - the patient's contact number
	 * @param hNumber - the house number of the patient's Address
	 * @param strName - the street name of patient's Address
	 * @param district - the district name of the patient's Address
	 * @param city - The town or city name of the patient's Address
	 * @param postcode - The postcode of the patients Address
	 */
	private void createAppointment(String partner, String visitType, int patientID, java.sql.Date appointmentDate, Time startTime, Time endTime){
		String query = null;
		//query = "INSERT INTO CALENDER VALUES ('" + appointmentDate + "','" + startTime + 
		//									"','" + endTime + "'," + patientID + ",'" + partner + 
		//	
		
		query = "INSERT INTO CALENDER VALUES (?, ?, ?, ?, ?, ?);" ;

		
		System.out.println(query);
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement(query);
			stmt.setDate(1, appointmentDate);
			stmt.setTime(2, startTime);
			stmt.setTime(3, endTime);
			stmt.setString(5, partner);
			if(noPatient.isSelected()){
				stmt.setNull(4, Types.INTEGER);
				stmt.setNull(6, Types.VARCHAR);
			} else {
				stmt.setInt(4, patientID);
				stmt.setString(6, visitType);
			}
			int success = stmt.executeUpdate();
			if (success > 0){
				JOptionPane.showMessageDialog(this,
					    "Successfully add new appointment",
					    "Success !",
					    JOptionPane.PLAIN_MESSAGE);
				
			} else {
				JOptionPane.showMessageDialog(this,
					    "Error ! Try again",
					    "Something went wrong !",
					    JOptionPane.PLAIN_MESSAGE);
			}
		} catch (SQLException e) {
			//ignored
		} finally {
			if (stmt != null) 
				try {
					stmt.close();
				} catch (SQLException e) { }
		}
		
		
	}
	
	public static void main(String[] args) throws SQLException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new BookAppointment();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}