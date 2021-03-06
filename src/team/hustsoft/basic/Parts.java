package team.hustsoft.basic;
import org.json.simple.JSONObject;
import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.math.BigDecimal;
import java.util.regex.*;



public class Parts{
    private int pid;
	private String partName;
	private BigDecimal price;
	private String modelNumber;
	private int quantity;
	private Timestamp inTime;
	private int warningQuantity;
	private int status;
  public Parts(String partName,BigDecimal price,String modelNumber,int quantity,int warningQuantity){
		this.partName = partName;
		this.price = price;
		this.modelNumber = modelNumber;
		this.quantity = quantity;
		this.warningQuantity = warningQuantity;
		this.inTime = new Timestamp(System.currentTimeMillis());
        this.setStatus();
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

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
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

  public int getWarningQuantity() {
    return warningQuantity;
  }

  public void setWarningQuantity(int warningQuantity) {
    this.warningQuantity = warningQuantity;
  }
  public int getStatus(){
    return this.status;
  }
  public void setStatus(){
    if(this.quantity>this.warningQuantity)
      this.status = 0;
    else if(this.quantity==this.warningQuantity)
      this.status = 1;
    else if(this.quantity<this.warningQuantity)
      if(this.quantity>0)
        this.status = 2;
      else
        this.status = 3;
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
}
