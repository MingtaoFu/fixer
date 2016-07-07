package team.hustsoft.DA;
import java.math.BigDecimal;
import team.hustsoft.basic.Expense;
import com.mysql.jdbc.Driver;
import team.hustsoft.DA.DABase;
import java.util.*;
import java.sql.*;
import org.json.simple.JSONObject;

public class ExpenseDA extends DABase{
  /**
   * get all
   * @return Customer[]
   */
  //  public ArrayList<Expense> query(String search) {
  //    ArrayList<Expense> expenses = new ArrayList<Expense>();
  //    conn = initialize();

  //    // String parttern;
  //    // if(search == null || search.equals("")) {
  //    //   parttern = "\'%\'";
  //    // } else {
  //    //   parttern = "%";
  //    //   for (int i = 0; i < search.length(); i++) {
  //    //     parttern += search.charAt(i);
  //    //     parttern += "%";
  //    //   }
  //    //   parttern = "\'" + parttern + "\'";
  //    // }

  //    // String sql = "select * from Settlement where contactPersonName like " +
  //    //  parttern;
  //    try{
  //      ResultSet rs = statement.executeQuery(sql);
  //      while(rs.next()) {
  //        int sid = rs.getInt("sid");
  //        int rrid = rs.getInt("rrid");
  //        BigDecimal laborCosts = rs.getBigDecimal("laborCosts");
  //        BigDecimal materialsCosts = rs.getBigDecimal("materialsCosts");
  //        String warrantyPromise = rs.getString("warrantyPromise");
  //        String notice = rs.getString("notice");
  //        Timestamp settlementTime = rs.getTimestamp("settlementTime");

  //        Expense expense = new Expense(sid, rrid, laborCosts, materialsCosts,
  //        warrantyPromise, notice, settlementTime);
  //        expenses.add(expense);
  //      }
  //    } catch (SQLException e) {
  //      System.out.println(e);
  //      return null;
  //    }
  //    finally{
  //      terminate();
  //    }
  //   return expenses;
  // }

  /**
   * get by id
   * @param  id
   * @return    a expense or null
   */
  public ArrayList<Expense> query(String sid) {
    ArrayList<Expense> expenses = new ArrayList<Expense>();
    String sql;
      if(sid.equals("")) {
          sql = "select *  from Settlement";
      }
      else {
        sql ="SELECT *FROM Settlement sid = \'"+sid+"\';";}
      //int sid_int = Integer.parseInt(sid);
       conn = initialize();
       ResultSet rs;
       Expense expense = null;
      try{
        rs = statement.executeQuery(sql);
         while(rs.next()) {
         sid = rs.getString("sid");
         int rrid = rs.getInt("rrid");
         BigDecimal laborCosts = rs.getBigDecimal("laborCosts");
         BigDecimal materialsCosts = rs.getBigDecimal("materialsCosts");
         String warrantyPromise = rs.getString("warrantyPromise");
         String notice = rs.getString("notice");
         Timestamp settlementTime = rs.getTimestamp("settlementTime");
         int status = rs.getInt("status");
         int sid_int = Integer.parseInt(sid);

         expense = new Expense(sid_int, rrid, laborCosts, materialsCosts,
         warrantyPromise, notice, settlementTime,status);
         expenses.add(expense);
         }
      }
      catch(SQLException e){
               System.out.println(e);//?
      }
      finally{
          terminate();
      }
       return expenses;
  }

