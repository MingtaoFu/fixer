package team.hustsoft.basic;
import java.sql.Timestamp;

public class DevicePrinter {
	private int did;
	private Timestamp ctime;
	private int deviceType;
	private String deviceBrand;
	private String deviceModel;	
	private String deviceSerialNum;

	private String companyName;
	private String name;

	private String lackPart;
	private String breakdownAppearance;

	public DevicePrinter(int did,Timestamp ctime,int deviceType,String deviceBrand,String deviceModel,String deviceSerialNum,		
		String companyName,String name,String lackPart,String breakdownAppearance){
		this.did =did;
		this.ctime = ctime;
		this.deviceType = deviceType;
		this.deviceBrand= deviceBrand;
		this.deviceModel = deviceModel;
		this.deviceSerialNum = deviceSerialNum;
		this.companyName=companyName;
		this.name= name;
		this.lackPart=lackPart;
		this.breakdownAppearance=breakdownAppearance;
	}


	public int getDid(){
	    return this.did;
	}
	
	public void setDid(int did){
	    this.did = did;
	}
	
	public Timestamp getCtime(){
	    return this.ctime;
	}
	
	public void setCtime(Timestamp ctime){
	    this.ctime = ctime;
	}
	
	public int getDeviceType(){
	    return this.deviceType;
	}
	
	public void setDeviceType(int deviceType){
	    this.deviceType = deviceType;
	}
	
	public String getDeviceBrand(){
	    return this.deviceBrand;
	}
	
	public void setDeviceBrand(String deviceBrand){
	    this.deviceBrand = deviceBrand;
	}
	
	public String getDeviceModel(){
	    return this.deviceModel;
	}
	
	public void setDeviceModel(String deviceModel){
	    this.deviceModel = deviceModel;
	}
	
	public String getDeviceSerialNum(){
	    return this.deviceSerialNum;
	}
	
	public void setDeviceSerialNum(String deviceSerialNum){
	    this.deviceSerialNum = deviceSerialNum;
	}
	public String getCompanyName(){
	    return this.companyName;
	}

	public void setCompanyName(String companyName){
	    this.companyName = companyName;
	}

	public String getName(){
	    return this.name;
	}
	
	public void setName(String name){
	    this.name = name;
	}
	
	public String getLackPart(){
	    return this.lackPart;
	}
	
	public void setLackPart(String lackPart){
	    this.lackPart = lackPart;
	}
	
	public String getBreakdownAppearance(){
	    return this.breakdownAppearance;
	}
	
	public void setBreakdownAppearance(String breakdownAppearance){
	    this.breakdownAppearance = breakdownAppearance;
	}
	


}