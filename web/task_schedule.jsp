<%@ page language="java"
  pageEncoding="UTF-8"
  import="java.util.*"
%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>维修分配页面</title>
    <link href="./static/css/bootstrap/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="./static/css/bootstrap-table.css">
    <link rel="stylesheet" href="./static/css/bootstrap-editable/bootstrap-editable.css">
    <link rel="stylesheet" href="./static/css/bootstrap-select/ajax.css">
    <link rel="stylesheet" href="./static/css/bootstrap-select/bootstrap-select.min.css">
    <script src="./static/js/jquery/jquery.min.js"></script>
    <script src="./static/js/bootstrap/bootstrap.min.js"></script>
    <script src="./static/js/bootstrap-select/bootstrap-select.min.js"></script>
    <script type="text/javascript" src="./static/js/bootstrap-select/ajax.js"></script>

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
        <a class="navbar-brand" href="login">Fixer 任务调度</a>
      </div>

      <!-- Collect the nav links, forms, and other content for toggling -->
      <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
        <ul class="nav navbar-nav">
          <li class="active"><a href="#">维修管理<span class="sr-only">(current)</span></a></li>
          <!-- <li><a href="#">报修管理</a></li> -->
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
      </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
  </nav>
  <div class="container">
    <h1>维修记录</h1>
    <div id="toolbar">
<!--       <button id="remove" class="btn btn-danger" disabled>
        <i class="glyphicon glyphicon-remove"></i> 删除
      </button>
      Button trigger modal
      <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">
        新增
      </button> -->
    </div>
    <table id="table" data-toolbar="#toolbar" data-search="true"
    data-show-refresh="true" data-show-toggle="false" data-show-columns="true"
    data-show-export="false" data-detail-view="false"
    data-detail-formatter="detailFormatter" data-minimum-count-columns="2"
    data-pagination="true"
    data-id-field="id" data-page-list="[10, 25, 50, 100, ALL]"
    data-show-footer="false" data-side-pagination="server"
    data-url="task_schedule" data-response-handler="responseHandler">
  </table>
</div>
<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">分配任务</h4>
      </div>
      <div class="modal-body">
        <form class="form-horizontal container-fluid" id="add_form" method="post">
          <fieldset class="row">
            <div class="alert-field"></div>
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
              <div class="control-group">
                <label class="control-label" for="maintenance_name">维修人员</label>
                <div class="controls">
<!--                   <input type="text" placeholder="维修人员姓名" class="form-control"
                  id="maintenance_name" name="maintenance_name">
 -->                  <select class="selectpicker form-control selectpicker-ajax" data-live-search="true"
                    id="maintenance_name" name="maintenance_name"></select>

                </div>
              </div>
             <!--  <div class="control-group">
                <label class="control-label" for="mobile_phone">手机</label>
                <div class="controls">
                  <input type="tel" placeholder="手机" class="form-control"
                  id="mobile_phone" name="mobile_phone">
                </div>
              </div>
              <div class="control-group">
                <label class="control-label" for="email">email</label>
                <div class="controls">
                  <input type="email" placeholder="电子邮箱" class="form-control"
                   id="email" name="email">
                </div>
              </div>
              <div class="control-group">
                <label class="control-label" for="property">性质</label>
                <div class="controls">
                  <select class="selectpicker form-control" id="property" name="property">
                    <option title="1" value="0">Mustard</option>
                    <option title="2">Ketchup</option>
                    <option title="3">Relish</option>
                    <option title="4">Relish</option>
                  </select>
                </div>
              </div>
              <div class="control-group">
                <label class="control-label" for="citizen_id">身份证</label>
                <div class="controls">
                  <input type="text" placeholder="身份证" class="form-control"
                   id="citizen_id" name="citizen_id">
                </div>
              </div>

            </div>
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
              <div class="control-group">
                <label class="control-label" for="company_name">单位名称</label>
                <div class="controls">
                  <input type="text" placeholder="单位名称" class="form-control"
                   id="company_name" name="company_name">
                </div>
              </div>
              <div class="control-group">
                <label class="control-label" for="company_phone">单位电话</label>
                <div class="controls">
                  <input type="tel" placeholder="单位电话" class="form-control"
                   id="company_phone" name="company_phone">
                </div>
              </div>
              <div class="control-group">
                <label class="control-label" for="address">地址</label>
                <div class="controls">
                  <input type="text" placeholder="地址" class="form-control"
                   id="address" name="address">
                </div>
              </div>
              <div class="control-group">
                <label class="control-label" for="zip_code">邮编</label>
                <div class="controls">
                  <input type="text" placeholder="邮编" class="form-control"
                   id="zip_code" name="zip_code">
                </div>
              </div> -->

            </div>
            </fieldset>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" id="add_submit">Distribute</button>
      </div>
    </div>
  </div>
</div>
<script type="text/javascript" src="./static/js/task_schedule.js"></script>
</body>
</html>
