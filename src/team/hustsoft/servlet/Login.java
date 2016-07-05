package team.hustsoft.servlet;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.json.simple.JSONObject;

public class Login extends HttpServlet {
  public void doGet(HttpServletRequest request,
    HttpServletResponse response) throws ServletException {

    RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
    try {
      rd.forward(request,response);
    } catch (Exception e) {
      System.out.println(e);
    }
  }
}
