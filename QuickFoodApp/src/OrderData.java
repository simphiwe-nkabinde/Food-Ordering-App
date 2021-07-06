import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;



public class OrderData {
	
	//fields
	int orderId;
	int customerId;
	String restaurantId;
	int driverId;
	String prepInstr;
	ArrayList<int[]>  itemsArr;
	
	//constructor
	public OrderData(int customerId, String restaurantId, int driverId, String prepInstr, ArrayList<int[]> itemsArr) {
		
		this.customerId = customerId;
		this.restaurantId = restaurantId;
		this.driverId = driverId;
		this.prepInstr = prepInstr;
		this.itemsArr = itemsArr;
	}
	
	//method
	public void DBinsert_orderHeader() {
		//connect to database
		Statement statement = null;
		ResultSet results;
		int rowsAffected;
		
		try {
			Connection connection = DriverManager.getConnection(
					"jdbc:sqlserver://localhost:1433;database=QuickFoodMS",
					"User_name",
					"User_password"					
					);		
			
			statement = connection.createStatement();
			
		}	catch(SQLException e) {
			System.out.println("failed to connect to Database");
			e.printStackTrace();
		}		
		//insert order Header data and get orderID
		try {
			
			String insertStatement = "EXEC sp_insert_orderheader '" + this.customerId + "', '" 
					+ this.restaurantId +"', '" + this.driverId + "', '" + this.prepInstr + "'";
			rowsAffected = statement.executeUpdate(insertStatement);
			System.out.println("insert: " + rowsAffected + "rows affected.");
			
			String queryStatement = "SELECT dbo.fn_get_orderid ('" + this.customerId + "')"; 
			results = statement.executeQuery(queryStatement);
			results.next();
			this.orderId = results.getInt(1);
			
		}	catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void DBinsert_orderDetails() {
		
		//connect to database
		Statement statement = null;
		ResultSet results;
		int rowsAffected;
		
		try {
			Connection connection = DriverManager.getConnection(
					"jdbc:sqlserver://localhost:1433;database=QuickFoodMS",
					"User_name",
					"User_password"					
					);		
			
			statement = connection.createStatement();
			
		}	catch(SQLException e) {
			System.out.println("failed to connect to Database");
			e.printStackTrace();
		}
		
		try {
			
			for (int[] i: itemsArr) {
				
				int id = i[0];
				int qty = i[1];
				
				String insertStatement = "EXEC sp_insert_orderitem '" + this.orderId + "', '" + id + "', '" + qty + "'";
				rowsAffected = statement.executeUpdate(insertStatement);
				System.out.println("insert: " + rowsAffected + "rows affected.");								
			}			
		}	catch(SQLException e) {
			e.printStackTrace();
		}		
	}	
}
