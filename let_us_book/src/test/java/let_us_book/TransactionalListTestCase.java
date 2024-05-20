package let_us_book;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import javax.swing.JTable;
import let_us_book.Tools.Parser;
import let_us_book.Transactional.transactional_list_panel;

class TransactionalListTestCase {
    
    private transactional_list_panel panel;

    @BeforeEach
    void setUp() {
        panel = new transactional_list_panel();
    }

    @Test
    @DisplayName("Panel should be correctly initialized")
    void testPanelInitialization() {
        assertNotNull(panel, "Panel should not be null");
        assertNotNull(panel.transactionalListTable, "Transactional list table should not be null");
        assertTrue(panel.transactionalListTable instanceof JTable, "Transactional list should be a JTable");
        assertEquals(2, panel.transactionalListTable.getColumnCount(), "Table should have two columns");
    }

    @Test
    @DisplayName("Test if transactional list data is not empty or null")
    void testTransactionalDataNotEmptyOrNull() {
        assertNotNull(panel.transactionalList, "Transactional list should not be null");
        assertTrue(panel.transactionalList.length > 0, "Transactional list should not be empty");
        for (String[] row : panel.transactionalList) {
            for (String cell : row) {
                assertNotNull(cell, "Cell in transactional list should not be null");
                assertFalse(cell.isEmpty(), "Cell in transactional list should not be empty");
            }
        }
    }

    @Test
    @DisplayName("Test table properties")
    void testTableProperties() {
        assertEquals(25, panel.transactionalListTable.getRowHeight(), "Row height should be 25");
        assertEquals(18, panel.transactionalListTable.getFont().getSize(), "Font size should be 18");
    }
}
