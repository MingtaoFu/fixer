<%@ page language="java"
  pageEncoding="UTF-8"
  import="java.util.*,team.hustsoft.PD.*,team.hustsoft.SHA"
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
    out.println(11);
    //out.println(Account.authorized());
    %>
    <script>
		var xhr = new XMLHttpRequest();
		xhr.open("get", "./servlet_test");
		xhr.onreadystatechange = function() {
			if(xhr.readyState === 4) {
				if(xhr.status >= 200 && xhr.status < 300 || xhr.status === 304) {
					console.log(JSON.parse(xhr.responseText));
				}
			}
		}
		xhr.send(null);
		</script>
  </body>
</html>
