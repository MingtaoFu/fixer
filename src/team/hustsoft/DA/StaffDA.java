package team.hustsoft.DA;

import java.util.*;
import java.sql.*;
import com.mysql.jdbc.Driver;
import team.hustsoft.basic.Staff;

public class StaffDA extends DABase {
  public ArrayList<Staff> query(String search) {
    ArrayList<Staff> staff = new ArrayList<Staff>();
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
     String sql = "select * from User where userName like "+parttern+" and characters != \'0\'";

     ResultSet rs = null;
     Staff stf = null;
     try{
       rs = statement.executeQuery(sql);
       while(rs.next()) {
         int uid = rs.getInt("uid");
         String userName = rs.getString("userName");
         int characters = Integer.parseInt(rs.getString("characters"));
         stf = new Staff(uid, userName, null, characters);
         staff.add(stf);
       }
     } catch (SQLException e) {
       System.out.println(e);
       return null;
     } finally {
       terminate();
     }
     return staff;
  }

  public int insert(Staff staff){
    if (staff == null) {
      return -1;
    }
    //System.out.println(customer.getCitizenId());
    String sql0 = "SELECT * FROM User where userName=\'"+staff.getUserName()+"\';";
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

    String sql = "INSERT INTO User "+
    "VALUES(null, \'"+staff.getUserName()+"\',\'"+staff.getPassword()+"\',\'"+
    staff.getCharacters()+"\');";

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

  public int delete(int id) {
      String sql0 = "SELECT * FROM User where uid =\'"+id+"\';";
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
    String sql = "DELETE FROM User where uid =\'"+id+"\';";
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
