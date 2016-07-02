package team.hustsoft.servlet;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class PageManage extends HttpServlet {
	public void doGet(HttpServletRequest request,
	HttpServletResponse response) throws ServletException, IOException {
    int property = Integer.parseInt(request.getParameter("property"));
    RequestDispatcher rd = request.getRequestDispatcher("req_repair.html");
    rd.forward(request,response);
  }
}
