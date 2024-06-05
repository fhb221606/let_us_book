package let_us_book;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.swing.JButton;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import let_us_book.Master.master_add_hotel_window;
import let_us_book.Tools.Parser;

class master_add_hotel_test {

    private master_add_hotel_window window;
    private Parser mockParser;
    

    @BeforeEach
    void setUp() throws Exception {
        mockParser = Mockito.mock(Parser.class);
        window = new master_add_hotel_window();
        window.p = mockParser;  // Inject mock parser
    }


    @Test
    @DisplayName("Test adding entry with all fields filled")
    void testAddEntryWithAllFieldsFilled() {
        window.textFieldName.setText("Test Hotel");
        window.textFieldCategory.setText("*****");
        window.textFieldRooms.setText("100");
        window.textFieldBeds.setText("200");
        window.textFieldCity.setText("Test City");
        window.textFieldStreet.setText("Test Street");

        JButton addButton = (JButton) window.getContentPane().getComponent(12); // Assuming button is the 13th component
        addButton.doClick();

        Mockito.verify(mockParser).insertDataIntoDB(
                "INSERT INTO Hotel VALUES ('Test Hotel', '*****', 100, 200, 'Test City', 'Test Street');"
        		
        
        );
    }

}