<%@ page language="java"
  pageEncoding="UTF-8"
  import="java.util.*,team.hustsoft.basic.Customer,java.text.SimpleDateFormat"
%>
<%
  Customer customer = (Customer)request.getAttribute("customer");
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title></title>
  <style type="text/css">
  .tg  {border-collapse:collapse;border-spacing:0;margin:0px auto;}
  .tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;}
  .tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;}
  .tg .tg-yw4l{vertical-align:top}
  @media screen and (max-width: 767px) {.tg {width: auto !important;}.tg col {width: auto !important;}.tg-wrap {overflow-x: auto;-webkit-overflow-scrolling: touch;margin: auto 0px;}}</style>
</head>
<body>

  <div class="tg-wrap"><table class="tg">
    <tr>
      <th class="tg-yw4l" colspan="4">**计算机服务公司取机凭证**</th>
    </tr  >
    <tr>
      <td class="tg-yw4l">报修时间</td>
      <td class="tg-yw4l"><%= request.getParameter("ctime")%></td>
      <td class="tg-yw4l">报修编号</td>
      <td class="tg-yw4l"><%= request.getParameter("did")%></td>
    </tr>
    <tr>
      <td class="tg-yw4l">产品类型</td>
      <td class="tg-yw4l">
        <%
        String[] arr =
          {"台式机", "笔记本", "投影仪", "打印机", "其他"};
        int index = Integer.parseInt(request.getParameter("deviceType"));
        out.print(arr[index]);
        %>
      </td>
      <td class="tg-yw4l">机器品牌</td>
      <td class="tg-yw4l"><%= request.getParameter("deviceBrand")%></td>
    </tr>
    <tr>
      <td class="tg-yw4l">机器型号</td>
      <td class="tg-yw4l"><%= request.getParameter("deviceModel")%></td>
      <td class="tg-yw4l">系列号</td>
      <td class="tg-yw4l"><%= request.getParameter("deviceSerialNum")%></td>
    </tr>
    <tr>
      <td class="tg-yw4l">单位名称</td>
      <td class="tg-yw4l"><%= customer.getCompanyName()%></td>
      <td class="tg-yw4l">联系人</td>
      <td class="tg-yw4l"><%= customer.getName()%></td>
    </tr>
    <tr>
      <td class="tg-yw4l" colspan="4">机器故障现象</td>
    </tr>
    <tr>
      <td class="tg-yw4l" colspan="4"><%= request.getParameter("breakdownAppearance")%></td>
    </tr>
    <tr>
      <td class="tg-yw4l" colspan="2">缺少零件</td>
      <td class="tg-yw4l" colspan="2">随机附件</td>
    </tr>
    <tr>
      <td class="tg-yw4l" colspan="2"><%= request.getParameter("lackPart")%></td>
      <td class="tg-yw4l" colspan="2">
        <%
        String[] field =
          {"HHD", "RAM", "PCCard", "ACAdapter", "battery", "CD_ROM"};
        String[] label =
          {"HDD", "内存", "外置PC卡", "AC适配器", "电池", "外置光驱"};
        for(int i = 0; i < field.length; i++) {
          if(!request.getParameter(field[i]).equals("")) {
            out.print(label[i]+": "+request.getParameter(field[i])+", ");
          }
        }
        %>
      </td>
    </tr>
    <tr>
      <td class="tg-yw4l">接机签字：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
      <td class="tg-yw4l">机主签字：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
      <td class="tg-yw4l" colspan="2">
        打印时间:
        <%=
        (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date())
        %>
      </td>
    </tr>
  </table></div>

<script>
window.print();
</script>
</body>
</html>
