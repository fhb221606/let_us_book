package let_us_book.Master;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import com.formdev.flatlaf.FlatLightLaf;

import let_us_book.Tools.Parser;

public class master_summary_panel extends JPanel {

	private static final long serialVersionUID = 1L;
	public JTable masterSummaryTable;
	
	public String[][] content_master_table;
	public String[][] total_master_table;

	/**
	 * Create the panel.
	 */
	
	
	public master_summary_panel() {
		
		
		setLayout(null); // Use null layout for absolute positioning
	    setBounds(20, 20, 900, 560); // Set bounds of the JPanel
		
		JLabel masterSummaryLabel = new JLabel("Master Data Summary");
		masterSummaryLabel.setBounds(341, 4, 225, 25);
		masterSummaryLabel.setForeground(Color.BLACK);
		masterSummaryLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		masterSummaryLabel.setBackground(new Color(213, 0, 0));
		add(masterSummaryLabel);
		
		
		masterSummaryTable = new JTable();
		masterSummaryTable.setFont(new Font("Tahoma", Font.PLAIN, 18));
		masterSummaryTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		masterSummaryTable.setRowHeight(30);
		masterSummaryTable.setModel(new DefaultTableModel(
			new Object[][] {
				{"*****", null, null, null},
				{"****", null, null, null},
				{"***", null, null, null},
				{"**", null, null, null},
				{"*", null, null, null},
				{"Total", null, null, null},
			},
			new String[] {
				"Category", "Hotels", "Rooms", "Beds"
			}
		));
		masterSummaryTable.getColumnModel().getColumn(0).setPreferredWidth(100);
		masterSummaryTable.getColumnModel().getColumn(1).setPreferredWidth(100);
		masterSummaryTable.getColumnModel().getColumn(2).setPreferredWidth(100);
		masterSummaryTable.getColumnModel().getColumn(3).setPreferredWidth(100);
		masterSummaryTable.setBounds(21, 58, 658, 210);
		
        JScrollPane scrollPane = new JScrollPane(masterSummaryTable);
        scrollPane.setBorder(null);
        scrollPane.setBounds(10, 40, 880, 205); 
        add(scrollPane); 
        scrollPane.setViewportView(masterSummaryTable);
		
		
		//-----------------------------------------------------------------------------
		
		Parser p = new Parser();
		
		content_master_table = p.getDataFromDB(
				"SELECT \r\n"
				+ "  Category,\r\n"
				+ "  COUNT(*) AS Establishments,\r\n"
				+ "  SUM(Rooms) as Rooms,\r\n"
				+ "  SUM(Beds) as Beds\r\n"
				+ "FROM Hotel\r\n"
				+ "WHERE Category IS NOT NULL\r\n"
				+ "GROUP BY Category\r\n"
				+ "ORDER BY Category DESC");
		
		/*for (int i = 0; i < content_master_table.length; i++) {
	        for (int j = 0; j < content_master_table[i].length; j++) {
	            System.out.print(content_master_table[i][j] + " ");
	        }
	        System.out.println(); 
	    }*/
		
		
		total_master_table = p.getDataFromDB("SELECT \r\n"
				+ "  COUNT(*) AS Total_Hotels,\r\n"
				+ "  SUM(Rooms) as Rooms,\r\n"
				+ "  SUM(Beds) as Beds\r\n"
				+ "FROM Hotel");
		
		/*for (int i = 0; i < total_master_table.length; i++) {
	        for (int j = 0; j < total_master_table[i].length; j++) {
	            System.out.print(total_master_table[i][j] + " ");
	        }
	        System.out.println(); 
	    }*/
		
		//Manipulate data in table
		for (int i = 0; i < content_master_table.length; i++) {
	        for (int j = 0; j < content_master_table[i].length; j++) {
	    		masterSummaryTable.setValueAt(content_master_table[i][j], i, j);
	        }
	    }
		
		for (int i = 0; i < total_master_table.length; i++) {
	        for (int j = 0; j < total_master_table[i].length; j++) {
	    		masterSummaryTable.setValueAt(total_master_table[i][j], content_master_table.length, j + 1);
	        }
	    }
		
		//-----------------------------------------------------------------------------
		
	}
}
