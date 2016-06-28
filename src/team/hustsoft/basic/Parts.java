package team.hustsoft.basic;
//import org.json.simple.JSONObject;
import java.lang.reflect.Field;
import java.sql.Timestamp;

enum STATUS{
	NORMAL,
	CRITICAL,
	WARNING,
	LACK
};

public class Parts{
    private int pid;
	private String partName;
	private double price;
	private String modelNumber;
	private int quantity;
	private Timestamp inTime;
	private int waringQuantity;
	private STATUS status;
  public Parts(String partName,double price,String modelNumber,int quantity,int waringQuantity){
		this.partName = partName;
		this.price = price;
		this.modelNumber = modelNumber;
		this.quantity = quantity;
		this.waringQuantity = waringQuantity;
		this.inTime = new Timestamp(System.currentTimeMillis());
  }
  public int getPid() {
    return pid;
  }

  public void setPid(int pid) {
    this.pid = pid;
  }

  public String getPartName() {
    return partName;
  }

  public void setPartName(String partName) {
    this.partName = partName;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public String getModelNumber() {
    return modelNumber;
  }

  public void setModelNumber(String modelNumber) {
    this.modelNumber = modelNumber;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public Timestamp getInTime() {
    return inTime;
  }

  public void setInTime(Timestamp inTime) {
    this.inTime = inTime;
  }

  public int getWaringQuantity() {
    return waringQuantity;
  }

  public void setWaringQuantity(int waringQuantity) {
    this.waringQuantity = waringQuantity;
  }
  public int getStatus(){
    return this.status.ordinal();
  }
  public void setStatus(){
    if(this.quantity>this.waringQuantity)
      this.status = STATUS.NORMAL;
    else if(this.quantity==this.waringQuantity)
      this.status = STATUS.CRITICAL;
    else if(this.quantity<this.waringQuantity)
      if(this.quantity>0)
        this.status = STATUS.WARNING;
      else
        this.status = STATUS.LACK;
  }
}
