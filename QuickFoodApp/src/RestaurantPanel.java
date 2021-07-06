import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import java.awt.SystemColor;

public class RestaurantPanel extends JPanel {
	private final ButtonGroup buttonGroup = new ButtonGroup();
	String chosenRestaurantId;
	String customerCity;


	public RestaurantPanel(JButton btn_Next) {
//User Interface Components
		setBackground(new Color(255, 51, 51));
		setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("QuickFood");
		lblNewLabel_1.setBounds(172, 5, 106, 25);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Select any of the following Restaurants available in " + customerCity + ":");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(10, 50, 430, 55);
		add(lblNewLabel_2);
		

		btn_Next.setBackground(SystemColor.control);
		btn_Next.setBounds(172, 442, 89, 23);
		add(btn_Next);

	}


//METHOD
	
	public void listRestaurantOptions(String customerCity) {
		this.customerCity = customerCity;	
		//connect to Database
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
		//get restaurants from selected city
		try {
			String sqlStatement = "SELECT RestaurantID, Name FROM Restaurants WHERE City = '" + customerCity + "'";
			results = statement.executeQuery(sqlStatement);
			int y_pos = 147; //y position of radio button
			
			//iterate through results
			while (results.next()) {
				String restId = results.getString("RestaurantID");
				String restName = results.getString("Name");
				
				//create radio button for each restaurant
				JRadioButton restBtn = new JRadioButton(restName);
				restBtn.addActionListener(new ActionListener() {	//if button clicked chosenRestaurantId is set
					public void actionPerformed(ActionEvent e) {
						chosenRestaurantId = restId;
					}
				});
				buttonGroup.add(restBtn);
				restBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
				restBtn.setBackground(new Color(255, 102, 102));
				restBtn.setBounds(89, y_pos, 254, 39);
				add(restBtn);
				y_pos = y_pos +70; //increment y position for the next radio button				
			}			
		}	catch(SQLException e) {
			e.printStackTrace();
		}		
	}	
}
