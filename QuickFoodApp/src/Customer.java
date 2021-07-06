import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Customer {

	//fields
	int id;
	String name;
	String phone;
	String email;
	String city;
	String address;

	//Constructor
	public Customer() {

		
	}
	
	//methods
	
	public void addInfo(String name, String phone, String address, String city, String email) {
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.city = city;
		this.address = address;
	}

	public void DBCon_insertcust_setID() {
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
			System.out.println("Customer Class: \n---------------------------- \n"
					+ "DB connection successful");
			
			
		
		} 	catch (SQLException e) {
			System.out.println("failed to connect to Database");
				e.printStackTrace();
			}
		
		//execute sp_insert_cust stored-procedure with parameters
		try {
			String sqlStatement = "EXEC sp_insert_cust '" + this.name + "', '" + this.phone + "', '"
									+ this.address + "', '" + this.city + "', '" + this.email + "'" ;
			rowsAffected = statement.executeUpdate(sqlStatement);
			System.out.println("DB insert query for new customer succcessful. " + rowsAffected + " rows Affected.");
			
		}	catch(SQLException e) {
			e.printStackTrace();
		}
		
		//execute fn_get_custid UDF with parameters
		try {
			String sqlStatement = "SELECT dbo.fn_get_custid ('" + this.name + "', '" + this.phone + "', '" + this.address + "')";
			results = statement.executeQuery(sqlStatement);
		
			results.next();
			this.id = results.getInt(1); //sets Customer.id
			System.out.println("new Customer object created:\n    "
					+ "ID: " + this.id 
					+ "    Name: " + this.name
					+ "    phone: " + this.phone
					+ "    address: " + this.address
					+ "    city: " + this.city
					+ "    email " + this.email);
			
		}	catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
}
