package team.hustsoft.DA;

import team.hustsoft.basic.Device;
import team.hustsoft.basic.Customer;
import team.hustsoft.basic.DevicePrinter;
import com.mysql.jdbc.Driver;
import team.hustsoft.DA.DABase;
import java.util.*;
import java.sql.*;
import java.math.BigDecimal;

public class DeviceDA extends DABase{

   public Customer query_c(int did) {
     conn = initialize();
     String sql = "select Customer.* from Customer,Device where "+
        "Device.did = "+did+" and "+
        "Customer.cid = Device.cid";
     ResultSet rs = null;
     Customer customer = null;
     try{
       rs = statement.executeQuery(sql);
       if(rs.next()) {
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

         customer = new Customer(id, property, companyName, companyPhone,
         mobilePhone, addr, zipCode, name, email, citizenId);
       }
     } catch (SQLException e) {
       System.out.println(e);
     } finally{
       terminate();
     }
     return customer;
   }

   public ArrayList<Device> query(String search) {
     ArrayList<Device> devices = new ArrayList<Device>();
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

     String sql = "select Device.* from Customer,Device where "+
        "Customer.contactPersonName like "+parttern+" and "+
        "Customer.cid = Device.cid";

     ResultSet rs = null;
     Device device  = null;
     try{
       rs = statement.executeQuery(sql);
       while(rs.next()) {
         int did = rs.getInt("did");
         int cid = rs.getInt("cid");
         Timestamp ctime = rs.getTimestamp("ctime");//
         BigDecimal expectedPrice = rs.getBigDecimal("expectedPrice");
	  Timestamp expectedCompletedTime =rs.getTimestamp("expectedCompletedTime");
         int status = rs.getInt("status");
         int deviceType = rs.getInt("deviceType");
         String deviceBrand = rs.getString("deviceBrand");
         String deviceModel = rs.getString("deviceModel");
         String deviceSerialNum = rs.getString("deviceSerialNum");
         String lackPart =rs.getString("lackPart");
         String breakdownAppearance = rs.getString("breakdownAppearance");
         int breakdownType = rs.getInt("breakdownType");
         String appearanceCheck = rs.getString("appearanceCheck");
         String startingUpCommand = rs.getString("startingUpCommand");
         String significantMaterial =rs.getString("significantMaterial");
         String HHD = rs.getString("HHD");
         String RAM = rs.getString("RAM");
         String PCCard = rs.getString("PCCard");
         String ACAdapter = rs.getString("ACAdapter");
         String battery =rs.getString("battery");
         String CD_ROM = rs.getString("CD_ROM");
         String floppy = rs.getString("floppy");
         String other= rs.getString("other");

         device = new Device(cid,expectedPrice,deviceType,deviceBrand,deviceModel,deviceSerialNum,
         	lackPart,breakdownAppearance,breakdownType,appearanceCheck,startingUpCommand,significantMaterial,
         	HHD,RAM,PCCard,ACAdapter,battery,CD_ROM,floppy,other);
         device.setDid(did);
         device.setStatus(status);
         device.setCtime(ctime);
			device.setExpectedCompletedTime(expectedCompletedTime);
         devices.add(device);
       }
     } catch (SQLException e) {
       System.out.println(e);
       return null;
     }
     finally{
       terminate();
     }
    return devices;
  }

