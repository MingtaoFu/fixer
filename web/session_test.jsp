<%--
此页面说明jsp已经为我们提供了session
我们只需要将UID和加密后的密码存在session中即可获取验证
--%>
<%@ page import="java.io.*,java.util.*" %>
<%
  if(session.isNew()) {
    session.setAttribute("UID", "the_ID");
    out.println("new");
  } else {
    String UID = (String)session.getAttribute("UID");
    out.println(UID);
    session.invalidate();
  }
%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>session_test</title>
  </head>
  <body>

  </body>
</html>
