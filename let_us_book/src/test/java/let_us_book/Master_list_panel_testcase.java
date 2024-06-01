package let_us_book;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.swing.JTable;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import let_us_book.Master.master_list_panel;
import let_us_book.Tools.Parser;

class MasterListPanelTest {

    private master_list_panel panel;
    private String[][] hotelData;

    @BeforeEach
    void setUp() throws Exception {
        panel = new master_list_panel();
        Parser p = new Parser();
        hotelData = p.getDataFromDB("SELECT HID, NAME, CATEGORY FROM HOTEL");
    }

    @Test
    @DisplayName("Ensure master list table is properly initialized and visible")
    void testTableInitialization() {
        JTable table = panel.getMasterListTable(); // make sure you have a getter for masterListTable
        assertNotNull(table, "Table should be instantiated");
        assertTrue(table.isVisible(), "Table should be visible");
        assertEquals(3, table.getModel().getColumnCount(), "Table should have three columns");
        assertEquals("ID", table.getModel().getColumnName(0), "First column should be 'ID'");
        assertEquals("Name", table.getModel().getColumnName(1), "Second column should be 'Name'");
        assertEquals("Category", table.getModel().getColumnName(2), "Third column should be 'Category'");
    }

    @Test
    @DisplayName("Test if hotel data is not empty or null")
    void testHotelDataNotEmptyOrNull() {
        assertNotNull(hotelData, "Hotel data should not be null");
        assertTrue(hotelData.length > 0, "Hotel data should not be empty");
        for (String[] row : hotelData) {
            for (String cell : row) {
                assertNotNull(cell, "Cell should not be null");
                assertFalse(cell.isEmpty(), "Cell should not be empty");
            }
        }
    }

    @Test
    @DisplayName("Ensure deleting a hotel entry works correctly")
    void testDeleteHotelEntry() {
        Parser p = new Parser();
        int initialCount = hotelData.length;

        // Adding a temporary hotel entry to delete
        p.insertDataIntoDB("INSERT INTO HOTEL (HID, NAME, CATEGORY) VALUES (999, 'Test Hotel', 'Test Category')");
        String[][] updatedData = p.getDataFromDB("SELECT HID, NAME, CATEGORY FROM HOTEL");
        assertEquals(initialCount + 1, updatedData.length, "Hotel count should increase by 1 after insert");

        // Deleting the temporary hotel entry
        p.deleteDataFromDB("DELETE FROM HOTEL WHERE HID = 999");
        updatedData = p.getDataFromDB("SELECT HID, NAME, CATEGORY FROM HOTEL");
        assertEquals(initialCount, updatedData.length, "Hotel count should return to initial count after delete");
    }
}
