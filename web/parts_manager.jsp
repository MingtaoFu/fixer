<%@ page language="java"
  pageEncoding="UTF-8"
  import="java.util.*"
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>备件管理页面</title>
    <link href="//cdn.bootcss.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="./static/css/bootstrap-table.css">
    <link rel="stylesheet" href="./static/css/formValidation.css">
    <link rel="stylesheet" href="//rawgit.com/vitalets/x-editable/master/dist/bootstrap3-editable/css/bootstrap-editable.css">
    <link rel="stylesheet" href="./static/css/bootstrap-datetimepicker.min.css">

    <script src="//cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
    <script type="text/javascript" src="./static/js/formValidation.min.js"></script>
    <script type="text/javascript" src="./static/js/formValidation-bootstrap.js"></script>
    <script type="text/javascript" src="./static/js/bootstrap-datetimepicker.min.js"></script>
    <script src="//cdn.bootcss.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <!--
    <script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.10.1/bootstrap-table.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.10.1/locale/bootstrap-table-zh-CN.min.js"></script>
    -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.10.0/css/bootstrap-select.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.10.0/js/bootstrap-select.min.js"></script>

</head>
<body>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <!--<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">-->
                <!--<span class="sr-only">Toggle navigation</span>-->
            <!--</button>-->
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
          <span class="sr-only">Toggle navigation</span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </button>
            <a class="navbar-brand" href="login">Fixer 库管版</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li class="active"><a href="#">库存管理<span class="sr-only">(current)</span></a></li>
                <li><a href="page?property=5&page=2">出库清单</a></li>
                <li><a href="page?property=5&page=3">出库请求</a></li>
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
    <h1>备件信息管理</h1>
    <div id="toolbar">
        <!--<button id="remove" class="btn btn-danger" disabled>-->
            <!--<i class="glyphicon glyphicon-remove"></i> 出库-->
        <!--</button>-->
        <!-- Button trigger modal -->
        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">
            入库
        </button>
    </div>
    <table id="table" data-toolbar="#toolbar" data-search="true"
           data-show-refresh="true" data-show-toggle="false" data-show-columns="true"
           data-show-export="false" data-detail-view="false"
           data-detail-formatter="detailFormatter" data-minimum-count-columns="2"
           data-pagination="true"
           data-id-field="id" data-page-list="[10, 25, 50, 100, ALL]"
           data-show-footer="false" data-side-pagination="server"
           data-url="parts_manage" data-response-handler="responseHandler">
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
                <form class="form-horizontal container-fluid"
                      id="add_form" method="post">
                    <fieldset class="row">
                        <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
                            <div class="control-group">
                                <label class="control-label" for="partName">种类名称</label>
                                <div class="controls">
                                    <input type="text" placeholder="种类名称" class="form-control"
                                           id="partName" name="partName">
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="price">单价</label>
                                <div class="controls">
                                    <input type="tel" placeholder="单价" class="form-control"
                                           id="price" name="price">
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="quantity">数量</label>
                                <div class="controls">
                                    <input type="text" placeholder="数量" class="form-control"
                                           id="quantity" name="quantity">
                                </div>
                            </div>

                        </div>
                        <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
                            <div class="control-group">
                                <label class="control-label" for="modelNumber">型号</label>
                                <div class="controls">
                                    <input type="text" placeholder="型号" class="form-control"
                                           id="modelNumber" name="modelNumber">

                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="warningQuantity">警戒量</label>
                                <div class="controls">
                                    <input type="text" placeholder="警戒量" class="form-control"
                                           id="warningQuantity" name="warningQuantity">
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="status">状态</label>
                                <div class="controls">
                                    <select class="selectpicker form-control" id="status" name="status">
                                    <option value="0">正常</option>
                                    <option value="1">临界</option>
                                    <option value="2">警示</option>
                                    <option value="3">缺货</option>
                                    </select>
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
<script type="text/javascript" src="./static/js/parts_manager.js"></script>
</body>
</html>
