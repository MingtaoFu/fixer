package team.hustsoft.servlet;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import team.hustsoft.basic.Staff;
import team.hustsoft.PD.AccountManageService;

public class PageManage extends HttpServlet {
	public void doPost(HttpServletRequest request,
	HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("charset=utf-8");
    String userId = request.getParameter("name");
    String pwd  = request.getParameter("password");

    Staff staff = AccountManageService.getInstance().login(userId, pwd);
    if(staff == null) {
      response.sendRedirect("login?error=Error! Please login again");
    } else {
      HttpSession session = request.getSession();
      session.setAttribute("uname", userId);
      session.setAttribute("pwd", staff.getPassword());
      response.sendRedirect("page?page=1&property="+staff.getCharacters());
    }
  }

  public void doGet(HttpServletRequest request,
	HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("charset=utf-8");
    int page = Integer.parseInt(request.getParameter("page"));
    int property = Integer.parseInt(request.getParameter("property"));
    HttpSession session = request.getSession();
    String userId = (String)session.getAttribute("uname");
    String pwd = (String)session.getAttribute("pwd");
    Boolean authorized = AccountManageService.getInstance().authorize(userId, pwd, property);
    if(authorized) {
      RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
      switch(property) {
				case 0:
					switch(page) {
						case 1:
              rd = request.getRequestDispatcher("root.jsp");
						default:
					}
          break;
        case 1:
          switch(page) {
            case 1:
              rd = request.getRequestDispatcher("customer_care.jsp");
              break;
            case 2:
              rd = request.getRequestDispatcher("req_repair.jsp");
              break;
            case 3:
              rd = request.getRequestDispatcher("expense_settlement.jsp");
              break;
            default:
          }
          break;
        case 2:
					switch(page) {
						case 1:
              rd = request.getRequestDispatcher("task_schedule.jsp");
						default:
					}
          break;
        case 3:
					switch(page) {
						case 1:
              rd = request.getRequestDispatcher("engineer.jsp");
						default:
					}
          break;
				case 4:
					switch(page) {
						case 1:
              rd = request.getRequestDispatcher("financial.jsp");
						default:
					}
          break;
				case 5:
					switch(page) {
						case 1:
              rd = request.getRequestDispatcher("parts_manager.jsp");
              break;
              case 2:
              rd = request.getRequestDispatcher("delivery_list.jsp");
              break;
              case 3:
              rd = request.getRequestDispatcher("parts_request.jsp");
              break;
						default:
					}
          break;
        default:
      }
      rd.forward(request,response);
    } else {
      response.sendRedirect("login?error=Error! Please login again");
    }
  }
}
