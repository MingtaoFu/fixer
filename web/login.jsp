<%@ page language="java"
  pageEncoding="UTF-8"
  import="team.hustsoft.basic.Staff,
    java.util.*"
%>
<%
if (request.getMethod().equals("POST")) {
  String name = request.getParameter("name");
  String password = request.getParameter("password");
  Staff user = new Staff(name, password);
  //is not int
  Map<Integer, String> urlMap = new HashMap<Integer, String>();
  urlMap.put(1, "page");
  urlMap.put(2, "page");

  if (user != null) {
    response.sendRedirect(urlMap.get(user.getType()));
  }
}
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
  <title>登录</title>
  <link href="//cdn.bootcss.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
  <script src="//cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
  <script src="//cdn.bootcss.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>
  <div class="container">
    <div class="row">
      <div class="col-lg-6 col-lg-offset-3 col-md-8 col-md-offset-2 col-sm-10 col-sm-offset-1">
        <form class="form-horizontal panel panel-default" id="form" method="post">
          <div class="panel-body">
            <fieldset>
              <div id="legend" class="">
                <legend class="">登录</legend>
              </div>
              <div class="control-group">
                <!-- Text input-->
                <label class="control-label" for="user_id">用户ID</label>
                <div class="controls">
                  <input type="text" placeholder="用户ID" class="form-control"
                   id="user_id" name="name">
                  <p class="help-block">输入公司用户ID</p>
                </div>
              </div>

              <div class="control-group">

                <!-- Text input-->
                <label class="control-label" for="password">密码</label>
                <div class="controls">
                  <input type="text" placeholder="请输入密码" class="form-control"
                   id="password" name="password">
                  <p class="help-block">6-16位数字、字母、下划线</p>
                </div>
              </div>

              <div class="control-group">
                <!-- Button -->
                <div class="controls">
                  <button class="btn btn-default">登录</button>
                </div>
              </div>

            </fieldset>

          </div>
        </form>
      </div>
    </div>
  </div>
  </body>
</html>
