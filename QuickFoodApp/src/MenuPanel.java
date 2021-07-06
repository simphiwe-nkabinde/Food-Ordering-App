import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class MenuPanel extends JPanel {
	
	

	//Fields
	ArrayList<int[]> orderArrList = new ArrayList<int[]>();
	String prepInstr;

	public MenuPanel(JButton btnOrder, JTextArea textArea_prepInstr) {
		setBackground(new Color(255, 51, 51));
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("MENU");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(166, 11, 86, 39);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Select your meals:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(43, 61, 338, 39);
		add(lblNewLabel_1);
	
		//textArea_prepInstr style attributes
		textArea_prepInstr.setBounds(20, 346, 395, 39);
		add(textArea_prepInstr);
		
		JLabel lblNewLabel_3 = new JLabel("Special Preparation Instructions:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_3.setBounds(20, 321, 286, 14);
		add(lblNewLabel_3);
			
		btnOrder.setBounds(163, 412, 89, 23);
		add(btnOrder);		
		
/*
		JLabel lblNewLabel_2 = new JLabel("Thank you for using our service. Your receipt is ready!");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(10, 416, 409, 39);
		add(lblNewLabel_2);
*/
	}
	
//METHOD
	
	public void listMenuItems(String restaurantID) {
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
			
		}	catch(SQLException e) {
			System.out.println("failed to connect to Database");
			e.printStackTrace();
		}
		
		//get items from [Items] table with selected restaurantID
		try {
			String sqlStatement = "SELECT ItemID, Name, Price FROM Items "
					+ "WHERE RestaurantID = '" + restaurantID + "'";
			results = statement.executeQuery(sqlStatement);
			//y position of components
			int ypos_itemName = 151;
			int ypos_qty = 153;
			int ypos_addbtn = 152;
			
			while (results.next()) {
				int itemId = results.getInt("ItemID");
				String itemName = results.getString("Name");
				String itemPrice = results.getString("Price");
				
				//create item:
				
				//item name and price label
				JLabel lbl_itemName = new JLabel( itemName + " @ " + "R" + itemPrice);
				lbl_itemName.setFont(new Font("Tahoma", Font.PLAIN, 15));
				lbl_itemName.setBounds(10, ypos_itemName, 242, 24);
				add(lbl_itemName);
				//quantity text area
				JTextArea textArea_qty = new JTextArea();
				textArea_qty.setText("0");
				textArea_qty.setBounds(262, ypos_qty, 28, 22);
				add(textArea_qty);
				//'Add' button
				JButton btn_add = new JButton("ADD");
				btn_add.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String qtyStr = textArea_qty.getText();
						Integer qty = Integer.parseInt(qtyStr);

						if(qty != null && qty != 0) {
							int[] idQtyArray = {itemId, qty};
							//disable button and text area
							btn_add.setEnabled(false);
							textArea_qty.setEnabled(false);
							//add idQtyArray to array list
							orderArrList.add(idQtyArray);
						}
					}
				});
				btn_add.setBackground(SystemColor.control);
				btn_add.setBounds(338, ypos_addbtn, 76, 23);
				add(btn_add);				
				//increment all y position values by 50px
				ypos_itemName += 50;
				ypos_qty += 50;
				ypos_addbtn += 50;			
			}
		}	catch(SQLException e) {
			System.out.println("error in processing query");
			e.printStackTrace();
		}
	}
}