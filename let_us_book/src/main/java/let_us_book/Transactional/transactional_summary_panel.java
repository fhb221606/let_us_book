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

public class transactional_summary_panel extends JPanel {

	private static final long serialVersionUID = 1L;
	public JTable transactionalSummaryTable;
	
	public String[][] content_transactional_table;
	public String[][] total_transactional_table;

	/**
	 * Create the panel.
	 */
	
	
	public transactional_summary_panel() {
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
			new Object[][] {
				{"*****", null, null, null, null},
				{"****", null, null, null, null},
				{"***", null, null, null, null},
				{"**", null, null, null, null},
				{"*", null, null, null, null},
				{"Total", null, null, null, null},
			},
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
        scrollPane.setBounds(10, 40, 880, 205); 
        add(scrollPane); 
        scrollPane.setViewportView(transactionalSummaryTable);
		
		
		//-----------------------------------------------------------------------------
		
		Parser p = new Parser();
		
		content_transactional_table = p.getDataFromDB(
				"SELECT h.Category,\r\n"
				+ "    SUM(h.Rooms) AS Total_Rooms,\r\n"
				+ "    ROUND(CAST(SUM(t.Rooms_Occupied) AS FLOAT) / SUM(h.Rooms) * 100, 2) AS Percentage_Rooms_Occupied,\r\n"
				+ "    SUM(h.Beds) AS Total_Beds,\r\n"
				+ "    ROUND(CAST(SUM(t.Beds_Occupied) AS FLOAT) / SUM(h.Beds) * 100, 2) AS Percentage_Beds_Occupied\r\n"
				+ "FROM Hotel h\r\n"
				+ "JOIN Transactional t ON h.HID = t.HID\r\n"
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
				+ "JOIN Transactional t ON h.HID = t.HID");
		
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
	    
		
		//-----------------------------------------------------------------------------
		
	}
}
