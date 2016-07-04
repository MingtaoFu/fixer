package team.hustsoft.DA;

import team.hustsoft.basic.Parts;
//import team.hustsoft.basic.DevicePrinter;
import com.mysql.jdbc.Driver;
import team.hustsoft.DA.DABase;
import java.util.*;
import java.sql.*;
import java.math.BigDecimal;

public  class PartsDA extends DABase{
    public ArrayList<Parts> query(String search, String order){
        ArrayList<Parts> partsList = new ArrayList<Parts>();
        conn = initialize();

        String parttern;
        if(search == null || search.equals("")) {
            parttern = "\'%\'";
        } else {
            parttern = "%";
            for (int i = 0; i < search.length(); i++) {
                parttern += search.charAt(i);
                parttern += "%";
            }
            parttern = "\'" + parttern + "\'";
        }

       // String sql = "select * from Customer where contactPersonName like " +
                //parttern;
        String sql = "select * from Parts where partName like " + parttern + ";";
        ResultSet rs = null;
        Parts part = null;
        try {
            rs = statement.executeQuery(sql);
            while (rs.next()){
                int pid = rs.getInt("pid");
                String partName = rs.getString("partName");
                BigDecimal price = rs.getBigDecimal("price");
                String modelNumber = rs.getString("modelNumber");
                int quantity = rs.getInt("quantity");
                Timestamp inTime = rs.getTimestamp("inTime");
                int warningQuantity = rs.getInt("warningQuantity");
                int status = rs.getInt("status");

                part = new Parts(partName,price,modelNumber,quantity,warningQuantity);
                part.setPid(pid);
                partsList.add(part);
                System.out.println(part.getPartName());
            }
        }catch (SQLException e) {
            System.out.println(e);
            return null;
        }
        finally{
            terminate();
            System.out.println(sql);
        }
        return partsList;
    }

    public Parts query(int pid) {
        String sql ="SELECT *FROM Parts WHERE pid = \'"+pid+"\';";
        conn = initialize();
        ResultSet rs;
        Parts parts = null;
        try{
            rs = statement.executeQuery(sql);
            if(rs.next())
                parts = new Parts(rs.getString("partName"),rs.getBigDecimal("price"),
                        rs.getString("modelNumber"),rs.getInt("quantity"),rs.getInt("warningQuantity"));
        }
        catch(SQLException e){
            System.out.println(e);//?
        }
        finally{
            terminate();
        }
        return parts;
    }


    public int insert(Parts parts){
        if (parts == null)
            return -1;
        conn = initialize();
        String partName = parts.getPartName();
        BigDecimal price = parts.getPrice();
        String modelNumber = parts.getModelNumber();
        int quantity = parts.getQuantity();
        Timestamp inTime = parts.getInTime();//
        int warningQuantity = parts.getWarningQuantity();
        int status = parts.getStatus();

        String sql = "INSERT INTO Parts(partName,price,modelNumber,quantity,inTime,warningQuantity,status)"+
                "VALUES(\'"+partName+"\',\'"+price.toString()+"\',\'"+modelNumber+"\',\'"+
                quantity+"\',\'"+inTime.toString()+"\',\'"+warningQuantity+"\',\'"+status+"\')";
        try{
            statement.executeUpdate(sql);
        }
        catch(SQLException e){
            System.out.println(e);//?
            return -2;
        }
        finally{
            terminate();
        }
        return 1;
    }


    public  int update(Parts parts) {
        if (parts == null)
            return -1;
        String sql0 = "SELECT pid FROM Parts WHERE pid="+parts.getPid()+";";
        ResultSet rs = null;
        conn = initialize();
        try{
            rs = statement.executeQuery(sql0);
            if(!rs.next()){
                terminate();
                return -1;
            }
        }
        catch(SQLException e){
            System.out.println(e);//?
            return -2;
        }
        String partName = parts.getPartName();
        BigDecimal price = parts.getPrice();
        String modelNumber = parts.getModelNumber();
        int quantity = parts.getQuantity();
        Timestamp inTime = parts.getInTime();//
        int warningQuantity = parts.getWarningQuantity();
        int status = parts.getStatus();

        String sql = "UPDATE Parts SET partName=" + partName +",price=\'"+price.toString()+"\',modelNumber=\'"+modelNumber+
                "\',quantity=\'"+quantity+"\',inTime="+inTime.toString()+",warningQuantity="+warningQuantity+
                ",status=\'"+status+"\';";
        try{
            statement.executeUpdate(sql);
        }
        catch(SQLException e){
            System.out.println(e);//?
            return -2;
        }
        finally{
            terminate();
        }
        return 1;
    }

    public  int delete(int pid) {
        String sql0 = "SELECT pid FROM Parts WHERE pid ="+pid+";";
        conn = initialize();
        ResultSet rs  =null;
        try{
            rs=statement.executeQuery(sql0);
            if(!rs.next()){
                terminate();
                return -1;
            }
        }
        catch(SQLException e){
            System.out.println(e);//?
            return -2;
        }
        String sql = "DELETE FROM Parts where pid =\'"+pid+"\';";
        //conn = initialize();
        try{
            statement.executeUpdate(sql);
        }
        catch(SQLException e){
            System.out.println(e);//?
            return -2;
        }
        finally{
            terminate();
        }
        return 1;
    }

}