package team.hustsoft.basic;
import org.json.simple.JSONObject;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.regex.*;

public class Expense{
	private int sid;
	private int rrid;
	private BigDecimal laborCosts;
	private BigDecimal materialsCosts;
	private String warrantyPromise;
	private String notice;
	private Timestamp settlementTime;

	//setters and getters
	public Expense(int sid, int rrid, BigDecimal laborCosts,
	 	BigDecimal materialsCosts, String warrantyPromise, String notice,
		Timestamp settlementTime) {
			this.sid = sid;
			this.rrid = rrid;
			this.warrantyPromise = warrantyPromise;
			this.notice = notice;
			this.settlementTime = settlementTime;
			this.materialsCosts = materialsCosts;
			this.laborCosts = laborCosts;			
 	}


 	public int getSid(){
 	    return this.sid;
 	}		
 	
 	public void setSid(int sid){
 	    this.sid = sid;
 	}		


 	public int getRrid(){
 	    return this.rrid;
 	}
 	
 	public void setRrid(int rrid){
 	    this.rrid = rrid;
 	}


 	public BigDecimal getLaborCosts(){
 	    return this.laborCosts;
 	}
 	
 	public void setLaborCosts(BigDecimal laborCosts){
 	    this.laborCosts = laborCosts;
 	}
 	

 	public BigDecimal getMaterialsCosts(){
 	    return this.materialsCosts;
 	}
 	
 	public void setMaterialsCosts(BigDecimal materialsCosts){
 	    this.materialsCosts = materialsCosts;
 	}
 	
	public String getWarrantyPromise(){
	    return this.warrantyPromise;
	}

	public void setWarrantyPromise(String warrantyPromise){
	    this.warrantyPromise = warrantyPromise;
	}

	public String getNotice(){
	    return this.notice;
	}
	
	public void setNotice(String notice){
	    this.notice = notice;
	}
	
	public Timestamp getSettlementTime(){
	    return this.settlementTime;
	}
	
	public void setSettlementTime(Timestamp settlementTime){
	    this.settlementTime = settlementTime;
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
