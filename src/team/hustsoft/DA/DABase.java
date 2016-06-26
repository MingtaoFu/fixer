package team.hustsoft.DA;

import java.sql.*;
import com.mysql.jdbc.Driver;

public class DABase {
  static Connection conn;
	static Statement statement;
  public static Connection initialize() {
    try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/fixer?" +
							"user=taotao&password=taotao666");
      statement = conn.createStatement();
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} catch(Exception ex){
			System.out.println(ex);
		}
		return conn;
  }
  public static void terminate() {
    try {
			statement.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
    try{
			conn.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
  }
}
