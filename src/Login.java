import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.awt.*;

/**
 * Login interface for partner and secretary
 *
 */
public class Login extends JFrame{
	
	// Components
	private static JTextField username;
	private JTextField password;
	private JLabel user, userPass;
	private JButton btnConfirm;
	private Component frame;

	// Initiate connect to database
	static ConnectDB db = new ConnectDB();
	static Connection con = db.getCon();
	
	/**
	 * Creates the form
	 */
	public Login() {
		initialize();
	}
	
	/**
	 * Initialise the login interface
	 */
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
	
	/**
	 * A method to validate username and password
	 * @param user String - username
	 * @param pass String - password
	 * @return boolean - If it is valid
	 */
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
	 * A method to validate username (must has 1 Upper-case letter and 2 to 15 lower-case letters
	 * @param username String - username
	 * @return boolean - If it is valid
	 */

	private static boolean validateUsername(String username){
		return username.matches("[A-Z]{1}[a-z]{2,15}?");
	}
	
	/**
	 * A method to validate password (must has 3 numeric letters)
	 * @param pass String - password
	 * @return boolean - If it is valid
	 */
	private static boolean validatePassword(String pass){
		return pass.matches("[0-9]{3}?");
	}

	/**
	 * Method takes username and pass from user, validate it and initiate method signIn
	 */
	private void logIn(){
		String user = username.getText();
		String pass = password.getText();
		System.out.println(user + " " + pass);
		if (validateInputs(user,pass)){
			signIn(user,pass);
		}
	}

	/**
	 * A method to validate username and password using database, then open interface according to username
	 * @param pass String - password
	 */
	private void signIn(String username,String pass){
		String select = null;
		// Select all from column with value 'username'
		select = "SELECT * FROM USERS WHERE userType='" + username + "';" ;

		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement(select);
			ResultSet rs = stmt.executeQuery();
			int columns = rs.getMetaData().getColumnCount();
			//Extract username and password from database
			if(rs.next()){
				String[] security = new String[3];
					for (int i=1; i <= columns; i++){
						security[i] = rs.getString(i) ;
				}
				
				// if password from user matches the one on database, initiate interface
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
					
					// dispose login interface
					this.dispose();

				} else { // Wrong user name
					JOptionPane.showMessageDialog(this,
						    "Error ! Try again",
						    "Wrong password !",
						    JOptionPane.ERROR_MESSAGE);
				}
					
			} else { //return error if username does not match with any data on database
				JOptionPane.showMessageDialog(this,
					    "Error ! Try again",
					    "Wrong username !",
					    JOptionPane.ERROR_MESSAGE);
			}

		} catch (SQLException e) {
			//System.out.println(e.getMessage() + "Error");
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

