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

public class Engineer extends HttpServlet{
	public void doPost(HttpServletRequest request,
	HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();

		String operation = request.getParameter("op");
		JSONObject json = new JSONObject();


		if(operation.equals("update")){
			int rrid = Integer.parseInt(request.getParameter("rrid"));
			int did = Integer.parseInt(request.getParameter("did"));
			String maintenance = request.getParameter("maintenance");
			int status = Integer.parseInt(request.getParameter("status"));
			Timestamp distributeTime = Timestamp.valueOf(request.getParameter("distributeTime")+":00");
			Timestamp repairTime = Timestamp.valueOf(request.getParameter("repairTime")+":00");
			if(repairTime.before(Timestamp.valueOf("1971-01-01 00:00:00"))){
				json.put("status",false);
				json.put("error","维修日期不合法!");
				return;
			}

			String detectionRecord = request.getParameter("detectionRecord");
			String repairRecord = request.getParameter("repairRecord");
			String workload = request.getParameter("workload");
			String requiredPart = request.getParameter("requiredPart");
			int delayDegree = Integer.parseInt(request.getParameter("delayDegree"));
			RepairRecord repairRecord0 = new RepairRecord(did,distributeTime,maintenance,detectionRecord,
						repairRecord,repairTime,workload,requiredPart,status,delayDegree);
			repairRecord0.setRrid(rrid);
			int result = RepairManageService.getInstance().update(repairRecord0);
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

	}
	public void doGet(HttpServletRequest request,
	HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
    		String uname = (String)session.getAttribute("uname");

    		System.out.println(uname);
    		ArrayList<RepairRecord> records = RepairManageService.getInstance().query(uname);

    		ArrayList<JSONObject> list = new ArrayList<JSONObject>();
    		if(records != null){
			for(int i=0;i<records.size();i++){
				list.add(records.get(i).toJSON());
			}	
    		}

		JSONObject json = new JSONObject();
		json.put("total", records != null?records.size():0);
		json.put("rows", list);
		out.print(json);
	}

}