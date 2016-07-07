<%@ page language="java"
  pageEncoding="UTF-8"
  import="java.util.*,org.json.simple.JSONObject,java.text.SimpleDateFormat"
%>
<%
  JSONObject json = (JSONObject)request.getAttribute("content");
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
.tg .tg-baqh{text-align:center;vertical-align:top}
.tg .tg-yw4l{vertical-align:top}
</style>
</head>
<body>

<table class="tg">
  <tr>
    <th class="tg-baqh" colspan="8">***计算机服务有限公司结算清单***</th>
  </tr>
  <tr>
    <td class="tg-baqh" colspan="2">维修单号</td>
    <td class="tg-yw4l" colspan="2"><%=request.getParameter("rrid")%></td>
    <td class="tg-yw4l" colspan="2"></td>
    <td class="tg-yw4l" colspan="2"></td>
  </tr>
  <tr>
    <td class="tg-baqh" colspan="2">接修时间</td>
    <td class="tg-yw4l" colspan="2"><%=json.get("dt")%></td>
    <td class="tg-baqh" colspan="2">修复时间</td>
    <td class="tg-yw4l" colspan="2"><%=json.get("rt")%></td>
  </tr>
  <tr>
    <td class="tg-baqh" colspan="2">产品类型</td>
    <td class="tg-yw4l" colspan="2">
        <%
        String[] arr =
          {"台式机", "笔记本", "投影仪", "打印机", "其他"};
        int index = Integer.parseInt(json.get("deviceT").toString());
        out.print(arr[index]);
        %>
    </td>
    <td class="tg-baqh" colspan="2">机器品牌</td>
    <td class="tg-yw4l" colspan="2"><%=json.get("deviceB")%></td>
  </tr>
  <tr>
    <td class="tg-baqh" colspan="2">机器型号</td>
    <td class="tg-yw4l" colspan="2"><%=json.get("deviceM")%></td>
    <td class="tg-baqh" colspan="2">系列号</td>
    <td class="tg-yw4l" colspan="2"><%=json.get("deviceSN")%></td>
  </tr>
  <tr>
    <td class="tg-baqh" colspan="2">单位名称</td>
    <td class="tg-yw4l" colspan="2"><%=json.get("cn")%></td>
    <td class="tg-baqh" colspan="2">联系人</td>
    <td class="tg-yw4l" colspan="2"><%=json.get("cpn")%></td>
  </tr>
  <tr>
    <td class="tg-baqh" colspan="2">合计金额</td>
    <td class="tg-yw4l" colspan="2">
      <%
        double mc = Double.parseDouble(json.get("mc").toString());
        double lc = Double.parseDouble(json.get("lc").toString());
        out.print(mc + lc);
      %>
    </td>
    <td class="tg-yw4l">修理费</td>
    <td class="tg-yw4l"><%=json.get("lc")%></td>
    <td class="tg-yw4l">材料费</td>
    <td class="tg-yw4l"><%=json.get("mc")%></td>
  </tr>
  <tr>
    <td class="tg-baqh" colspan="8">机器故障现象</td>
  </tr>
  <tr>
    <td class="tg-yw4l" colspan="8"><%=json.get("deviceBA")%></td>
  </tr>
  <tr>
    <td class="tg-baqh" colspan="4">报修承诺</td>
    <td class="tg-baqh" colspan="4">注意事项</td>
  </tr>
  <tr>
    <td class="tg-yw4l" colspan="4"><%=json.get("wp")%></td>
    <td class="tg-yw4l" colspan="4"><%=json.get("nt")%></td>
  </tr>
  <tr>
    <td class="tg-yw4l" colspan="2">部件名称</td>
    <td class="tg-yw4l" colspan="2">型号</td>
    <td class="tg-yw4l" colspan="2">数量</td>
    <td class="tg-yw4l" colspan="2">单价</td>
  </tr>
  <%
  ArrayList<JSONObject> list = (ArrayList<JSONObject>)json.get("parts");
  for (int i = 0; i < list.size(); i++) {
    %>
  <tr>
    <td class="tg-yw4l" colspan="2"><%=list.get(i).get("partName")%></td>
    <td class="tg-yw4l" colspan="2"><%=list.get(i).get("modelNumber")%></td>
    <td class="tg-yw4l" colspan="2"><%=list.get(i).get("quantity")%></td>
    <td class="tg-yw4l" colspan="2"><%=list.get(i).get("price")%></td>
  </tr>
  <%
  }
  %>
  <tr>
    <td class="tg-yw4l" colspan="2">发货签字：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
    <td class="tg-yw4l" colspan="2">机主签字：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
    <td class="tg-yw4l" colspan="4">打印时间：
      <%=
        (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date())
        %>
      </td>
  </tr>
</table>
<script>
window.print();
</script>
</body>
</html>
