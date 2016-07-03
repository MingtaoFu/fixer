package team.hustsoft.servlet;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.json.simple.JSONObject;

import team.hustsoft.PD.PartsManageService;
import team.hustsoft.basic.Parts;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class PartsManage extends HttpServlet {
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException{
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        ArrayList<JSONObject> list = new ArrayList<JSONObject>();

        String search = request.getParameter("search");
        String order = request.getParameter("order");
        //to get the total num, do not add offset and limit in SQL
        int offset = Integer.parseInt(request.getParameter("offset"));
        int limit = Integer.parseInt(request.getParameter("limit"));

        ArrayList<Parts> parts = PartsManageService.getInstance().query(search,order);
        System.out.println("do get");
        int count = limit + offset < parts.size() ? limit + offset: parts.size();
        for(int i = offset; i < count; i++) {
            list.add(parts.get(i).toJSON());
        }
        JSONObject json = new JSONObject();
        json.put("total", parts.size());
        json.put("rows", list);
        out.print(json);
    }


    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException{
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        String op = request.getParameter("op");
        int id=0,value=0;
//        int cid= 0;
//        Timestamp ctime= null;
//        BigDecimal expectedPrice= null;
//        Timestamp expectedCompleteTime= null;
//        int status=0, deviceType=0, breakdownType= 0;
//        String deviceBrand, deviceModel, deviceSerialNum, lackPart, breakdownAppearance, appearanceCheck;
//        String startingUpCommand, significantMaterial, HHD, RAM, PCCard, ACAdapter, battery, CD_ROM, floppy, other;
        JSONObject json = new JSONObject();

        switch(op){
            case "add":
                //cid =request.getParameter("id");
                break;
            case "delete":
                id = Integer.parseInt(request.getParameter("id"));
                value = PartsManageService.getInstance().delete(id);
                switch (value) {
                    case 1:
                        json.put("status", true);
                        break;
                    case -1:
                        json.put("status", false);
                        json.put("error", "找不到用户");
                        break;
                    case -2:
                        json.put("status", false);
                        json.put("error", "服务器错误");
                        break;
                    default:
                        json.put("status", false);
                        json.put("error", "未知错误，请联系管理员");
                }
                out.print(json);
                break;
            case "update":


                break;
        }

    }

}