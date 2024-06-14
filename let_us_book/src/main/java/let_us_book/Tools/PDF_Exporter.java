package let_us_book.Tools;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class PDF_Exporter {
	
	public static void writeToPdf(String file, String heading, String[][] data) {
        try {
        	String exportDirectory = "export";

            //check if the directory exists, if not, create it
            File directory = new File(exportDirectory);
            if (!directory.exists()) {
                directory.mkdir();  // Create the directory if it doesn't exist
            }
        	
            // Initialize PDF document
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();

            // Add a title paragraph
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Paragraph title = new Paragraph(heading, titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            // Add a blank line
            document.add(new Paragraph("\n"));

            // Create a table with the number of columns matching the length of the first row of data
            int numColumns = data[0].length;
            PdfPTable table = new PdfPTable(numColumns);
            table.setWidthPercentage(100);

            // Define fonts and colors
            Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, BaseColor.WHITE);
            Font cellFont = FontFactory.getFont(FontFactory.HELVETICA, 12);
            BaseColor headerColor = new BaseColor(140, 221, 8);
            BaseColor rowColor = new BaseColor(221, 234, 239);

            // Populate the table with data
            for (int i = 0; i < data.length; i++) {
                for (String cellData : data[i]) {
                    PdfPCell cell = new PdfPCell(new Paragraph(cellData, i == 0 ? headerFont : cellFont));
                    if (i == 0) {
                        // Header row styling
                        cell.setBackgroundColor(headerColor);
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    } else {
                        // Alternate row coloring
                        if (i % 2 == 0) {
                            cell.setBackgroundColor(rowColor);
                        }
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    }
                    cell.setPadding(5);
                    table.addCell(cell);
                }
            }

            // Add the table to the document
            document.add(table);

            // Close document
            document.close();

            System.out.println("PDF Created Successfully!");
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
