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
   public ArrayList<Customer> query() {
     ArrayList<Customer> customers = new ArrayList<Customer>();
     conn = initialize();

     String sql = "select * from Customer";
     try{
       ResultSet rs = statement.executeQuery(sql);
       while(rs.next()) {
         int id = rs.getInt("cid");
         int property = rs.getInt("property");
         String companyName = rs.getString("companyName");
         String companyPhone = rs.getString("tel");
         String mobilePhone = rs.getString("mobilePhone");
         String addr = rs.getString("address");
         String zipCode = rs.getString("zipCode");
         String name = rs.getString("contactPersonName");
         String email = rs.getString("email");

         Customer customer = new Customer(id, property, companyName, companyPhone,
         mobilePhone, addr, zipCode, name, email);
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
