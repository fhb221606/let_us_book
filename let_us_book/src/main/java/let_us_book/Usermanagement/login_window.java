package let_us_book.Usermanagement;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import let_us_book.Tools.Encrypter;
import let_us_book.Tools.Parser;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class login_window extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static String permission = "none";
	private boolean closed = false;
	
	/**
	 * Create the frame.
	 */
	public login_window(JFrame parent) {
		super(parent, "Login", true);
		
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 435, 260);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel label = new JLabel("Username:");
        label.setFont(new Font("Tahoma", Font.PLAIN, 18));
        label.setBounds(10, 53, 100, 50);
        panel.add(label);
        JTextField usernameField = new JTextField();
        usernameField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        usernameField.setBounds(134, 64, 250, 30);
        panel.add(usernameField);

        JLabel label_1 = new JLabel("Password:");
        label_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
        label_1.setBounds(10, 114, 100, 50);
        panel.add(label_1);
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(134, 123, 250, 30);
        panel.add(passwordField);

        // login button
        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
        loginButton.setBounds(271, 164, 113, 47);
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            if (authenticate(username, password)) {
                JOptionPane.showMessageDialog(this, "Login Successful!");
                
                Parser p = new Parser();
                String [][] permission2d = p.getDataFromDB("SELECT * FROM Employee WHERE Name ='"+username+"'");
                permission = permission2d[0][4];
                
                dispose(); // Close the dialog
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        panel.add(loginButton);

        getContentPane().add(panel);
        
        JLabel logoLabel = new JLabel("LET US BOOK");
        logoLabel.setFont(new Font("Palatino Linotype", Font.BOLD, 24));
        logoLabel.setBounds(131, 11, 177, 42);
        panel.add(logoLabel);
        
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		closed = true;
        		dispose();
        	}
        });
        closeButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
        closeButton.setBounds(134, 164, 113, 47);
        panel.add(closeButton);
        setVisible(true);
	}
	
	public boolean getClosed() {
		return closed;
	}
	
	// Authentication Process
	protected boolean authenticate(String username, String password) {
		
		String[][] result = null;
		String encrypt_pw = "";
		
		boolean survived_try_1 = false;
		boolean survived_try_2 = false;
		
		// get the user data from DB
		try {
			Parser p = new Parser();
			result = p.getDataFromDB("SELECT Name, Password, Role FROM Employee WHERE Name = '" + username + "'");
			survived_try_1 = true;
		} catch (Exception e) {
			System.err.println("Something went wrong when getting user data");
		}
		
		// Encrypt password
		try {
			Encrypter e = new Encrypter();
			encrypt_pw = e.encrypt(password);
			survived_try_2 = true;
		} catch (Exception e) {
			System.err.println("Something went wrong when encrypting");
		}
		
		// Check if all went well 
		if (survived_try_1 && survived_try_2 && result != null && result.length != 0) {
			if (username.equals(result[0][0]) && encrypt_pw.equals(result[0][1])) {
				permission = result[0][2];
				return true;
			}
		}
		
		return false;
	}
}
