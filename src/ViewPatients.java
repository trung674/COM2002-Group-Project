package project;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.Panel;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ViewPatients {

	private JFrame frame;
	private JTextField txtID;
	private JTextField txtForename;
	private JTextField txtSurname;
	private JTextField txtDob;
	private JTextField txtHouseNumber;
	private JTextField txtStreetName;
	private JTextField txtDistrict;
	private JTextField txtCity;
	private JTextField txtPostcode;
	private JTextField txtHealthName;
	private JTextField txtMonthlyCost;
	private JTextField txtCheckups;
	private JTextField txtHygieneVisits;
	private JTextField txtRepairs;
	private JTextField txtMoreTreatments;
	
	public final static String NEWLINE = "\n";
	private JTextField txtTotalcost;
	private JTextField txtContactNumber;
	
	private ArrayList<Patient> patients;
	private Patient currentPatient;
	private int currentPatientIndex;
	
	
	private JTextField txtTitle;
	
	// Healthplan update form
	private JFrame jfHealthPlan;
	private JComboBox cmbHealthPlan;
	private JTextField txtCurrent;
	private JTextArea txtAreaTreatments;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewPatients window = new ViewPatients(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * A methods that creats a new ViewPatients Frame
	 * @throws SQLException 
	 */
	public ViewPatients(ArrayList<Patient> queryPatients) throws SQLException {
		initialize(queryPatients);
	}

	/**
	 * Create a new frame to view the patients
	 * @throws SQLException 
	 */
	private void initialize(ArrayList<Patient> queryPatients) throws SQLException {
		
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				frame.dispose();
			}
		});
		frame.setBounds(100, 100, 900, 700);
		frame.getContentPane().setLayout(null);
		
		JLabel lblPatientRecord = new JLabel("Patient Record");
		lblPatientRecord.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPatientRecord.setBounds(303, 11, 134, 14);
		frame.getContentPane().add(lblPatientRecord);
		
		JPanel pnlInformation = new JPanel();
		pnlInformation.setBorder(new LineBorder(Color.ORANGE, 2));
		pnlInformation.setBounds(10, 36, 427, 204);
		frame.getContentPane().add(pnlInformation);
		pnlInformation.setLayout(null);
		
		JLabel lblInfoTitle = new JLabel("Personal Information");
		lblInfoTitle.setHorizontalAlignment(SwingConstants.LEFT);
		lblInfoTitle.setBounds(136, 10, 164, 14);
		pnlInformation.add(lblInfoTitle);
		lblInfoTitle.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel lblPatientId = new JLabel("ID:");
		lblPatientId.setBounds(10, 38, 84, 14);
		pnlInformation.add(lblPatientId);
		
		JLabel lblForename = new JLabel("Forename:");
		lblForename.setBounds(10, 98, 84, 14);
		pnlInformation.add(lblForename);
		
		JLabel lblSurname = new JLabel("Surname:");
		lblSurname.setBounds(10, 123, 84, 14);
		pnlInformation.add(lblSurname);
		
		JLabel lblDateOfBirth = new JLabel("Date of Birth:");
		lblDateOfBirth.setBounds(10, 151, 84, 14);
		pnlInformation.add(lblDateOfBirth);
		
		JLabel lblContactNumber = new JLabel("Contact Number:");
		lblContactNumber.setBounds(10, 176, 126, 14);
		pnlInformation.add(lblContactNumber);
		
		txtID = new JTextField();
		txtID.setBounds(136, 35, 87, 20);
		pnlInformation.add(txtID);
		txtID.setColumns(10);
		
		txtForename = new JTextField();
		txtForename.setColumns(10);
		txtForename.setBounds(136, 95, 229, 20);
		pnlInformation.add(txtForename);
		
		txtSurname = new JTextField();
		txtSurname.setColumns(10);
		txtSurname.setBounds(136, 120, 229, 20);
		pnlInformation.add(txtSurname);
		
		txtDob = new JTextField();
		txtDob.setColumns(10);
		txtDob.setBounds(136, 148, 112, 20);
		pnlInformation.add(txtDob);
		
		txtContactNumber = new JTextField();
		txtContactNumber.setColumns(10);
		txtContactNumber.setBounds(136, 173, 229, 20);
		pnlInformation.add(txtContactNumber);
		
		JLabel lblTitle = new JLabel("Title:");
		lblTitle.setBounds(10, 67, 46, 14);
		pnlInformation.add(lblTitle);
		
		txtTitle = new JTextField();
		txtTitle.setColumns(10);
		txtTitle.setBounds(136, 64, 46, 20);
		pnlInformation.add(txtTitle);
		
		JPanel pnlAddress = new JPanel();
		pnlAddress.setBorder(new LineBorder(Color.ORANGE, 2));
		pnlAddress.setBounds(10, 251, 427, 162);
		frame.getContentPane().add(pnlAddress);
		pnlAddress.setLayout(null);
		
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
		
		JLabel label = new JLabel("District:");
		label.setBounds(10, 86, 116, 14);
		pnlAddress.add(label);
		
		JLabel lblTowncity = new JLabel("Town/City:");
		lblTowncity.setBounds(10, 111, 116, 14);
		pnlAddress.add(lblTowncity);
		
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
		
		JPanel pnlHealthPlan = new JPanel();
		pnlHealthPlan.setLayout(null);
		pnlHealthPlan.setBorder(new LineBorder(Color.ORANGE, 2));
		pnlHealthPlan.setBounds(10, 424, 427, 162);
		frame.getContentPane().add(pnlHealthPlan);
		
		JLabel lblHealthPlan = new JLabel("Health Plan");
		lblHealthPlan.setHorizontalAlignment(SwingConstants.CENTER);
		lblHealthPlan.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblHealthPlan.setBounds(88, 11, 164, 14);
		pnlHealthPlan.add(lblHealthPlan);
		
		JLabel lblHealthPlanName = new JLabel("Name:");
		lblHealthPlanName.setBounds(10, 36, 102, 14);
		pnlHealthPlan.add(lblHealthPlanName);
		
		JLabel lblMonthlyCost = new JLabel("Monthly cost:");
		lblMonthlyCost.setBounds(10, 61, 102, 14);
		pnlHealthPlan.add(lblMonthlyCost);
		
		JLabel lblNum = new JLabel("No. checkups:");
		lblNum.setBounds(10, 86, 102, 14);
		pnlHealthPlan.add(lblNum);
		
		JLabel lblNo = new JLabel("No.  hygiene_visits:");
		lblNo.setBounds(10, 111, 114, 14);
		pnlHealthPlan.add(lblNo);
		
		JLabel lblNoRepairs = new JLabel("No. repairs:");
		lblNoRepairs.setBounds(10, 136, 102, 14);
		pnlHealthPlan.add(lblNoRepairs);
		
		txtHealthName = new JTextField();
		txtHealthName.setColumns(10);
		txtHealthName.setBounds(133, 33, 211, 20);
		pnlHealthPlan.add(txtHealthName);
		
		txtMonthlyCost = new JTextField();
		txtMonthlyCost.setColumns(10);
		txtMonthlyCost.setBounds(132, 58, 63, 20);
		pnlHealthPlan.add(txtMonthlyCost);
		
		txtCheckups = new JTextField();
		txtCheckups.setColumns(10);
		txtCheckups.setBounds(132, 83, 63, 20);
		pnlHealthPlan.add(txtCheckups);
		
		txtHygieneVisits = new JTextField();
		txtHygieneVisits.setColumns(10);
		txtHygieneVisits.setBounds(132, 108, 63, 20);
		pnlHealthPlan.add(txtHygieneVisits);
		
		txtRepairs = new JTextField();
		txtRepairs.setColumns(10);
		txtRepairs.setBounds(132, 133, 63, 20);
		pnlHealthPlan.add(txtRepairs);
		
		JPanel pnlTreatmentsAndCosts = new JPanel();
		pnlTreatmentsAndCosts.setLayout(null);
		pnlTreatmentsAndCosts.setBorder(new LineBorder(Color.ORANGE, 2));
		pnlTreatmentsAndCosts.setBounds(454, 36, 420, 519);
		frame.getContentPane().add(pnlTreatmentsAndCosts);
		
		JLabel lblTreatments = new JLabel("Treatments");
		lblTreatments.setHorizontalAlignment(SwingConstants.CENTER);
		lblTreatments.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTreatments.setBounds(128, 11, 164, 14);
		pnlTreatmentsAndCosts.add(lblTreatments);
		
		JLabel lblNeedMoreTreatments = new JLabel("More treatments required?:");
		lblNeedMoreTreatments.setBounds(10, 290, 206, 14);
		pnlTreatmentsAndCosts.add(lblNeedMoreTreatments);
		
		txtMoreTreatments = new JTextField();
		txtMoreTreatments.setColumns(10);
		txtMoreTreatments.setBounds(226, 287, 78, 20);
		pnlTreatmentsAndCosts.add(txtMoreTreatments);
		
		Panel pnlTreatments = new Panel();
		pnlTreatments.setBounds(10, 39, 398, 242);
		pnlTreatmentsAndCosts.add(pnlTreatments);
		pnlTreatments.setLayout(new BoxLayout(pnlTreatments, BoxLayout.X_AXIS));
		
		txtAreaTreatments = new JTextArea();
		txtAreaTreatments.setAlignmentY(Component.TOP_ALIGNMENT);
		txtAreaTreatments.setAlignmentX(Component.RIGHT_ALIGNMENT);
		txtAreaTreatments.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtAreaTreatments.setLineWrap(true);
		txtAreaTreatments.setWrapStyleWord(true);
		pnlTreatments.add(txtAreaTreatments);
		
		JButton btnPay = new JButton("Payments Made");
		btnPay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!(currentPatient == null)){
					try {
						makePayments();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						reset();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			}
			
		});
		btnPay.setBackground(SystemColor.control);
		btnPay.setForeground(Color.BLACK);
		btnPay.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnPay.setBounds(226, 343, 182, 42);
		pnlTreatmentsAndCosts.add(btnPay);
		
		JLabel lblTotalCost = new JLabel("Total cost of treatments:");
		lblTotalCost.setBounds(10, 315, 206, 14);
		pnlTreatmentsAndCosts.add(lblTotalCost);
		
		txtTotalcost = new JTextField();
		txtTotalcost.setColumns(10);
		txtTotalcost.setBounds(226, 312, 182, 20);
		pnlTreatmentsAndCosts.add(txtTotalcost);
		
		JPanel pnlPrevNext = new JPanel();
		pnlPrevNext.setBorder(new LineBorder(Color.ORANGE, 2));
		pnlPrevNext.setBounds(10, 597, 212, 53);
		frame.getContentPane().add(pnlPrevNext);
		pnlPrevNext.setLayout(new GridLayout(1, 0, 0, 0));
		
		JButton btnPrev = new JButton("< Previous");
		btnPrev.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(currentPatientIndex == 0){
					currentPatientIndex = patients.size() - 1;
					currentPatient = patients.get(currentPatientIndex);
				}else{
					currentPatient = patients.get(currentPatientIndex - 1);
					currentPatientIndex -= 1;
				}
				txtAreaTreatments.setText(null);
				displayPatient();
				try {
					getTreatments();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		btnPrev.setBackground(SystemColor.control);
		pnlPrevNext.add(btnPrev);
		
		JButton btnNext = new JButton("Next >");
		btnNext.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(patients.size() == currentPatientIndex + 1){
					currentPatientIndex = 0;
					currentPatient = patients.get(0);
				}else{
					currentPatient = patients.get(currentPatientIndex + 1);
					currentPatientIndex += 1;
				}
				txtAreaTreatments.setText(null);
				displayPatient();
				try {
					getTreatments();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNext.setBackground(SystemColor.control);
		pnlPrevNext.add(btnNext);
		
		JPanel pnlUpdatesAndQuery = new JPanel();
		pnlUpdatesAndQuery.setBorder(new LineBorder(Color.ORANGE, 2));
		pnlUpdatesAndQuery.setBounds(377, 597, 497, 53);
		frame.getContentPane().add(pnlUpdatesAndQuery);
		pnlUpdatesAndQuery.setLayout(new GridLayout(1, 0, 0, 0));
		
		JButton btnUpdate = new JButton("Update Health Plan");
		btnUpdate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					updateHealthplan();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnUpdate.setBackground(SystemColor.control);
		pnlUpdatesAndQuery.add(btnUpdate);
		
		JButton btnAll = new JButton("Get all patients");
		btnAll.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					reset();
					patients = Patient.getAllPatients();
					currentPatient = patients.get(0);
					currentPatientIndex = 0;
					displayPatient();
					getTreatments();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnAll.setBackground(SystemColor.control);
		pnlUpdatesAndQuery.add(btnAll);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					new FindPatients();
					frame.dispose();
				} catch (SQLException e1) {
					frame.dispose();
				}
				
			}
		});
		btnSearch.setBackground(SystemColor.control);
		pnlUpdatesAndQuery.add(btnSearch);
		
		
		if(queryPatients == null){
			this.patients = new ArrayList<Patient>();
			this.patients = Patient.getAllPatients();
			if(this.patients.isEmpty()){
				this.currentPatient = null;
			}else{
				this.currentPatient = this.patients.get(0);
				this.currentPatientIndex = 0;
				System.out.println(this.currentPatient.getID());
				displayPatient();
				getTreatments();
				
			}
			
		}else{
			this.patients = queryPatients;
			if(this.patients.isEmpty()){
				this.currentPatient = null;
			}else{
				this.currentPatient = this.patients.get(0);
				this.currentPatientIndex = 0;
				System.out.println(this.currentPatient.getID());
				displayPatient();
				getTreatments();
				
			}
		}
		
	    frame.setVisible(true);
	}
	
	public void reset() throws SQLException{
		txtAreaTreatments.setText(null);
		this.patients = new ArrayList<Patient>();
		this.patients = Patient.getAllPatients();
		if(this.patients.isEmpty()){
			this.currentPatient = null;
		}else{
			this.currentPatient = this.patients.get(0);
			this.currentPatientIndex = 0;
			displayPatient();
			getTreatments();
		}
	}
	
	
	/**
	 * A method that displays the currents patients details on the form
	 */
	
	private void displayPatient(){
		
		String Id = Integer.toString(this.currentPatient.getID());
		txtID.setText(Id);
		txtTitle.setText(this.currentPatient.getTitle());
		txtForename.setText(this.currentPatient.getForename());
		txtSurname.setText(this.currentPatient.getSurname());
		txtDob.setText(this.currentPatient.getDateOfBirth().toString());
		txtContactNumber.setText(this.currentPatient.getPhoneNumber());
		
		if(!(this.currentPatient.getAdddress() == null)){
			txtHouseNumber.setText(this.currentPatient.getAdddress().getHouseNumber());
			txtStreetName.setText(this.currentPatient.getAdddress().getStreetName());
			txtDistrict.setText(this.currentPatient.getAdddress().getDistrict());
			txtCity.setText(this.currentPatient.getAdddress().getCity());
			txtPostcode.setText(this.currentPatient.getAdddress().getPostcode());
		}else{
			txtHouseNumber.setText("N/A");
			txtStreetName.setText("N/A");
			txtDistrict.setText("N/A");
			txtCity.setText("N/A");
			txtPostcode.setText("N/A");
		}
		
		if(!(this.currentPatient.getHealthcarePlan() == null)){
			txtHealthName.setText(this.currentPatient.getHealthcarePlan().getHealthcareName());
			String monthlyCost = "£" + String.valueOf(this.currentPatient.getHealthcarePlan().getMonthlyCost() + "0");
			txtMonthlyCost.setText(monthlyCost);
			String checkups = String.valueOf(this.currentPatient.getHealthcarePlan().getNumCheckUps());
			txtCheckups.setText(checkups);
			String hygiene = String.valueOf(this.currentPatient.getHealthcarePlan().getNumHygieneVisits());
			txtHygieneVisits.setText(hygiene);
			String repairs = String.valueOf(this.currentPatient.getHealthcarePlan().getNumRepairs());
			txtRepairs.setText(repairs);
		}else{
			txtHealthName.setText("None");
			txtMonthlyCost.setText("£0.00");
			txtCheckups.setText("0");
			txtHygieneVisits.setText("0");
			txtRepairs.setText("0");
		}
		
		
		
	}
	
	/**
	 * A method that gets the treatments that have not been payed for by the current patient being viewed
	 * @throws SQLException 
	 */
	private void getTreatments() throws SQLException{
		
		ConnectDB connect = new ConnectDB();
		Connection con = connect.getCon();
		
		ArrayList<Treatment> treatments = new ArrayList<Treatment>();
		PreparedStatement stmtPayments = con.prepareStatement("SELECT treatment_name FROM PAYMENTS WHERE patient_id = ?");
		stmtPayments.setInt(1, this.currentPatient.getID());
		ResultSet paymentsSet = stmtPayments.executeQuery();
		
		float total_cost = 0.0f;
		int noCheckUps;
		int noRepairs;
		int noHygiene;
		
		if(this.currentPatient.getHealthcarePlan() == null){
			noCheckUps = 0;
			noRepairs = 0;
			noHygiene = 0;
		}else{
			noCheckUps = this.currentPatient.getHealthcarePlan().getNumCheckUps();
			noRepairs = this.currentPatient.getHealthcarePlan().getNumRepairs();
			noHygiene = this.currentPatient.getHealthcarePlan().getNumHygieneVisits();
		}

		
			
		while(paymentsSet.next()){
			String treatment_name = paymentsSet.getString(1);
			String type = Treatment.getType(treatment_name);
			
			if(type.equals("Check-up") && noCheckUps > 0){
				float cost = 0.0f;
				String displayTreatments = treatment_name + " - £" + String.valueOf(cost) + "0" + NEWLINE;
				this.txtAreaTreatments.append(displayTreatments);
				noCheckUps--;
			}else if(type.equals("Hygiene") && noHygiene > 0 ){
				float cost = 0.0f;
				String displayTreatments = treatment_name + " - £" + String.valueOf(cost) + "0" + NEWLINE;
				this.txtAreaTreatments.append(displayTreatments);
				noHygiene--;
			}else if(noRepairs > 0){
				float cost = 0.0f;
				String displayTreatments = treatment_name + " - £" + String.valueOf(cost) + "0" + NEWLINE;
				this.txtAreaTreatments.append(displayTreatments);
				noRepairs--;
			}else{
				PreparedStatement stmtCosts = con.prepareStatement("SELECT cost FROM TREATMENTS WHERE treatment_name = ?");
				stmtCosts.setString(1,treatment_name);
				ResultSet costsSet = stmtCosts.executeQuery();
				while(costsSet.next()){
					float cost = costsSet.getFloat(1);
					total_cost += cost;
					String displayTreatments = treatment_name + " - £" + String.valueOf(cost) + "0" + NEWLINE;
					this.txtAreaTreatments.append(displayTreatments);
				}
				stmtCosts.close();
			}

			
		}
		this.txtTotalcost.setText("£"+String.valueOf(total_cost) +"0");
		
		
		stmtPayments.close();
		
		// Determine if a patient needs more treatments
		PreparedStatement stmtNeedCheckup = con.prepareStatement("SELECT * FROM LOGS WHERE patient_id = ? ORDER BY date DESC");
		stmtNeedCheckup.setInt(1, this.currentPatient.getID());
		ResultSet logsSet = stmtNeedCheckup.executeQuery();
		
		if(logsSet.next()){
			String extraVisitType = logsSet.getString(5);
			if (extraVisitType.equals("Treatment")){
				txtMoreTreatments.setText("Yes");
			}else{
				txtMoreTreatments.setText("No");
			}
		}else{
			txtMoreTreatments.setText("Yes");
		}
		stmtNeedCheckup.close();
		con.close();
	}
	
	private void makePayments() throws SQLException{
		
		ConnectDB connect = new ConnectDB();
		Connection con = connect.getCon();
		
		PreparedStatement stmtMakePayments = con.prepareStatement("DELETE FROM PAYMENTS WHERE patient_id = ?");
		stmtMakePayments.setInt(1, this.currentPatient.getID());
		stmtMakePayments.executeUpdate();
		stmtMakePayments.close();
		con.close();
		
		JOptionPane.showMessageDialog(null,"Payments Made","The patients treatments have been set as payed.",JOptionPane.INFORMATION_MESSAGE);
		reset();
		
	}
	
	private void updateHealthplan() throws SQLException{
		if (this.currentPatient == null){
			
		}else{

			ArrayList<HealthcarePlan> plans = HealthcarePlan.getAllPlans();
			
			jfHealthPlan = new JFrame();
			jfHealthPlan.setTitle("Change Healthplan");
			jfHealthPlan.setSize(400, 250);
			jfHealthPlan.setLocationRelativeTo(null);
			jfHealthPlan.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	
			jfHealthPlan.getContentPane().setLayout(null);
			
			JLabel lblCurrent = new JLabel("Current Healthplan:");
			lblCurrent.setBounds(20, 30, 300, 30);
			
			txtCurrent = new JTextField();
			txtCurrent.setBounds(200,30,150,30);
			txtCurrent.setText(this.txtHealthName.getText());
			
			JLabel lblSelectHealth = new JLabel("Select New Healthplan:");
			lblSelectHealth.setBounds(20, 70, 200, 30);
		
			cmbHealthPlan= new JComboBox<String>();
			cmbHealthPlan.setBounds(200, 70, 150, 30);
			
			JButton btnUpdate = new JButton("Update");
			btnUpdate.setBounds(200,110,100,30);
			btnUpdate.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent arg0) {
						try {
							completeUpdate();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
			
			JButton btnRemove = new JButton("Remove");
			btnRemove.setBounds(80,110,100,30);
			btnRemove.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent arg0) {
					try {
						completeRemove();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			
			for(HealthcarePlan p: plans){
				cmbHealthPlan.addItem(p.getHealthcareName());
			}
			
			if(this.currentPatient.getAge() >=18){
				cmbHealthPlan.removeItem("NHS Free Plan");
			}
			
				
			jfHealthPlan.getContentPane().add(lblCurrent);
			jfHealthPlan.getContentPane().add(lblSelectHealth);
			jfHealthPlan.getContentPane().add(txtCurrent);
			jfHealthPlan.getContentPane().add(cmbHealthPlan);
			jfHealthPlan.getContentPane().add(btnUpdate);
			jfHealthPlan.getContentPane().add(btnRemove);
			jfHealthPlan.setVisible(true);
			
		}
	}
	
	
	private void completeRemove() throws SQLException{
		if(txtCurrent.getText().equals("None")){
			JOptionPane.showMessageDialog(null,"Error","The patient doesn't have a health plan.",JOptionPane.ERROR_MESSAGE);  
		}else{
			String planName = (String) cmbHealthPlan.getSelectedItem();
			System.out.println(planName);
			this.currentPatient.updateHealthCarePlan(null);
			JOptionPane.showMessageDialog(null,"Success","Plan updated.",JOptionPane.INFORMATION_MESSAGE);
			jfHealthPlan.dispose();
			reset();
		}
	}
	
	private void completeUpdate() throws SQLException{
		if(cmbHealthPlan.getSelectedItem().equals(null)){
			JOptionPane.showMessageDialog(null,"Error","You have not selected a healthplan!",JOptionPane.ERROR_MESSAGE);  
		}else{
			String planName = (String) cmbHealthPlan.getSelectedItem();
			System.out.println(planName);
			this.currentPatient.updateHealthCarePlan(planName);
			JOptionPane.showMessageDialog(null,"Success","Plan updated.",JOptionPane.INFORMATION_MESSAGE);
			jfHealthPlan.dispose();
			reset();
			
		}
	}

}
