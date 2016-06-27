package team.hustsoft.PD;
import team.hustsoft.basic.Customer;
import team.hustsoft.DA.CustomerDA;
import java.util.*;

public class CustomerManageService {
  /**
   * class instance
   */
   private static CustomerManageService instance = new CustomerManageService();

   public static CustomerManageService getInstance() {
     return instance;
   }

   public ArrayList<Customer> query(String search, String order) {
     CustomerDA customerDA = new CustomerDA();
     ArrayList<Customer> customers = customerDA.query(search, order);
     return customers;
   }
}
