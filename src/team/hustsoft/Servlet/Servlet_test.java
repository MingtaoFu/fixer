package team.hustsoft.Servlet;

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
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		JSONObject ss = new JSONObject();
		JSONObject ss2 = new JSONObject();
		ss2.put("value", 1);
		ss.put("status", true);
		ss.put("data", ss2);
		out.print(ss);
		//out.print();


		try {
		//	DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/fixer" +
							"user=taotao&password=taotao666");

			Statement  statement = conn.createStatement();         //??
			out.print("ex");

			conn.close();
			statement.close();

		} catch (SQLException ex) {	
			// handle any errors
			out.print("SQLException: " + ex.getMessage());
			out.print("SQLState: " + ex.getSQLState());
			out.print("VendorError: " + ex.getErrorCode());
		}
		catch(ClassNotFoundException ex){
			out.print(ex);
		}
	}
	public void destroy(){}
}
