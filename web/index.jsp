<%@ page language="java"
  pageEncoding="UTF-8"
  import="java.util.*,team.hustsoft.PD.*"
  %>
  <html>
    <head>
        <title>JSP 2.0 Expression Language - Basic Arithmetic</title>
    <meta charset="utf-8">
  </head>
  <body>
    <%
    String str = request.getParameter("param");
    out.println(SHA.encode(str));
    out.println(Account.authorized());
    %>
  </body>
</html>
