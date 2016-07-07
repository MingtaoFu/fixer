package team.hustsoft.servlet;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.json.simple.JSONObject;
import team.hustsoft.PD.ExpenseManageService;

public class Table2 extends HttpServlet {
	public void doGet(HttpServletRequest request,
	HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
    int rrid = Integer.parseInt(request.getParameter("rrid"));
		JSONObject json = ExpenseManageService.getInstance().print(rrid);
    request.setAttribute("content", json);

    RequestDispatcher rd = request.getRequestDispatcher("table2.jsp");
    rd.forward(request,response);
  }
}
