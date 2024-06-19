package let_us_book;

import java.awt.EventQueue;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

import com.formdev.flatlaf.FlatLightLaf;

import let_us_book.Help.help_panel;
import let_us_book.Master.master_list_panel;
import let_us_book.Master.master_save_button;
import let_us_book.Master.master_summary_panel;
import let_us_book.Tools.Encrypter;
import let_us_book.Tools.Log;
import let_us_book.Tools.Parser;
import let_us_book.Tools.PDF_Exporter;
import let_us_book.Transactional.transactional_list_panel;
import let_us_book.Transactional.transactional_summary_panel;
import let_us_book.Usermanagement.login_window;
import let_us_book.Usermanagement.user_list_panel;

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
import java.io.File;
import java.io.IOException;

public class start_window extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private CardLayout cardLayout = new CardLayout(0, 0);
	private static String permission;
	
	protected static Log logger = new Log("start_window.txt");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		logger.info("Start Application");
		
		
		/*
		//Use this to create your account
		
		Parser p = new Parser();
		Encrypter e = new Encrypter();
		
		String username = "benni";
		String email = "test@website.com";
		String password = "test";
		
		try {
			password = e.encrypt(password);
		} catch (Exception ex) {
			System.err.println(e);
		}
		
		p.insertDataIntoDB("INSERT INTO Employee\r\n"
				+ "VALUES ('" + username + "', '" + email + "', '" + password + "', 'Senior User');");
		*/
		
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					start_window frame = new start_window();
					frame.setVisible(false);
					
					login_window login = new login_window(frame);
					
					if (!login.isVisible()) {
						if (login.getClosed()) {
							System.exit(0);
						}
						frame.setVisible(true);
						permission = login.permission;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public String[][] combineByRows(String[][] first, String[][] second) {
        String[][] result = new String[first.length + second.length][];

        for (int i = 0; i < first.length; i++) {
            result[i] = first[i];
        }
        for (int i = 0; i < second.length; i++) {
            result[first.length + i] = second[i];
        }

        return result;
    }
	

	/**
	 * Create the frame.
	 */
	public start_window() {
		
		FlatLightLaf.setup();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 955, 661);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//-------------------------------------------------
		//MENU
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 404, 25);
		contentPane.add(menuBar);
		
		JMenu masterMenu = new JMenu("Master");
		menuBar.add(masterMenu);
		
		JMenuItem masterSummaryMenuItem = new JMenuItem("Master Summary");
		masterMenu.add(masterSummaryMenuItem);
		
		JMenuItem masterListMenuItem = new JMenuItem("Master List");
		masterMenu.add(masterListMenuItem);
		
		JMenu transactionalMenu = new JMenu("Transactional");
		menuBar.add(transactionalMenu);
		
		JMenuItem transactionalSummaryMenuItem = new JMenuItem("Transactional Summary");
		transactionalMenu.add(transactionalSummaryMenuItem);
		
		JMenuItem transactionalListMenuItem = new JMenuItem("Transactional List");
		transactionalMenu.add(transactionalListMenuItem);
		
		JMenu usermanagementMenu = new JMenu("Usermanagement");
		menuBar.add(usermanagementMenu);	
		
		JMenuItem usermanagementMenuItem = new JMenuItem("User List");
		usermanagementMenu.add(usermanagementMenuItem);
		
		JMenu backupMenu = new JMenu("Backup");
		menuBar.add(backupMenu);
		
		JMenuItem backupMenuItem = new JMenuItem("Save Backup");
		backupMenu.add(backupMenuItem);
		
		JMenu helpMenu = new JMenu("Help");
		menuBar.add(helpMenu);
		JMenu exportMenu = new JMenu("Export");
		menuBar.add(exportMenu);
		
		JMenuItem exportMenuItem = new JMenuItem("Export as pdf");
		exportMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Parser p = new Parser();
				
				String[][] master_data = p.getDataFromDB(
						"SELECT \r\n"
								+ "  Category,\r\n"
								+ "  COUNT(*) AS Establishments,\r\n"
								+ "  SUM(Rooms) as Rooms,\r\n"
								+ "  SUM(Beds) as Beds\r\n"
								+ "FROM Hotel\r\n"
								+ "WHERE Category IS NOT NULL\r\n"
								+ "GROUP BY Category\r\n"
								+ "ORDER BY Category DESC");
				
				String[][] master_total = p.getDataFromDB("SELECT \r\n"
						+ "  COUNT(*) AS Total_Hotels,\r\n"
						+ "  SUM(Rooms) as Rooms,\r\n"
						+ "  SUM(Beds) as Beds\r\n"
						+ "FROM Hotel");
				
				String[][] transactional_data = p.getDataFromDB(
						"SELECT h.Category,\r\n"
						+ "    SUM(h.Rooms) AS Total_Rooms,\r\n"
						+ "    ROUND(CAST(SUM(t.Rooms_Occupied) AS FLOAT) / SUM(h.Rooms) * 100, 2) AS Percentage_Rooms_Occupied,\r\n"
						+ "    SUM(h.Beds) AS Total_Beds,\r\n"
						+ "    ROUND(CAST(SUM(t.Beds_Occupied) AS FLOAT) / SUM(h.Beds) * 100, 2) AS Percentage_Beds_Occupied\r\n"
						+ "FROM Hotel h\r\n"
						+ "JOIN Transactional t ON h.HID = t.HID\r\n"
						+ "GROUP BY h.Category\r\n"
						+ "ORDER BY h.Category DESC ");
				
				
				String[][] transactional_total = p.getDataFromDB(
						"SELECT SUM(h.Rooms) AS Total_Rooms,\r\n"
						+ "    ROUND(CAST(SUM(t.Rooms_Occupied) AS FLOAT) / SUM(h.Rooms) * 100, 2) AS Percentage_Rooms_Occupied,\r\n"
						+ "    SUM(h.Beds) AS Total_Beds,\r\n"
						+ "    ROUND(CAST(SUM(t.Beds_Occupied) AS FLOAT) / SUM(h.Beds) * 100, 2) AS Percentage_Beds_Occupied\r\n"
						+ "FROM Hotel h\r\n"
						+ "JOIN Transactional t ON h.HID = t.HID");
				
				String[][] master_header = {{"Category", "Hotels", "Rooms", "Beds"}};
				
				String[][] master1 = combineByRows(master_header, master_data);
				String[][] master2 = combineByRows(master1, master_total);
				
				String[][] transactional_header = {{"Category", "Rooms", "Rooms Occupancy", "Beds", "Beds Occupancy"}};
				
				String[][] transactional1 = combineByRows(transactional_header, transactional_data);
				String[][] transactional2 = combineByRows(transactional1, transactional_total);
				
				PDF_Exporter.writeToPdf("export/master.pdf", "Master Summary", master2);
				PDF_Exporter.writeToPdf("export/transactional.pdf", "Transactional Summary", transactional2);
				
			}
		});
		exportMenu.add(exportMenuItem);
		
		JMenuItem FAQMenuItem = new JMenuItem("Help");
		helpMenu.add(FAQMenuItem);
		
		//-------------------------------------------------
		
		//PANEL
		
		JPanel contentPanel = new JPanel(cardLayout);
		contentPanel.setBounds(10, 33, 928, 582);
		contentPane.add(contentPanel);
		
		JPanel masterSummaryPanel = new master_summary_panel();
		masterSummaryPanel.setBounds(10, 33, 928, 519);
		contentPanel.add(masterSummaryPanel, "Master Summary");
		
		JPanel masterListPanel = new master_list_panel();
		masterListPanel.setBounds(10, 33, 928, 519);
		contentPanel.add(masterListPanel, "Master List");
		
		JPanel transactionalSummaryPanel = new transactional_summary_panel();
		transactionalSummaryPanel.setBounds(10, 33, 928, 519);
		contentPanel.add(transactionalSummaryPanel, "Transactional Summary");
		
		JPanel transactionalListPanel = new transactional_list_panel();
		transactionalListPanel.setBounds(10, 33, 928, 519);
		contentPanel.add(transactionalListPanel, "Transactional List");
		
		JPanel userListPanel = new user_list_panel();
		userListPanel.setBounds(10, 33, 928, 519);
		contentPanel.add(userListPanel, "User List");
		
		JPanel helpPanel = new help_panel();
		helpPanel.setBounds(10, 33, 928, 519);
		contentPanel.add(helpPanel, "Help");
		
		
		
		
		//-------------------------------------------------
		
		//ACTIONS
		
		masterSummaryMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(contentPanel, "Master Summary");
            }
        });

        masterListMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(contentPanel, "Master List");
            }
        });
        
        transactionalSummaryMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(contentPanel, "Transactional Summary");
            }
        });

        transactionalListMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(contentPanel, "Transactional List");
            }
        });
        
        usermanagementMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(contentPanel, "User List");
            }
        });
        
        backupMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				master_save_button btn= new master_save_button();
				btn.backup();
				try {
					Desktop.getDesktop().open(new File("backup/master.csv"));
					Desktop.getDesktop().open(new File("backup/transactional.csv"));

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
        
        FAQMenuItem.addActionListener(new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(contentPanel, "Help");
            }
        });
        
      
        //-------------------------------------------------
		
		
        cardLayout.show(contentPanel, "Master Summary");
		
        logger.info("Setup of window was successful");
	}
}
