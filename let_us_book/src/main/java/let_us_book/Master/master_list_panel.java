package let_us_book.Master;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import let_us_book.Tools.Parser;
import let_us_book.Usermanagement.login_window;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class master_list_panel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable masterListTable;
	private JTextField textFieldHotelID;
	private JTable editTable;
	private JTextField textHtlName;
	private JTextField textLocation;
	private JTextField textCategory;
	private JTextField textBeds;
	private JTextField textRooms;
	private JButton btnApplyChangs;
	private JTextField textStreet;
	public Object btnDlteEntry;
	
	
	public JTable getMasterListTable() {
	    return masterListTable;
	}
	
	/**
	 * Create the panel.
	 */
	public master_list_panel() {
	    setBounds(20, 20, 987, 560);
		setLayout(null);
		
		
		JLabel masterListLabel = new JLabel("Master List");
		masterListLabel.setBounds(389, 7, 101, 22);
        masterListLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        add(masterListLabel);

        Parser p = new Parser();
        
        // Initialize JTable with data and column names
        masterListTable = new JTable();
        String [][] array2d = p.getDataFromDB("SELECT HID,NAME,CATEGORY FROM HOTEL");
       
        masterListTable.setModel(new DefaultTableModel(
        		array2d
            ,
            new String[] {
                "ID", "Name", "Category"
            }
        ));
        masterListTable.setRowHeight(25);
        masterListTable.setFont(new Font("Tahoma", Font.PLAIN, 18));
        

        

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 40, 880, 312);
        scrollPane.setBorder(null);
        add(scrollPane); 
        scrollPane.setViewportView(masterListTable);
        
        editTable = new JTable();
        editTable.setRowHeight(25);
        editTable.setFont(new Font("Tahoma", Font.PLAIN, 14));
        editTable.setModel(new DefaultTableModel(
        	new Object[][] {
        		{null, null, null, null, null, null},
        	},
        	new String[] {
        		"Name", "Category", "Rooms", "Beds", "City", "Street"
        	}
        ));
        
        JScrollPane scrollPane2 = new JScrollPane();
        scrollPane2.setVisible(false);
        scrollPane2.setBounds(10, 397, 880, 52);
        scrollPane2.setBorder(null);
        add(scrollPane2); 
        scrollPane2.setViewportView(editTable);
        
        
        
        JButton btnAddHtlEntry = new JButton("Add hotel entry");
        btnAddHtlEntry.setBounds(765, 10, 125, 23);
        btnAddHtlEntry.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		//Implementation to open the class master_add_hotel_window
        		master_add_hotel_window masterAddHotelWindow = new master_add_hotel_window();
        		
        		masterAddHotelWindow.setVisible(true);
        	}
        });
        add(btnAddHtlEntry);
        
        
        
        JButton btnDlteEntry = new JButton("Remove");
        btnDlteEntry.setEnabled(false);
        btnDlteEntry.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnDlteEntry.setBounds(272, 363, 100, 23);
        btnDlteEntry.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(login_window.permission.equals("Senior User"))
        		{
	        		if(!getTextFieldHotelID().equals(""))
	        		{
	            		int hotelId = Integer.valueOf(getTextFieldHotelID().getText());
	            		
	            		p.deleteDataFromDB("DELETE FROM let_us_book.Hotel WHERE HID = " + hotelId + ";");
						JOptionPane.showMessageDialog(null, "Hotel was successfully removed. Please refresh the page to load the changes in the view.");
	        		}
					else {
							JOptionPane.showMessageDialog(null, "The selected hotel does not exist, please choose one that is available in the list view.");
		        	}
        		}
        		else
        		{
        			JOptionPane.showMessageDialog(null, "You do not have the permission to delete hotels.");
        		}
        		
        	}
        });
        add(btnDlteEntry);
        
        
        
        setTextFieldHotelID(new JTextField());
        getTextFieldHotelID().setBounds(89, 364, 63, 20);
        add(getTextFieldHotelID());
        getTextFieldHotelID().setColumns(10);
        
        JLabel lblHotelID = new JLabel("Hotel ID:");
        lblHotelID.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblHotelID.setBounds(10, 363, 82, 23);
        add(lblHotelID);
        
        JButton btnRefresh = new JButton("Refresh view");
        btnRefresh.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		String [][] array2d = p.getDataFromDB("SELECT HID, Name, Category FROM Hotel");
     	       
        		masterListTable.setModel(new DefaultTableModel(
                	array2d,
                    new String[] {
                        "HID", "Name", "Category"
                    }
                ));
        		

				JOptionPane.showMessageDialog(null, "View was refreshed, please check if changes are correct.");
        	}
        });
        btnRefresh.setBounds(10, 10, 137, 23);
        add(btnRefresh);
        
        textHtlName = new JTextField();
        textHtlName.setVisible(false);
        textHtlName.setFont(new Font("Tahoma", Font.PLAIN, 14));
        textHtlName.setBounds(21, 489, 125, 25);
        add(textHtlName);
        textHtlName.setColumns(10);
        
        textLocation = new JTextField();
        textLocation.setVisible(false);
        textLocation.setFont(new Font("Tahoma", Font.PLAIN, 14));
        textLocation.setColumns(10);
        textLocation.setBounds(608, 489, 125, 25);
        add(textLocation);
        
        textCategory = new JTextField();
        textCategory.setVisible(false);
        textCategory.setFont(new Font("Tahoma", Font.PLAIN, 14));
        textCategory.setColumns(10);
        textCategory.setBounds(169, 489, 125, 25);
        add(textCategory);
        
        textBeds = new JTextField();
        textBeds.setVisible(false);
        textBeds.setFont(new Font("Tahoma", Font.PLAIN, 14));
        textBeds.setColumns(10);
        textBeds.setBounds(317, 489, 125, 25);
        add(textBeds);
        
        textRooms = new JTextField();
        textRooms.setVisible(false);
        textRooms.setFont(new Font("Tahoma", Font.PLAIN, 14));
        textRooms.setColumns(10);
        textRooms.setBounds(461, 489, 125, 25);
        add(textRooms);
        
        btnApplyChangs = new JButton("Change");
        btnApplyChangs.setVisible(false);
        btnApplyChangs.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnApplyChangs.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		int hotelId = Integer.valueOf(textFieldHotelID.getText());
        		
        		String name = textHtlName.getText();
                String category = textCategory.getText();
                int beds = Integer.parseInt(textRooms.getText());
                int rooms = Integer.parseInt(textBeds.getText());
                String city = textLocation.getText();
                String street = textStreet.getText();
              
        		
        		String updateQuery=("UPDATE Hotel SET Name = '"+ name+ "', Category ='"+ category + "', Rooms =" + rooms +", Beds =" + beds + ", City ='" + city +"', Street ='" + street +"'  WHERE HID =" +hotelId);        		
        		 int rowAffected = p.updateDataIntoDB(updateQuery);
        		 
        		 if(rowAffected > 0) {
        			 
        			JOptionPane.showMessageDialog(null, "Hotel information updated successfully");
        		 }else {
        			 JOptionPane.showMessageDialog(null, "Failed to update hotel information");
        		 }
        	}
        });
        btnApplyChangs.setBounds(810, 524, 80, 25);
        add(btnApplyChangs);
        
        textStreet = new JTextField();
        textStreet.setVisible(false);
        textStreet.setFont(new Font("Tahoma", Font.PLAIN, 14));
        textStreet.setColumns(10);
        textStreet.setBounds(749, 489, 125, 25);
        add(textStreet);
        
        JLabel lblEdit1 = new JLabel("Name");
        lblEdit1.setVisible(false);
        lblEdit1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblEdit1.setBounds(20, 460, 80, 25);
        add(lblEdit1);
        
        JLabel lblEdit2 = new JLabel("Category");
        lblEdit2.setVisible(false);
        lblEdit2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblEdit2.setBounds(169, 460, 80, 25);
        add(lblEdit2);
        
        JLabel lblEdit3 = new JLabel("Rooms");
        lblEdit3.setVisible(false);
        lblEdit3.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblEdit3.setBounds(317, 460, 80, 25);
        add(lblEdit3);
        
        JLabel lblEdit4 = new JLabel("Beds");
        lblEdit4.setVisible(false);
        lblEdit4.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblEdit4.setBounds(461, 460, 80, 25);
        add(lblEdit4);
        
        JLabel lblEdit5 = new JLabel("City");
        lblEdit5.setVisible(false);
        lblEdit5.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblEdit5.setBounds(608, 460, 80, 25);
        add(lblEdit5);
        
        JLabel lblEdit6 = new JLabel("Street");
        lblEdit6.setVisible(false);
        lblEdit6.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblEdit6.setBounds(749, 460, 80, 25);
        add(lblEdit6);
        
        JButton btnEditEntry = new JButton("Edit");
        btnEditEntry.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		textHtlName.setVisible(true);
                textCategory.setVisible(true);
                textRooms.setVisible(true);
                textBeds.setVisible(true);
                textLocation.setVisible(true);
                textStreet.setVisible(true);
                btnApplyChangs.setVisible(true);
                lblEdit1.setVisible(true);
                lblEdit2.setVisible(true);
                lblEdit3.setVisible(true);
                lblEdit4.setVisible(true);
                lblEdit5.setVisible(true);
                lblEdit6.setVisible(true);
        	}
        });
        btnEditEntry.setEnabled(false);
        btnEditEntry.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnEditEntry.setBounds(383, 363, 100, 23);
        add(btnEditEntry);
        
        
        JButton btnSearchHotel = new JButton("Search");
        btnSearchHotel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnSearchHotel.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {

        		int hotelId = Integer.valueOf(getTextFieldHotelID().getText());
        		
        		String [][] array2d = p.getDataFromDB("Select Name, Category, Rooms, Beds, City, Street FROM Hotel WHERE HID = " + hotelId);
        		
        		try {
        			if (array2d[0][0] != null) {
            			System.out.println("found");
            		}
        			
        			editTable.setModel(new DefaultTableModel(
                        	array2d,
                            new String[] {
                                "Name", "Category", "Rooms", "Beds", "City", "Street"
                            }
                        ));
        			
        			btnDlteEntry.setEnabled(true);
        			btnEditEntry.setEnabled(true);
        			scrollPane2.setVisible(true);
        			
        		} catch (Exception ex) {
        			System.out.println("not found");
        			btnDlteEntry.setEnabled(false);
        			btnEditEntry.setEnabled(false);
        			scrollPane2.setVisible(false);
        			textHtlName.setVisible(false);
                    textCategory.setVisible(false);
                    textRooms.setVisible(false);
                    textBeds.setVisible(false);
                    textLocation.setVisible(false);
                    textStreet.setVisible(false);
                    btnApplyChangs.setVisible(false);
                    lblEdit1.setVisible(false);
                    lblEdit2.setVisible(false);
                    lblEdit3.setVisible(false);
                    lblEdit4.setVisible(false);
                    lblEdit5.setVisible(false);
                    lblEdit6.setVisible(false);
        		}
        		
        	}
        });
        
        btnSearchHotel.setBounds(162, 363, 100, 23);
        add(btnSearchHotel);
        
        
	}


	public JTextField getTextFieldHotelID() {
		return textFieldHotelID;
	}

	public void setTextFieldHotelID(JTextField textFieldHotelID) {
		this.textFieldHotelID = textFieldHotelID;
		textFieldHotelID.setFont(new Font("Tahoma", Font.PLAIN, 14));
	}
}