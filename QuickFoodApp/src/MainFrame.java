import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MainFrame extends JFrame {


	
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 515);
		this.setVisible(true);
	}
	
	public void showCustomerPanel(CustomerPanel panel) {
		getContentPane().add(panel);
	}
	
	public void showRestaurantPanel(RestaurantPanel panel) {
		Container contentPane = getContentPane();
		contentPane.removeAll();
		contentPane.add(panel);
		setVisible(true);
	}
	public void showMenuPanel(MenuPanel panel) {
		Container contentPane = getContentPane();
		contentPane.removeAll();
		contentPane.add(panel);
		setVisible(true);
	}

}
