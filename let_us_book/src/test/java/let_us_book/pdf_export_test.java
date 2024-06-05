package let_us_book;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.itextpdf.text.pdf.PdfReader;

import let_us_book.Tools.PDF_Exporter;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

class pdf_export_test {

	private static final String OUTPUT_FILE = "test_output.pdf";

    @BeforeEach
    public void setUp() {
        // Clean up before each test
        File file = new File(OUTPUT_FILE);
        if (file.exists()) {
            file.delete();
        }
    }

    @AfterEach
    public void tearDown() {
        // Clean up after each test
        File file = new File(OUTPUT_FILE);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public void testWriteToPdf() {
        String heading = "Test Heading";
        String[][] data = {
                {"Header1", "Header2", "Header3"},
                {"Row1Col1", "Row1Col2", "Row1Col3"},
                {"Row2Col1", "Row2Col2", "Row2Col3"}
        };

        PDF_Exporter.writeToPdf(OUTPUT_FILE, heading, data);

        // Verify the file was created
        File file = new File(OUTPUT_FILE);
        assertTrue(file.exists(), "PDF file should be created");

        // Verify the PDF content
        try {
        	PdfReader reader = new PdfReader(OUTPUT_FILE);
            assertNotNull(reader, "PDF reader should not be null");
            assertTrue(reader.getNumberOfPages() > 0, "PDF should have at least one page");
            // Further content verification can be done here if needed
        } catch (IOException e) {
            e.printStackTrace();
            assertTrue(false, "IOException should not be thrown");
        }
    }

}
