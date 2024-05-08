package let_us_book;

import java.util.logging.Logger;
import java.sql.*;

public class main {
	
	public static void main(String[] args) {
		
		String connectionUrl = "jdbc:sqlserver://185.119.119.126;databaseName=SWP_2024_let_us_book;user=let_us_book;password=let_us_book;encrypt=false;trustServerCretificate=true";
		
		try (Connection con = DriverManager.getConnection(connectionUrl); Statement stmt = con.createStatement();) {
            String SQL = "SELECT * FROM Mitarbeiter";
            ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                System.out.println(rs.getString("Name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
