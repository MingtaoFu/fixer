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
     }
     terminate();
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
  //public Customer query(int id) {

  //}

  public void insert() {

  }

  public void update() {

  }

  public void delete() {

  }
}
