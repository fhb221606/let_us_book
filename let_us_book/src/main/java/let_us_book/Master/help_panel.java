package let_us_book.Master;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;
import javax.swing.*;

public class help_panel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public help_panel() {
		setLayout(null); // Use null layout for absolute positioning
	    setBounds(20, 20, 900, 560); // Set bounds of the JPanel
		
		JLabel helpLabel = new JLabel("Help");
		helpLabel.setHorizontalAlignment(SwingConstants.CENTER);
		helpLabel.setBounds(282, 34, 327, 40);
		helpLabel.setForeground(Color.BLACK);
		helpLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		helpLabel.setBackground(new Color(213, 0, 0));
		add(helpLabel);
		
		JLabel textLabel = new JLabel("<html><body style='text-align: center;'>"
                + "Welcome to the Application Let Us Book!<br><br>"
                + "This application helps you to see and manage data of hotel bookings in Lower Austria.<br><br>"
                + "<b>Basic Usage:</b><br>"
                + "Master: See all the Hotels and add or remove certain hotels. <br>"
                + "Transactional: See all the transactional data regarding the hotels.<br>"
                + "Usermanagement: Manage the existing users.<br>"
                + "Backup: Save the data into a CSV file to your computer.<br>"
                + "Help: See the explanation of this Application.<br><br><br>"
                + "For further help, please contact support@let-us-book.at."
                + "</body></html>");
		textLabel.setLocation(179, 85);
		textLabel.setSize(539, 357);
        textLabel.setFont(new Font("Arial", Font.PLAIN, 14)); // Set font for the plain text
        textLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        add(textLabel);
		
		
    
}

}
