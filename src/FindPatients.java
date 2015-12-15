
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import java.awt.GridLayout;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FindPatients {

	private JFrame frame;
	private JTextField txtHouseNumber;
	private JTextField txtStreetName;
	private JTextField txtDistrict;
	private JTextField txtCity;
	private JTextField txtPostcode;
	private JTextField txtPersonalID;
	private JTextField txtSurname;
	private JTextField txtDob;
	private JTextField txtNumber;
	private JTextField txtForename;
	
	private JComboBox cmbHealthPlan;
	
	private int patientID;
	private String forename;
	private String surname;
	private Date dob;
	private String contactNumber;
	
	private String houseNumber;
	private String streetName;
	private String district;
	private String city;
	private String postcode;
	
	private String healthPlanName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FindPatients window = new FindPatients();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws SQLException 
	 */
	public FindPatients() throws SQLException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 */
	private void initialize() throws SQLException {
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 650);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblFindPatients = new JLabel("Find Patients");
		lblFindPatients.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblFindPatients.setBounds(171, 11, 122, 14);
		frame.getContentPane().add(lblFindPatients);
		
		JPanel pnlHelp = new JPanel();
		pnlHelp.setBorder(new LineBorder(Color.ORANGE, 2));
		pnlHelp.setBounds(10, 36, 450, 46);
		frame.getContentPane().add(pnlHelp);
		pnlHelp.setLayout(new GridLayout(0, 1, 0, 0));
		
		JTextArea txtAreaHelp = new JTextArea();
		txtAreaHelp.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtAreaHelp.setLineWrap(true);
		txtAreaHelp.setText("Use this form to find patients using queries, if a field is left blank then it will not be used to filter the search. Ensure at least one field is not empty.");
		txtAreaHelp.setBackground(new Color(0, 0, 0, 0));
		txtAreaHelp.setWrapStyleWord(true);
		pnlHelp.add(txtAreaHelp);
		
		JPanel pnlTools = new JPanel();
		pnlTools.setBorder(new LineBorder(Color.ORANGE, 2));
		pnlTools.setBounds(10, 536, 178, 46);
		frame.getContentPane().add(pnlTools);
		pnlTools.setLayout(new GridLayout(1, 0, 0, 0));
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(!validateInputs()){
					JOptionPane.showMessageDialog(null,"Error","Some fields are not valid.",JOptionPane.ERROR_MESSAGE);
				}else{
					setQuery();
					try {
						ArrayList<Patient> matchedPatients = getPatientsFromQuery();
						if(matchedPatients.size() == 0){
							JOptionPane.showMessageDialog(null,"Result","No patients were found matching the fields.",JOptionPane.INFORMATION_MESSAGE);
						}else{
							new ViewPatients(matchedPatients);
							frame.dispose();
						}
					} catch (SQLException e) {
						
					}
				}
			}
		});
		pnlTools.add(btnSearch);
		
		JButton btnBack = new JButton("Back");
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					new ViewPatients(null);
					frame.dispose();
				} catch (SQLException e1) {
					frame.dispose();
				}
				
			}
		});
		pnlTools.add(btnBack);
		
		JPanel pnlAddress = new JPanel();
		pnlAddress.setLayout(null);
		pnlAddress.setBorder(new LineBorder(Color.ORANGE, 2));
		pnlAddress.setBounds(10, 283, 450, 162);
		frame.getContentPane().add(pnlAddress);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setHorizontalAlignment(SwingConstants.LEFT);
		lblAddress.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAddress.setBounds(136, 11, 164, 14);
		pnlAddress.add(lblAddress);
		
		JLabel lblHouseNumber = new JLabel("House Number:");
		lblHouseNumber.setBounds(10, 36, 116, 14);
		pnlAddress.add(lblHouseNumber);
		
		JLabel lblStreetName = new JLabel("Street Name:");
		lblStreetName.setBounds(10, 61, 116, 14);
		pnlAddress.add(lblStreetName);
		
		JLabel lblDistrict = new JLabel("District:");
		lblDistrict.setBounds(10, 86, 116, 14);
		pnlAddress.add(lblDistrict);
		
		JLabel lblCity = new JLabel("Town/City:");
		lblCity.setBounds(10, 111, 116, 14);
		pnlAddress.add(lblCity);
		
		JLabel lblPostcode = new JLabel("Postcode:");
		lblPostcode.setBounds(10, 136, 116, 14);
		pnlAddress.add(lblPostcode);
		
		txtHouseNumber = new JTextField();
		txtHouseNumber.setColumns(10);
		txtHouseNumber.setBounds(136, 33, 87, 20);
		pnlAddress.add(txtHouseNumber);
		
		txtStreetName = new JTextField();
		txtStreetName.setColumns(10);
		txtStreetName.setBounds(136, 58, 235, 20);
		pnlAddress.add(txtStreetName);
		
		txtDistrict = new JTextField();
		txtDistrict.setColumns(10);
		txtDistrict.setBounds(136, 83, 235, 20);
		pnlAddress.add(txtDistrict);
		
		txtCity = new JTextField();
		txtCity.setColumns(10);
		txtCity.setBounds(136, 108, 235, 20);
		pnlAddress.add(txtCity);
		
		txtPostcode = new JTextField();
		txtPostcode.setColumns(10);
		txtPostcode.setBounds(136, 133, 87, 20);
		pnlAddress.add(txtPostcode);
		
		JPanel pnlPersonalInformation = new JPanel();
		pnlPersonalInformation.setLayout(null);
		pnlPersonalInformation.setBorder(new LineBorder(Color.ORANGE, 2));
		pnlPersonalInformation.setBounds(10, 93, 450, 173);
		frame.getContentPane().add(pnlPersonalInformation);
		
		JLabel lblPersonalInformation = new JLabel("Personal Information");
		lblPersonalInformation.setHorizontalAlignment(SwingConstants.LEFT);
		lblPersonalInformation.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPersonalInformation.setBounds(136, 10, 164, 14);
		pnlPersonalInformation.add(lblPersonalInformation);
		
		JLabel lblID = new JLabel("ID:");
		lblID.setBounds(10, 38, 84, 14);
		pnlPersonalInformation.add(lblID);
		
		JLabel lblForename = new JLabel("Forename:");
		lblForename.setBounds(10, 63, 84, 14);
		pnlPersonalInformation.add(lblForename);
		
		JLabel lblSurname = new JLabel("Surname:");
		lblSurname.setBounds(10, 88, 84, 14);
		pnlPersonalInformation.add(lblSurname);
		
		JLabel lblDateOfBirth = new JLabel("Date of Birth:");
		lblDateOfBirth.setBounds(10, 116, 84, 14);
		pnlPersonalInformation.add(lblDateOfBirth);
		
		JLabel lblContactNumber = new JLabel("Contact Number:");
		lblContactNumber.setBounds(10, 141, 126, 14);
		pnlPersonalInformation.add(lblContactNumber);
		
		txtPersonalID = new JTextField();
		txtPersonalID.setColumns(10);
		txtPersonalID.setBounds(136, 35, 87, 20);
		pnlPersonalInformation.add(txtPersonalID);
		
		txtForename = new JTextField();
		txtForename.setColumns(10);
		txtForename.setBounds(136, 60, 229, 20);
		pnlPersonalInformation.add(txtForename);
		
		txtSurname = new JTextField();
		txtSurname.setColumns(10);
		txtSurname.setBounds(136, 85, 229, 20);
		pnlPersonalInformation.add(txtSurname);
		
		txtDob = new JTextField();
		txtDob.setColumns(10);
		txtDob.setBounds(136, 113, 112, 20);
		pnlPersonalInformation.add(txtDob);
		
		txtNumber = new JTextField();
		txtNumber.setColumns(10);
		txtNumber.setBounds(136, 138, 229, 20);
		pnlPersonalInformation.add(txtNumber);
		
		JPanel pnlHealthplan = new JPanel();
		pnlHealthplan.setLayout(null);
		pnlHealthplan.setBorder(new LineBorder(Color.ORANGE, 2));
		pnlHealthplan.setBounds(10, 456, 450, 69);
		frame.getContentPane().add(pnlHealthplan);
		
		JLabel lblHealthPlan = new JLabel("Health Plan");
		lblHealthPlan.setHorizontalAlignment(SwingConstants.CENTER);
		lblHealthPlan.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblHealthPlan.setBounds(88, 11, 164, 14);
		pnlHealthplan.add(lblHealthPlan);
		
		JLabel lblHealthName = new JLabel("Health Plan:");
		lblHealthName.setBounds(10, 36, 113, 14);
		pnlHealthplan.add(lblHealthName);
		
	    cmbHealthPlan = new JComboBox();
	    cmbHealthPlan.addItem("");
		cmbHealthPlan.setBounds(133, 33, 307, 20);
		
		ArrayList<HealthcarePlan> plans = HealthcarePlan.getAllPlans();
		for(HealthcarePlan plan: plans){
			cmbHealthPlan.addItem(plan.getHealthcareName());
		}
		pnlHealthplan.add(cmbHealthPlan);
		
		frame.setVisible(true);
	}
	
	
	public boolean validateInputs(){
		
		if(!txtPersonalID.getText().isEmpty()){
			try {
				 int id = Integer.parseInt(txtPersonalID.getText());
				    if(id < 1000){
				    	return false;
				    }
				
			} catch (NumberFormatException e) {
				 return false;
			}
		}
		
		if(!txtForename.getText().isEmpty() && !Registration.validateName(txtForename.getText())){
			return false;
		}else if(!txtSurname.getText().isEmpty() && !Registration.validateName(txtSurname.getText())){
			return false;
		}else if(!txtDob.getText().isEmpty() && validateDateInput(txtDob.getText())){
			return false;
		}else if(!txtNumber.getText().isEmpty() && !Registration.validateContactNumber(txtNumber.getText())){
			return false;
		}else if(!txtHouseNumber.getText().isEmpty() && !Registration.validateHouseNum(txtHouseNumber.getText())){
			return false;
		}else if(!txtStreetName.getText().isEmpty() && !Registration.validateStreetName(txtStreetName.getText())){
			return false;
		}else if(!txtDistrict.getText().isEmpty() && !Registration.validateDistrict(txtDistrict.getText())){
			return false;
		}else if(!txtCity.getText().isEmpty() && !Registration.validateCity(txtCity.getText())){
			return false;
		}else if(!txtPostcode.getText().isEmpty() && !Registration.validatePostCode(txtPostcode.getText())){
			return false;
		}
		
		return true;

	}
	
	public void setQuery(){
		
		if(txtPersonalID.getText().isEmpty()){
			this.patientID = -1;
		}else{
			this.patientID = Integer.parseInt(txtPersonalID.getText());
		}
		
		if(txtForename.getText().isEmpty()){
			this.forename = null;
		}else{
			this.forename = txtForename.getText();
		}
		
		if(txtSurname.getText().isEmpty()){
			this.surname = null;
		}else{
			this.surname = txtSurname.getText();
		}
		
		if(txtDob.getText().isEmpty()){
			this.dob = null;
		}else{
			this.dob = Registration.StringToDate(txtDob.getText());
		}
		
		if(txtNumber.getText().isEmpty()){
			this.contactNumber = null;
		}else{
			this.contactNumber = txtNumber.getText();
		}
		
		if(txtHouseNumber.getText().isEmpty()){
			this.houseNumber = null;
		}else{
			this.houseNumber = txtHouseNumber.getText();
		}
		
		if(txtStreetName.getText().isEmpty()){
			this.streetName = null;
		}else{
			this.streetName = txtStreetName.getText();
		}
		
		if(txtDistrict.getText().isEmpty()){
			this.district = null;
		}else{
			this.district = txtDistrict.getText();
		}
		
		if(txtCity.getText().isEmpty()){
			this.city = null;
		}else{
			this.city = txtCity.getText();
		}
		
		if(txtPostcode.getText().isEmpty()){
			this.postcode = null;
		}else{
			this.postcode = txtPostcode.getText();
		}
		
		if(cmbHealthPlan.getSelectedItem().equals("")){
			this.healthPlanName = null;
		}else{
			this.healthPlanName = (String) cmbHealthPlan.getSelectedItem();
		}
		
		
		
	}
	
	public boolean emptyID(){
		return this.patientID == -1;
	}
	
	public boolean emptyForename(){
		return this.forename == null;
	}
	
	public boolean emptySurname(){
		return this.surname == null;
	}
	
	public boolean emptyDOB(){
		return this.dob == null;
	}
	
	public boolean emptyContactNumber(){
		return this.contactNumber == null;
	}
	
	public boolean emptyHouseNumber(){
		return this.houseNumber == null;
	}
	
	public boolean emptyStreetName(){
		return this.streetName == null;
	}
	
	public boolean emptyDistrict(){
		return this.district == null;
	}
	
	public boolean emptyCity(){
		return this.city == null;
	}
	
	public boolean emptyPostcode(){
		return this.postcode == null;
	}
	
	public boolean emptyHealthPlan(){
		return this.healthPlanName == null;
	}
	
	public ArrayList<Patient> getPatientsFromQuery() throws SQLException{
		ArrayList<Patient> patients = Patient.getAllPatients();
		ArrayList<Patient> matchedPatients = new ArrayList<Patient>();
		
		for(Patient p: patients){
			if(patientMatch(p)){
				matchedPatients.add(p);
			}
		}
		
		
		return matchedPatients;
	}

	private boolean patientMatch(Patient p){
		
		if(!(emptyID())){
			if(!(this.patientID == p.getID())){
				return false;
			}
		}
		
		if(!(emptyForename())){
			if(!(this.forename.equals(p.getForename())))
				return false;
		}
		
		if(!(emptySurname())){
			if(!(this.surname.equals(p.getSurname()))){
				return false;
			}
		}
		
		if(!(emptyDOB())){
			if(!(sameDay(this.dob,p.getDateOfBirth()))){
				return false;
			}
		}
		
		if(!(emptyContactNumber())){
			if(!(this.contactNumber.equals(p.getPhoneNumber())))
				return false;
		}
		
		if(!(emptyHouseNumber())){
			if(p.getAdddress() == null){
				return false;
			}else if(!(this.houseNumber.equals(p.getAdddress().getHouseNumber()))){
				return false;
			}
		}
		
		if(!(emptyStreetName())){
			if(p.getAdddress() == null){
				return false;
			}else if(!(this.streetName.equals((p.getAdddress().getStreetName())))){
				return false;
			}
		}
		
		if(!(emptyDistrict())){
			if(p.getAdddress() == null){
				return false;
			}else if(!(this.district.equals((p.getAdddress().getDistrict())))){
				return false;
			}
		}
		
		if(!(emptyCity())){
			if(p.getAdddress() == null){
				return false;
			}else if(!(this.city.equals((p.getAdddress().getCity())))){
				return false;
			}
		}
		
		if(!(emptyPostcode())){
			if(p.getAdddress() == null){
				return false;
			}else if(!(this.postcode.equals((p.getAdddress().getPostcode())))){
				return false;
			}
		}
		
		if(!(emptyHealthPlan())){
			if(p.getHealthcarePlan() == null){
				return false;
			}else if(!(this.healthPlanName.equals(p.getHealthcarePlan().getHealthcareName()))){
				return false;
			}
		}
		
		return true; // If matches
	
			
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
	
	public boolean validateDateInput(String dateString){
		if(dateString.length() != 10){
			return false;
		}else{
			Date d = Registration.StringToDate(dateString);
			if(d == null){
				return false;
			}else{
				return true;
			}
		}
		
		
	}
}
