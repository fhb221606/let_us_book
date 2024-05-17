package let_us_book.Master;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class master_list_panel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable masterListTable;

	/**
	 * Create the panel.
	 */
	public master_list_panel() {
		setLayout(null); // Use null layout for absolute positioning
	    setBounds(20, 20, 900, 560); // Set bounds of the JPanel
		
		JLabel masterListLabel = new JLabel("Master List");
        masterListLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        masterListLabel.setBounds(388, 7, 101, 22);
        add(masterListLabel);

        // Initialize JTable with data and column names
        masterListTable = new JTable();
        masterListTable.setModel(new DefaultTableModel(
            new Object[][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
                // Add more rows as needed...
            },
            new String[] {
                "ID", "Name", "Category", "Details"
            }
        ));
        masterListTable.setRowHeight(25);
        masterListTable.setFont(new Font("Tahoma", Font.PLAIN, 18));
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBorder(null);
        scrollPane.setBounds(10, 40, 880, 509); 
        add(scrollPane); 
        scrollPane.setViewportView(masterListTable);
        
        JButton btnAddHtlEntry = new JButton("Add hotel entry");
        btnAddHtlEntry.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		
        		//Implementation to open the class 
        		master_add_hotel_window masterAddHotelWindow = new master_add_hotel_window();
        		
        		masterAddHotelWindow.setVisible(true);
        		
        		
        	}
        });
        btnAddHtlEntry.setBounds(742, 10, 148, 23);
        add(btnAddHtlEntry);
        
	}

}
