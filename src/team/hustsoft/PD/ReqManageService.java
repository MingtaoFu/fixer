package team.hustsoft.PD;

import org.json.simple.JSONObject;
import team.hustsoft.basic.Customer;
import java.util.*;
import team.hustsoft.DA.CustomerDA;

public class ReqManageService {
  private static ReqManageService instance = new ReqManageService();

  public static ReqManageService getInstance() {
    return instance;
  }

  public ArrayList<JSONObject> query_customer(String search) {
    CustomerDA customerDA = new CustomerDA();
    ArrayList<Customer> customers = customerDA.query(search, "");
    ArrayList<JSONObject> list = new ArrayList<JSONObject>();
    for(int i = 0; i < customers.size(); i++) {
      JSONObject json = new JSONObject();
      json.put("value", customers.get(i).getId());
      JSONObject data = new JSONObject();
      data.put("content", customers.get(i).getName());
      json.put("data", data);

      list.add(json);
    }
    return list;
  }
}
