
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Component;
import javax.swing.Box;
import java.awt.FlowLayout;
import javax.swing.JSplitPane;
import java.awt.GridBagLayout;
import javax.swing.JTabbedPane;
import java.awt.GridBagConstraints;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class SubcribeHealthplan {

	private JFrame frame;
	private JSplitPane split;
	private JPanel healthplan;
	private JLabel lblPatientId;
	private JLabel lblTitle;
	private JLabel lblForename;
	private JLabel lblSurname;
	private JLabel lblDateOfBirth;
	private JLabel label_1;
	private JLabel lblCurrentHealthplan;
	private JLabel lblDescription;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SubcribeHealthplan window = new SubcribeHealthplan();
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
	public SubcribeHealthplan() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(0, 0, 600, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(1, 2, 10, 0));
		
		
		healthplan = new JPanel();
		healthplan.setLayout(null);
		JLabel lblPatient = new JLabel("Patient");
		lblPatient.setHorizontalAlignment(SwingConstants.CENTER);
		lblPatient.setBounds(100, 10, 200, 20);
		lblPatient.setFont(new Font("Tahoma", Font.BOLD, 16));
		healthplan.add(lblPatient);
		frame.getContentPane().add(healthplan);
		
		lblPatientId = new JLabel("Patient ID:");
		lblPatientId.setBounds(20, 50, 100, 20);
		healthplan.add(lblPatientId);
		
		lblTitle = new JLabel("Title:");
		lblTitle.setBounds(20, 80, 100, 20);
		healthplan.add(lblTitle);
		
		lblForename = new JLabel("Forename:");
		lblForename.setBounds(20, 110, 100, 20);
		healthplan.add(lblForename);
		
		lblSurname = new JLabel("Surname:");
		lblSurname.setBounds(20, 140, 100, 20);
		healthplan.add(lblSurname);
		
		lblDateOfBirth = new JLabel("Date of Birth:");
		lblDateOfBirth.setBounds(20, 170, 100, 20);
		healthplan.add(lblDateOfBirth);
		
		label_1 = new JLabel("Healthplan Subscription");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_1.setBounds(100, 250, 200, 20);
		healthplan.add(label_1);
		
		lblCurrentHealthplan = new JLabel("Current Healthplan:");
		lblCurrentHealthplan.setBounds(20, 280, 100, 20);
		healthplan.add(lblCurrentHealthplan);
		
		lblDescription = new JLabel("Description:");
		lblDescription.setBounds(20, 310, 100, 20);
		healthplan.add(lblDescription);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(130, 308, 4, 22);
		healthplan.add(textArea);
		
		

	
		
	}
}
