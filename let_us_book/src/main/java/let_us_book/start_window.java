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
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class start_window extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	public static String[][] content_master_table;
	public static String[][] total_master_table;
	public static String[][] new_data_content;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
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
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 101, 22);
		contentPane.add(menuBar);
		
		JMenu masterMenu = new JMenu("Master");
		menuBar.add(masterMenu);
		
		JMenuItem masterSummaryMenuItem = new JMenuItem("Master Summary");
		masterMenu.add(masterSummaryMenuItem);
		
		JMenuItem masterListMenuItem = new JMenuItem("Master List");
		masterMenu.add(masterListMenuItem);
		
		JPanel contentPanel = new JPanel();
		contentPanel.setBounds(10, 33, 928, 519);
		contentPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel masterSummaryPanel = new master_summary_panel();
		masterSummaryPanel.setBounds(10, 33, 928, 519);
		contentPanel.add(masterSummaryPanel);
		masterSummaryPanel.setVisible(true);
		
		JPanel masterListPanel = new master_list_panel();
		masterListPanel.setBounds(10, 33, 928, 519);
		contentPanel.add(masterListPanel);
		masterListPanel.setVisible(false);
		
		
		masterSummaryMenuItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				System.out.println("Summary click");
			}
		});
		
		masterListMenuItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				masterSummaryPanel.setVisible(false);
				masterListPanel.setVisible(true);
				System.out.println("List click");
			}
		});
		
		
		contentPane.add(contentPanel);
		
		
		
	}
}
