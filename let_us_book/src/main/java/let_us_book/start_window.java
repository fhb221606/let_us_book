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
		setBounds(100, 100, 955, 661);
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
		
		JPanel contentPanel = new JPanel(cardLayout);
		contentPanel.setBounds(10, 33, 928, 582);
		contentPane.add(contentPanel);
		
		JPanel masterSummaryPanel = new master_summary_panel();
		masterSummaryPanel.setBounds(10, 33, 928, 519);
		contentPanel.add(masterSummaryPanel, "Master Summary");
		
		JPanel masterListPanel = new master_list_panel();
		masterListPanel.setBounds(10, 33, 928, 519);
		contentPanel.add(masterListPanel, "Master List");
		
		
		
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
		
		
        cardLayout.show(contentPanel, "Master Summary");
		
        logger.info("Setup of window was successful");
	}
}
