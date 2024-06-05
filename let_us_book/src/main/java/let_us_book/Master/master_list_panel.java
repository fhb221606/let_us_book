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
		masterListLabel.setBounds(388, 7, 101, 22);
        masterListLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        add(masterListLabel);

        Parser p = new Parser();
        

        JButton btnEditHotel = new JButton("Show hotel to be changed");
        btnEditHotel.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		

        		int hotelId = Integer.valueOf(textFieldHotelID.getText());
        		
        		String [][] array2d = p.getDataFromDB("Select Name, Category, Rooms, Beds, City, Street FROM Hotel WHERE HID = " + hotelId);
     	       
        		editTable.setModel(new DefaultTableModel(
                	array2d,
                    new String[] {
                        "Name", "Category", "Rooms", "Beds", "City", "Street"
                    }
                ));
        		

				JOptionPane.showMessageDialog(null, "Edit view was updated, edit is now ready to .");
        		
        		
        	}
        });
        
        
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
        

        btnEditHotel.setBounds(753, 496, 137, 23);
        add(btnEditHotel);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 40, 880, 312);
        scrollPane.setBorder(null);
        add(scrollPane); 
        scrollPane.setViewportView(masterListTable);
        
        
        
        
        
        
        editTable = new JTable();
        editTable.setModel(new DefaultTableModel(
        	new Object[][] {
        		{null, null, null, null, null, null},
        	},
        	new String[] {
        		"Name", "Category", "Rooms", "Beds", "City", "City"
        	}
        ));
        
        JScrollPane scrollPane2 = new JScrollPane();
        scrollPane2.setBounds(10, 363, 880, 60);
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
        
        
        
        JButton btnDlteEntry = new JButton("Remove hotel entry");
        btnDlteEntry.setBounds(618, 10, 137, 23);
        btnDlteEntry.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		
        		if(!textFieldHotelID.equals(""))
        		{
            		int hotelId = Integer.valueOf(textFieldHotelID.getText());
            		
            		p.deleteDataFromDB("DELETE FROM let_us_book.Hotel WHERE HID = " + hotelId + ";");
					JOptionPane.showMessageDialog(null, "Hotel was successfully removed. Please refresh the page to load the changes in the view.");
				
					
					
        		}
			else {
					JOptionPane.showMessageDialog(null, "The selected hotel does not exist, please choose one that is available in the list view.");
        		}
        		
        		
        	}
        });
        add(btnDlteEntry);
        
        
        
        textFieldHotelID = new JTextField();
        textFieldHotelID.setBounds(570, 11, 45, 20);
        add(textFieldHotelID);
        textFieldHotelID.setColumns(10);
        
        JLabel lblHotelID = new JLabel("Hotel ID");
        lblHotelID.setBounds(514, 14, 46, 14);
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
        btnRefresh.setBounds(162, 10, 137, 23);
        add(btnRefresh);
        
        
        
        
        
        textHtlName = new JTextField();
        textHtlName.setBounds(10, 431, 125, 20);
        add(textHtlName);
        textHtlName.setColumns(10);
        
        textLocation = new JTextField();
        textLocation.setColumns(10);
        textLocation.setBounds(618, 431, 125, 20);
        add(textLocation);
        
        textCategory = new JTextField();
        textCategory.setColumns(10);
        textCategory.setBounds(162, 431, 125, 20);
        add(textCategory);
        
        textBeds = new JTextField();
        textBeds.setColumns(10);
        textBeds.setBounds(309, 431, 125, 20);
        add(textBeds);
        
        textRooms = new JTextField();
        textRooms.setColumns(10);
        textRooms.setBounds(458, 431, 125, 20);
        add(textRooms);
        
        btnApplyChangs = new JButton("Apply changes");
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
        btnApplyChangs.setBounds(753, 462, 137, 23);
        add(btnApplyChangs);
        
        textStreet = new JTextField();
        textStreet.setColumns(10);
        textStreet.setBounds(765, 431, 125, 20);
        add(textStreet);
        
	}
}