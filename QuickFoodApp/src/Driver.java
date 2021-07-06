import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Driver {

	
	
	//fields
	int id;
	String name;
	String city;
	
	
	public Driver() {

	}
	
	public void DBCon_getdriver(String city) {
		
		this.city = city;
		
		
		int rowsAffected = 0;
		Statement statement = null;
		ResultSet results;
		
		//connect to Database
		try {
			Connection connection = DriverManager.getConnection(
				"jdbc:sqlserver://localhost:1433;database=QuickFoodMS",
				"User_name",
				"User_password"
				);
			
			statement = connection.createStatement();
			System.out.println("\nDriver Class:\n----------------------------\n"
					+ "DB connection succesful");
			
		
		} 	catch (SQLException e) {
			System.out.println("failed to connect to Database");
				e.printStackTrace();
			}
		
		//get driver info
		try {
			//String sqlStatement =  "SELECT dbo.fn_get_driver ('" + this.city + "')" ;
			String sqlStatement1 = "SELECT TOP 1 DriverID, Name FROM Drivers "
					+ "WHERE City = '" + this.city
					+ "' ORDER BY Load";
			
			results = statement.executeQuery(sqlStatement1);
			results.next();
			this.id = results.getInt("DriverID");
			this.name = results.getString("Name");
			System.out.println("DB select query for driver successful.");
			System.out.println("new Driver object created:\n    ID: " + this.id
					+ "    Name: " + this.name + "    city: " + this.city);
			
			
		}	catch(SQLException e) {
			e.printStackTrace();
		}
		
		//increment driver load
		try {
			String sqlStatement = "EXEC sp_incr_driverload '" + this.id +  "'" ;
			rowsAffected = statement.executeUpdate(sqlStatement);
			System.out.println("Driver Load incremented by 1 in DB. " + rowsAffected + " row Affected.");
			
		}	catch(SQLException e) {
			e.printStackTrace();
		}

		
		
	}
	
	
	
	
	
	
	
}
