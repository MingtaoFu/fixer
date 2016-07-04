package team.hustsoft.servlet;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.json.simple.JSONObject;
import team.hustsoft.PD.StaffManageService;
import team.hustsoft.basic.Staff;

public class StaffManage extends HttpServlet {
  public void doGet(HttpServletRequest request,
  HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();

    ArrayList<JSONObject> list = new ArrayList<JSONObject>();

		String search = request.getParameter("search");
		String order = request.getParameter("order");
		//to get the total num, do not add offset and limit in SQL
		int offset = Integer.parseInt(request.getParameter("offset"));
		int limit = Integer.parseInt(request.getParameter("limit"));

		ArrayList<Staff> staff = StaffManageService.getInstance().query(search);

		int count = limit + offset < staff.size() ? limit + offset: staff.size();
		for(int i = offset; i < count; i++) {
			list.add(staff.get(i).toJSON());
		}
    JSONObject json = new JSONObject();
		json.put("total", staff.size());
		json.put("rows", list);
		out.print(json);
  }
}
