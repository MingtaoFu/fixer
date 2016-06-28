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
	private int cid;
	private Timestamp ctime;//保修时间
	private BigDecimal expectedPrice;
	private Timestamp expectedCompleteTime;
	private REPAIRSTATUS repairStatus;
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
  public Device(int cid,BigDecimal expectedPrice,DEVICETYPE deviceType,String deviceBrand,String deviceModel,String deviceSerialNum,String lackPart,String breakDownAppearance,BREAKDOWNTYPE breakdownType,String startingUpCommand,String significantMaterial,String HHD,String RAM,String PCCard,String ACAdapter,String battery,String CD_ROM,String floppy,String Other){
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
  public int getRepairStatus(){
    return this.repairStatus.ordinal();
  }
  public void setRepairStatus(REPAIRSTATUS changeStatus){
    this.repairStatus = changeStatus;
  }
}

