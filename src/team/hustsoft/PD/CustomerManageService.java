package team.hustsoft.PD;
import team.hustsoft.basic.Customer;
import team.hustsoft.DA.CustomerDA;
import java.util.*;
import java.sql.Timestamp;

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

   public int delete(int id) {
     CustomerDA customerDA = new CustomerDA();
     return customerDA.delete(id);
   }

   public int insert(Customer customer) {
     CustomerDA customerDA = new CustomerDA();
     return customerDA.insert(customer);
   }

   public int update(Customer customer) {
     CustomerDA customerDA = new CustomerDA();
     return customerDA.update(customer);
   }
}
