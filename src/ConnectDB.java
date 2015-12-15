import java.sql.*;

/**
 * Create a connection to database server
 */
public class ConnectDB {
	private Connection con;
	public ConnectDB(){
		con = null;
		try{
			String DB ="jdbc:mysql://stusql.dcs.shef.ac.uk/team008?user=team008&password=fff8f017";
			con = DriverManager.getConnection(DB);
		}
		catch(SQLException ex){
				ex.printStackTrace();
			}
			
	}
	
	// getter method
	public Connection getCon(){
		return con;
	}

	public static void main(String[] args) {
		ConnectDB test = new ConnectDB();
		Connection testDB = test.getCon();
		System.out.println(testDB);
	}

}
