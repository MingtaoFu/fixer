package team.hustsoft.servlet;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.json.simple.JSONObject;

import team.hustsoft.PD.DeviceManageService;
import team.hustsoft.basic.Device;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class DeviceManage extends HttpServlet {
	public void doGet(HttpServletRequest request,
	HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();


//		case"query"
//		case"print"
	}


	public void doPost(HttpServletRequest request,
	HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		String op = request.getParameter("op");
		int id=0,value=0;
		int cid= 0;
		Timestamp ctime= null;
		BigDecimal expectedPrice= null;
		Timestamp expectedCompleteTime= null;
		int status=0, deviceType=0, breakdownType= 0;
		String deviceBrand, deviceModel, deviceSerialNum, lackPart, breakdownAppearance, appearanceCheck;
		String startingUpCommand, significantMaterial, HHD, RAM, PCCard, ACAdapter, battery, CD_ROM, floppy, other;
		JSONObject json = new JSONObject();

		switch(op){
		case "add":
			//cid =request.getParameter("id");
			break;
		case "delete":
			id = Integer.parseInt(request.getParameter("id"));
	 		value = DeviceManageService.getInstance().delete(id);
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
			id = Integer.parseInt(request.getParameter("did"));
			cid = Integer.parseInt(request.getParameter("cid"));
			String expectedPriceStr = request.getParameter("expectedPrice").replaceAll(",", "");
			expectedPrice = expectedPriceStr==""?null:new BigDecimal(expectedPriceStr);
			deviceType = Integer.parseInt(request.getParameter("deviceType"));
			deviceBrand = request.getParameter("deviceBrand");
			deviceModel = request.getParameter("deviceModel");
			deviceSerialNum = request.getParameter("deviceSerialNum");
			lackPart = request.getParameter("lackPart");
			breakdownAppearance = request.getParameter("breakdownAppearance");
			breakdownType = Integer.parseInt(request.getParameter("breakdownType"));
			appearanceCheck = request.getParameter("appearanceCheck");
			status = Integer.parseInt(request.getParameter("status"));
			startingUpCommand = request.getParameter("startingUpCommand");
			significantMaterial = request.getParameter("significantMaterial");
			HHD = request.getParameter("HHD");
			RAM = request.getParameter("RAM");
			PCCard = request.getParameter("PCCard");
			ACAdapter = request.getParameter("ACAdapter");
			battery = request.getParameter("battery");
			CD_ROM = request.getParameter("CD_ROM");
			floppy = request.getParameter("floppy");
			other = request.getParameter("other");

			Device device = new Device(cid, expectedPrice, deviceType, deviceBrand,
			deviceModel, deviceSerialNum, lackPart, breakdownAppearance, breakdownType,
			appearanceCheck, startingUpCommand, significantMaterial, HHD, RAM,
			PCCard, ACAdapter, battery, CD_ROM, floppy, other);

			device.setDid(id);
			device.setStatus(status);

			value = DeviceManageService.getInstance().update(device);
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
		}

	}

}