  public int insert(Expense expense){
    if (expense == null) {
      return -1;
    }
    //System.out.println(expense.getCitizenId());
    String sql0 = "SELECT rrid FROM RepairRecord where status=\'3\' and rrid="+expense.getRrid();
    String sql1_5 = "select price, quantity from DetailedPartsList where rrid="+expense.getRrid();
    String sql1 = "SELECT rrid FROM Settlement where rrid="+expense.getRrid();
    conn = initialize();
    ResultSet rs  =null;
    ResultSet rs1  =null;
    try{
      rs=statement.executeQuery(sql0);
      conn=initialize();
      rs1=statement.executeQuery(sql1);
      if(!rs.next()||rs1.next()){
        terminate();
        return -1;
      }
    }
    catch(SQLException e){
      System.out.println(e);//?
      return -2;
    }

    BigDecimal totalPrice = BigDecimal.ZERO;
    try{
      rs=statement.executeQuery(sql1_5);
      while(rs.next()) {
         BigDecimal price = rs.getBigDecimal("price");
         int quantity = rs.getInt("quantity");
         totalPrice = totalPrice.add(price.multiply(new BigDecimal(quantity)));
      }
    } catch(SQLException e){
      System.out.println(e);//?
      return -2;
    }

    String sql = "INSERT INTO Settlement(rrid,laborCosts,materialsCosts,warrantyPromise,notice,settlementTime,status)"+
    "VALUES(\'"+expense.getRrid()+"\',\'"+expense.getLaborCosts()+"\',\'"+
    totalPrice+"\',\'"+expense.getWarrantyPromise()+"\',\'"+expense.getNotice()+"\',\'"+
    expense.getSettlementTime()+"\',\'"+expense.getStatus()+"\');";

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

  public int update(Expense expense) {
      if (expense == null) {
        return -1;
      }
      String sql0 = "SELECT sid FROM Settlement where sid =\'"+expense.getSid()+"\';";
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


    String sql = "UPDATE Settlement SET rrid=\'"+expense.getRrid()+
        "\',laborCosts=\'"+expense.getLaborCosts()+"\',materialsCosts=\'"+expense.getMaterialsCosts()+"\',warrantyPromise=\'"+
        expense.getWarrantyPromise()+"\',notice=\'"+expense.getNotice()+"\',settlementTime=\'"+expense.getSettlementTime()+"\' where sid="+expense.getSid();
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

  public int delete(int sid) {
      String sql0 = "SELECT sid FROM Settlement where sid =\'"+sid+"\';";
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
    String sql = "DELETE FROM Settlement where sid =\'"+sid+"\';";
   //   conn = initialize();
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

  public ArrayList<JSONObject> getParts(int rrid) {
    conn = initialize();
    String sql = "select * from DetailedPartsList where rrid ="+ rrid;
    ResultSet rs = null;
    JSONObject json = null;
    ArrayList<JSONObject> list = new ArrayList<JSONObject>();
    try {
      rs = statement.executeQuery(sql);
      while(rs.next()) {
        json = new JSONObject();
        json.put("partName", rs.getString("partName"));
        json.put("price", rs.getBigDecimal("price"));
        json.put("modelNumber", rs.getString("modelNumber"));
        json.put("quantity", rs.getInt("quantity"));
        list.add(json);
      }
    } catch (Exception e) {
      System.out.println(e);
    } finally {
      terminate();
    }
    return list;
  }

  public JSONObject printInfo(int rrid) {
    conn = initialize();
    String sql = "select RepairRecord.distributeTime, RepairRecord.repairTime,"+
    "  Settlement.warrantyPromise, Settlement.notice, Settlement.laborCosts, "+
    "Settlement.materialsCosts, Customer.companyName, Customer.contactPersonName,"+
    " Device.deviceType, Device.deviceBrand ,Device.deviceModel, Device.deviceSerialNum,"+
    " Device.breakdownAppearance from RepairRecord, Device, Customer, Settlement"+
    " where RepairRecord.rrid = Settlement.rrid ="+rrid+" and RepairRecord.did = Device.did and Customer.cid = Device.cid;";
    String sql1 = "update Settlement set status = \'1\' where rrid = "+rrid;
    ResultSet rs  =null;
    JSONObject json = null;
    try {
      rs = statement.executeQuery(sql);
      conn = initialize();
      statement.executeUpdate(sql1);
      if(rs.next()) {
        json = new JSONObject();
        json.put("dt", rs.getString("distributeTime"));
        json.put("rt", rs.getString("repairTime"));
        json.put("wp", rs.getString("warrantyPromise"));
        json.put("nt", rs.getString("notice"));
        json.put("lc", rs.getBigDecimal("laborCosts"));
        json.put("mc", rs.getBigDecimal("materialsCosts"));
        json.put("cn", rs.getString("companyName"));
        json.put("cpn", rs.getString("contactPersonName"));
        json.put("deviceT", rs.getInt("deviceType"));
        json.put("deviceB", rs.getString("deviceBrand"));
        json.put("deviceM", rs.getString("deviceModel"));
        json.put("deviceSN", rs.getString("deviceSerialNum"));
        json.put("deviceBA", rs.getString("breakdownAppearance"));
      }
    } catch (Exception e) {
      System.out.println(e);
    } finally {
      terminate();
    }
    return json;
  }
}