  public int insert(Device device){
  	if (device == null)
  		return -1;
  	conn = initialize();
	int cid =device.getCid();
	Timestamp ctime = device.getCtime();//
	BigDecimal expectedPrice = device.getExpectedPrice();
	Timestamp expectedCompletedTime = device.getExpectedCompletedTime();
	int status = device.getStatus();
	int deviceType = device.getDeviceType();
	String deviceBrand = device.getDeviceBrand();
	String deviceModel = device.getDeviceModel();
	String deviceSerialNum = device.getDeviceSerialNum();
	String lackPart = device.getLackPart();
	String breakdownAppearance = device.getBreakdownAppearance();
	int breakdownType = device.getBreakdownType();
  String appearanceCheck = device.getAppearanceCheck();
	String startingUpCommand = device.getStartingUpCommand();
	String significantMaterial = device.getSignificantMaterial();
	String HHD = device.getHHD();
	String RAM = device.getRAM();
	String PCCard = device.getPCCard();
	String ACAdapter = device.getACAdapter();
	String battery = device.getBattery();
	String CD_ROM = device.getCD_ROM();
	String floppy = device.getFloppy();
	String other =device.getOther();
  if(expectedPrice == null) {
    expectedPrice = new BigDecimal("0");
  }
  String sql = "INSERT INTO Device(cid,ctime,expectedPrice,expectedCompletedTime,status,deviceType,deviceBrand,"+
             "deviceModel,deviceSerialNum,lackPart,breakdownType,appearanceCheck,startingUpCommand,significantMaterial,"+
         	"HHD,RAM,PCCard,ACAdapter,battery,CD_ROM,floppy,other,breakdownAppearance)"+
  		"VALUES("+cid+",\'"+ctime.toString()+"\',\'"+expectedPrice.toString()+"\',\'"+expectedCompletedTime.toString()+"\',\'"+
  		status+"\',\'"+deviceType+"\',\'"+deviceBrand+"\',\'"+deviceModel+"\',\'"+deviceSerialNum+"\',\'"+lackPart+"\',\'"+
  		breakdownType+"\',\'"+appearanceCheck+"\',\'"+startingUpCommand+"\',\'"+significantMaterial+"\',\'"+
  		HHD+"\',\'"+RAM+"\',\'"+PCCard+"\',\'"+ACAdapter+"\',\'"+battery+"\',\'"+CD_ROM+"\',\'"+floppy+"\',\'"+other+"\',\'"
             +breakdownAppearance+"\')";
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


  public  int update(Device device) {
      if (device == null)
  		return -1;
  	String sql0 = "SELECT did FROM Device WHERE did="+device.getDid()+";";
  	ResultSet rs = null;
  	conn = initialize();
  	try{
      		rs = statement.executeQuery(sql0);
       	if(!rs.next()){
       	       terminate();
             	return -1;
        	}
      }
      catch(SQLException e){
               System.out.println(e);//?
               return -2;
      }
      	int cid =device.getCid();
	Timestamp ctime = device.getCtime();//
	BigDecimal expectedPrice = device.getExpectedPrice();
	Timestamp expectedCompletedTime = device.getExpectedCompletedTime();
	int status = device.getStatus();
	int deviceType = device.getDeviceType();
	String deviceBrand = device.getDeviceBrand();
	String deviceModel = device.getDeviceModel();
	String deviceSerialNum = device.getDeviceSerialNum();
	String lackPart = device.getLackPart();
	String breakdownAppearance = device.getBreakdownAppearance();
	int breakdownType = device.getBreakdownType();
       String appearanceCheck = device.getAppearanceCheck();
	String startingUpCommand = device.getStartingUpCommand();
	String significantMaterial = device.getSignificantMaterial();
	String HHD = device.getHHD();
	String RAM = device.getRAM();
	String PCCard = device.getPCCard();
	String ACAdapter = device.getACAdapter();
	String battery = device.getBattery();
	String CD_ROM = device.getCD_ROM();
	String floppy = device.getFloppy();
	String other =device.getOther();
	String sql = "UPDATE Device SET cid=" + cid +",ctime=\'"+ctime.toString()+"\',expectedPrice=\'"+expectedPrice.toString()+
		"\',expectedCompletedTime=\'"+expectedCompletedTime.toString()+"\',status=\'"+status+"\',deviceType=\'"+deviceType+
		"\',deviceBrand=\'"+deviceBrand+"\',deviceModel=\'"+deviceModel+"\',deviceSerialNum=\'"+deviceSerialNum+
		"\',lackPart=\'"+lackPart+"\',appearanceCheck=\'"+appearanceCheck+
             "\',breakdownType=\'"+breakdownType+
		"\',startingUpCommand=\'"+startingUpCommand+"\',significantMaterial=\'"+significantMaterial+"\',HHD=\'"+HHD+
		"\',RAM=\'"+RAM+"\',PCCard=\'"+PCCard+"\',ACAdapter=\'"+ACAdapter+"\',battery=\'"+battery+"\',CD_ROM=\'"+CD_ROM+
		"\',floppy=\'"+floppy+"\',other=\'"+other+"\' WHERE did = "+device.getDid()+";";
  System.out.println(sql);
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

  public  int delete(int did) {
  	String sql0 = "SELECT did FROM Device WHERE did ="+did+";";
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
      String sql = "DELETE FROM Device where did ="+did+";";
   	//conn = initialize();
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

  public  int print2(int did) {
  	String sql0 = "SELECT did FROM Device WHERE did ="+did+";";
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
  	  String sql = "update Device set status = \'1\' WHERE did ="+did+";";
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

  public  int confirm(int did) {
  	String sql0 = "SELECT did FROM Device WHERE did ="+did+";";
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
  	  String sql = "update Device set status = \'2\' WHERE did ="+did+";";
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

  public DevicePrinter print(int did){
  	String sql0 = "SELECT cid,ctime,deviceType,deviceBrand,deviceModel,deviceSerialNum,lackPart,breakdownAppearance "+
  		"FROM Device WHERE did ="+did+";";
  	conn = initialize();
      ResultSet rs  =null;
      int cid=0;
      Timestamp ctime=null;
      int deviceType=0;
      String deviceBrand=null;
      String deviceModel=null;
      String deviceSerialNum=null;
      String lackPart=null;
      String breakdownAppearance=null;
      String companyName=null;
      String contactPersonName =null;

      try{
        rs=statement.executeQuery(sql0);
        if(!rs.next()){
          terminate();
          return null;
        }
        else{
        	cid = rs.getInt("cid");
        	ctime=rs.getTimestamp("ctime");
        	deviceType=rs.getInt("deviceType");
        	deviceBrand=rs.getString("deviceBrand");
        	deviceModel=rs.getString("deviceModel");
        	deviceSerialNum=rs.getString("deviceSerialNum");
        	lackPart=rs.getString("lackPart");
        	breakdownAppearance=rs.getString("breakdownAppearance");
        }
      }
      catch(SQLException e){
               System.out.println(e);//?
               return null;
  	}

  	sql0 = "SELECT companyName,contactPersonName FROM Customer WHERE cid ="+cid+";";
  	try{
        rs=statement.executeQuery(sql0);
        if(!rs.next()){
          terminate();
          return null;
        }
        else{
        	companyName = rs.getString("companyName");
        	contactPersonName = rs.getString("contactPersonName");
        }
      }
      catch(SQLException e){
               System.out.println(e);//?
               return null;
  	}

  	return new DevicePrinter(did,ctime,deviceType,deviceBrand,deviceModel,deviceSerialNum,
  		companyName,contactPersonName,lackPart,breakdownAppearance);

	}
}
