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
	//private String citizenId;
	private String name;
	private String email;

	//setters and getters
	public Customer(int id, int property, String companyName,
	 	String companyPhone, String mobilePhone, String addr,
		String zipCode, String name, String email) {
			this.id = id;
			this.property = property;
			this.mobilePhone = mobilePhone;
			this.addr = addr;
			this.zipCode = zipCode;
			this.name = name;
			this.email = email;
			this.companyPhone = companyPhone;
			this.companyName = companyName;
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
