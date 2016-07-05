package team.hustsoft.PD;

import org.json.simple.JSONObject;
import java.util.*;
import team.hustsoft.DA.StaffDA;
import team.hustsoft.basic.Staff;

public class StaffManageService {
  public static StaffManageService instance = new StaffManageService();

  public static StaffManageService getInstance() {
    return instance;
  }

  public ArrayList<Staff> query(String search) {
    StaffDA staffDA = new StaffDA();
    return staffDA.query(search);
  }

  public int insert(Staff staff) {
    StaffDA staffDA = new StaffDA();
    return staffDA.insert(staff);
  }

  public int delete(int id) {
    StaffDA staffDA = new StaffDA();
    return staffDA.delete(id);
  }

  public int update(Staff staff) {
    StaffDA staffDA = new StaffDA();
    return staffDA.update(staff);
  }
}
