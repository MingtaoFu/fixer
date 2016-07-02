package team.hustsoft.servlet;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.json.simple.JSONObject;
import team.hustsoft.PD.ReqManageService;
import team.hustsoft.basic.Device;

public class ReqManage extends HttpServlet {
	public void doPost(HttpServletRequest request,
	HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
    String op = request.getParameter("op");
    switch(op) {
      //get customers name
      case "getcname":
        String search = request.getParameter("search");
        ArrayList<JSONObject> list = ReqManageService.getInstance().query_customer(search);
        out.println(list);
        break;
			case "add":
				Device device = new Device(request);
				System.out.println(device.getBreakdownAppearance());	
				break;
      default:
    }
  }
  public void doGet(HttpServletRequest request,
  HttpServletResponse response) throws ServletException, IOException {

  }
}
