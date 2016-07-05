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

	}
	public void doGet(HttpServletRequest request,
	HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
    		String uname = (String)session.getAttribute("uname");

    		//System.out.println(uname);
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