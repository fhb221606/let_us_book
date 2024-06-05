package let_us_book.Tools;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class Parser {
	
	public static String connectionUrl = "jdbc:sqlserver://185.119.119.126;databaseName=SWP_2024_let_us_book;user=let_us_book;password=let_us_book;encrypt=false;trustServerCretificate=true";
	
	public static String[][] getDataFromDB(String command) {
		try (Connection con = DriverManager.getConnection(connectionUrl); Statement stmt = con.createStatement();) {
			
			//Create substring without ORDER BY
			String sub_command = "";
			
			// Find the index of the substring "ORDER BY"
	        int index = command.indexOf("ORDER BY");
	        
	        // Check if "ORDER BY" is found
	        if (index != -1) {
	            // Get the substring from the start of the original string to the index of "ORDER BY"
	            sub_command = command.substring(0, index);
	        } else {
	        	sub_command = command;
	        }
	        
			ResultSet countRs = stmt.executeQuery("SELECT COUNT(*) AS 'rowcount' FROM (" + sub_command + ") as Sub_Select;");
            countRs.next();
            int rowCount = countRs.getInt("rowcount");
            countRs.close();

            // Execute the provided SQL command
            ResultSet rs = stmt.executeQuery(command);

            // Obtain the ResultSet metadata to determine the number of columns
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();

            // Create a 2D String array to hold the data
            String[][] data = new String[rowCount][columnCount];

            // Iterate through the ResultSet and populate the array
            int rowIndex = 0;
            while (rs.next()) {
                for (int colIndex = 1; colIndex <= columnCount; colIndex++) {
                    data[rowIndex][colIndex - 1] = rs.getString(colIndex);
                }
                rowIndex++;
            }
            return data;
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		return null;
	}
	
	public static void insertDataIntoDB(String command) {
		try (Connection con = DriverManager.getConnection(connectionUrl); Statement stmt = con.createStatement();) {
            // Execute the provided INSERT command
            stmt.execute(command);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	public static int updateDataIntoDB(String command) {
		try (Connection con = DriverManager.getConnection(connectionUrl); Statement stmt = con.createStatement();) {
            // Execute the provided INSERT command
            stmt.execute(command);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
	}
	
	public static void deleteDataFromDB(String command) {
		try (Connection con = DriverManager.getConnection(connectionUrl); Statement stmt = con.createStatement();) {
	        // Execute the provided INSERT command
	        stmt.execute(command);
	     } catch (Exception e) {
	    	 e.printStackTrace();
	     }
	}
}
