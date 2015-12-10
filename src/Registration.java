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

	// Frame and components
	private JFrame frame;
	private JButton btnRegister;
	private JTextField txtForename, txtSurname,txtDay, txtMonth, txtYear, txtHNumber,txtStrName,txtCity,txtPostcode,txtDistrict,txtPhone;
	private JLabel lblForename, lblSurname,lblDateOfBirth,lblHouseNumber,lblStreetName,lblTowncity,lblPostcode,lblTitle,lblDistrict,lblPhone,lblSlash1,lblSlash2;
	private String[] titles = {"Mr", "Mrs", "Miss", "Ms","Dr"};
	private JComboBox cmbTitle;

	
	/**
	 * Launches the frame if it is run on its own via command line or IDE
	 * @param args 
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new Registration();
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
		frame.setTitle("Registration");
		frame.getContentPane().setFont(new Font("Arial", Font.PLAIN, 11));
		frame.setBounds(100, 100, 400, 500);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		// Add a button which calls the register method when clicked on
		btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				register();
			}
		});
		btnRegister.setBackground(SystemColor.inactiveCaption);
		btnRegister.setBounds(120, 380, 89, 23);
		frame.getContentPane().add(btnRegister);
		
		// Adds labels and textfields to the content pane on the frame
		JLabel lblRegistrationForm = new JLabel("Registration Form");
		lblRegistrationForm.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblRegistrationForm.setBounds(120, 20, 165, 25);
		frame.getContentPane().add(lblRegistrationForm);
		
		txtForename = new JTextField();
		txtForename.setBounds(120, 85, 140, 20);
		frame.getContentPane().add(txtForename);
		txtForename.setColumns(10);
		
		txtSurname = new JTextField();
		txtSurname.setBounds(120, 115, 141, 20);
		frame.getContentPane().add(txtSurname);
		txtSurname.setColumns(10);
		
		txtDay = new JTextField();
		txtDay.setBounds(120, 145, 25, 20);
		
		frame.getContentPane().add(txtDay);
		txtDay.setColumns(10);
		
		txtMonth = new JTextField();
		txtMonth.setColumns(10);
		txtMonth.setBounds(160, 145, 25, 20);
		
		frame.getContentPane().add(txtMonth);
		
		txtYear = new JTextField();
		txtYear.setColumns(10);
		txtYear.setBounds(200, 145, 50, 20);
			
			
		frame.getContentPane().add(txtYear);
		
		txtHNumber = new JTextField();
		txtHNumber.setBounds(120, 205, 50, 20);
		frame.getContentPane().add(txtHNumber);
		txtHNumber.setColumns(10);
		
		txtStrName = new JTextField();
		txtStrName.setColumns(10);
		txtStrName.setBounds(120, 235, 150, 20);
		frame.getContentPane().add(txtStrName);
		
		txtCity = new JTextField();
		txtCity.setColumns(10);
		txtCity.setBounds(120, 295, 150, 20);
		frame.getContentPane().add(txtCity);
		
		txtPostcode = new JTextField();
		txtPostcode.setBounds(120, 325, 85, 20);
		frame.getContentPane().add(txtPostcode);
		txtPostcode.setColumns(10);
		
		lblForename = new JLabel("Forename:");
		lblForename.setFont(new Font("Arial", Font.PLAIN, 11));
		lblForename.setBounds(20, 85, 100, 20);
		frame.getContentPane().add(lblForename);
		
		lblSurname = new JLabel("Surname:");
		lblSurname.setFont(new Font("Arial", Font.PLAIN, 11));
		lblSurname.setBounds(20, 115, 100, 20);
		frame.getContentPane().add(lblSurname);
		
		lblDateOfBirth = new JLabel("DOB(dd/mm/yyyy):");
		lblDateOfBirth.setFont(new Font("Arial", Font.PLAIN, 11));
		lblDateOfBirth.setBounds(20, 145, 100, 20);
		frame.getContentPane().add(lblDateOfBirth);
		
		lblHouseNumber = new JLabel("House Number:");
		lblHouseNumber.setFont(new Font("Arial", Font.PLAIN, 11));
		lblHouseNumber.setBounds(20, 205, 100, 20);
		frame.getContentPane().add(lblHouseNumber);
		
		lblStreetName = new JLabel("Street Name:");
		lblStreetName.setFont(new Font("Arial", Font.PLAIN, 11));
		lblStreetName.setBounds(20, 235, 85, 20);
		frame.getContentPane().add(lblStreetName);
		
		lblTowncity = new JLabel("Town/City:");
		lblTowncity.setFont(new Font("Arial", Font.PLAIN, 11));
		lblTowncity.setBounds(20, 295, 85, 20);
		frame.getContentPane().add(lblTowncity);
		
		lblPostcode = new JLabel("Postcode:");
		lblPostcode.setFont(new Font("Arial", Font.PLAIN, 11));
		lblPostcode.setBounds(20, 325, 85, 20);
		frame.getContentPane().add(lblPostcode);
		
		lblTitle = new JLabel("Title:");
		lblTitle.setFont(new Font("Arial", Font.PLAIN, 11));
		lblTitle.setBounds(20, 55, 50, 20);
		frame.getContentPane().add(lblTitle);
		
		// Creates a combo box for the titles of patients from the title array
		cmbTitle = new JComboBox(titles);
		cmbTitle.setBounds(120, 55, 80, 20);
		frame.getContentPane().add(cmbTitle);
		
	    lblDistrict = new JLabel("District:");
		lblDistrict.setFont(new Font("Arial", Font.PLAIN, 11));
		lblDistrict.setBounds(20, 265, 100, 20);
		frame.getContentPane().add(lblDistrict);
		
		txtDistrict = new JTextField();
		txtDistrict.setBounds(120, 265, 150, 20);
		frame.getContentPane().add(txtDistrict);
		txtDistrict.setColumns(10);
		
		lblPhone = new JLabel("Contact Number:");
		lblPhone.setFont(new Font("Arial", Font.PLAIN, 11));
		lblPhone.setBounds(20, 175, 100, 20);
		frame.getContentPane().add(lblPhone);
		
		txtPhone = new JTextField();
		txtPhone.setBounds(120, 175, 150, 20);
		frame.getContentPane().add(txtPhone);
		txtPhone.setColumns(10);
		
		lblSlash1 = new JLabel ("/");
		lblSlash1.setFont(new Font("Arial", Font.PLAIN, 11));
		lblSlash1.setBounds(150,145,10,20);
		frame.getContentPane().add(lblSlash1);
		
		lblSlash2 = new JLabel ("/");
		lblSlash2.setFont(new Font("Arial", Font.PLAIN, 11));
		lblSlash2.setBounds(190,145,10,20);
		frame.getContentPane().add(lblSlash2);
		
		frame.setVisible(true);
		
	}
	
	/**
	 * A function that validates the inputs for the form
	 * @param fname String - the patients firstname
	 * @param sname String - the patients surname
	 * @param day String - the value representing the day value of their date of birth
	 * @param month String - the value representing the month value of their date of birth
	 * @param year String - the value representing the year value of their date of birth
	 * @param pNumber String - the value representing the patients phone number
	 * @param houseNum String - the value representing the patient's Address house number
	 * @param strName String - Address street name of patient
	 * @param district String - Address district of patient
	 * @param city String - City of Address of the patient
	 * @param postcode String - The patients postcode
	 * @return boolean - If all the inputs are valid
	 */
	private  boolean validateInputs(String fname, String sname, String day, String month, String year, String pNumber,String houseNum, String strName,String district, String city, String postcode ){
		
		boolean validInputs = true; // Inputs valid until proven otherwise
		String[] msg = {"@","@","@","@","@","@","@","@","@"}; // Message string for each of the patients parameters, @ denotes that there is no error
		
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
		if(!validateContactNumber(pNumber)){
			validInputs = false;
			msg[3] = "Input Error: Contact Number is invalid.";
			txtPhone.setText("");
		}
		if(!validateHouseNum(houseNum)){
			validInputs = false;
			msg[4] = "Input Error: House number is invalid";
			txtHNumber.setText("");
		}
		if(!validateStreetName(strName)){
			validInputs = false;
			msg[5] = "Input Error: Street Name is invalid";
			txtStrName.setText("");
		}
		if(!validateDistrict(district)){
			validInputs = false;
			msg[6] = "Input Error: District is invalid";
			txtDistrict.setText("");
		}
		if(!validateCity(city)){
			validInputs = false;
			msg[7] = "Input Error: Town/City is invalid";
			txtCity.setText("");
		}
		if(!validatePostCode(postcode)){
			validInputs = false;
			msg[8] = "Input Error: Postcode is invalid";
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
	public static Date StringToDate(String date) {
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
	public static boolean validateHouseNum(String houseNum){
		
		return houseNum.matches("[[1-9][0-9]{0,4}?]{1,5}?");
	}
	
	/**
	 * A method that validates the street name
	 * @param strName - the street name input
	 * @return boolean - if a valid street name
	 */
	public static boolean validateStreetName(String strName){
		return (strName.matches("[A-Z]{1}[a-z]{1,35}") || strName.matches("[[A-Z]{1}[a-z]" + " " + "[A-Z]{1}[a-z]]{1,35}"));
	}
	
	/**
	 * A method that validates the district name
	 * @param district String - the district name input
	 * @return boolean - if a valid district name
	 */
	public static boolean validateDistrict(String district){
		return (district.matches("[A-Z]{1}[a-z]{1,35}") || district.matches("[[A-Z]{1}[a-z]" + " " + "[A-Z]{1}[a-z]]{1,35}"));
	}
	
	/**
	 * A method that validates the town/city name
	 * @param city String - the town or city
	 * @return boolean - if a city name is valid or not
	 */
	public static boolean validateCity(String city){
		return (city.matches("[A-Z]{1}[a-z]{1,35}") || city.matches("[[A-Z]{1}[a-z]" + " " + "[A-Z]{1}[a-z]]{1,35}"));
	}
	
	/**
	 * A method to validate a postcode
	 * @param postcode String - the postcode input
	 * @return boolean - if the postcode matches the UK postcode standard
	 */
	public static boolean validatePostCode(String postcode){
		// UK GOVERNMENT POSTCODE STANDARD REGULAR EXPRESSION
		return postcode.matches("(GIR 0AA)|((([A-Z-[QVX]][0-9][0-9]?)|(([A-Z-[QVX]][A-Z-[IJZ]][0-9][0-9]?)|(([A-Z-[QVX]][0-9][A-HJKPSTUW])|([A-Z-[QVX]][A-Z-[IJZ]][0-9][ABEHMNPRVWXY])))) [0-9][A-Z-[CIKMOV]]{2})");	
	}
	
	/**
	 * A method to validate a contact number
	 * @param pNumber - The contact number being validated
	 * @return boolean - if the number is valid
	 */
	public static boolean validateContactNumber(String pNumber){
		System.out.println(1);
		return pNumber.matches("(08001111)|((07)([0-9]{9}))|((0845464)([0-9]{1}))|((0)([0-9]{8}))|((0)([0-9]{9}))");
		
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
		String pNumber = txtPhone.getText();//.replace("\\s", "");
		System.out.println(pNumber);
		String hNumber = txtHNumber.getText();
		String strName = txtStrName.getText();
		String district = txtDistrict.getText();
		String city = txtCity.getText();
		String postcode = txtPostcode.getText();
		
		// If all inputs are valid then create a Date object to represent Date of birth and convert the house number input to integer
		if(validateInputs(fname,sname,day,month,year,pNumber,hNumber,strName,district,city,postcode)){
			Date dob = StringToDate(year + "-" + month + "-" + day);
			int houseNumber = Integer.parseInt(hNumber);
			createPatient(fname,sname,dob,pNumber,houseNumber,strName,district,city,postcode);
		}
	}
		

	/**
	 * A method that creates a new patient in the database using the validated inputs from the form
	 * @param fname String - the forname of the patient
	 * @param sname String - the surname of the patient
	 * @param dob Date - the date of birth of the patient
	 * @param pNumber String - the patient's contact number
	 * @param hNumber - the house number of the patient's Address
	 * @param strName - the street name of patient's Address
	 * @param district - the district name of the patient's Address
	 * @param city - The town or city name of the patient's Address
	 * @param postcode - The postcode of the patients Address
	 */
	private void createPatient(String fname, String sname, Date dob,String pNumber, int hNumber,String strName, String district, String city, String postcode){
		System.out.println("Adding " + fname + " " + sname + " " + dob
				+ "Contact number: " + pNumber + " " + "living at " + hNumber + " " + strName + ", " + district + ", " + city + ", " + postcode);
		
	}
}
