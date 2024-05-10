package let_us_book;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Component;
import java.awt.Color;
import javax.swing.border.LineBorder;

public class start_window extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable master_table;
	
	public static String[][] content_master_table;
	public static String[][] total_master_table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		
		//-----------------------------------------------------------------------------
		Parser p = new Parser();
		
		content_master_table = p.getDataFromDB(
				"SELECT \r\n"
				+ "  Category,\r\n"
				+ "  COUNT(*) AS Establishments,\r\n"
				+ "  SUM(Rooms) - SUM(Occupied_rooms) as Rooms,\r\n"
				+ "  SUM(Beds) - SUM(Occupied_beds) as Beds\r\n"
				+ "FROM Hotel\r\n"
				+ "WHERE Category IS NOT NULL\r\n"
				+ "GROUP BY Category\r\n"
				+ "ORDER BY Category DESC;");
		
		total_master_table = p.getTotalDataFromDB("SELECT \r\n"
				+ "  COUNT(*) AS Total_Hotels,\r\n"
				+ "  SUM(Rooms) - SUM(Occupied_rooms) as Rooms,\r\n"
				+ "  SUM(Beds) - SUM(Occupied_beds) as Beds\r\n"
				+ "FROM Hotel");
		
		
		for (int i = 0; i < content_master_table.length; i++) {
	        for (int j = 0; j < content_master_table[i].length; j++) {
	            System.out.print(content_master_table[i][j] + " ");
	        }
	        System.out.println(); 
	    }
		
		for (int i = 0; i < total_master_table.length; i++) {
	        for (int j = 0; j < total_master_table[i].length; j++) {
	            System.out.print(total_master_table[i][j] + " ");
	        }
	        System.out.println(); 
	    }
		
		
		//-----------------------------------------------------------------------------
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					start_window frame = new start_window();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	/**
	 * Create the frame.
	 */
	public start_window() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 964, 613);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel masterLabel = new JLabel("Hello World");
		masterLabel.setForeground(new Color(0, 0, 0));
		masterLabel.setBackground(new Color(213, 0, 0));
		masterLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		masterLabel.setBounds(25, 28, 136, 40);
		
		masterLabel.setText("Master Data");
		contentPane.add(masterLabel);
		
		master_table = new JTable();
		master_table.setBorder(new LineBorder(new Color(0, 0, 0)));
		master_table.setRowHeight(30);
		master_table.setRowSelectionAllowed(false);
		master_table.setFillsViewportHeight(true);
		master_table.setFont(new Font("Tahoma", Font.PLAIN, 18));
		master_table.setModel(new DefaultTableModel(
			new Object[][] {
				{"Category", "Establishments", "Rooms", "Beds"},
				{"*****", null, null, null},
				{"****", null, null, null},
				{"***", null, null, null},
				{"**", null, null, null},
				{"*", null, null, null},
				{"Total", null, null, null},
			},
			new String[] {
				"Category", "Establishments", "Rooms", "Beds"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		master_table.getColumnModel().getColumn(0).setPreferredWidth(100);
		master_table.getColumnModel().getColumn(1).setPreferredWidth(100);
		master_table.getColumnModel().getColumn(2).setPreferredWidth(100);
		master_table.getColumnModel().getColumn(3).setPreferredWidth(100);
		master_table.setBounds(25, 89, 888, 210);
		
		//-----------------------------------------------------------------------------
		
		//Manipulate data in table
		for (int i = 0; i < content_master_table.length; i++) {
	        for (int j = 0; j < content_master_table[i].length; j++) {
	    		master_table.setValueAt(content_master_table[i][j], i+1, j);
	        }
	    }
		
		for (int i = 0; i < total_master_table.length; i++) {
	        for (int j = 0; j < total_master_table[i].length; j++) {
	    		master_table.setValueAt(total_master_table[i][j], content_master_table.length + 1, j + 1);
	        }
	    }
		
		//-----------------------------------------------------------------------------
		
		contentPane.add(master_table);
	}
}
