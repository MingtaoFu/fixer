package team.hustsoft.basic;
import org.json.simple.JSONObject;
import java.lang.reflect.Field;

public class Expense{
	private int sid;
	private int rrid;
	private Decimal laborCosts;
	private Decimal materialsCosts;
	private String warrantyPromise;
	private String notice;
	private Timestamp settlementTime;

	//setters and getters
	public Customer(int sid, int rrid, Decimal laborCosts,
	 	Decimal materialsCosts, String warrantyPromise, String notice,
		Timestamp settlementT) {
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


 	public String getLaborCosts(){
 	    return this.laborCosts;
 	}
 	
 	public void setLaborCosts(Decimal laborCosts){
 	    this.laborCosts = laborCosts;
 	}
 	

 	public String getMaterialsCosts(){
 	    return this.materialsCosts;
 	}
 	
 	public void setMaterialsCosts(Decimal materialsCosts){
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
	
	public String getSettlementTime(){
	    return this.settlementTime;
	}
	
	public void setSettlementTime(Timestamp settlementTime){
	    this.settlementTime = settlementTime;
	}
	
	
	public JSONObject toJSON() {
		JSONObject json = new JSONObject();
		Class cls = this.getClass();
		Field[] fileds = cls.getDeclaredFields();
		for(int i = 0; i < fileds.length; i++) {
			Field f = fileds[i];
			f.setAccessible(true);
			try {
				json.put(f.getName(), f.get(this));
			} catch (Exception e) {

			}
		}
		return json;
	}
}
