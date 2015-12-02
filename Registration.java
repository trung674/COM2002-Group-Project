

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;

/**
 * A Registration form which validates the inputs for creating patients
 * @author Aaron Ayres
 *
 */
public class Registration {

	// Frame and componenets
	private JFrame frame;
	private JButton btnRegister;
	private JTextField txtForename, txtSurname,txtDay, txtMonth, txtYear, txtHNumber,txtStrName,txtCity,txtPostcode,txtTitle;
	private JLabel lblForename, lblSurname,lblDateOfBirth,lblHouseNumber,lblStreetName,lblTowncity,lblPostcode,lblTitle;
	private String[] titles = {"Mr", "Mrs", "Miss", "Ms","Dr"};
	private JComboBox cmbTitle;
	private JTextField txtDistrict;
	
	/**
	 * Launches the frame if it is run on its own via command line or IDE
	 * @param args 
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registration window = new Registration();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Creates the form
	 */
	public Registration() {
		initialize();
	}

	/**
	 * Initialize the frame and its contents
	 */
	private void initialize() {
		
		// Set layout and bounds of the frame
		frame = new JFrame();
		frame.setBounds(100, 100, 400, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		// Add a button which calls the register method when clicked on
		btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				register();
			}
		});
		btnRegister.setBackground(SystemColor.inactiveCaption);
		btnRegister.setBounds(117, 351, 89, 23);
		frame.getContentPane().add(btnRegister);
		
		// Adds labels and textfields to the content pane on the frame
		JLabel lblRegistrationForm = new JLabel("Registration Form");
		lblRegistrationForm.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblRegistrationForm.setBounds(117, 20, 167, 23);
		frame.getContentPane().add(lblRegistrationForm);
		
		txtForename = new JTextField();
		txtForename.setBounds(117, 85, 141, 23);
		frame.getContentPane().add(txtForename);
		txtForename.setColumns(10);
		
		txtSurname = new JTextField();
		txtSurname.setBounds(117, 119, 141, 20);
		frame.getContentPane().add(txtSurname);
		txtSurname.setColumns(10);
		
		txtDay = new JTextField();
		txtDay.setBounds(117, 150, 29, 20);
		
		frame.getContentPane().add(txtDay);
		txtDay.setColumns(10);
		
		txtMonth = new JTextField();
		txtMonth.setColumns(10);
		txtMonth.setBounds(156, 150, 29, 20);
		
		frame.getContentPane().add(txtMonth);
		
		txtYear = new JTextField();
		txtYear.setColumns(10);
		txtYear.setBounds(195, 150, 63, 20);
			
			
		frame.getContentPane().add(txtYear);
		
		txtHNumber = new JTextField();
		txtHNumber.setBounds(117, 181, 89, 23);
		frame.getContentPane().add(txtHNumber);
		txtHNumber.setColumns(10);
		
		txtStrName = new JTextField();
		txtStrName.setColumns(10);
		txtStrName.setBounds(117, 215, 141, 23);
		frame.getContentPane().add(txtStrName);
		
		txtCity = new JTextField();
		txtCity.setColumns(10);
		txtCity.setBounds(117, 283, 141, 23);
		frame.getContentPane().add(txtCity);
		
		txtPostcode = new JTextField();
		txtPostcode.setBounds(117, 317, 85, 23);
		frame.getContentPane().add(txtPostcode);
		txtPostcode.setColumns(10);
		
		lblForename = new JLabel("Forename:");
		lblForename.setBounds(22, 86, 236, 21);
		frame.getContentPane().add(lblForename);
		
		lblSurname = new JLabel("Surname:");
		lblSurname.setBounds(22, 119, 236, 21);
		frame.getContentPane().add(lblSurname);
		
		lblDateOfBirth = new JLabel("DOB(dd/mm/yyyy):");
		lblDateOfBirth.setBounds(22, 148, 236, 23);
		frame.getContentPane().add(lblDateOfBirth);
		
		lblHouseNumber = new JLabel("House Number:");
		lblHouseNumber.setBounds(22, 185, 85, 14);
		frame.getContentPane().add(lblHouseNumber);
		
		lblStreetName = new JLabel("Street Name:");
		lblStreetName.setBounds(22, 219, 85, 14);
		frame.getContentPane().add(lblStreetName);
		
		lblTowncity = new JLabel("Town/City:");
		lblTowncity.setBounds(22, 287, 85, 14);
		frame.getContentPane().add(lblTowncity);
		
		lblPostcode = new JLabel("Postcode:");
		lblPostcode.setBounds(22, 320, 85, 14);
		frame.getContentPane().add(lblPostcode);
		
		lblTitle = new JLabel("Title:");
		lblTitle.setBounds(22, 55, 29, 21);
		frame.getContentPane().add(lblTitle);
		
		// Creates a combo box for the titles of patients from the title array
		cmbTitle = new JComboBox(titles);
		cmbTitle.setBounds(117, 54, 51, 20);
		frame.getContentPane().add(cmbTitle);
		
		JLabel lblDistrict = new JLabel("District:");
		lblDistrict.setBounds(22, 253, 46, 14);
		frame.getContentPane().add(lblDistrict);
		
		txtDistrict = new JTextField();
		txtDistrict.setBounds(117, 249, 141, 23);
		frame.getContentPane().add(txtDistrict);
		txtDistrict.setColumns(10);
		
	}
	
	/**
	 * A function that validates the inputs for the form
	 * @param fname String - the patients firstname
	 * @param sname String - the patients surname
	 * @param day String - the value representing the day value of their date of birth
	 * @param month String - the value representing the month value of their date of birth
	 * @param year String - the value representing the year value of their date of birth
	 * @param houseNum String - the value representing the patient's Address house number
	 * @param strName String - Address street name of patient
	 * @param district String - Address district of patient
	 * @param city String - City of Address of the patient
	 * @param postcode String - The patients postcode
	 * @return boolean - If all the inputs are valid
	 */
	private  boolean validateInputs(String fname, String sname, String day, String month, String year, String houseNum, String strName,String district, String city, String postcode ){
		
		boolean validInputs = true; // Inputs valid until proven otherwise
		String[] msg = {"@","@","@","@","@","@","@","@"}; // Message string for each of the patients parameters, @ denotes that there is no error
		
		// Validate each of the inputs, creating an error message when invalid and resetting the text field.
		if(!validateName(fname)){
			validInputs = false;
			msg[0] = "Input Error: Forname is invalid.";
			txtForename.setText("");
		}
		if(!validateName(sname)){
			validInputs = false;
			msg[1] = "Input Error: Surname is invalid.";
			txtSurname.setText("");
		}
		if(!validateDate(day,month,year)){
			validInputs = false;
			msg[2] = "Input Error: Date of Birth is invalid.";
			txtDay.setText("");
			txtMonth.setText("");
			txtYear.setText("");
		}
		if(!validateHouseNum(houseNum)){
			validInputs = false;
			msg[3] = "Input Error: House number is invalid";
			txtHNumber.setText("");
		}
		if(!validateStreetName(strName)){
			validInputs = false;
			msg[4] = "Input Error: Street Name is invalid";
			txtStrName.setText("");
		}
		if(!validateDistrict(district)){
			validInputs = false;
			msg[5] = "Input Error: District is invalid";
			txtDistrict.setText("");
		}
		if(!validateCity(city)){
			validInputs = false;
			msg[6] = "Input Error: Town/City is invalid";
			txtCity.setText("");
		}
		if(!validatePostCode(postcode)){
			validInputs = false;
			msg[7] = "Input Error: Postcode is invalid";
			txtPostcode.setText("");
		}
		
		// If there are some invalid inputs then display the error message
		if(!validInputs){
			showErrorMessage(msg);
			return false;
		}
		
		// All inputs are valid
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
			JOptionPane.showMessageDialog(this.frame,errorMessage, "Input Error!",
				    JOptionPane.ERROR_MESSAGE);
		
	}
	
	/**
	 * A method to validate a name
	 * @param name String - Surname or Forename
	 * @return boolean - If it is valid
	 */
	private static boolean validateName(String name){
		
		return name.matches("[A-Z]{1}[a-z]{2,34}?"); // Name must be capitalised
		
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
		if(day.equals(null) || month.equals(null) || year.equals(false)){
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
			System.out.println("y doesn't match pattern");
			return false;
		}else{
			// If the year integer is not in range then return false
			if(Integer.parseInt(year) < 1880){
					return false;
			}
		}
		
		// Try to create a date object from the inputs given
		Date bDate = StringToDate(year + "-" + month + "-" + day);
		if(bDate == null){
			// if it is an invalid date then return false
			return false;
		}
		// Get todays date
		Date today = (Date) Calendar.getInstance().getTime();
		
		// If DOB given occurs after today then it is not valid
		if (bDate.after(today)){
			return false;
		}else{
			return true;
		}
		
	}
	
	/**
	 * Attempts to return a Date object
	 * @param date String - The String that is to be converted into a date
	 * @return Date - The Date from the given String
	 */
	private static Date StringToDate(String date) {
		try {
			return new SimpleDateFormat("yyyy-mm-dd").parse(date);
		} 
		catch (ParseException e) {
			// If the string cannot be converted into a date
			return null;
		}
	}
	
	/**
	 * A method that validates a house number
	 * @param houseNum String - the house number input
	 * @return boolean - if it is a valid house number
	 */
	private static boolean validateHouseNum(String houseNum){
		
		return houseNum.matches("[[1-9][0-9]{0,4}?]{1,5}?");
	}
	
	/**
	 * A method that validates the street name
	 * @param strName - the street name input
	 * @return boolean - if a valid street name
	 */
	private static boolean validateStreetName(String strName){
		return (strName.matches("[A-Z]{1}[a-z]{1,40}") || strName.matches("[[A-Z]{1}[a-z]" + " " + "[A-Z]{1}[a-z]]{1,40}"));
	}
	
	/**
	 * A method that validates the district name
	 * @param district String - the district name input
	 * @return boolean - if a valid district name
	 */
	private static boolean validateDistrict(String district){
		return (district.matches("[A-Z]{1}[a-z]{1,40}") || district.matches("[[A-Z]{1}[a-z]" + " " + "[A-Z]{1}[a-z]]{1,40}"));
	}
	
	/**
	 * A method that validates the town/city name
	 * @param city String - the town or city
	 * @return boolean - if a city name is valid or not
	 */
	private static boolean validateCity(String city){
		return (city.matches("[A-Z]{1}[a-z]{1,40}") || city.matches("[[A-Z]{1}[a-z]" + " " + "[A-Z]{1}[a-z]]{1,40}"));
	}
	
	/**
	 * A method to validate a postcode
	 * @param postcode String - the postcode input
	 * @return boolean - if the postcode matches the UK postcode standard
	 */
	public static boolean validatePostCode(String postcode){
		// UK GOVERNMENT POSTCODE STANDARD
		return postcode.matches("(GIR 0AA)|((([A-Z-[QVX]][0-9][0-9]?)|(([A-Z-[QVX]][A-Z-[IJZ]][0-9][0-9]?)|(([A-Z-[QVX]][0-9][A-HJKPSTUW])|([A-Z-[QVX]][A-Z-[IJZ]][0-9][ABEHMNPRVWXY])))) [0-9][A-Z-[CIKMOV]]{2})");	
	}
	
	/**
	 * A method that will register a patient using the inputs on the form
	 */
	private void register(){
		
		// Get the inputs from the form
		String fname = txtForename.getText();
		String sname = txtSurname.getText();
		String day = txtDay.getText();
		String month = txtMonth.getText();
		String year = txtYear.getText();
		String hNumber = txtHNumber.getText();
		String strName = txtStrName.getText();
		String district = txtDistrict.getText();
		String city = txtCity.getText();
		String postcode = txtPostcode.getText();
		
		// If all inputs are valid then create a Date object to represent Date of birth and convert the house number input to integer
		if(validateInputs(fname,sname,day,month,year,hNumber,strName,district,city,postcode)){
			Date dob = StringToDate(year + "-" + month + "-" + day);
			int houseNumber = Integer.parseInt(hNumber);
			createPatient(fname,sname,dob,houseNumber,strName,district,city,postcode);
		}
	}
		

	/**
	 * A method that creates a new patient in the database using the validated inputs from the form
	 * @param fname String - the forname of the patient
	 * @param sname String - the surname of the patient
	 * @param dob Date - the date of birth of the patient
	 * @param hNumber - the house number of the patient's Address
	 * @param strName - the street name of patient's Address
	 * @param district - the district name of the patient's Address
	 * @param city - The town or city name of the patient's Address
	 * @param postcode - The postcode of the patients Address
	 */
	private void createPatient(String fname, String sname, Date dob, int hNumber,String strName, String district, String city, String postcode){
		System.out.println("Adding " + fname + " " + sname + " " + dob + " living at " + hNumber + " " + strName + ", " + district + ", " + city + ", " + postcode);
	}
}
