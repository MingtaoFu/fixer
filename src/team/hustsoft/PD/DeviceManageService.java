package team.hustsoft.PD;
import team.hustsoft.basic.Device;
import team.hustsoft.basic.DevicePrinter;
import team.hustsoft.DA.DeviceDA;
import java.util.*;

public class DeviceManageService {

   private static DeviceManageService instance = new DeviceManageService();

   public static DeviceManageService getInstance() {
     return instance;
   }

   public ArrayList<Device> query(int cid) {
     DeviceDA deviceDA = new DeviceDA();
     ArrayList<Device> devices = deviceDA.query(cid);
     return devices;
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
   public  DevicePrinter print(int did){
     DeviceDA deviceDA = new DeviceDA();
     return deviceDA.print(did); 	
   }

}
