

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;

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
	 */
	public FindPatients() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 502, 628);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		txtAreaHelp.setText("Use this form to find patients using queries, if a field is left blank then it will not be used to filter the search, leaving all fields blank will return all the patients.");
		txtAreaHelp.setBackground(new Color(0, 0, 0, 0));
		txtAreaHelp.setWrapStyleWord(true);
		pnlHelp.add(txtAreaHelp);
		
		JPanel pnlTools = new JPanel();
		pnlTools.setBorder(new LineBorder(Color.ORANGE, 2));
		pnlTools.setBounds(10, 536, 178, 46);
		frame.getContentPane().add(pnlTools);
		pnlTools.setLayout(new GridLayout(1, 0, 0, 0));
		
		JButton btnSearch = new JButton("Search");
		pnlTools.add(btnSearch);
		
		JButton btnBack = new JButton("Back");
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
		
		JTextField txtForename = new JTextField();
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
		
		JComboBox cmbHealthPlan = new JComboBox();
		cmbHealthPlan.setBounds(133, 33, 307, 20);
		pnlHealthplan.add(cmbHealthPlan);
	}
}
