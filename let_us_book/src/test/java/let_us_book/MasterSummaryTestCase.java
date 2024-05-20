package let_us_book;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import let_us_book.Master.master_summary_panel;
import let_us_book.Tools.Parser;
import let_us_book.Transactional.transactional_summary_panel;

class MasterSummaryTestCase {
	
	private master_summary_panel panel;
	public String[][] content_master_table;
	public String[][] total_master_table;

	@BeforeEach
	void setUp() throws Exception {
		panel = new master_summary_panel();
		Parser p = new Parser();
		
		content_master_table = p.getDataFromDB(
				"SELECT \r\n"
				+ "  Category,\r\n"
				+ "  COUNT(*) AS Establishments,\r\n"
				+ "  SUM(Rooms) as Rooms,\r\n"
				+ "  SUM(Beds) as Beds\r\n"
				+ "FROM Hotel\r\n"
				+ "WHERE Category IS NOT NULL\r\n"
				+ "GROUP BY Category\r\n"
				+ "ORDER BY Category DESC;");
		total_master_table = p.getDataFromDB("SELECT \r\n"
				+ "  COUNT(*) AS Total_Hotels,\r\n"
				+ "  SUM(Rooms) as Rooms,\r\n"
				+ "  SUM(Beds) as Beds\r\n"
				+ "FROM Hotel");
	}
	
	@Test
	@DisplayName("Ensure master summary table is properly initialized and visible")
	void testTableInitialization() {
		assertNotNull(panel.masterSummaryTable, "Table should be instantiated");
		assertTrue(panel.masterSummaryTable.isVisible(), "Table should be visible");
		assertEquals(4, panel.masterSummaryTable.getModel().getColumnCount(), "Table should have five columns");
		assertEquals("Category", panel.masterSummaryTable.getModel().getColumnName(0), "First column should be 'Category'");
	}

	@Test
    @DisplayName("Test if category data is empty or null")
    void testCategoryDataNotEmptyOrNull() {
        assertNotNull(content_master_table, "Content master table should not be null");
        assertTrue(content_master_table.length > 0, "Content master table should not be empty");
        for (String[] row : content_master_table) {
            for (String cell : row) {
                assertNotNull(cell, "Cell should not be null");
                assertFalse(cell.isEmpty(), "Cell should not be empty");
            }
        }
    }
    
    @Test
    @DisplayName("Test if total data is not empty or null")
    void testTotalDataNotEmptyOrNull() {
        assertNotNull(total_master_table, "Total master table should not be null");
        assertTrue(total_master_table.length > 0, "Total master table should not be empty");
        for (String[] row : total_master_table) {
            for (String cell : row) {
                assertNotNull(cell, "Cell should not be null");
                assertFalse(cell.isEmpty(), "Cell should not be empty");
            }
        }
    }

}
