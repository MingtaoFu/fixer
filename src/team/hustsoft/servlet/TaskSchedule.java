package team.hustsoft.servlet;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.json.simple.JSONObject;
import team.hustsoft.PD.RepairManageService;
// import team.hustsoft.PD.DeviceManageService;
import team.hustsoft.basic.RepairRecord;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class TaskSchedule extends HttpServlet{
	public void doPost(HttpServletRequest request,
	HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();

		String operation = request.getParameter("op");
		JSONObject json = new JSONObject();

		// System.out.println(operation);
		if(operation.equals("distribute")){
			int rrid = Integer.parseInt(request.getParameter("rrid"));
			int did = Integer.parseInt(request.getParameter("did"));
			String maintenance_name = request.getParameter("maintenance_name");
			int status = 1;
			Timestamp distributeTime = new Timestamp(System.currentTimeMillis());
			RepairRecord rr = new RepairRecord(did,distributeTime,maintenance_name,null,null,null,null,null,status,0);
			rr.setRrid(rrid);
			int result = RepairManageService.getInstance().update(rr);
			System.out.println(result);

			switch(result){
			case -1:
				json.put("status",false);
				json.put("error","记录不存在!");
				break;
			case -2:
				json.put("status",false);
				json.put("error","服务器错误");
				break;
			case 1:
				json.put("status",true);
				break;
			default:
				json.put("status", false);
				json.put("error", "未知错误，请联系管理员");
			}
			out.print(json);
		}
		else if(operation.equals("getuname")){
			String search = request.getParameter("search");
			ArrayList<JSONObject> list = RepairManageService.getInstance().query_u(search);
			out.println(list);
		}



	}
	public void doGet(HttpServletRequest request,
	HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();

		// String search = request.getParameter("search");
		// String order = request.getParameter("order");

		ArrayList<RepairRecord> records = RepairManageService.getInstance().query();
		ArrayList<JSONObject> list = new ArrayList<JSONObject>();
		for(int i=0;i<records.size();i++){
			list.add(records.get(i).toJSON());
		}

		JSONObject json = new JSONObject();
		json.put("total", records.size());
		json.put("rows", list);
		out.print(json);
	}

}