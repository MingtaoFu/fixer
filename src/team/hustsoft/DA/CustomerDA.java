package team.hustsoft.DA;

import team.hustsoft.basic.Customer;
import com.mysql.jdbc.Driver;
import team.hustsoft.DA.DABase;
import java.util.*;
import java.sql.*;

public class CustomerDA extends DABase{
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
   * get by name
   * @param  name
   * @return  Customer[]
   */
  //public ArrayList<Customer> query(String name) {

  //}
  /**
   * get by id
   * @param  id
   * @return    a customer or null
   */
  public static Customer query(int id) {
      String sql ="SELECT *FROM Customer WHERE cid = \'"+id+"\';";
       conn = initialize();
       ResultSet rs;
       Customer customer = null;
      try{
        rs = statement.executeQuery(sql);
        if(rs.next())
          customer = new Customer(id,rs.getInt("property"),rs.getString("companyName"),
                  rs.getString("tel"),rs.getString("mobilePhone"),rs.getString("address"),
                  rs.getString("zipCode"),rs.getString("contactPersonName"),rs.getString("email"),rs.getString("id"));
      }
      catch(SQLException e){
               System.out.println(e);//?
      }
      finally{
          terminate();
      }
       return customer;
  }

  public static int insert(Customer customer){
      if (customer == null) {
        return -1;
      }
      String sql0 = "SELECT * FROM Customer where id=\'"+customer.getCitizenId()+"\';";
      conn = initialize();
      ResultSet rs  =null;
      try{
        rs=statement.executeQuery(sql0);
        if(rs.next()){
          terminate();
          return -1;
        }
      }
      catch(SQLException e){
               System.out.println(e);//?
               return -2;
      }


      String sql = "INSERT INTO Customer(id,property,companyName,tel,mobilePhone,address,zipCode,contactPersonName,email)"+
        "VALUES(\'"+customer.getCitizenId()+"\',\'"+customer.getProperty()+"\',\'"+
        customer.getCompanyName()+"\',\'"+customer.getCompanyPhone()+"\',\'"+customer.getMobilePhone()+"\',\'"+
        customer.getAddr()+"\',\'"+customer.getZipCode()+"\',\'"+customer.getName()+"\',\'"+customer.getEmail()+"\');";

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

  public static int update(Customer customer) {
      if (customer == null) {
        return -1;
      }
      String sql0 = "SELECT cid FROM Customer where cid =\'"+customer.getId()+"\';";
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


    String sql = "UPDATE Customer SET id=\'"+customer.getCitizenId()+"\',property=\'"+customer.getProperty()+
        "\',companyName=\'"+customer.getCompanyName()+"\',tel=\'"+customer.getCompanyPhone()+"\',mobilePhone=\'"+customer.getMobilePhone()+
        "\',address=\'"+customer.getAddr()+"\',zipCode=\'"+customer.getZipCode()+"\',contactPersonName=\'"+customer.getName()+
        "\',email=\'"+customer.getEmail()+"\' WHERE cid = \'"+customer.getId()+"\';";
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

  public static int delete(int id) {
      String sql0 = "SELECT cid FROM Customer where cid =\'"+id+"\';";
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
    String sql = "DELETE FROM Customer where cid =\'"+id+"\';";
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
