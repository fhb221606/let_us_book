package let_us_book;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.swing.table.DefaultTableModel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import let_us_book.Transactional.transactional_list_panel;

class TransactionalSummaryListTest {
    
    private transactional_list_panel panel;

    @BeforeEach
    void setUp() {
        panel = new transactional_list_panel();
    }


    @Test
    @DisplayName("Test showing transactional data of a specific hotel")
    void testShowTransactionalData() {
        panel.textFieldEnterName.setText("TestHotel");


        DefaultTableModel model = (DefaultTableModel) panel.tableAllTransactionalData.getModel();
        assertTrue(model.getRowCount() > 0, "Table should have data for the specified hotel");

        for (int row = 0; row < model.getRowCount(); row++) {
            for (int column = 0; column < model.getColumnCount(); column++) {
                assertNotNull(model.getValueAt(row, column), "Cell in transactional data table should not be null");
                assertFalse(model.getValueAt(row, column).toString().isEmpty(), "Cell in transactional data table should not be empty");
            }
        }
    }
}
