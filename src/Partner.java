package project;

import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.awt.*;

public class Partner extends JFrame {
	
	private JFrame jfTreatment;
	private Description pDescription;
	private JButton btnAddTreatment;
	private static String[] treatment = {"Check-up", "Gold Crown", "Silver Amalgam Filling", "Teeth Cleaning", "White Composite Resin Filling"};
	private static String[] visit_type = {"Check-up", "Treatment"};
	private ArrayList<String> selectedTreatment = new ArrayList<String>();
	private JComboBox<String> cmbTreatment, cmbVisit;
	private JLabel lblSelectTreatment, lblPatient, lblExtra;
	private JButton btnAdd, btnConfirm;
	private JScrollPane jspSelectedTreatment;
	private DefaultListModel<String> dlmTreatment = new DefaultListModel<String>();
	private JList<String> listTreatment;
	
	static Calendar now = Calendar.getInstance();
	static ConnectDB db = new ConnectDB();
	static Connection con = db.getCon();

	public Partner() throws SQLException {
		Secretary me = new Secretary();
				
		// remove Menu bar in partner interface
		me.setJMenuBar(null);
		
		//remove cancel button and add new button for adding treatment
		pDescription = me.getDescription();
		pDescription.remove(pDescription.getBtnCancel());
		
		btnAddTreatment = new JButton("Add Treatment");
		btnAddTreatment.setPreferredSize(new Dimension(100,40));	
		btnAddTreatment.addMouseListener(new MouseAdapter() {
      	  public void mouseClicked(MouseEvent e)  {
      		try {
				addTreatment(pDescription.getAppointment());
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
      	  }	  
      	});
		
		pDescription.add(btnAddTreatment);
		me.revalidate();
		me.repaint();
        me.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        me.setVisible(true);
        
        
	}
	
	private void addTreatment(Appointment a) throws SQLException{
		if (a == null){
			JOptionPane.showMessageDialog(jfTreatment, "No appintment is selected", "Error!",
				    JOptionPane.ERROR_MESSAGE);
		} else {
			jfTreatment = new JFrame();
			jfTreatment.setTitle("Add Treatment");
			jfTreatment.setSize(400, 500);
			jfTreatment.setLocationRelativeTo(null);
			jfTreatment.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	
			jfTreatment.getContentPane().setLayout(null);
			
			// Add components
			lblPatient = new JLabel("Patient : " + a.getPatient().getSurname() + " " + a.getPatient().getForename());
			lblPatient.setBounds(50, 30, 300, 30);
			lblSelectTreatment = new JLabel("Select Treatment");
			lblSelectTreatment.setBounds(50, 80, 200, 30);
			cmbTreatment = new JComboBox<String>(treatment);
			cmbTreatment.setBounds(50, 110, 200, 30);
			
			// Add new treatment to list
			btnAdd = new JButton("Add");
			btnAdd.setBounds(260, 110, 100, 30);
			btnAdd.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e)  {
					String strTreatment = (String) cmbTreatment.getSelectedItem();
					if(selectedTreatment.contains(strTreatment)){
						JOptionPane.showMessageDialog(jfTreatment, "Treatment is already added", "Error!",
							    JOptionPane.ERROR_MESSAGE);
					} else {
						selectedTreatment.add(strTreatment);
						dlmTreatment.addElement(strTreatment);
						jspSelectedTreatment.revalidate();
						jspSelectedTreatment.repaint();
					}
					
				}
		    });
			
			// Display list of selected treatment
		    listTreatment = new JList<String>(dlmTreatment);
			jspSelectedTreatment = new JScrollPane(listTreatment);
			jspSelectedTreatment.setBounds(50, 160, 300, 150);
			
			// Combo box for extra visit type
			lblExtra = new JLabel("Extra visit");
			lblExtra.setBounds(50, 320, 200, 30);
			cmbVisit= new JComboBox<String>(visit_type);
			cmbVisit.setBounds(50, 350, 200, 30);
			
			// confirm button
			btnConfirm = new JButton("Confirm");
			btnConfirm.setBounds(50, 410, 100, 30);
			btnConfirm.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(selectedTreatment.isEmpty()){ //no treatment is selected, return error
						JOptionPane.showMessageDialog(jfTreatment, "No Treatment is seleceted", "Error!",
							    JOptionPane.ERROR_MESSAGE);
					} else {
						int check = JOptionPane.showConfirmDialog(null, "Are you sure you want to add these treatments?", "Confirmation", JOptionPane.YES_NO_OPTION);
						
						if (JOptionPane.YES_OPTION == check) {
							// create list of queries
							ArrayList<PreparedStatement> queryList = new ArrayList<PreparedStatement>();
							ArrayList<PreparedStatement> paymentQueryList = new ArrayList<PreparedStatement>();
							// Get data from given appointment
							java.sql.Date date = a.getDate();
							int patient_id = a.getID();
							String partner_type = a.getPartner();
							String visit_type = (String) cmbVisit.getSelectedItem();
							
							for(String treatment_name : selectedTreatment){
								String query = "INSERT INTO LOGS (date, treatment_name, patient_id, extra_visit, partner_type) VALUES (?, ?, ?, ?, ?);" ;
								String paymentQuery = "INSERT INTO PAYMENTS(patient_id, treatment_name) VALUES (?,?)";
								PreparedStatement insertTreatment = null;
								PreparedStatement insertPayment = null;
								
								
								try {
									insertTreatment = con.prepareStatement(query);
									insertTreatment.setDate(1, date);
									insertTreatment.setString(2, treatment_name);
									insertTreatment.setInt(3, patient_id);
									insertTreatment.setString(4, visit_type);
									insertTreatment.setString(5, partner_type);
									queryList.add(insertTreatment);
									
									insertPayment = con.prepareStatement(paymentQuery);
									insertPayment.setInt(1, patient_id);
									insertPayment.setString(2, treatment_name);
									paymentQueryList.add(insertPayment);

									
									
									
								} catch (SQLException e1) {
									e1.printStackTrace();
									
									
								}	
								
								
								
							}
							
							// execute each query one at a time
							int result = 0;
							for(PreparedStatement treatmentQuery : queryList ){
								try {
									result = treatmentQuery.executeUpdate();									
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} finally {
									try {
										treatmentQuery.close();
									} catch (SQLException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								}
								
							}
							
							for(PreparedStatement paymentQuery: paymentQueryList ){
								try {
									result = paymentQuery.executeUpdate();									
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} finally {
									try {
										paymentQuery.close();
									} catch (SQLException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								}
							}
							
	
							
							if (result > 0){
								JOptionPane.showMessageDialog(jfTreatment, "Successfully added treatments", "Success", JOptionPane.PLAIN_MESSAGE);
								
							} else {
								JOptionPane.showMessageDialog(jfTreatment,"Error ! Try again","Something went wrong !", JOptionPane.PLAIN_MESSAGE);  
							}														
						}
					}
				}
			});
			
			jfTreatment.add(btnAdd);
			jfTreatment.add(lblPatient);
			jfTreatment.add(lblSelectTreatment);
			jfTreatment.add(cmbTreatment);
			jfTreatment.add(jspSelectedTreatment);
			jfTreatment.add(lblExtra);
			jfTreatment.add(cmbVisit);
			jfTreatment.add(btnConfirm);
			jfTreatment.setVisible(true);
			
		}
		
		
	}
	
	public static void main(String[] args) {
		try {
			new Partner();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}