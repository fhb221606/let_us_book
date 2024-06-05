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

import let_us_book.Master.help_panel;
import let_us_book.Master.master_help_button;
import let_us_book.Master.master_list_panel;
import let_us_book.Master.master_save_button;
import let_us_book.Master.master_summary_panel;
import let_us_book.Tools.Encrypter;
import let_us_book.Tools.Log;
import let_us_book.Tools.Parser;
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

public class start_window extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private CardLayout cardLayout = new CardLayout(0, 0);
	
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
					}
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
		setBounds(100, 100, 955, 661);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//-------------------------------------------------
		//MENU
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 348, 25);
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
		
		JMenuItem FAQMenuItem = new JMenuItem("Help and FAQ");
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
		contentPanel.add(helpPanel, "Help and FAQ");
		
		
		
		
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
			}
		});
        
        FAQMenuItem.addActionListener(new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(contentPanel, "Help and FAQ");
            }
        });
        
      
        //-------------------------------------------------
		
		
        cardLayout.show(contentPanel, "Master Summary");
		
        logger.info("Setup of window was successful");
	}
}
