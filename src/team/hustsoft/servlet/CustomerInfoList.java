package team.hustsoft.servlet;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.json.simple.JSONObject;
import team.hustsoft.DA.CustomerDA;
import team.hustsoft.basic.Customer;

public class CustomerInfoList extends HttpServlet {
	public void doGet(HttpServletRequest request,
	HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		ArrayList<JSONObject> list = new ArrayList<JSONObject>();
		CustomerDA customerDA = new CustomerDA();
		ArrayList<Customer> customers = customerDA.query();
		for(int i = 0; i < customers.size(); i++) {
			list.add(customers.get(i).toJSON());
		}
		out.print(list);
	}
}
