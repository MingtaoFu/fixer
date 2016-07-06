package team.hustsoft.basic;
import java.sql.Timestamp;
import org.json.simple.JSONObject;
import java.lang.reflect.Field;
import java.math.BigDecimal;


public class DetailedPartsList{
    private int plid;
    private int rrid;
    private int pid;
    private String partName;
    private BigDecimal price;
    private String modelNumber;
    private int quantity;
    private Timestamp outTime;

    public DetailedPartsList(int rrid,int pid,String partName,BigDecimal price,String modelNumber,int quantity){
        this.rrid = rrid;
        this.pid = pid;
        this.partName = partName;
        this.price = price;
        this.modelNumber = modelNumber;
        this.quantity = quantity;
    }

    public int getPlid() {
        return plid;
    }

    public void setPlid(int plid) {
        this.plid = plid;
    }

    public int getRrid() {
        return rrid;
    }

    public void setRrid(int rrid) {
        this.rrid = rrid;
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

    public Timestamp getOutTime() {
        return outTime;
    }

    public void setOutTime(Timestamp outTime) {
        this.outTime = outTime;
    }



    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        Class cls = this.getClass();
        Field[] fileds = cls.getDeclaredFields();
        for(int i = 0; i < fileds.length; i++) {
            Field f = fileds[i];
            f.setAccessible(true);
            try {
                if(f.get(this) instanceof Timestamp){
                    json.put(f.getName(), f.get(this).toString());
                }
                else{
                    json.put(f.getName(), f.get(this));
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return json;
    }

}
