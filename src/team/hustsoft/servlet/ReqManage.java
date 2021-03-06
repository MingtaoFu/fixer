package team.hustsoft.servlet;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.json.simple.JSONObject;
import team.hustsoft.PD.ReqManageService;
import team.hustsoft.PD.DeviceManageService;
import team.hustsoft.basic.Device;
import java.math.BigDecimal;
import java.sql.Timestamp;
import team.hustsoft.basic.RepairRecord;
import team.hustsoft.PD.RepairManageService;

public class ReqManage extends HttpServlet {
	public void doPost(HttpServletRequest request,
	HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
    String op = request.getParameter("op");
		int value, id;
		JSONObject json = new JSONObject();
    switch(op) {
			case "print":
				id = Integer.parseInt(request.getParameter("did"));
		 		value = DeviceManageService.getInstance().print(id);
				switch (value) {
					case 1:
						json.put("status", true);
						break;
					case -1:
						json.put("status", false);
						json.put("error", "找不到条目");
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
			case "confirm":
				id = Integer.parseInt(request.getParameter("did"));
		 		value = DeviceManageService.getInstance().confirm(id);
				switch (value) {
					case 1:
						RepairRecord repairRecord = new RepairRecord(id,null,null,null,null,null,null,null,0,0);
						value = RepairManageService.getInstance().insert(repairRecord);
						switch (value) {
							case 1:
								json.put("status", true);
								break;
							default:
								json.put("status", false);
								json.put("error", "分配维修记录错误");
						}
						break;
					case -1:
						json.put("status", false);
						json.put("error", "找不到条目");
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

      //get customers name
      case "getcname":
        String search = request.getParameter("search");
        ArrayList<JSONObject> list = ReqManageService.getInstance().query_customer(search);
        out.println(list);
        break;
			case "add":
				int cid = Integer.parseInt(request.getParameter("cid"));
				String expectedPriceStr = request.getParameter("expectedPrice").replaceAll(",", "");
				BigDecimal expectedPrice = expectedPriceStr==""?null:new BigDecimal(expectedPriceStr);
				int deviceType = Integer.parseInt(request.getParameter("deviceType"));
				String deviceBrand = request.getParameter("deviceBrand");
				String deviceModel = request.getParameter("deviceModel"); String deviceSerialNum = request.getParameter("deviceSerialNum");
				String lackPart = request.getParameter("lackPart");
				String breakdownAppearance = request.getParameter("breakdownAppearance");
				int breakdownType = Integer.parseInt(request.getParameter("breakdownType"));
				String appearanceCheck = request.getParameter("appearanceCheck");
				String startingUpCommand = request.getParameter("startingUpCommand");
				String significantMaterial = request.getParameter("significantMaterial");
				String HHD = request.getParameter("HHD");
				String RAM = request.getParameter("RAM");
				String PCCard = request.getParameter("PCCard");
				String ACAdapter = request.getParameter("ACAdapter");
				String battery = request.getParameter("battery");
				String CD_ROM = request.getParameter("CD_ROM");
				String floppy = request.getParameter("floppy");
				String other = request.getParameter("other");

				Device device = new Device(cid, expectedPrice, deviceType, deviceBrand,
					deviceModel, deviceSerialNum, lackPart, breakdownAppearance, breakdownType,
					appearanceCheck, startingUpCommand, significantMaterial, HHD, RAM,
					PCCard, ACAdapter, battery, CD_ROM, floppy, other);
				value = DeviceManageService.getInstance().insert(device);
				switch (value) {
					case 1:
						json.put("status", true);
						break;
					case -1:
						json.put("status", false);
						json.put("error", "服务器错误");
						break;
					case -2:
						json.put("status", false);
						json.put("error", "用户不存在或服务器错误");
						break;
					default:
						json.put("status", false);
						json.put("error", "未知错误，请告知管理员");
				}
				out.print(json);
				break;
      default:
    }
  }
  public void doGet(HttpServletRequest request,
  HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();

		String search = request.getParameter("search");
		String order = request.getParameter("order");
		//to get the total num, do not add offset and limit in SQL
		int offset = Integer.parseInt(request.getParameter("offset"));
		int limit = Integer.parseInt(request.getParameter("limit"));

		ArrayList<Device> devices = ReqManageService.getInstance().query(search);
    ArrayList<JSONObject> list = new ArrayList<JSONObject>();

		int count = limit + offset < devices.size() ? limit + offset: devices.size();
		for(int i = 0; i < count; i++) {
      list.add(devices.get(i).toJSON());
    }
		JSONObject json = new JSONObject();
		json.put("total", devices.size());
		json.put("rows", list);
		out.print(json);
  }
}
