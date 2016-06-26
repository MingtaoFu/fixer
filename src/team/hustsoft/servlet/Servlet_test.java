package team.hustsoft.servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.json.simple.JSONObject;
import java.sql.*;
import com.mysql.jdbc.Driver;

public class Servlet_test extends HttpServlet {
	private String message;
	public void init() throws ServletException{
		message = "hello world";
	}
	public void doGet(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("text/html;charset=utf-8");////设置字符集，重要！
		PrintWriter out = response.getWriter();
		JSONObject ss = new JSONObject();
		JSONObject ss2 = new JSONObject();
		ss2.put("value", 1);
		ss.put("status", true);
		ss.put("data", ss2);
		out.print(ss);
		//out.print();
		Connection conn = null;
		Statement  statement = null;
		try {
		//	DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306?" +
							"user=taotao&password=taotao666");

			statement = conn.createStatement();         //??
			//out.print("ex");
			PreparedStatement preparedStatement;
			String[] sqlArr={
				"USE fixer",
				"CREATE TABLE IF NOT EXISTS User("+
					"uid INT(5) AUTO_INCREMENT NOT NULL,"+
					"userName VARCHAR(50) NOT NULL UNIQUE,"+
					"passWord VARCHAR(50) NOT NULL,"+
					"characters enum(\"0\",\"1\",\"2\",\"3\",\"4\",\"5\",\"6\")  NOT NULL,"+
					"CONSTRAINT PK_UID PRIMARY KEY(uid))"+
					"DEFAULT CHARSET=utf8 COLLATE utf8_general_ci;"
			};

			//out.print(sqlArr[0]);
			for(int i = 0; i < sqlArr.length; i++) {
				preparedStatement = conn.prepareStatement(sqlArr[i]);
				preparedStatement.execute();
			}

			ResultSet rs = statement.executeQuery("select *from User;");
			if(!rs.next())
				statement.executeUpdate("insert into User(userName,passWord,characters) VALUES('套套','taotao666','0');");

			rs = statement.executeQuery("select *from User;");
			while(rs.next())
			{
				out.print(rs.getString("uid")+"\n");
				out.print(rs.getString("userName")+"\n");
				out.print(rs.getString("passWord")+"\n");
				out.print(rs.getString("characters"));
			}

		} catch (SQLException ex) {
			// handle any errors
			out.print("SQLException: " + ex.getMessage());
			out.print("SQLState: " + ex.getSQLState());
			out.print("VendorError: " + ex.getErrorCode());
		}
		catch(ClassNotFoundException ex){
			out.print(ex);
		}
		finally{
			try {
				statement.close();
			}
			catch (SQLException e) {
				System.out.println(e);
			}
			try{
				conn.close();
			}
			catch (SQLException e) {
				System.out.println(e);
			}
		}
	}
//	public void destroy(){}
}
