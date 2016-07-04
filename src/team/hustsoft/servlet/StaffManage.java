package team.hustsoft.servlet;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.json.simple.JSONObject;
import team.hustsoft.PD.StaffManageService;
import team.hustsoft.basic.Staff;
import team.hustsoft.SHA;

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
  public void doPost(HttpServletRequest request,
	HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();

		String operation = request.getParameter("op");
		JSONObject json = new JSONObject();
		int value;
    Staff staff;
    String password;
    String userName;
    int characters;
    int id;
    switch (operation) {
      case "add":
        password = request.getParameter("password");
        String password2 = SHA.encode(password);
        userName = request.getParameter("userName");
        characters = Integer.parseInt(request.getParameter("characters"));
        staff = new Staff(0, userName, password2, characters);

        value = StaffManageService.getInstance().insert(staff);
        switch (value) {
					case 1:
						json.put("status", true);
						break;
					case -1:
						json.put("status", false);
						json.put("error", "用户名重复");
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
      case "delete":
				id = Integer.parseInt(request.getParameter("uid"));
		 		value = StaffManageService.getInstance().delete(id);
				switch (value) {
					case 1:
						json.put("status", true);
						break;
					case -1:
						json.put("status", false);
						json.put("error", "找不到员工");
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
