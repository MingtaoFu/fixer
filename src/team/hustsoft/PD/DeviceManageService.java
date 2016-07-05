package team.hustsoft.PD;
import team.hustsoft.basic.Device;
import team.hustsoft.basic.Customer;
import team.hustsoft.basic.DevicePrinter;
import team.hustsoft.DA.DeviceDA;
import java.util.*;

public class DeviceManageService {

   private static DeviceManageService instance = new DeviceManageService();

   public static DeviceManageService getInstance() {
     return instance;
   }

   public Customer query_c(int did) {
     DeviceDA deviceDA = new DeviceDA();
     Customer customer = deviceDA.query_c(did);
     return customer;
   }

   public int delete(int id) {
     DeviceDA deviceDA = new DeviceDA();
     return deviceDA.delete(id);
   }

   public int insert(Device device) {
     DeviceDA deviceDA = new DeviceDA();
     return deviceDA.insert(device);
   }

   public int update(Device device) {
     DeviceDA deviceDA = new DeviceDA();
     return deviceDA.update(device);
   }
   public  int print(int did){
     DeviceDA deviceDA = new DeviceDA();
     return deviceDA.print2(did);
   }
   public  int confirm(int did){
     DeviceDA deviceDA = new DeviceDA();
     return deviceDA.confirm(did);
   }
}
