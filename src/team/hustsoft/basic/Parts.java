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
  public Timestamp getInTime(){
    return this.inTime;
  } 
}
