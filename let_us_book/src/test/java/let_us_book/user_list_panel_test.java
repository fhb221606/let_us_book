package let_us_book;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import let_us_book.Tools.Parser;
import let_us_book.Usermanagement.user_list_panel;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import static org.junit.jupiter.api.Assertions.*;

class user_list_panel_test {

    private user_list_panel userListPanel;

    @BeforeEach
    void setUp() {
        userListPanel = new user_list_panel();
    }

    @Test
    void testListParsing() {
        // Initialize Parser and simulate the parsing process
        Parser parser = new Parser();
        String[][] data = parser.getDataFromDB("SELECT * FROM Employee");

        // Assuming Parser returns some data for this test
        assertNotNull(data, "Data should not be null");
        assertTrue(data.length > 0, "Data should contain at least one row");
    }
}