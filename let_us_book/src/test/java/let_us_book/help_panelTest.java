package let_us_book;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.swing.JTable;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import let_us_book.Help.help_panel;
import let_us_book.Master.master_list_panel;
import let_us_book.Tools.Parser;



public class help_panelTest {
	
	  private help_panel panel;
	    
	    @BeforeEach
	    public void setUp() {
	        panel = new help_panel();
	    }
	    
	    @Test
	    public void testHelpPanelInitialization() {
	        assertNotNull(panel);
	        assertEquals(2, panel.getComponentCount()); // Check if two components are added to the panel
	    }

	    @Test
	    public void testHelpLabelProperties() {
	        JLabel helpLabel = (JLabel) panel.getComponent(0); // Get the first component (helpLabel)
	        assertNotNull(helpLabel);
	        assertEquals("Help", helpLabel.getText());
	        assertEquals(SwingConstants.CENTER, helpLabel.getHorizontalAlignment());
	        assertEquals(new Rectangle(286, 80, 327, 40), helpLabel.getBounds());
	        assertEquals(Color.BLACK, helpLabel.getForeground());
	        assertEquals(new Font("Times New Roman", Font.BOLD, 24), helpLabel.getFont());
	        assertEquals(new Color(213, 0, 0), helpLabel.getBackground());
	    }
	    
	    @Test
	    public void testTextLabelProperties() {
	        JLabel textLabel = (JLabel) panel.getComponent(1); // Get the second component (textLabel)
	        assertNotNull(textLabel);
	        assertEquals("<html><body style='text-align: center;'>"
	                + "Welcome to the Application Let Us Book!<br><br>"
	                + "This application helps you to see and manage data of hotel bookings in Lower Austria.<br><br>"
	                + "<b>Basic Usage:</b><br>"
	                + "Master: See all the Hotels and add or remove certain hotels. <br>"
	                + "Transactional: See all the transactional data regarding the hotels.<br>"
	                + "Usermanagement: Manage the existing users.<br>"
	                + "Backup: Save the data into a CSV file to your computer.<br>"
	                + "Help: See the explanation of this Application.<br><br><br>"
	                + "For further help, please contact support@let-us-book.at."
	                + "</body></html>", textLabel.getText());
	        assertEquals(new Rectangle(180, 101, 539, 357), textLabel.getBounds());
	        assertEquals(new Font("Arial", Font.PLAIN, 14), textLabel.getFont());
	        assertEquals(Component.CENTER_ALIGNMENT, textLabel.getAlignmentX());
	    }

    }

