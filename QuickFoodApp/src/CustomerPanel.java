import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CustomerPanel extends JPanel {


	public CustomerPanel(Customer customer1, JButton btnNext_1) {
//User Interface components
		setBackground(new Color(255, 51, 51));
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome to");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblNewLabel.setBounds(162, 0, 91, 40);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("QuickFood");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setBounds(141, 36, 132, 40);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Please enter your details");
		lblNewLabel_2.setBounds(141, 87, 146, 28);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Name:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_3.setBounds(53, 136, 52, 22);
		add(lblNewLabel_3);
		
		JTextArea textArea_name = new JTextArea();
		textArea_name.setBackground(SystemColor.control);
		textArea_name.setBounds(107, 136, 265, 22);
		add(textArea_name);
		
		JLabel lblNewLabel_3_1 = new JLabel("Phone:");
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_3_1.setBounds(53, 188, 52, 22);
		add(lblNewLabel_3_1);
		
		JTextArea textArea_phone = new JTextArea();
		textArea_phone.setBackground(SystemColor.menu);
		textArea_phone.setBounds(107, 188, 265, 22);
		add(textArea_phone);
		
		JLabel lblNewLabel_3_2 = new JLabel("Address:");
		lblNewLabel_3_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_3_2.setBounds(53, 302, 52, 22);
		add(lblNewLabel_3_2);
		
		JTextArea textArea_address = new JTextArea();
		textArea_address.setBackground(SystemColor.menu);
		textArea_address.setBounds(107, 302, 265, 22);
		add(textArea_address);
		
		JLabel lblNewLabel_3_3 = new JLabel("Email:");
		lblNewLabel_3_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_3_3.setBounds(53, 356, 52, 22);
		add(lblNewLabel_3_3);
		
		JTextArea textArea_email = new JTextArea();
		textArea_email.setBackground(SystemColor.menu);
		textArea_email.setBounds(107, 356, 265, 22);
		add(textArea_email);
		
		JComboBox comboBox_city = new JComboBox();
		comboBox_city.setModel(new DefaultComboBoxModel(new String[] {"Johannesburg", "Bloemfontein", "Cape Town", "Durban", "Potchefstroom", "Springbok", "Witbank", "Port Elizabeth"}));
		comboBox_city.setBounds(107, 244, 265, 22);
		add(comboBox_city);
		
		JLabel lblNewLabel_3_1_1 = new JLabel("City:");
		lblNewLabel_3_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_3_1_1.setBounds(53, 243, 52, 22);
		add(lblNewLabel_3_1_1);
		
//---------------------------------------------------------------------------------------------
		//Button action
		JButton btn_submit = new JButton("Submit");
		btn_submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//customer input values
				String custName = textArea_name.getText();
				String custPhone = textArea_phone.getText();
				String custAddress = textArea_address.getText();
				String custCity = (String) comboBox_city.getSelectedItem();
				String custEmail = textArea_email.getText();
				btn_submit.setEnabled(false); //disable submit btn
				
				customer1.addInfo(custName, custPhone, custAddress, custCity, custEmail);
				customer1.DBCon_insertcust_setID();
			}
		});
		btn_submit.setBackground(SystemColor.control);
		btn_submit.setBounds(183, 398, 89, 23);
		add(btn_submit);
		
//--------------------------------------------------------------------------------------------
		//'Next' btn attributes
		btnNext_1.setBackground(SystemColor.control);
		btnNext_1.setBounds(141, 432, 168, 23);
		add(btnNext_1);

	}
}
