package team.hustsoft.DA;

import team.hustsoft.basic.Expense;
import com.mysql.jdbc.Driver;
import team.hustsoft.DA.DABase;
import java.util.*;
import java.sql.*;

public class ExpenseDA extends DABase{
  /**
   * get all
   * @return Customer[]
   */
   public ArrayList<Customer> query(String search, String order) {
     ArrayList<Customer> customers = new ArrayList<Customer>();
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

     String sql = "select * from Customer where contactPersonName like " +
      parttern;
     try{
       ResultSet rs = statement.executeQuery(sql);
       while(rs.next()) {
         int id = rs.getInt("cid");
         String citizenId = rs.getString("id");
         int property = rs.getInt("property");
         String companyName = rs.getString("companyName");
         String companyPhone = rs.getString("tel");
         String mobilePhone = rs.getString("mobilePhone");
         String addr = rs.getString("address");
         String zipCode = rs.getString("zipCode");
         String name = rs.getString("contactPersonName");
         String email = rs.getString("email");

         Customer customer = new Customer(id, property, companyName, companyPhone,
         mobilePhone, addr, zipCode, name, email, citizenId);
         customers.add(customer);
       }
     } catch (SQLException e) {
       System.out.println(e);
       return null;
     }
     finally{
       terminate();
     }
    return customers;
  }

  /**
   * get by id
   * @param  id
   * @return    a expense or null
   */
  public Expense query(int sid) {
      String sql ="SELECT *FROM Expense WHERE sid = \'"+sid+"\';";
       conn = initialize();
       ResultSet rs;
       Expense expense = null;
      try{
        rs = statement.executeQuery(sql);
        if(rs.next())
          expense = new Expense(sid,rs.getInt("rrid"),rs.getDecimal("laborCosts"),
                  rs.getDecimal("materialsCosts"),rs.getString("warrantyPromise"),rs.getString("notice"),
                  rs.getTimestamp("settlementTime"));
      }
      catch(SQLException e){
               System.out.println(e);//?
      }
      finally{
          terminate();
      }
       return expense;
  }

  public int insert(Expense expense){
    if (expense == null) {
      return -1;
    }
    //System.out.println(expense.getCitizenId());
    String sql0 = "SELECT id FROM RepairRecord where rrid=\'"+expense.getRrid()+"\';";
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

    String sql = "INSERT INTO Expense(rrid,laborCosts,materialsCosts,warrantyPromise,notice,settlementTime)"+
    "VALUES(\'"+expense.getRrid()+"\',\'"+expense.getLaborCosts()+"\',\'"+
    expense.getMaterialsCosts()+"\',\'"+expense.getWarrantyPromise()+"\',\'"+expense.getNotice()+"\',\'"+
    expense.getSettlementTime()+"\');";

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


    String sql = "UPDATE Settlement SET sid=\'"+expense.getSid()+"\',rrid=\'"+expense.getRrid()+
        "\',laborCosts=\'"+expense.getLaborCosts()+"\',materialsCosts=\'"+expense.getMaterialsCosts()+"\',warrantyPromise=\'"+
        expense.getWarrantyPromise()+"\',notice=\'"+expense.getNotice()+"\',settlementTime=\'"+expense.getSettlementTime()+"\';";
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
      String sql0 = "SELECT cid FROM Settlement where sid =\'"+sid+"\';";
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
}
