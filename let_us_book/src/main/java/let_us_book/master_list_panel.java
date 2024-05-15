package let_us_book;

import javax.swing.JScrollPane;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class master_list_panel extends JScrollPane {

	private static final long serialVersionUID = 1L;
	private JTable table;

	/**
	 * Create the panel.
	 */
	public master_list_panel() {
		JLabel masterListLabel = new JLabel("Master List");
        masterListLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        masterListLabel.setBounds(297, 5, 85, 22);

        // Initialize JTable with data and column names
        table = new JTable();
        table.setModel(new DefaultTableModel(
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
        table.setRowHeight(25);
        table.setFont(new Font("Tahoma", Font.PLAIN, 18));

        // Set the viewport view of the JScrollPane to the table
        setViewportView(table);

        // Set scroll bar policies
        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        // Additional setup for the JScrollPane if needed
        setBounds(20, 38, 632, 175); // Adjust size as necessary

	}

}
