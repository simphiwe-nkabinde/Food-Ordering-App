import java.awt.Desktop;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Formatter;

public class Invoice {
	
	//fields
	
	public Invoice() {
	}
	
	public void printInvoice(Customer cust, Restaurant rest, Driver driver, OrderData order ) {	
		//invoice data
		String dateTime = "";
		String orderSummary = "";
		String total = "";
	
		//connect to database
		Statement statement = null;
		ResultSet results;
		try {
			Connection connection = DriverManager.getConnection(
				"jdbc:sqlserver://localhost:1433;database=QuickFoodMS",
				"User_name",
				"User_password"
				);
			
			statement = connection.createStatement();
			
			
		
		} 	catch (SQLException e) {
			System.out.println("failed to connect to Database");
				e.printStackTrace();
			}
		//get data from database
		try {
			//get dateTime
			String sqlstmnt = "SELECT TOP 1 DateTime FROM [Order Header] WHERE OrderID = " + order.orderId;
			results = statement.executeQuery(sqlstmnt);
			results.next();
			dateTime = results.getString(1);
			
			//get orderSummary
			sqlstmnt = "SELECT Qty, [Item Name] as Name, [Unit Price]*Qty as TotalPrice FROM [Order Item Details] WHERE OrderID = " + order.orderId;
			results = statement.executeQuery(sqlstmnt);
			while(results.next()) {
				orderSummary = orderSummary + "\n"
						+ results.getInt("Qty") + " x " + results.getString("Name") + " (R" + results.getString("TotalPrice") + ")";
			}
			
			//get total cost
			sqlstmnt = "SELECT SUM([Total Price]) FROM [Order Item Details] WHERE OrderID = " + order.orderId;
			results = statement.executeQuery(sqlstmnt);
			results.next();
			total = results.getString(1);
			
		}	catch (SQLException e) {
				e.printStackTrace();
		}
		
		//invoice content
		String invoiceStr = ""
				+ "QuickFood Invoice\n"
				+ "\nCustomer: " + cust.name
				+ "\nEmail: " + cust.email
				+ "\nPhone Number: " + cust.phone
				+ "\nCity: " + cust.city
				
				+ "\n\nOrder ID: " + order.orderId
				+ "\n" + dateTime
				
				+ "\n\nYou have ordered the following from " + rest.name + " in " + rest.city + ": "
				
				+ "\n\n" + orderSummary
				
				+ "\n\nSpecial Preparation Instructions: " + order.prepInstr
				
				+ "\nTotal: R" + total
				
				+ "\n\n" + driver.name + " is nearest to the restaurant and so he will be"
						+ "\ndelivering your order to you at: "
				
				+ "\n" + cust.address
				
				+ "\n\nIf you need to contact the restaurant, their number is " + rest.phone + "."
				+ "\n\nEnjoy your meal!";
		
		//write to text file
		try {
			Formatter f = new Formatter("invoice.txt");
			f.format("%s", invoiceStr);
			f.close();
		}
		catch (Exception e) {
			System.out.println("Error");
		}
		//opens invoice txt file
		try {
			File file = new File("invoice.txt");
			Desktop desktop = Desktop.getDesktop();
			desktop.open(file);
			
		}	catch(Exception e) {
			e.printStackTrace();
		};
	}
}
