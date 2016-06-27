package team.hustsoft.servlet;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.json.simple.JSONObject;
import team.hustsoft.DA.CustomerDA;
import team.hustsoft.basic.Customer;
import java.sql.*;

public class CustomerModify extends HttpServlet {
	public void doGet(HttpServletRequest request,
	HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		ArrayList<JSONObject> list = new ArrayList<JSONObject>();
		Customer c = CustomerDA.query(1);
		if(c!=null){
			//c.setId(18);
			//c.setCitizenId("111111111111111");
			out.println(CustomerDA.insert(c));
			//c.setId(19);
			//c.setCitizenId("222222222222222222");
			out.println(CustomerDA.insert(c));
			//c.setId(1);
			//c.setName("taotaoGAYohyeaha");
			//CustomerDA.update(c);
			CustomerDA.delete(3);
		}

		// JSONObject j = new JSONObject();
		// j.put("test",c.toJSON());
		// out.print(j);


		ArrayList<Customer> customers = CustomerDA.query();
		for(int i = 0; i < customers.size(); i++) {
			list.add(customers.get(i).toJSON());
		}
		JSONObject json = new JSONObject();
		json.put("total", 3);
		json.put("rows", list);
		out.print(json);
	}

}