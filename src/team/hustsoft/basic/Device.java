package team.hustsoft.basic;
import java.sql.Timestamp;
import java.math.BigDecimal;

enum REPAIRSTATUS{
	Underway,
	Finish,
	Undo
}
enum DEVICETYPE{
	Desktop,
	Notebook,
	Projector,
	Printer,
	Other
}
enum BREAKDOWNTYPE{
	Regular,
	Intermittent
}

public class Device{
	private int did;
	private int cid;
	private Timestamp ctime;//保修时间
	private BigDecimal expectedPrice;
	private Timestamp expectedCompleteTime;
	private REPAIRSTATUS status;
	private DEVICETYPE deviceType;
	private String deviceBrand;
	private String deviceModel;
	private String deviceSerialNum;
	private String lackPart;
	private String breakDownAppearance;
	private BREAKDOWNTYPE breakdownType;
	private String startingUpCommand;
	private String significantMaterial;
	private String HHD;
	private String RAM;
	private String PCCard;
	private String ACAdapter;
	private String battery;
	private String CD_ROM;
	private String floppy;
	private String Other;
  public Device(int did,int cid,BigDecimal expectedPrice,DEVICETYPE deviceType,String deviceBrand,String deviceModel,String deviceSerialNum,String lackPart,String breakDownAppearance,BREAKDOWNTYPE breakdownType,String startingUpCommand,String significantMaterial,String HHD,String RAM,String PCCard,String ACAdapter,String battery,String CD_ROM,String floppy,String Other){
	this.cid = cid;
	this.ctime = new Timestamp(System.currentTimeMillis());
	this.expectedPrice = expectedPrice;
	this.deviceType = deviceType;
	this.deviceBrand = deviceBrand;
	this.deviceModel = deviceModel;
	this.deviceSerialNum = deviceSerialNum;
	this.lackPart = lackPart;
	this.breakDownAppearance = breakDownAppearance;
	this.breakdownType = breakdownType;
	this.startingUpCommand = startingUpCommand;
	this.significantMaterial = significantMaterial;
	this.HHD = HHD;
	this.RAM = RAM;
	this.PCCard = PCCard;
	this.ACAdapter = ACAdapter;
	this.battery = battery;
	this.CD_ROM = CD_ROM;
	this.floppy = floppy;
	this.Other = Other;
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

	public Timestamp getExpectedCompleteTime() {
		return expectedCompleteTime;
	}

	public void setExpectedCompleteTime(Timestamp expectedCompleteTime) {
		this.expectedCompleteTime = expectedCompleteTime;
	}

	public team.hustsoft.basic.REPAIRSTATUS getStatus() {
		return status;
	}

	public void setStatus(team.hustsoft.basic.REPAIRSTATUS status) {
		this.status = status;
	}

	public team.hustsoft.basic.DEVICETYPE getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(team.hustsoft.basic.DEVICETYPE deviceType) {
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

	public String getBreakDownAppearance() {
		return breakDownAppearance;
	}

	public void setBreakDownAppearance(String breakDownAppearance) {
		this.breakDownAppearance = breakDownAppearance;
	}

	public team.hustsoft.basic.BREAKDOWNTYPE getBreakdownType() {
		return breakdownType;
	}

	public void setBreakdownType(team.hustsoft.basic.BREAKDOWNTYPE breakdownType) {
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
		return Other;
	}

	public void setOther(String other) {
		Other = other;
	}
}

