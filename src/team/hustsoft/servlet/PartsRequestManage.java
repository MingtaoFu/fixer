package team.hustsoft.servlet;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.json.simple.JSONObject;
import team.hustsoft.PD.RepairManageService;
import team.hustsoft.PD.PartsManageService;
import team.hustsoft.PD.DetailedPLManageService;
import team.hustsoft.basic.RepairRecord;
import team.hustsoft.basic.DetailedPartsList;
import java.math.BigDecimal;
import java.sql.Timestamp;


public class PartsRequestManage extends HttpServlet{
	public void doPost(HttpServletRequest request,
	HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		JSONObject json = new JSONObject();
		//String search = request.getParameter("search");
		String op = request.getParameter("op");
		if(op.equals("confirm")){
			String partName = request.getParameter("partName");
			BigDecimal price =new BigDecimal(request.getParameter("price"));
			int quantity = Integer.parseInt(request.getParameter("quantity"));
			String modelNumber = request.getParameter("modelNumber");
			int rrid = Integer.parseInt(request.getParameter("rrid"));
			int pid = PartsManageService.getInstance().query_pid(partName);
			if(pid<=0){
				json.put("status",false);
				json.put("error","零件不存在!");
				out.print(json);
				return;
			}
			DetailedPartsList part = new DetailedPartsList(rrid,pid,partName,price,modelNumber,quantity);
			int result1 = DetailedPLManageService.getInstance().insert(part);
			int result2 = PartsManageService.getInstance().delivery(pid,quantity);
			//int result = result1&result2;
			if (result1==-1 || result2==-1) {
				json.put("status", false);
				json.put("error", "记录不存在!");
			}else if (result1==-2 || result2 == -2) {
				json.put("status", false);
				json.put("error", "服务器错误");
			}else if(result1==1 && result2==1 )
				json.put("status",true);
			else{
				json.put("status", false);
				json.put("error", "未知错误，请联系管理员");
			}
			out.print(json);
			//???将请求显示在模态框中,,,直接跳转页面
		}
		else if(op.equals("getpname")){
			String search = request.getParameter("search");
			ArrayList<JSONObject> list = PartsManageService.getInstance().query_p(search);
			out.println(list);
		}



	}

	public void doGet(HttpServletRequest request,
	HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();

		ArrayList<RepairRecord> records = RepairManageService.getInstance().query_p();
		ArrayList<JSONObject> list = new ArrayList<JSONObject>();
		JSONObject obj = new JSONObject();
		if(records!=null){
			for(int i=0;i<records.size();i++){
				obj.put("rrid",records.get(i).getRrid());
				obj.put("maintenance",records.get(i).getMaintenance());
				obj.put("requiredPart",records.get(i).getRequiredPart());
				list.add(obj);
			}
			JSONObject json = new JSONObject();
			json.put("total", records.size());
			json.put("rows", list);
			out.print(json);	
		}


	}


}