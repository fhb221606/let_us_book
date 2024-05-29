package let_us_book.Usermanagement;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import let_us_book.Tools.Parser;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class user_list_panel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable userListTable;

	/**
	 * Create the panel.
	 */
	public user_list_panel() {
		setLayout(null); // Use null layout for absolute positioning
	    setBounds(20, 20, 900, 560); // Set bounds of the JPanel
		
		JLabel userListLabel = new JLabel("User List");
        userListLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        userListLabel.setBounds(388, 7, 101, 22);
        add(userListLabel);

        // Initialize JTable with data and column names
        userListTable = new JTable();
        Parser p = new Parser();
        String [][] array2d = p.getDataFromDB("SELECT MID, Name, Email, Role FROM Employee");
       
        userListTable.setModel(new DefaultTableModel(
        	array2d,
            new String[] {
                "ID", "Name", "Email", "Role"
            }
        ));
        userListTable.setRowHeight(25);
        userListTable.setFont(new Font("Tahoma", Font.PLAIN, 18));
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBorder(null);
        scrollPane.setBounds(10, 40, 880, 509); 
        add(scrollPane); 
        scrollPane.setViewportView(userListTable);
        
        JButton btnAddUserEntry = new JButton("Add User entry");
        btnAddUserEntry.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		user_add_window userAddHotelWindow = new user_add_window();
        		userAddHotelWindow.setVisible(true);
        	}
        });
        btnAddUserEntry.setBounds(742, 10, 148, 23);
        add(btnAddUserEntry);
        
        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String [][] array2d = p.getDataFromDB("SELECT MID, Name, Email, Role FROM Employee");
        	       
                userListTable.setModel(new DefaultTableModel(
                	array2d,
                    new String[] {
                        "ID", "Name", "Email", "Role"
                    }
                ));
        	}
        });
        refreshButton.setBounds(10, 10, 148, 23);
        add(refreshButton);
        
	}

}
