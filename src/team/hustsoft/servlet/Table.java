package team.hustsoft.servlet;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import team.hustsoft.PD.DeviceManageService;
import team.hustsoft.basic.Customer;

public class Table extends HttpServlet {
	public void doGet(HttpServletRequest request,
	HttpServletResponse response) throws ServletException, IOException {
    int did = Integer.parseInt(request.getParameter("did"));
    Customer customer = DeviceManageService.getInstance().query_c(did);
    request.setAttribute("customer", customer);

    RequestDispatcher rd = request.getRequestDispatcher("table.jsp");
    rd.forward(request,response);
  }
}
