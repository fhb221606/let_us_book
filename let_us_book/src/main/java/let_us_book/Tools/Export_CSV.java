package let_us_book.Tools;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Export_CSV {
	
	  public Parser p = new Parser();
	  
	  public void backup() {
		  
		  String master1 = "SELECT * FROM Hotel";
		  
		  String[][] master = p.getDataFromDB(master1);
		  
		  String transactional1 = "SELECT * FROM Transactional";
		  
		  String[][] transactional = p.getDataFromDB(transactional1);
		  
		  writeCSV(master, "backup/master.csv");
		  writeCSV(transactional, "backup/transactional.csv");
		  
	  	}
	  
	  public  void writeCSV(String[][] data, String filePath) {
	        File file = new File(filePath);
	        file.getParentFile().mkdirs(); // Create the backup directory if it doesn't exist

	        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
	            for (String[] row : data) {
	                writer.println(String.join(",", row));
	            }
	            System.out.println("CSV file was created successfully in the backup folder.");
	        } catch (IOException e) {
	            System.err.println("An error occurred while writing the CSV file: " + e.getMessage());
	        }
	    }
	}
	  
	  
	  
	  
   




