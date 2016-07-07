package team.hustsoft.DA;
import java.math.BigDecimal;
import team.hustsoft.basic.Financial;
import com.mysql.jdbc.Driver;
import team.hustsoft.DA.DABase;
import java.util.*;
import java.sql.*;

public class FinancialDA extends DABase{
  /**
   * get all
   * @return Customer[]
   */
  //  public ArrayList<financial> query(String search) {
  //    ArrayList<financial> financials = new ArrayList<financial>();
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

  //        financial financial = new financial(sid, rrid, laborCosts, materialsCosts,
  //        warrantyPromise, notice, settlementTime);
  //        financials.add(financial);
  //      }
  //    } catch (SQLException e) {
  //      System.out.println(e);
  //      return null;
  //    }
  //    finally{
  //      terminate();
  //    }
  //   return financials;
  // }

  /**
   * get by id
   * @param  id
   * @return    a financial or null
   */
  public ArrayList<Financial> query(String sid) {
    ArrayList<Financial> financials = new ArrayList<Financial>();
    String sql;
      if(sid.equals("")) {
          sql = "select *  from Settlement where status = \'1\'";
      }
      else {
        sql ="SELECT *FROM Settlement WHERE status = \'1\' and sid = \'"+sid+"\';";}
      //int sid_int = Integer.parseInt(sid);
       conn = initialize();
       ResultSet rs;
       Financial financial = null;
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

         financial = new Financial(sid_int, rrid, laborCosts, materialsCosts,
         warrantyPromise, notice, settlementTime,status);
         financials.add(financial);
         }
      }
      catch(SQLException e){
               System.out.println(e);//?
      }
      finally{
          terminate();
      }
       return financials;
  }



  // public int insert(financial financial){
    // if (financial == null) {
    //   return -1;
    // }
    // //System.out.println(financial.getCitizenId());
    // String sql0 = "SELECT rrid FROM RepairRecord where rrid=\'"+financial.getRrid()+"\';";
    // String sql1 = "SELECT rrid FROM Settlement where rrid=\'"+financial.getRrid()+"\';";
    // conn = initialize();
    // ResultSet rs  =null;
    // ResultSet rs1  =null;
    // try{
    //   rs=statement.executeQuery(sql0);
    //   rs1=statement.executeQuery(sql1);
    //   if(!rs.next()||!rs1.next()){
    //     terminate();
    //     return -1;
    //   }
    // }
    // catch(SQLException e){
    //   System.out.println(e);//?
    //   return -2;
    // }

  //   String sql = "INSERT INTO Settlement(rrid,laborCosts,materialsCosts,warrantyPromise,notice,settlementTime)"+
  //   "VALUES(\'"+financial.getRrid()+"\',\'"+financial.getLaborCosts()+"\',\'"+
  //   financial.getMaterialsCosts()+"\',\'"+financial.getWarrantyPromise()+"\',\'"+financial.getNotice()+"\',\'"+
  //   financial.getSettlementTime()+"\');";

  //   try{
  //     statement.executeUpdate(sql);
  //   }
  //   catch(SQLException e){
  //     System.out.println(e);//?
  //     return -2;
  //   }
  //   finally{
  //     terminate();
  //   }
  //   return 1;
  // }

  public int update(int sid) {

      String sql0 = "SELECT sid FROM Settlement where status = \'1\' and  sid =\'"+sid+"\';";
      String sql1 = "SELECT sid FROM Settlement where status =\'2\' and sid="+sid;
      conn = initialize();
      ResultSet rs  =null;
      ResultSet rs1  =null;
      try{
        rs=statement.executeQuery(sql0);
        conn = initialize();
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


    String sql = "UPDATE Settlement SET status=\'2\' where sid="+sid;
    System.out.println(sql);
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

  // public int delete(int sid) {
  //     String sql0 = "SELECT sid FROM Settlement where sid =\'"+sid+"\';";
  //     conn = initialize();
  //     ResultSet rs  =null;
  //     try{
  //       rs=statement.executeQuery(sql0);
  //       if(!rs.next()){
  //         terminate();
  //         return -1;
  //       }
  //     }
  //     catch(SQLException e){
  //              System.out.println(e);//?
  //                             return -2;
  //     }
  //   String sql = "DELETE FROM Settlement where sid =\'"+sid+"\';";
  //  //   conn = initialize();
  //     try{
  //       statement.executeUpdate(sql);
  //     }
  //     catch(SQLException e){
  //              System.out.println(e);//?
  //                             return -2;
  //     }
  //     finally{
  //         terminate();
  //     }
  //           return 1;
  // }
}
