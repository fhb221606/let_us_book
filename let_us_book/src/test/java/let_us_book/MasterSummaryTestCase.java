package let_us_book;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import let_us_book.Tools.Parser;

class MasterSummaryTestCase {
	
	public String[][] content_master_table;
	public String[][] total_master_table;

	@BeforeEach
	void setUp() throws Exception {
		
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
