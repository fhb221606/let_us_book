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
					JOptionPane.showMessageDialog(null, "Hotel wurde erfolgreich entfernt. Please refresh the page to load the changes in the view.");
				
					
					
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
        
        
        
        
        
     
        
	}
}
