<%@ page language="java"
  pageEncoding="UTF-8"
  import="java.util.*"
%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>超级管理员页面</title>
    <link href="./static/css/bootstrap/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="./static/css/bootstrap-table.css">
    <link rel="stylesheet" href="./static/css/formValidation.css">
    <link rel="stylesheet" href="./static/css/bootstrap-editable/bootstrap-editable.css">
    <script src="./static/js/jquery/jquery.min.js"></script>
    <script src="./static/js/bootstrap/bootstrap.min.js"></script>
    <script type="text/javascript" src="./static/js/formValidation.min.js"></script>
    <script type="text/javascript" src="./static/js/formValidation-bootstrap.js"></script>
    <link rel="stylesheet" href="./static/css/bootstrap-select/bootstrap-select.min.css">
    <script src="./static/js/bootstrap-select/bootstrap-select.min.js"></script>
</head>
<body>
  <nav class="navbar navbar-default">
    <div class="container-fluid">
      <!-- Brand and toggle get grouped for better mobile display -->
      <div class="navbar-header">
        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
          <span class="sr-only">Toggle navigation</span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="login">Fixer 超级管理员版</a>
      </div>

      <!-- Collect the nav links, forms, and other content for toggling -->
      <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
        <ul class="nav navbar-nav">
          <li class="active"><a href="#">员工管理<span class="sr-only">(current)</span></a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
          <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
            aria-haspopup="true" aria-expanded="false"><%=session.getAttribute("uname")%><span class="caret"></span></a>
            <ul class="dropdown-menu">
              <li><a href="login?op=logout">登出</a></li>
            </ul>
          </li>
        </ul>
      </div>
    </div>
  </nav>
  <div class="container">
    <h1>员工信息维护</h1>
    <div id="toolbar">
      <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">
        新增
      </button>
    </div>
    <table id="table" data-toolbar="#toolbar" data-search="true"
    data-show-refresh="true" data-show-toggle="false" data-show-columns="true"
    data-show-export="false" data-detail-view="false"
    data-detail-formatter="detailFormatter" data-minimum-count-columns="2"
    data-pagination="true"
    data-id-field="id" data-page-list="[10, 25, 50, 100, ALL]"
    data-show-footer="false" data-side-pagination="server"
    data-url="staff_manage" data-response-handler="responseHandler">
    </table>
  </div>
  <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">新增记录</h4>
      </div>
      <div class="modal-body">
        <div id="add_form_modal"></div>
        <form class="form-horizontal container-fluid"
          id="add_form" method="post">
          <fieldset class="row">
              <div class="control-group">
                <label class="control-label" for="userName">用户名</label>
                <div class="controls">
                  <input type="text" placeholder="用户名" class="form-control"
                  id="userName" name="userName">
                </div>
              </div>
              <div class="control-group">
                <label class="control-label" for="password">密码</label>
                <div class="controls">
                  <input type="password" placeholder="密码" class="form-control"
                   id="password" name="password">
                </div>
              </div>
              <div class="control-group">
                <label class="control-label" for="characters">类别</label>
                <div class="controls">
                  <select class="selectpicker form-control" id="characters" name="characters">
                    <option value="1">客服</option>
                    <option value="2">任务调度</option>
                    <option value="3">技术工程师</option>
                    <option value="4">财务</option>
                    <option value="5">库管</option>
                    <option value="6">运营监督</option>
                  </select>
              </div>
            </div>
            </fieldset>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" id="add_submit">确定</button>
      </div>
    </div>
  </div>
</div>
<div class="modal fade" id="confirm_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="confirm_modal_label">确认</h4>
      </div>
      <div class="modal-body">
        <div class="alert-field"></div>
        确定执行操作？
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
        <button type="button" class="btn btn-primary" onclick="func_confirm()">确定</button>
      </div>
    </div>
  </div>
</div>
<script src="./static/js/root.js"></script>
</body>
</html>
