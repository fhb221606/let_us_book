package let_us_book.Transactional;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import let_us_book.Tools.Parser;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class transactional_summary_panel extends JPanel {

	private static final long serialVersionUID = 1L;
	public JTable transactionalSummaryTable;
	
	public String[][] content_transactional_table;
	public String[][] total_transactional_table;
	
	public String[][] table_content;

	/**
	 * Create the panel.
	 */
	
	public void set_table_content() {
		table_content = new String[][] {
			{"*****", null, null, null, null},
			{"****", null, null, null, null},
			{"***", null, null, null, null},
			{"**", null, null, null, null},
			{"*", null, null, null, null},
			{"Total", null, null, null, null},
		};
		
	}
	
	public void update_table() {
		transactionalSummaryTable.setModel(new DefaultTableModel(
			table_content,
			new String[] {"Category", "Rooms", "Room occupancy", "Beds", "Beds occupancy"}
		));
	}
	
	
	public transactional_summary_panel() {
		
		set_table_content();
		
		setLayout(null); // Use null layout for absolute positioning
	    setBounds(20, 20, 900, 560); // Set bounds of the JPanel
		
		JLabel transactionalSummaryLabel = new JLabel("Transactional Data Summary");
		transactionalSummaryLabel.setBounds(292, 10, 308, 25);
		transactionalSummaryLabel.setForeground(Color.BLACK);
		transactionalSummaryLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		transactionalSummaryLabel.setBackground(new Color(213, 0, 0));
		add(transactionalSummaryLabel);
		
		
		transactionalSummaryTable = new JTable();
		transactionalSummaryTable.setFont(new Font("Tahoma", Font.PLAIN, 18));
		transactionalSummaryTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		transactionalSummaryTable.setRowHeight(30);
		transactionalSummaryTable.setModel(new DefaultTableModel(
			table_content,
			new String[] {
				"Category", "Rooms", "Room occupancy", "Beds", "Beds occupancy"
			}
		));
		transactionalSummaryTable.getColumnModel().getColumn(0).setPreferredWidth(100);
		transactionalSummaryTable.getColumnModel().getColumn(1).setPreferredWidth(100);
		transactionalSummaryTable.getColumnModel().getColumn(2).setPreferredWidth(100);
		transactionalSummaryTable.getColumnModel().getColumn(3).setPreferredWidth(100);
		transactionalSummaryTable.setBounds(21, 58, 658, 210);
		
        JScrollPane scrollPane = new JScrollPane(transactionalSummaryTable);
        scrollPane.setFont(new Font("Tahoma", Font.PLAIN, 18));
        scrollPane.setBorder(null);
        scrollPane.setBounds(10, 106, 880, 205); 
        add(scrollPane); 
        scrollPane.setViewportView(transactionalSummaryTable);
        
        JComboBox<Integer> monthBox = new JComboBox();
        monthBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
        monthBox.setEditable(true);
        monthBox.setModel(new DefaultComboBoxModel(new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12}));
        monthBox.setSelectedIndex(0);
        monthBox.setBounds(84, 63, 63, 22);
        add(monthBox);
        
        JLabel lblNewLabel = new JLabel("Date:");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel.setBounds(23, 67, 48, 14);
        add(lblNewLabel);
        
        JComboBox<Integer> yearBox = new JComboBox();
        yearBox.setModel(new DefaultComboBoxModel(new Integer[] {2020, 2021, 2022, 2023, 2024, 2025, 2026, 2027, 2028, 2029, 2030}));
        yearBox.setSelectedIndex(3);
        yearBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
        yearBox.setEditable(true);
        yearBox.setBounds(153, 63, 74, 22);
        add(yearBox);
        
        
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		set_data((int)monthBox.getSelectedItem(), (int)yearBox.getSelectedItem());
        	}
        });
        searchButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        searchButton.setBounds(237, 59, 101, 30);
        add(searchButton);
		
        set_data((Integer)monthBox.getSelectedItem(), (Integer)yearBox.getSelectedItem());
	}
	
	public void set_data(int month, int year) {
		
		set_table_content();
		update_table();
		
		Parser p = new Parser();
		
		
		content_transactional_table = p.getDataFromDB(
				"SELECT h.Category,\r\n"
				+ "    SUM(h.Rooms) AS Total_Rooms,\r\n"
				+ "    ROUND(CAST(SUM(t.Rooms_Occupied) AS FLOAT) / SUM(h.Rooms) * 100, 2) AS Percentage_Rooms_Occupied,\r\n"
				+ "    SUM(h.Beds) AS Total_Beds,\r\n"
				+ "    ROUND(CAST(SUM(t.Beds_Occupied) AS FLOAT) / SUM(h.Beds) * 100, 2) AS Percentage_Beds_Occupied\r\n"
				+ "FROM Hotel h\r\n"
				+ "JOIN Transactional t ON h.HID = t.HID\r\n"
				+ "WHERE DATEPART(YEAR, t.Index_Date) = " + year + " AND DATEPART(MONTH, t.Index_Date) = " + month + "\r\n"
				+ "GROUP BY h.Category\r\n"
				+ "ORDER BY h.Category DESC ");
		
		/*for (int i = 0; i < content_transactional_table.length; i++) {
	        for (int j = 0; j < content_transactional_table[i].length; j++) {
	            System.out.print(content_transactional_table[i][j] + " ");
	        }
	        System.out.println(); 
	    }*/

		
		
		total_transactional_table = p.getDataFromDB(
				"SELECT SUM(h.Rooms) AS Total_Rooms,\r\n"
				+ "    ROUND(CAST(SUM(t.Rooms_Occupied) AS FLOAT) / SUM(h.Rooms) * 100, 2) AS Percentage_Rooms_Occupied,\r\n"
				+ "    SUM(h.Beds) AS Total_Beds,\r\n"
				+ "    ROUND(CAST(SUM(t.Beds_Occupied) AS FLOAT) / SUM(h.Beds) * 100, 2) AS Percentage_Beds_Occupied\r\n"
				+ "FROM Hotel h\r\n"
				+ "JOIN Transactional t ON h.HID = t.HID\r\n"
				+ "WHERE DATEPART(YEAR, t.Index_Date) = " + year + " AND DATEPART(MONTH, t.Index_Date) = " + month);
		
		/*for (int i = 0; i < total_transactional_table.length; i++) {
	        for (int j = 0; j < total_transactional_table[i].length; j++) {
	            System.out.print(total_transactional_table[i][j] + " ");
	        }
	        System.out.println(); 
	    }*/
		
		//Manipulate data in table
		for (int i = 0; i < content_transactional_table.length; i++) {
	        for (int j = 0; j < content_transactional_table[i].length; j++) {
	    		transactionalSummaryTable.setValueAt(content_transactional_table[i][j], i, j);
	        }
	    }
		
        for (int j = 0; j < total_transactional_table[0].length; j++) {
    		transactionalSummaryTable.setValueAt(total_transactional_table[0][j], content_transactional_table.length, j + 1);
        }
	}
}
