<%@ page language="java"
  pageEncoding="UTF-8"
  import="team.hustsoft.basic.Staff,
    java.util.*"
%>
<%
String op = request.getParameter("op");
if(op != null && op.equals("logout")) {
  session.invalidate();
}
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
  <title>登录</title>
  <link href="./static/css/bootstrap/bootstrap.min.css" rel="stylesheet">
  <script src="./static/js/jquery/jquery.min.js"></script>
  <script src="./static/js/bootstrap/bootstrap.min.js"></script>
  <script type="text/javascript" src="./static/js/formValidation.min.js"></script>
  <script type="text/javascript" src="./static/js/formValidation-bootstrap.js"></script>
</head>
<body>
  <div class="container">
    <div class="row">
      <div class="col-lg-6 col-lg-offset-3 col-md-8 col-md-offset-2 col-sm-10 col-sm-offset-1">
        <form class="form-horizontal panel panel-default"
           id="form" method="post" action="page">
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
                  <input type="password" placeholder="请输入密码" class="form-control"
                   id="password" name="password">
                  <p class="help-block">6-16位数字、字母、下划线</p>
                </div>
              </div>

              <%
              String error = request.getParameter("error");
              if(error != null) {
                String str = "<div class=\"alert alert-danger alert-dismissible fade in\" role=\"alert\">"+
                "<button type=\"button\" class=\"close\" data-dismiss=\"alert\" "+
                "aria-label=\"Close\"><span aria-hidden=\"true\">×</span></button><p>" +
                error +
                "</p></div>";
                out.print(str);
              }
              %>
              <div class="control-group">
                <div class="controls">
                  <button type="submit" class="btn btn-default">登录</button>
                </div>
              </div>

            </fieldset>

          </div>
        </form>
      </div>
    </div>
  </div>
  </body>
  <script>
  $(function() {
    $('#form').formValidation({
      framework: 'bootstrap',
      message: '输入不合法',
      icon: {
        valid: 'glyphicon glyphicon-ok',
        invalid: 'glyphicon glyphicon-remove',
        validating: 'glyphicon glyphicon-refresh'
      },
      fields: {
        name: {
          row: '.controls',
          validators: {
            notEmpty: {
              message: '此项是必填的'
            }
          }
        },
        password: {
          row: '.controls',
          validators: {
            notEmpty: {
              message: '此项是必填的'
            },
            regexp: {
              regexp: /^[\w]{6,16}$/,
              message: '密码不合法'
            }
          }
        }
      }
    })
  });
  </script>
</html>
