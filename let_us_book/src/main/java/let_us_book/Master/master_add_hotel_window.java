package let_us_book.Master;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import let_us_book.Tools.Parser;

public class master_add_hotel_window extends JFrame {

	public static final long serialVersionUID = 1L;
	public JPanel contentPane;
	public JTextField textFieldName;
	public JTextField textFieldCategory;
	public JTextField textFieldRooms;
	public JTextField textFieldBeds;
	public JTextField textFieldCity;
	public JTextField textFieldStreet;
	
	public Parser p = new Parser();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					master_add_hotel_window frame = new master_add_hotel_window();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public master_add_hotel_window() {
		
		setTitle("Hotel Entry Window");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 848, 565);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblHotelName = new JLabel("Hotel name");
		lblHotelName.setBounds(37, 64, 63, 14);
		contentPane.add(lblHotelName);
		
		JLabel lblCategory = new JLabel("Category");
		lblCategory.setBounds(37, 115, 63, 14);
		contentPane.add(lblCategory);
		
		JLabel lblRooms = new JLabel("Rooms");
		lblRooms.setBounds(37, 168, 63, 14);
		contentPane.add(lblRooms);
		
		JLabel lblBeds = new JLabel("Beds");
		lblBeds.setBounds(37, 226, 63, 14);
		contentPane.add(lblBeds);
		
		JLabel lblCity = new JLabel("City");
		lblCity.setBounds(37, 284, 63, 14);
		contentPane.add(lblCity);
		
		JLabel lblStreet = new JLabel("Street");
		lblStreet.setBounds(37, 340, 63, 14);
		contentPane.add(lblStreet);
		
		textFieldName = new JTextField();
		textFieldName.setBounds(157, 61, 412, 20);
		contentPane.add(textFieldName);
		textFieldName.setColumns(10);
		
		textFieldCategory = new JTextField();
		textFieldCategory.setColumns(10);
		textFieldCategory.setBounds(157, 112, 412, 20);
		contentPane.add(textFieldCategory);
		
		textFieldRooms = new JTextField();
		textFieldRooms.setColumns(10);
		textFieldRooms.setBounds(157, 165, 412, 20);
		contentPane.add(textFieldRooms);
		
		textFieldBeds = new JTextField();
		textFieldBeds.setColumns(10);
		textFieldBeds.setBounds(157, 223, 412, 20);
		contentPane.add(textFieldBeds);
		
		textFieldCity = new JTextField();
		textFieldCity.setColumns(10);
		textFieldCity.setBounds(157, 281, 412, 20);
		contentPane.add(textFieldCity);
		
		textFieldStreet = new JTextField();
		textFieldStreet.setColumns(10);
		textFieldStreet.setBounds(157, 337, 412, 20);
		contentPane.add(textFieldStreet);
		
		JButton btnAddEntry = new JButton("Add entry to database");
		btnAddEntry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				if(!(textFieldName.getText().equals("") || textFieldCategory.getText().equals("") || textFieldRooms.getText().equals("") || 
						textFieldBeds.getText().equals("") || textFieldCity.getText().equals("") || textFieldStreet.getText().equals("")))
				{
					
					
					String hotelname = textFieldName.getText();
					String category = textFieldCategory.getText();
					int rooms = Integer.valueOf(textFieldRooms.getText());
					int beds = Integer.valueOf(textFieldBeds.getText());
					//String rooms = textFieldRooms.getText();
					//String beds = textFieldBeds.getText();
					String city = textFieldCity.getText();
					String street = textFieldStreet.getText();
					

					
					p.insertDataIntoDB("INSERT INTO Hotel VALUES ('" + hotelname + "', '" + category +"', " + rooms +", " + beds +", '" + city +"', '"+ street +"');");
					
					JOptionPane.showMessageDialog(null, "Hotel wurde erfolgreich eingefügt.");
					
				}
				else 
				{
					JOptionPane.showMessageDialog(null, "Please fill out all the textboxes.");

				}

			}
		});
		btnAddEntry.setBounds(157, 398, 164, 32);
		contentPane.add(btnAddEntry);
		
		JButton btnClearEntry = new JButton("Clear current entry");
		btnClearEntry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				textFieldName.setText("");
				textFieldCategory.setText("");
				textFieldRooms.setText("");
				textFieldBeds.setText("");
				textFieldCity.setText("");
				textFieldStreet.setText("");
				
			}
		});
		btnClearEntry.setBounds(405, 398, 164, 32);
		contentPane.add(btnClearEntry);
	}
}
