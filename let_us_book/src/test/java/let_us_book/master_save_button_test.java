package let_us_book;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import let_us_book.Tools.Parser;
import let_us_book.Tools.Export_CSV;

class master_save_button_test {

	   @Mock
	    private Parser parserMock;

	    private Export_CSV masterSaveButton;

	    @TempDir
	    File tempDir;

	    @BeforeEach
	    public void setUp() {
	        MockitoAnnotations.openMocks(this);
	        masterSaveButton = new Export_CSV();
	        masterSaveButton.p = parserMock;
	    }

	    
	    @Test
	    public void testWriteCSV() throws IOException {
	        String[][] data = { {"Column1", "Column2"}, {"Data1", "Data2"} };

	        File tempFile = new File(tempDir, "test.csv");
	        masterSaveButton.writeCSV(data, tempFile.getPath());

	        assertTrue(tempFile.exists());

	        
	    }
	}
