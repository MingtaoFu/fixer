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
    <link rel="stylesheet" href="./static/css/bootstrap-table.css">
    <link rel="stylesheet" href="./static/css/formValidation.css">
    <link rel="stylesheet" href="./static/css/bootstrap-datetimepicker.min.css">
    <link rel="stylesheet" href="./static/css/bootstrap-editable/bootstrap-editable.css">
    <link rel="stylesheet" href="./static/css/bootstrap-select/ajax.css">
    <script src="./static/js/jquery/jquery.min.js"></script>
    <script type="text/javascript" src="./static/js/formValidation.min.js"></script>
    <script type="text/javascript" src="./static/js/bootstrap-datetimepicker.min.js"></script>
    <script type="text/javascript" src="./static/js/formValidation-bootstrap.js"></script>
    <script src="./static/js/bootstrap/bootstrap.min.js"></script>
    <link rel="stylesheet" href="./static/css/bootstrap-select/bootstrap-select.min.css">
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
        <a class="navbar-brand" href="login">Fixer 客服版</a>
      </div>

      <!-- Collect the nav links, forms, and other content for toggling -->
      <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
        <ul class="nav navbar-nav">
          <li><a href="page?property=1&page=1">客户管理</a></li>
          <li class="active"><a href="#">报修管理<span class="sr-only">(current)</span></a></li>
          <li><a href="page?property=1&page=3">费用结算</a></li>
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
    <h1>报修管理</h1>
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
    data-url="req_manage" data-response-handler="responseHandler">
  </table>
