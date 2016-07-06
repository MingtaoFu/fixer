package team.hustsoft.servlet;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.json.simple.JSONObject;
import team.hustsoft.PD.RepairManageService;
import team.hustsoft.PD.DetailedPLManageService;
import team.hustsoft.basic.RepairRecord;
import team.hustsoft.basic.DetailedPartsList;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class deliveryManage extends HttpServlet{
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        ArrayList<JSONObject> list = new ArrayList<JSONObject>();

        String search = request.getParameter("search");
        String order = request.getParameter("order");
        //to get the total num, do not add offset and limit in SQL
        int offset = Integer.parseInt(request.getParameter("offset"));
        int limit = Integer.parseInt(request.getParameter("limit"));

        ArrayList<DetailedPartsList> partsList = DetailedPLManageService.getInstance().query(search, order);

        int count = limit + offset < partsList.size() ? limit + offset: partsList.size();
        for(int i = offset; i < count; i++) {
            list.add(partsList.get(i).toJSON());
        }
        JSONObject json = new JSONObject();
        json.put("total", partsList.size());
        json.put("rows", list);
        out.print(json);
    }



}