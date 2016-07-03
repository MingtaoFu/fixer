package team.hustsoft.DA;

import team.hustsoft.basic.Staff;
import com.mysql.jdbc.Driver;
import team.hustsoft.DA.DABase;
import java.util.*;
import java.sql.*;

public class AccountDA extends DABase{
  public Boolean authorize(String name, String pwd_sha, int property) {
    conn = initialize();
    String sql = "select * from User where userName=\""+
      name+"\" and passWord=\""+pwd_sha+"\" and characters=\""+property+"\"";
    try {
      ResultSet rs = statement.executeQuery(sql);
      if(rs.next()) {
        return true;
      } else {
        return false;
      }
    } catch (Exception e) {
      System.out.println(e);
      return false;
    }
  }
  public Staff query(String name, String pwd_sha) {
    conn = initialize();
    String sql = "select * from User where userName=\""+
      name+"\" and passWord=\""+pwd_sha+"\"";
    Staff staff = null;
    try {
      ResultSet rs = statement.executeQuery(sql);
      //no more than one, so not use loop
      if(rs.next()) {
        int id = rs.getInt("uid");
        int characters = rs.getInt("characters");
        staff = new Staff(id, name, pwd_sha, characters);
      }
    } catch (Exception e) {
      System.out.println(e);
    } finally {
      terminate();
    }
    return staff;
  }
}
