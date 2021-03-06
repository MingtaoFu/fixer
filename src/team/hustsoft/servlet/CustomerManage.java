package team.hustsoft.servlet;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.json.simple.JSONObject;
import team.hustsoft.PD.CustomerManageService;
import team.hustsoft.basic.Customer;

public class CustomerManage extends HttpServlet {
	public void doGet(HttpServletRequest request,
	HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		ArrayList<JSONObject> list = new ArrayList<JSONObject>();

		String search = request.getParameter("search");
		String order = request.getParameter("order");
		//to get the total num, do not add offset and limit in SQL
		int offset = Integer.parseInt(request.getParameter("offset"));
		int limit = Integer.parseInt(request.getParameter("limit"));

		ArrayList<Customer> customers = CustomerManageService.getInstance().query(search, order);

		int count = limit + offset < customers.size() ? limit + offset: customers.size();
		for(int i = offset; i < count; i++) {
			list.add(customers.get(i).toJSON());
		}
		JSONObject json = new JSONObject();
		json.put("total", customers.size());
		json.put("rows", list);
		out.print(json);
	}

	public void doPost(HttpServletRequest request,
	HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();

		String operation = request.getParameter("op");
		JSONObject json = new JSONObject();
		int value;
		Customer customer;
		String customer_name, company_name, company_phone, mobile_phone,
			email, address, zip_code, citizen_id;
		int id, property;
		switch (operation) {
			case "delete":
				id = Integer.parseInt(request.getParameter("id"));
		 		value = CustomerManageService.getInstance().delete(id);
				switch (value) {
					case 1:
						json.put("status", true);
						break;
					case -1:
						json.put("status", false);
						json.put("error", "找不到用户");
						break;
					case -2:
						json.put("status", false);
						json.put("error", "服务器错误");
						break;
					default:
						json.put("status", false);
						json.put("error", "未知错误，请联系管理员");
				}
				out.print(json);
				break;
			case "update":
				customer_name = request.getParameter("name");
				mobile_phone = request.getParameter("mobilePhone");
				email = request.getParameter("email");
				property = Integer.parseInt(request.getParameter("property"));
				citizen_id = request.getParameter("citizenId");
				company_name = request.getParameter("companyName");
				company_phone = request.getParameter("companyPhone");
				address = request.getParameter("addr");
				zip_code = request.getParameter("zipCode");
				id = Integer.parseInt(request.getParameter("id"));
				customer = new Customer(id, property, company_name,
					company_phone, mobile_phone, address, zip_code, customer_name,
					email, citizen_id);
				value = CustomerManageService.getInstance().update(customer);
				switch (value) {
					case 1:
						json.put("status", true);
						break;
					case -1:
						json.put("status", false);
						json.put("error", "无此id");
						break;
					case -2:
						json.put("status", false);
						json.put("error", "服务器错误");
						break;
					default:
						json.put("status", false);
						json.put("error", "未知错误，请联系管理员");
				}
				out.print(json);
				break;
			case "add":
				customer_name = request.getParameter("customer_name");
				mobile_phone = request.getParameter("mobile_phone");
				email = request.getParameter("email");
				property = Integer.parseInt(request.getParameter("property"));
				citizen_id = request.getParameter("citizen_id");
				company_name = request.getParameter("company_name");
				company_phone = request.getParameter("company_phone");
				address = request.getParameter("address");
				zip_code = request.getParameter("zip_code");
				customer = new Customer(0, property, company_name,
					company_phone, mobile_phone, address, zip_code, customer_name,
					email, citizen_id);
		 		value = CustomerManageService.getInstance().insert(customer);
				switch (value) {
					case 1:
						json.put("status", true);
						break;
					case -1:
						json.put("status", false);
						json.put("error", "身份证重复");
						break;
					case -2:
						json.put("status", false);
						json.put("error", "服务器错误");
						break;
					default:
						json.put("status", false);
						json.put("error", "未知错误，请联系管理员");
				}
				out.print(json);
				break;
				default:
		}
	}
}
