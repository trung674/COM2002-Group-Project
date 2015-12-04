import java.sql.*;

public class TestDB {

	public static void main(String[] args) throws SQLException {
		

		Connection con = null;
		try{
			String  DB ="jdbc:mysql://stusql.dcs.shef.ac.uk/team008?user=team008&password=fff8f017";
			con = DriverManager.getConnection(DB);
			
			Statement stmt = null;
			try{
				
				stmt = con.createStatement(); // Create a statement
				
				// Create Table
				String sql = "CREATE TABLE PATIENT " +
							" (id INTEGER not NULL, " +
							" forename VARCHAR(255) not NULL, " + 
							" surname VARCHAR(255) not NULL, " + 
							" healthplan VARCHAR(255) not NULL," +
							" PRIMARY KEY ( id ))"; 

				stmt.executeUpdate(sql);
				System.out.println("Created table...");
		      
				// Insert Records
				sql = "INSERT INTO Registration " +
						"VALUES (100, 'Tom', 'Green', 'NHS_FREE_UNDER_18')";
				stmt.executeUpdate(sql);
				sql = "INSERT INTO Registration " +
	                   "VALUES (101, 'Bob', 'Dylan', 'STANDARD')";
				stmt.executeUpdate(sql);
				sql = "INSERT INTO Registration " +
	                   "VALUES (102, 'Zoey', 'Apple', 'PREMIUM')";
				stmt.executeUpdate(sql);
				System.out.println("Inserting patients..");
				
				// Get Records
				sql = "SELECT * FROM PATIENT";
			    ResultSet rs = stmt.executeQuery(sql);
			    while(rs.next()){
			         int id  = rs.getInt("id");
			         String forename = rs.getString("forename");
			         String surname = rs.getString("surname");
			         String healthplan = rs.getString("healthplan");

			         //Display values
			         System.out.print("ID: " + id);
			         System.out.print(", Forename: " + forename);
			         System.out.print(", Surname: " + surname);
			         System.out.println(", Healthplan: " + healthplan);
			         
			   }
			   
			   // Drop table
			   sql = "DROP TABLE PATIENT ";
			    
			   stmt.executeUpdate(sql);
			   System.out.println("Deleted table..");
			}
			catch(SQLException ex){
				ex.printStackTrace();
			}
			finally{
				if (stmt != null) stmt.close();
			}
		}
		catch(SQLException ex){
			ex.printStackTrace();
		}
		finally{
			if (con != null) con.close();
		}

	}

}
