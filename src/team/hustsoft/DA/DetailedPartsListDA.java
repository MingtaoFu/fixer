package team.hustsoft.DA;

import team.hustsoft.basic.DetailedPartsList;
import com.mysql.jdbc.Driver;
import team.hustsoft.DA.DABase;
import java.math.BigDecimal;
import java.util.*;
import java.sql.*;

public class DetailedPartsListDA extends DABase{
    public ArrayList<DetailedPartsList> query(String search, String order){
        ArrayList<DetailedPartsList> partsList = new ArrayList<DetailedPartsList>();
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
        String sql = "select * from DetailedPartsList where partName like " + parttern + ";";
        ResultSet rs = null;
        DetailedPartsList detailedPartsRecord = null;
        try{
            rs = statement.executeQuery(sql);
            while(rs.next()) {
                int rrid = rs.getInt("rrid");
                int pid = rs.getInt("pid");
                String partName = rs.getString("partName");
                BigDecimal price = rs.getBigDecimal("price");
                String modelNumber  = rs.getString("modelNumber");
                int quantity = rs.getInt("quantity");
                Timestamp outTime =rs.getTimestamp("outTime");
                detailedPartsRecord = new DetailedPartsList(rrid,pid,partName,price,modelNumber,quantity,outTime);
                partsList.add(detailedPartsRecord);
            }
        }
        catch (SQLException e) {
            System.out.println(e);
            return null;
        }
        finally{
            terminate();
        }
        return partsList;
    }


//
    public int insert(DetailedPartsList rr){
        if(rr==null){
            return -1;
        }
        conn = initialize();
        int rrid = rr.getRrid();
        int pid = rr.getPid();
        String partName = rr.getPartName();
        BigDecimal price = rr.getPrice();
        String modelNumber = rr.getModelNumber();
        int quantity = rr.getQuantity();

        String sql = "INSERT INTO DetailedPartsList(rrid,pid,partName,price,modelNumber,quantity)"+
                "VALUES(\'"+rrid+"\',\'"+pid+"\',\'"+
                partName+"\',\'"+price+"\',\'"+modelNumber+"\',\'"+
               quantity+"\');";

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

    public int update(DetailedPartsList pl) {
        if (pl == null)
            return -1;
        int plid = pl.getPlid();
        String sql0="SELECT pl FROM DetailedPartsList WHERE plid="+plid+";";
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

        Timestamp outTime = pl.getOutTime();
        String sql = "UPDATE DetailedPartsList SET outTime ="+outTime+";";
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

    public  int delete(int plid) {
        String sql0="SELECT rrid FROM DetailedPartsList WHERE plid="+plid+";";
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
        String sql = "DELETE FROM DetailedPartsList WHERE plid="+plid+";";
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