package let_us_book;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class master_list_panel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;

	/**
	 * Create the panel.
	 */
	public master_list_panel() {
		setLayout(null);
		
		JLabel masterListLabel = new JLabel("Master List");
		masterListLabel.setBounds(297, 5, 85, 22);
		masterListLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		add(masterListLabel);
		
		table = new JTable();
		table.setRowHeight(25);
		table.setFont(new Font("Tahoma", Font.PLAIN, 18));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column"
			}
		));
		table.setBounds(20, 38, 632, 175);
		add(table);

	}

}
