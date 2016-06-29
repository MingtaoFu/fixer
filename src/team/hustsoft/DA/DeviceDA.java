package team.hustsoft.DA;

import team.hustsoft.basic.Device;
import team.hustsoft.basic.DevicePrinter;
import com.mysql.jdbc.Driver;
import team.hustsoft.DA.DABase;
import java.util.*;
import java.sql.*;
import java.math.BigDecimal;

public class DeviceDA extends DABase{


   public ArrayList<Device> query(int cid) {
     ArrayList<Device> devices = new ArrayList<Device>();
     conn = initialize();
     String sql = "select * from Device where cid=\'" + cid + "\';";
     ResultSet rs = null;
     Device device  = null;
     try{
       rs = statement.executeQuery(sql);
       while(rs.next()) {
         int did = rs.getInt("did");
         //int cid = cid;
         Timestamp ctime = rs.getTimestamp("ctime");//
         BigDecimal expectedPrice = rs.getBigDecimal("expectedPrice");
	  Timestamp expectedCompleteTime =rs.getTimestamp("expectedCompleteTime");
         int status = rs.getInt("status");
         int deviceType = rs.getInt("deviceType");
         String deviceBrand = rs.getString("deviceBrand");
         String deviceModel = rs.getString("deviceModel");
         String deviceSerialNum = rs.getString("deviceSerialNum");
         String lackPart =rs.getString("lackPart");
         String breakdownAppearance = rs.getString("breakdownAppearance");
         int breakdownType = rs.getInt("breakdownType");
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
         	lackPart,breakdownAppearance,breakdownType,startingUpCommand,significantMaterial,
         	HHD,RAM,PCCard,ACAdapter,battery,CD_ROM,floppy,other);
         device.setDid(did);
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
	Timestamp expectedCompleteTime = device.getExpectedCompleteTime();
	int status = device.getStatus();
	int deviceType = device.getDeviceType();
	String deviceBrand = device.getDeviceBrand();
	String deviceModel = device.getDeviceModel();
	String deviceSerialNum = device.getDeviceSerialNum();
	String lackPart = device.getLackPart();
	String breakdownAppearance = device.getBreakdownAppearance();
	int breakdownType = device.getBreakdownType();
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
  	String sql = "INSERT INTO Device(cid,expectedPrice,deviceType,deviceBrand,deviceModel,deviceSerialNum,"+
         	"lackPart,breakdownType,breakdownType,startingUpCommand,significantMaterial,"+
         	"HHD,RAM,PCCard,ACAdapter,battery,CD_ROM,floppy,other)"+
  		"VALUES("+cid+",\'"+ctime.toString()+"\',\'"+expectedPrice.toString()+"\',\'"+expectedCompleteTime.toString()+"\',"+
  		status+","+deviceType+",\'"+deviceBrand+"\',\'"+deviceModel+"\',\'"+deviceSerialNum+"\',\'"+lackPart+"\',\'"+
  		breakdownType+"\',"+breakdownType+",\'"+startingUpCommand+"\',\'"+significantMaterial+"\',\'"+
  		HHD+"\',\'"+RAM+"\',\'"+PCCard+"\',\'"+ACAdapter+"\',\'"+battery+"\',\'"+CD_ROM+"\',\'"+floppy+"\',\'"+other+"\')";
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
	Timestamp expectedCompleteTime = device.getExpectedCompleteTime();
	int status = device.getStatus();
	int deviceType = device.getDeviceType();
	String deviceBrand = device.getDeviceBrand();
	String deviceModel = device.getDeviceModel();
	String deviceSerialNum = device.getDeviceSerialNum();
	String lackPart = device.getLackPart();
	String breakdownAppearance = device.getBreakdownAppearance();
	int breakdownType = device.getBreakdownType();
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
		"\',expectedCompleteTime=\'"+expectedCompleteTime.toString()+"\',status="+status+",deviceType="+deviceType+
		",deviceBrand=\'"+deviceBrand+"\',deviceModel=\'"+deviceModel+"\',deviceSerialNum=\'"+deviceSerialNum+
		"\',lackPart=\'"+lackPart+"\',breakdownType=\'"+breakdownType+"\',breakdownType="+breakdownType+
		",startingUpCommand=\'"+startingUpCommand+"\',significantMaterial=\'"+significantMaterial+"\',HHD=\'"+HHD+
		"\',RAM=\'"+RAM+"\',PCCard=\'"+PCCard+"\',ACAdapter=\'"+ACAdapter+"\',battery=\'"+battery+"\',CD_ROM=\'"+CD_ROM+
		"\',floppy=\'"+floppy+"\',other\'"+other+"\';";
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
      String sql = "DELETE FROM Device where did =\'"+did+"\';";
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