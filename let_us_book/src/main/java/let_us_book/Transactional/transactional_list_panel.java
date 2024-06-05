package let_us_book.Transactional;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import let_us_book.Tools.Parser;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class transactional_list_panel extends JPanel {

	private static final long serialVersionUID = 1L;
	public JTable transactionalListTable;
	
	public String[][] transactionalList;
	public JTable tableAllTransactionalData;
	public JTextField textFieldEnterName;

	/**
	 * Create the panel.
	 */
	public transactional_list_panel() {
		setLayout(null); // Use null layout for absolute positioning
	    setBounds(20, 20, 900, 654); // Set bounds of the JPanel
		
		JLabel transactionalListLabel = new JLabel("Transactional List");
        transactionalListLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        transactionalListLabel.setBounds(369, 10, 179, 22);
        add(transactionalListLabel);

        // Initialize JTable with data and column names
        transactionalListTable = new JTable();
        
        Parser p = new Parser();
        
        transactionalList = p.getDataFromDB(
        		"SELECT t.TID, h.name\r\n"
        		+ "FROM Transactional t\r\n"
        		+ "JOIN Hotel h ON t.HID = h.HID");
        
        Object[][] transactionalObjectList = transactionalList;
        
        
        transactionalListTable.setModel(new DefaultTableModel(
        	transactionalObjectList,
            new String[] {
                "ID", "Name"
            }
        ));
        
        transactionalListTable.setRowHeight(25);
        transactionalListTable.setFont(new Font("Tahoma", Font.PLAIN, 18));
        
        
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setFont(new Font("Tahoma", Font.PLAIN, 18));
        scrollPane.setBorder(null);
        scrollPane.setBounds(10, 40, 880, 351); 
        add(scrollPane); 
        scrollPane.setViewportView(transactionalListTable);
        
        tableAllTransactionalData = new JTable();
        tableAllTransactionalData.setModel(new DefaultTableModel(
        	new Object[][] {
        		{null, null, null, null, null, null, null, ""},
        	},
        	new String[] {
        		"Name", "Rooms occuppied", "Beds Occupied", "Category", "Rooms", "Beds", "City", "Street"
        	}
        ));
        JScrollPane scrollPane2 = new JScrollPane();
        scrollPane2.setBounds(10, 426, 880, 60);
        scrollPane2.setBorder(null);
        add(scrollPane2); 
        scrollPane2.setViewportView(tableAllTransactionalData);
        
        
        textFieldEnterName = new JTextField();
        textFieldEnterName.setBounds(493, 497, 86, 20);
        add(textFieldEnterName);
        textFieldEnterName.setColumns(10);
        
        
        
        JButton btnShowAllTransactionalData = new JButton("Show this hotels transactional Data");
        btnShowAllTransactionalData.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		if (textFieldEnterName.getText() != "") {

        			
            		String [][] array2d = p.getDataFromDB("SELECT h.Name, t.Rooms_Occupied, t.Beds_Occupied, h.Category, h.Rooms, h.Beds, h.City, h.Street\r\n"
            				+ "        		FROM Transactional t\r\n"
            				+ "        		JOIN Hotel h ON t.HID = h.HID\r\n"
            				+ "				WHERE Name = '" + textFieldEnterName.getText() +"'");
         	       
            		tableAllTransactionalData.setModel(new DefaultTableModel(
                    	array2d,
                        new String[] {
                        		"Name", "Rooms occuppied", "Beds Occupied", "Category", "Rooms", "Beds", "City", "Street"
                        }
                    ));
            		
				}
        		
        		if (textFieldEnterName.getText().equals("")) {

    				JOptionPane.showMessageDialog(null, "Please enter a hotel name to show it's respective transactional and master data combined.");
				} 
					
        	}
        });
        btnShowAllTransactionalData.setBounds(589, 496, 201, 23);
        add(btnShowAllTransactionalData);
        
        
        /*
        
        ADD THIS BACK WHEN ADD TRANSACTION TICKET IS READY
        
        JButton btnAddHtlEntry = new JButton("Add Transaction entry");
        btnAddHtlEntry.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		
        		//Implementation to open the class 
        		transactional_add_hotel_window transactionalAddHotelWindow = new transactional_add_hotel_window();
        		
        		transactionalAddHotelWindow.setVisible(true);
        		
        		
        	}
        });
        btnAddHtlEntry.setBounds(742, 10, 148, 23);
        add(btnAddHtlEntry);*/
        
        
        
	}
}
