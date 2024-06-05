package let_us_book.Master;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class master_help_button {
	
	public  void createAndShowGUI() {
		
        // Create the frame
        JFrame frame = new JFrame("FAQ");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        
        // Create the panel
        JPanel panel = new JPanel();
        
        // Create the label with plain text
        JLabel label = new JLabel("FAQ"
        		+ "");
        
        panel.add(label);
        frame.add(panel);
        frame.setVisible(true);

}
}
