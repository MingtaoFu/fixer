package team.hustsoft.servlet;
import java.sql.Timestamp;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.json.simple.JSONObject;
import team.hustsoft.PD.ExpenseManageService;
import team.hustsoft.basic.Expense;
import java.math.BigDecimal;
import java.util.regex.*;

public class ExpenseManage extends HttpServlet {
	public void doGet(HttpServletRequest request,
	HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		ArrayList<JSONObject> list = new ArrayList<JSONObject>();




		String sid = request.getParameter("search");
		if(sid==null){
			sid="";
		}
		String order = request.getParameter("order");
		//to get the total num, do not add offset and limit in SQL
		int offset = Integer.parseInt(request.getParameter("offset"));
		int limit = Integer.parseInt(request.getParameter("limit"));

		ArrayList<Expense> expenses = ExpenseManageService.getInstance().query(sid);

		int count = limit + offset < expenses.size() ? limit + offset: expenses.size();
		for(int i = offset; i < count; i++) {
			list.add(expenses.get(i).toJSON());
		}
		JSONObject json = new JSONObject();
		json.put("total", expenses.size());
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
		Expense expense;
		String warrantyPromise,notice;
		BigDecimal laborCosts,materialsCosts;
		Timestamp settlementTime;
		int sid,rrid;
		switch (operation) {
			case "delete":
				sid = Integer.parseInt(request.getParameter("sid"));
		 		value = ExpenseManageService.getInstance().delete(sid);
				switch (value) {
					case 1:
						json.put("status", true);
						break;
					case -1:
						json.put("status", false);
						json.put("error", "找不到记录");
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
				String laborCostsStr = request.getParameter("laborCosts").replaceAll(",", "");
				laborCosts = laborCostsStr==""?null:new BigDecimal(laborCostsStr);
				String materialsCostsStr = request.getParameter("materialsCosts").replaceAll(",", "");
				materialsCosts = materialsCostsStr==""?null:new BigDecimal(materialsCostsStr);
				warrantyPromise = request.getParameter("warrantyPromise");
				notice = request.getParameter("notice");
				settlementTime = Timestamp.valueOf(request.getParameter("settlementTime")+":00");
				sid = Integer.parseInt(request.getParameter("sid"));
				rrid = Integer.parseInt(request.getParameter("rrid"));
				expense = new Expense(sid,rrid,laborCosts, materialsCosts,
					warrantyPromise, notice, settlementTime);
				value = ExpenseManageService.getInstance().update(expense);
				switch (value) {
					case 1:
						json.put("status", true);
						break;
					case -1:
						json.put("status", false);
						json.put("error", "无此rrid");
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
				laborCostsStr = request.getParameter("laborCosts").replaceAll(",", "");
				laborCosts = laborCostsStr==""?null:new BigDecimal(laborCostsStr);
				materialsCostsStr = request.getParameter("materialsCosts").replaceAll(",", "");
				materialsCosts = materialsCostsStr==""?null:new BigDecimal(materialsCostsStr);
				warrantyPromise = request.getParameter("warrantyPromise");
				notice = request.getParameter("notice");
				settlementTime = Timestamp.valueOf(request.getParameter("settlementTime")+":00");
				rrid = Integer.parseInt(request.getParameter("rrid"));
				expense = new Expense(0, rrid,laborCosts, materialsCosts,
					warrantyPromise, notice, settlementTime);
		 		value = ExpenseManageService.getInstance().insert(expense);
				switch (value) {
					case 1:
						json.put("status", true);
						break;
					case -1:
						json.put("status", false);
						json.put("error", "无此维修记录");
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
