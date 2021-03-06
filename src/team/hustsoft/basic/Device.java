package team.hustsoft.basic;
import java.sql.Timestamp;
import java.math.BigDecimal;
import org.json.simple.JSONObject;
import java.lang.reflect.Field;
import java.util.regex.*;
//import javax.servlet.http.*;
//import java.lang.reflect.Field;
// enum REPAIRSTATUS{
// 	Underway,
// 	Finish,
// 	Undo
// }
// enum DEVICETYPE{
// 	Desktop,
// 	Notebook,
// 	Projector,
// 	Printer,
// 	Other
// }
// enum BREAKDOWNTYPE{
// 	Regular,
// 	Intermittent
// }

public class Device{
	private int did;
	private int cid;
	private Timestamp ctime;//保修时间
	private BigDecimal expectedPrice;
	private Timestamp expectedCompletedTime;
	private int status;
	private int deviceType;
	private String deviceBrand;
	private String deviceModel;
	private String deviceSerialNum;
	private String lackPart;

	private String breakdownAppearance;
	private int breakdownType;
	private String appearanceCheck;
	private String startingUpCommand;
	private String significantMaterial;
	private String HHD;
	private String RAM;
	private String PCCard;
	private String ACAdapter;
	private String battery;
	private String CD_ROM;
	private String floppy;
	private String other;

/*
	//construct form request
	public Device(HttpServletRequest request) {
		Class cls = this.getClass();
		Field[] fields = cls.getDeclaredFields();
		for(int i = 0; i < fields.length; i++){
			Field f = fields[i];
			System.out.println(f.getType());
			if(f.getName().equals("did")) {
				continue;
			}
			f.setAccessible(true);
			try {
				f.set(this, request.getParameter(f.getName()));
			} catch (Exception e) {
				System.out.println(f.getName());
				System.out.println(e);
			}
		}
	}
*/
  public Device(int cid,BigDecimal expectedPrice,int deviceType,String deviceBrand,
		String deviceModel,String deviceSerialNum,String lackPart,
  	String breakdownAppearance,int breakdownType, String appearanceCheck,
		String startingUpCommand,String significantMaterial,
  	String HHD,String RAM,String PCCard,String ACAdapter,String battery,
		String CD_ROM,String floppy,String other){
	this.cid = cid;
	this.ctime = new Timestamp(System.currentTimeMillis());
	this.expectedCompletedTime = new Timestamp(this.ctime.getTime()+259200000l);
	this.expectedPrice = expectedPrice;
	this.deviceType = deviceType;
	this.deviceBrand = deviceBrand;
	this.deviceModel = deviceModel;
	this.deviceSerialNum = deviceSerialNum;
	this.lackPart = lackPart;
	this.breakdownAppearance = breakdownAppearance;
	this.breakdownType = breakdownType;
	this.appearanceCheck = appearanceCheck;
	this.startingUpCommand = startingUpCommand;
	this.significantMaterial = significantMaterial;
	this.HHD = HHD;
	this.RAM = RAM;
	this.PCCard = PCCard;
	this.ACAdapter = ACAdapter;
	this.battery = battery;
	this.CD_ROM = CD_ROM;
	this.floppy = floppy;
	this.other = other;
  }

	public JSONObject toJSON() {
		JSONObject json = new JSONObject();
		Class cls = this.getClass();
		Field[] fileds = cls.getDeclaredFields();

		String patternStr = "(20[0-9]{2}(-[0-9]{2}){2} [0-9]{2}:[0-9]{2})";
    Pattern ptn = Pattern.compile(patternStr);

		for(int i = 0; i < fileds.length; i++) {
			Field f = fileds[i];
			f.setAccessible(true);
			try {
				String value = f.get(this).toString();
				Matcher matcher = ptn.matcher(value);
        if(matcher.find()) {
            value = matcher.group(1);
        }
				json.put(f.getName(), value);
			} catch (Exception e) {

			}
		}
		return json;
	}

	public int getDid() {
		return did;
	}

	public void setDid(int did) {
		this.did = did;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public Timestamp getCtime() {
		return ctime;
	}

	public void setCtime(Timestamp ctime) {
		this.ctime = ctime;
	}

	public BigDecimal getExpectedPrice() {
		return expectedPrice;
	}

	public void setExpectedPrice(BigDecimal expectedPrice) {
		this.expectedPrice = expectedPrice;
	}

	public Timestamp getExpectedCompletedTime() {
		return expectedCompletedTime;
	}

	public void setExpectedCompletedTime(Timestamp expectedCompletedTime) {
		this.expectedCompletedTime = expectedCompletedTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(int deviceType) {
		this.deviceType = deviceType;
	}

	public String getDeviceBrand() {
		return deviceBrand;
	}

	public void setDeviceBrand(String deviceBrand) {
		this.deviceBrand = deviceBrand;
	}

	public String getDeviceModel() {
		return deviceModel;
	}

	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}

	public String getDeviceSerialNum() {
		return deviceSerialNum;
	}

	public void setDeviceSerialNum(String deviceSerialNum) {
		this.deviceSerialNum = deviceSerialNum;
	}

	public String getLackPart() {
		return lackPart;
	}

	public void setLackPart(String lackPart) {
		this.lackPart = lackPart;
	}

	public String getBreakdownAppearance() {
		return breakdownAppearance;
	}

	public void setBreakdownAppearance(String breakdownAppearance) {
		this.breakdownAppearance = breakdownAppearance;
	}

	public int getBreakdownType() {
		return breakdownType;
	}

	public void setBreakdownType(int breakdownType) {
		this.breakdownType = breakdownType;
	}

	public String getStartingUpCommand() {
		return startingUpCommand;
	}

	public void setStartingUpCommand(String startingUpCommand) {
		this.startingUpCommand = startingUpCommand;
	}

	public String getSignificantMaterial() {
		return significantMaterial;
	}

	public void setSignificantMaterial(String significantMaterial) {
		this.significantMaterial = significantMaterial;
	}

	public String getHHD() {
		return HHD;
	}

	public void setHHD(String HHD) {
		this.HHD = HHD;
	}

	public String getRAM() {
		return RAM;
	}

	public void setRAM(String RAM) {
		this.RAM = RAM;
	}

	public String getPCCard() {
		return PCCard;
	}

	public void setPCCard(String PCCard) {
		this.PCCard = PCCard;
	}

	public String getACAdapter() {
		return ACAdapter;
	}

	public void setACAdapter(String ACAdapter) {
		this.ACAdapter = ACAdapter;
	}

	public String getBattery() {
		return battery;
	}

	public void setBattery(String battery) {
		this.battery = battery;
	}

	public String getCD_ROM() {
		return CD_ROM;
	}

	public void setCD_ROM(String CD_ROM) {
		this.CD_ROM = CD_ROM;
	}

	public String getFloppy() {
		return floppy;
	}

	public void setFloppy(String floppy) {
		this.floppy = floppy;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}


	public String getAppearanceCheck(){
	    return this.appearanceCheck;
	}

	public void setAppearanceCheck(String appearanceCheck){
	    this.appearanceCheck = appearanceCheck;
	}

}
