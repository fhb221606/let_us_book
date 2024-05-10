package let_us_book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class Parser {
	public String[][] getDataFromDB(String table, String command) {
		String connectionUrl = "jdbc:sqlserver://185.119.119.126;databaseName=SWP_2024_let_us_book;user=let_us_book;password=let_us_book;encrypt=false;trustServerCretificate=true";
		
		try (Connection con = DriverManager.getConnection(connectionUrl); Statement stmt = con.createStatement();) {
			
			ResultSet countRs = stmt.executeQuery("SELECT COUNT(*) AS 'rowcount' FROM " + table);
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
}
