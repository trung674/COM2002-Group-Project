

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.awt.*;


public class Login extends JFrame{

	private static JTextField username;
	private JTextField password;
	private JLabel user, userPass;
	private JButton btnConfirm;
	private Component frame;

	static ConnectDB db = new ConnectDB();
	static Connection con = db.getCon();

	public Login() {
		initialize();

	}
	private void initialize(){
		new JFrame();
		setTitle("Login");
		setSize(250, 250);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		setResizable(false);


		user = new JLabel("Username");
		user.setBounds(30, 50, 130, 20);
		username = new JTextField(4);
		username.setBounds(100, 50, 100, 20);

		userPass = new JLabel("Password");
		userPass.setBounds(30, 90, 130, 20);
		password = new JTextField(4);
		password.setBounds(100, 90, 100, 20);

		btnConfirm = new JButton("Log In");
		btnConfirm.setBounds(85, 140, 70, 40);
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
							logIn();
			}
		});

		add(user);
		add(username);
		add(userPass);
		add(password);
		add(btnConfirm);

	}

	private boolean validateInputs(String user,String pass){

		boolean validInputs = true;
		String[] msg = {"@","@"};

		if(!validateUsername(user)){
			validInputs = false;
			msg[0] = "Input Error: Username is invalid";
			username.setText("");
		}
		if(!validatePassword(pass)){
			validInputs = false;
			msg[1] = "Input Error: Password is invalid";
			password.setText("");
		}

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
			JOptionPane.showMessageDialog(this.frame,errorMessage, "Input Error!",
				    JOptionPane.ERROR_MESSAGE);

	}

	/**
	 * A method to validate a name
	 * @param name String - Surname or Forename
	 * @return boolean - If it is valid
	 */

	private static boolean validateUsername(String username){
		return username.matches("[A-Z]{1}[a-z]{2,15}?");
	}

	private static boolean validatePassword(String pass){
		return pass.matches("[0-9]{3}?");
	}

	private void logIn(){
		String partner = username.getText();
		String pass = password.getText();

		if (validateInputs(partner,pass)){

			signIn(partner,pass);
		}
	}

	private void signIn(String username,String pass){
		String select = null;
		select = "SELECT * FROM USERS WHERE userType='" + username + "';" ;


		System.out.println(select);
		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement(select);


			ResultSet rs = stmt.executeQuery();
			int columns = rs.getMetaData().getColumnCount();


			String[] security = new String[3];
			while(rs.next()){
				for (int i=1; i <= columns; i++){
					security[i] = rs.getString(i) ;
				}


			}
			
			System.out.println(security[2]);
			System.out.print(pass);
			if (security[2].equals(pass)){
				if(username.equals("Secretary")){
					Secretary me = new Secretary();
			        me.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			        me.setVisible(true);
				} else {
					Partner pn = new Partner();
					pn.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					pn.setVisible(true);
				}
				
				this.dispose();

			} else {
				JOptionPane.showMessageDialog(this,
					    "Error ! Try again",
					    "Something went wrong !",
					    JOptionPane.PLAIN_MESSAGE);
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage() + "Error");
		} finally {
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) { }
		}


	}

	public static void main(String[] args) {
		Login me = new Login();
		me.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		me.setVisible(true);
	}


	}

