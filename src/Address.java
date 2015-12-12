import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Address {
	private String house_no;
	private String streetName;
	private String district;
	private String city;
	private String postcode;
	
	public Address( String house_no, String streetName, String district, String city, String postcode){
		this.house_no = house_no;
		this.streetName = streetName;
		this.district = district;
		this.city = city;
		this.postcode = postcode;
	}
	
	public boolean uniqueAddress() throws SQLException{
		
		ConnectDB connect = new ConnectDB();
		Connection con = connect.getCon();
		
		  
	    PreparedStatement stmt = con.prepareStatement(
	    		 "SELECT * FROM ADDRESS WHERE house_number = ? AND street_name = ? AND district = ?  AND city = ? AND post_code = ?");
	      
	    stmt.setString(1, this.house_no);
	    stmt.setString(2, this.streetName);
	    stmt.setString(3, this.district);
	    stmt.setString(4, this.city);
	    stmt.setString(5, this.postcode);
	      
	    ResultSet rs = stmt.executeQuery();
	    int rowcount = 0;
	    while(rs.next()){
	    	rowcount = rs.getRow();
	    }

	    return (rowcount == 0);
	      
		
	}
	
	public void addAddress(){
		
	}
	
}