</div>
<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog modal-lg" role="document">
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
            <div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
              <div class="control-group">
                <label class="control-label" for="cid">客户姓名</label>
                <div class="controls">
                  <select class="selectpicker form-control selectpicker-ajax" data-live-search="true"
                    id="cid" name="cid">
                  </select>
                </div>
              </div>
              <div class="control-group">
                <label class="control-label" for="ctime">报修时间</label>
                <div class="controls">
                  <input type="text" class="form-control form-datetime"
                  id="ctime" name="ctime">
                </div>
              </div>
              <div class="control-group">
                <label class="control-label" for="expectedPrice">预估价格</label>
                <div class="controls">
                  <input type="number" placeholder="预估价格" class="form-control"
                  id="expectedPrice" name="expectedPrice">
                </div>
              </div>
              <div class="control-group">
                <label class="control-label" for="expectedCompletedTime">预估完成时间</label>
                <div class="controls">
                  <input type="text" class="form-control form-datetime"
                  id="expectedCompletedTime" name="expectedCompletedTime">
                </div>
              </div>
              <div class="control-group">
                <label class="control-label" for="status">状态</label>
                <div class="controls">
                  <select class="selectpicker form-control" id="status" name="status">
                    <option value="0">进行中</option>
                    <option value="1">结束</option>
                    <option value="2">撤销</option>
                  </select>
                </div>
              </div>


            </div>
            <div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
              <div class="control-group">
                <label class="control-label" for="deviceType">设备类型</label>
                <div class="controls">
                  <select class="selectpicker form-control" id="deviceType"
                    name="deviceType">
                    <option value="0">台式机</option>
                    <option value="1">笔记本</option>
                    <option value="2">投影仪</option>
                    <option value="3">打印机</option>
                    <option value="4">其他</option>
                  </select>
                </div>
              </div>
              <div class="control-group">
                <label class="control-label" for="deviceBrand">设备品牌</label>
                <div class="controls">
                  <input type="text" placeholder="设备品牌" class="form-control"
                  id="deviceBrand" name="deviceBrand">
                </div>
              </div>
              <div class="control-group">
                <label class="control-label" for="deviceModel">设备型号</label>
                <div class="controls">
                  <input type="text" placeholder="设备型号" class="form-control"
                  id="deviceModel" name="deviceModel">
                </div>
              </div>
              <div class="control-group">
                <label class="control-label" for="deviceSerialNum">设备序列号</label>
                <div class="controls">
                  <input type="text" placeholder="设备序列号" class="form-control"
                  id="deviceSerialNum" name="deviceSerialNum">
                </div>
              </div>
              <div class="control-group">
                <label class="control-label" for="lackPart">缺少部件</label>
                <div class="controls">
                  <input type="text" placeholder="缺少部件" class="form-control"
                  id="lackPart" name="lackPart">
                </div>
              </div>
              <div class="control-group">
                <label class="control-label" for="breakdownAppearance">故障现象</label>
                <div class="controls">
                  <input type="text" placeholder="故障现象" class="form-control"
                  id="breakdownAppearance" name="breakdownAppearance">
                </div>
              </div>

            </div>
            <div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
              <div class="control-group">
                <label class="control-label" for="breakdownType">损坏类型</label>
                <div class="controls">
                  <select class="selectpicker form-control" id="breakdownType"
                    name="breakdownType">
                    <option value="0">固定性</option>
                    <option value="1">间隙性</option>
                  </select>
                </div>
              </div>
              <div class="control-group">
                <label class="control-label" for="appearanceCheck">外观检查</label>
                <div class="controls">
                  <input type="text" placeholder="外观检查" class="form-control"
                  id="appearanceCheck" name="appearanceCheck">
                </div>
              </div>
              <div class="control-group">
                <label class="control-label" for="startingUpCommand">开机口令</label>
                <div class="controls">
                  <input type="text" placeholder="开机口令" class="form-control"
                  id="startingUpCommand" name="startingUpCommand">
                </div>
              </div>
              <div class="control-group">
                <label class="control-label" for="significantMaterial">重要资料</label>
                <div class="controls">
                  <input type="text" placeholder="重要资料" class="form-control"
                  id="significantMaterial" name="significantMaterial">
                </div>
              </div>
              <div class="control-group">
                <label class="control-label" for="HHD">HDD</label>
                <div class="controls">
                  <input type="text" placeholder="HHD" class="form-control"
                  id="HHD" name="HHD">
                </div>
              </div>
              <div class="control-group">
                <label class="control-label" for="RAM">内存</label>
                <div class="controls">
                  <input type="text" placeholder="内存" class="form-control"
                  id="RAM" name="RAM">
                </div>
              </div>


            </div>
            <div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
              <div class="control-group">
                <label class="control-label" for="PCCard">外置PC卡</label>
                <div class="controls">
                  <input type="text" placeholder="外置PC卡" class="form-control"
                  id="PCCard" name="PCCard">
                </div>
              </div>
              <div class="control-group">
                <label class="control-label" for="ACAdapter">AC适配器</label>
                <div class="controls">
                  <input type="text" placeholder="AC适配器" class="form-control"
                  id="ACAdapter" name="ACAdapter">
                </div>
              </div>
              <div class="control-group">
                <label class="control-label" for="battery">电池</label>
                <div class="controls">
                  <input type="text" placeholder="电池" class="form-control"
                  id="battery" name="battery">
                </div>
              </div>
              <div class="control-group">
                <label class="control-label" for="floppy">软驱</label>
                <div class="controls">
                  <input type="text" placeholder="软驱" class="form-control"
                  id="floppy" name="floppy">
                </div>
              </div>
              <div class="control-group">
                <label class="control-label" for="CD_ROM">CD_ROM</label>
                <div class="controls">
                  <input type="text" placeholder="CD_ROM" class="form-control"
                  id="CD_ROM" name="CD_ROM">
                </div>
              </div>
              <div class="control-group">
                <label class="control-label" for="other">其他</label>
                <div class="controls">
                  <input type="text" placeholder="其他" class="form-control"
                  id="other" name="other">
                </div>
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
<script type="text/javascript" src="./static/js/req_repair.js"></script>
</body>
</html>
