package team.hustsoft.basic;
import org.json.simple.JSONObject;
import java.lang.reflect.Field;

public class Customer{
	private int id;
	private int property;
	private String companyName;
	private String companyPhone;
	private String mobilePhone;
	private String addr;
	private String zipCode;
	private String citizenId;
	private String name;
	private String email;

	//setters and getters
	public Customer(int id, int property, String companyName,
	 	String companyPhone, String mobilePhone, String addr,
		String zipCode, String name, String email, String citizenId) {
			this.id = id;
			this.property = property;
			this.mobilePhone = mobilePhone;
			this.addr = addr;
			this.zipCode = zipCode;
			this.name = name;
			this.email = email;
			this.companyPhone = companyPhone;
			this.companyName = companyName;
			this.citizenId = citizenId;
 	}


 	public int getId(){
 	    return this.id;
 	}		
 	
 	public void setId(int id){
 	    this.id = id;
 	}		


 	public int getProperty(){
 	    return this.property;
 	}
 	
 	public void setProperty(int property){
 	    this.property = property;
 	}


 	public String getCompanyName(){
 	    return this.companyName;
 	}
 	
 	public void setCompanyName(String companyName){
 	    this.companyName = companyName;
 	}
 	

 	public String getCompanyPhone(){
 	    return this.companyPhone;
 	}
 	
 	public void setCompanyPhone(String companyPhone){
 	    this.companyPhone = companyPhone;
 	}
 	

 	public String getCitizenId(){
 	    return this.citizenId;
 	}
 	
 	public void setCitizenId(String citizenId){
 	    this.citizenId = citizenId;
 	}
 	


	public String getMobilePhone(){
	    return this.mobilePhone;
	}

	public void setMobilePhone(String mobilePhone){
	    this.mobilePhone = mobilePhone;
	}


	public String getAddr(){
	    return this.addr;
	}
	
	public void setAddr(String addr){
	    this.addr = addr;
	}
	
	public String getZipCode(){
	    return this.zipCode;
	}
	
	public void setZipCode(String zipCode){
	    this.zipCode = zipCode;
	}
	
	public String getName(){
	    return this.name;
	}
	
	public void setName(String name){
	    this.name = name;
	}
	
	public String getEmail(){
	    return this.email;
	}
	
	public void setEmail(String email){
	    this.email = email;
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
