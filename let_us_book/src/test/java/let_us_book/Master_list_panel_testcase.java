package let_us_book;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.CardLayout;
import java.awt.Component;

import javax.swing.JPanel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Master_list_panel_testcase {

    private start_window window;

    @BeforeEach
    public void setUp() {
        // Initialize the start_window instance before each test
        window = new start_window();
    }

    @Test
    public void testWindowInitialization() {
        // Ensure no exceptions are thrown during initialization
        assertDoesNotThrow(() -> new start_window());
    }

   
}
