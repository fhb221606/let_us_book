package let_us_book;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import let_us_book.Tools.Parser;
import let_us_book.Transactional.transactional_summary_panel;

class TransactionalSummaryTestCase {
	
	private transactional_summary_panel panel;
	public String[][] content_transactional_table;
	public String[][] total_transactional_table;

	@BeforeEach
	void setUp() throws Exception {
		panel = new transactional_summary_panel();
		Parser p = new Parser();
		
		content_transactional_table = p.getDataFromDB(
			"SELECT h.Category,\r\n"
			+ "    SUM(h.Rooms) AS Total_Rooms,\r\n"
			+ "    ROUND(CAST(SUM(t.Rooms_Occupied) AS FLOAT) / SUM(h.Rooms) * 100, 2) AS Percentage_Rooms_Occupied,\r\n"
			+ "    SUM(h.Beds) AS Total_Beds,\r\n"
			+ "    ROUND(CAST(SUM(t.Beds_Occupied) AS FLOAT) / SUM(h.Beds) * 100, 2) AS Percentage_Beds_Occupied\r\n"
			+ "FROM Hotel h\r\n"
			+ "JOIN Transactional t ON h.HID = t.HID\r\n"
			+ "GROUP BY h.Category\r\n"
			+ "ORDER BY h.Category DESC ");

		total_transactional_table = p.getDataFromDB(
			"SELECT SUM(h.Rooms) AS Total_Rooms,\r\n"
			+ "    ROUND(CAST(SUM(t.Rooms_Occupied) AS FLOAT) / SUM(h.Rooms) * 100, 2) AS Percentage_Rooms_Occupied,\r\n"
			+ "    SUM(h.Beds) AS Total_Beds,\r\n"
			+ "    ROUND(CAST(SUM(t.Beds_Occupied) AS FLOAT) / SUM(h.Beds) * 100, 2) AS Percentage_Beds_Occupied\r\n"
			+ "FROM Hotel h\r\n"
			+ "JOIN Transactional t ON h.HID = t.HID");
	}

	@Test
	@DisplayName("Ensure transactional summary table is properly initialized and visible")
	void testTableInitialization() {
		assertNotNull(panel.transactionalSummaryTable, "Table should be instantiated");
		assertTrue(panel.transactionalSummaryTable.isVisible(), "Table should be visible");
		assertEquals(5, panel.transactionalSummaryTable.getModel().getColumnCount(), "Table should have five columns");
		assertEquals("Category", panel.transactionalSummaryTable.getModel().getColumnName(0), "First column should be 'Category'");
	}

	@Test
	@DisplayName("Test if transactional data is empty or null")
	void testTransactionalDataNotEmptyOrNull() {
	    assertNotNull(content_transactional_table, "Content transactional table should not be null");
	    assertTrue(content_transactional_table.length > 0, "Content transactional table should not be empty");
	    for (String[] row : content_transactional_table) {
	        for (String cell : row) {
	            assertNotNull(cell, "Cell should not be null");
	            assertFalse(cell.isEmpty(), "Cell should not be empty");
	        }
	    }
	}

	@Test
	@DisplayName("Test if total transactional data is not empty or null")
	void testTotalTransactionalDataNotEmptyOrNull() {
	    assertNotNull(total_transactional_table, "Total transactional table should not be null");
	    assertTrue(total_transactional_table.length > 0, "Total transactional table should not be empty");
	    for (String[] row : total_transactional_table) {
	        for (String cell : row) {
	            assertNotNull(cell, "Cell should not be null");
	            assertFalse(cell.isEmpty(), "Cell should not be empty");
	        }
	    }
	}
}
