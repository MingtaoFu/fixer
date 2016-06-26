package team.hustsoft.servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.json.simple.JSONObject;

public void doGet(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		JSONObject ss = new JSONObject();
		JSONObject ss2 = new JSONObject();
		ss2.put("value", 1);
		ss.put("status", true);
		ss.put("data", ss2);
		out.print(ss);
  }
