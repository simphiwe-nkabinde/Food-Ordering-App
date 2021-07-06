import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextArea;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//OBJECTS
		Customer cust1 = new Customer();
		Restaurant rest1 = new Restaurant();
		Driver driver1 = new Driver();
		
		//CustomerPanel
		JButton btnNext_1 = new JButton("Next");
		CustomerPanel custPanel = new CustomerPanel(cust1, btnNext_1);
		
		MainFrame mainframe = new MainFrame();
		mainframe.showCustomerPanel(custPanel);
		
		//RestaurantPanel
		JButton btnNext_2 = new JButton("Next");
		RestaurantPanel restPanel = new RestaurantPanel(btnNext_2);
		
		//MenuPanel
		JButton btnOrder = new JButton("Order");
		JTextArea textArea_prepInstr = new JTextArea();
		MenuPanel menuPanel = new MenuPanel(btnOrder, textArea_prepInstr);;
		 
		OrderData orderData;
		
		Invoice invoice = new Invoice();
		
		
		
		
		//'Next' button actionListener on CustomerPanel
		btnNext_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				driver1.DBCon_getdriver(cust1.city);
				restPanel.listRestaurantOptions(cust1.city);
				mainframe.showRestaurantPanel(restPanel);
			}
		});
		
		//'Next' button actionListener on RestaurantPanel
		btnNext_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				rest1.getRestaurant(restPanel.chosenRestaurantId);
				
				menuPanel.listMenuItems(rest1.id);
				mainframe.showMenuPanel(menuPanel);
			}
		});
		//'Order' button actionListener on MenuPanel
		btnOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuPanel.prepInstr = textArea_prepInstr.getText();
				
				btnOrder.setEnabled(false);	//disable Order btn
				
				OrderData orderData = new OrderData(cust1.id, rest1.id, driver1.id, menuPanel.prepInstr, menuPanel.orderArrList);
				orderData.DBinsert_orderHeader();
				orderData.DBinsert_orderDetails();
				
				invoice.printInvoice(cust1, rest1, driver1, orderData);
			}	
		});
			
	}

}
