import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Restaurant {

	
	//field
	String id;
	String name;
	String city;
	String phone;
	
	//constructor
	public Restaurant() {		
	}	
	//method
	
	public void getRestaurant(String id) {
		
		this.id = id;
		
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
			System.out.println("Restaurant Class:\n--------------------\n"
					+ "DB connection succcessful");
			
			
		
		} 	catch (SQLException e) {
			System.out.println("failed to connect to Database");
				e.printStackTrace();
			}
		
		//get restaurant info
		try {
			String sqlStatement = "SELECT * FROM Restaurants "
					+ "WHERE RestaurantID = '" + this.id + "'";
			
			results = statement.executeQuery(sqlStatement);
			results.next();
			this.name = results.getString("Name");
			this.city = results.getString("City");
			this.phone = results.getString("Phone");
			System.out.println("DB select query for Restaurant successful");
			System.out.println("new Restaurant object created:\n    id: " + this.id
					+ "    name: " + this.name + "    city: " + this.city + "    phone: " + this.phone);
			
			
		}	catch(SQLException e) {
			e.printStackTrace();
		}		
		
	}
	
}
