<%@ page language="java"
  pageEncoding="UTF-8"
  import="java.util.*"
%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>客服操作页面</title>
    <link href="./static/css/bootstrap/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="./static/css/bootstrap-datetimepicker.min.css">
    <link rel="stylesheet" href="./static/css/bootstrap-table.css">
    <link rel="stylesheet" href="./static/css/bootstrap-editable/bootstrap-editable.css">
    <script src="./static/js/jquery/jquery.min.js"></script>
    <script src="./static/js/bootstrap/bootstrap.min.js"></script>
    <!--
    <script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.10.1/bootstrap-table.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.10.1/locale/bootstrap-table-zh-CN.min.js"></script>
    -->

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.10.0/css/bootstrap-select.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.10.0/js/bootstrap-select.min.js"></script>
        <script type="text/javascript" src="./static/js/formValidation.min.js"></script>
    <script type="text/javascript" src="./static/js/formValidation-bootstrap.js"></script>
     <script type="text/javascript" src="./static/js/bootstrap-datetimepicker.min.js"></script>

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
        <a class="navbar-brand" href="login">Fixer 客服版</a>
      </div>

      <!-- Collect the nav links, forms, and other content for toggling -->
      <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
        <ul class="nav navbar-nav">
          <li><a href="page?property=1&page=1">客户管理</a></li>
          <li><a href="page?property=1&page=2">报修管理</a></li>
          <li class="active"><a href="#">费用结算<span class="sr-only">(current)</span></a></li>
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
    <h1>客服结算管理</h1>
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
    data-url="expense_manage" data-response-handler="responseHandler">
  </table>
</div>
<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">新增记录</h4>
      </div>
      <div class="modal-body">
        <div id="add_form_modal"></div>
        <form class="form-horizontal container-fluid" id="add_form" method="post">
          <fieldset class="row">
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">

<!--               <div class="control-group">
                <label class="control-label" for="sid">sid</label>
                <div class="controls">
                  <input type="number" placeholder="sid" class="form-control"
                  id="sid" name="sid">
                </div>
              </div> -->
              <div class="control-group">
                <label class="control-label" for="rrid">维修记录</label>
                <div class="controls">
                  <input type="text" placeholder="维修记录" class="form-control"
                   id="rrid" name="rrid">
                </div>
              </div><!--
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
              </div>-->
              <div class="control-group">
                <label class="control-label" for="laborCosts">人工费</label>
                <div class="controls">
                  <input type="number" placeholder="人工费" class="form-control"
                   id="laborCosts" name="laborCosts">
                </div>
              </div>

           </div>
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6"><!--
              <div class="control-group">
                <label class="control-label" for="company_name">材料费</label>
                <div class="controls">
                  <input type="text" placeholder="材料费" class="form-control"
                   id="company_name" name="company_name">
                </div>
              </div>-->
              <div class="control-group">
                <label class="control-label" for="warrantyPromise">报修承诺</label>
                <div class="controls">
                  <input type="text" placeholder="报修承诺" class="form-control"
                   id="warrantyPromise" name="warrantyPromise">
                </div>
              </div>
              <div class="control-group">
                <label class="control-label" for="notice">注意事项</label>
                <div class="controls">
                  <input type="text" placeholder="注意事项" class="form-control"
                   id="notice" name="notice">
                </div>
              </div>
              <div class="control-group">
                <label class="control-label" for="settlementTime">结算日期</label>
                <div class="controls">
                  <input type="datetime" placeholder="结算日期" class="form-control form-datetime"
                   id="settlementTime" name="settlementTime">
                </div>
              </div>

            </div>
            </fieldset>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" id="add_submit">Save changes</button>
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
<script type="text/javascript" src="./static/js/expense_settlement.js"></script>
</body>
</html>
