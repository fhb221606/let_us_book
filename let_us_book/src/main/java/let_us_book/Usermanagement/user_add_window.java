package let_us_book.Usermanagement;

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

import let_us_book.Tools.Encrypter;
import let_us_book.Tools.Parser;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPasswordField;

public class user_add_window extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField usernameTF;
	private JTextField passwordTF;
	private JTextField passwordAgainTF;
	private JTextField emailTF;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					user_add_window frame = new user_add_window();
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
	public user_add_window() {
		
		Parser p = new Parser();
		
		setTitle("Add User");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 553, 352);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblUsername.setBounds(10, 15, 110, 20);
		contentPane.add(lblUsername);
		
		usernameTF = new JTextField();
		usernameTF.setFont(new Font("Tahoma", Font.PLAIN, 16));
		usernameTF.setBounds(174, 10, 350, 30);
		contentPane.add(usernameTF);
		usernameTF.setColumns(10);
		

		JButton clearFormButton = new JButton("Clear Form");
		clearFormButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		clearFormButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				usernameTF.setText("");
				passwordTF.setText("");
				passwordAgainTF.setText("");
				emailTF.setText("");
				
			}
		});
		clearFormButton.setBounds(360, 270, 164, 32);
		contentPane.add(clearFormButton);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPassword.setBounds(10, 60, 110, 20);
		contentPane.add(lblPassword);
		
		passwordTF = new JPasswordField();
		passwordTF.setFont(new Font("Tahoma", Font.PLAIN, 16));
		passwordTF.setColumns(10);
		passwordTF.setBounds(174, 55, 350, 30);
		contentPane.add(passwordTF);
		
		passwordAgainTF = new JPasswordField();
		passwordAgainTF.setFont(new Font("Tahoma", Font.PLAIN, 16));
		passwordAgainTF.setColumns(10);
		passwordAgainTF.setBounds(174, 100, 350, 30);
		contentPane.add(passwordAgainTF);
		
		JLabel lblPasswordAgain = new JLabel("Password Again");
		lblPasswordAgain.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPasswordAgain.setBounds(10, 105, 136, 20);
		contentPane.add(lblPasswordAgain);
		
		JLabel lblEmail = new JLabel("EMail");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEmail.setBounds(10, 150, 110, 20);
		contentPane.add(lblEmail);
		
		emailTF = new JTextField();
		emailTF.setFont(new Font("Tahoma", Font.PLAIN, 16));
		emailTF.setColumns(10);
		emailTF.setBounds(174, 145, 350, 30);
		contentPane.add(emailTF);
		
		JLabel lblRole = new JLabel("Role");
		lblRole.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblRole.setBounds(10, 196, 110, 20);
		contentPane.add(lblRole);
		
		JComboBox roleCB = new JComboBox();
		roleCB.setModel(new DefaultComboBoxModel(new String[] {"Senior User", "Head"}));
		roleCB.setFont(new Font("Tahoma", Font.PLAIN, 16));
		roleCB.setBounds(174, 190, 350, 30);
		contentPane.add(roleCB);
		
		JButton addUserButton = new JButton("Add User");
		addUserButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		addUserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				boolean check;
				
				if(!(usernameTF.getText().equals("") || passwordTF.getText().equals("") || passwordAgainTF.getText().equals("") || emailTF.getText().equals(""))
						&& passwordTF.getText().equals(passwordAgainTF.getText())) {
					
					check = true;
					
					
					String username = usernameTF.getText();
					String password = passwordTF.getText();
					String email = emailTF.getText();
					String role = (String) roleCB.getSelectedItem();
					
					
					Encrypter enc = new Encrypter();
					
					try {
						password = enc.encrypt(password);
					} catch (Exception ex) {
						System.err.println(ex);
					}

					
					p.insertDataIntoDB("INSERT INTO Employee (Name, Email, Password, Role) VALUES ('" + username + "', '" + email +"', '" + password +"', '" + role +"');");
					
					JOptionPane.showMessageDialog(null, "User was successfully added!");
					
				}
				else 
				{
					JOptionPane.showMessageDialog(null, "Please fill out all the textboxes.");

				}

			}
		});
		addUserButton.setBounds(174, 270, 164, 32);
		contentPane.add(addUserButton);
		
		
	}
}
