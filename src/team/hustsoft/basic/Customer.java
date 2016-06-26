package team.hustsoft.basic.Customer;
import org.json.simple.JSONObject;
import java.lang.reflect.Field;

public class Customer{
	private int id;
	private String citizenId;
	private String contact;
	private int type;
	private String addr;
	private String zipCode;
	private String email;
	private String companyName;
	private String companyPhone;
	private String mobilePhone;

	//setters and getters
	public Customer() {
 	}
	public JSONObject toJSON() {
		JSONObject json = new JSONObject();
		Class cls = this.getClass();
		Field[] fileds = cls.getDeclaredFields();
		for(int i = 0; i < fileds.length; i++) {
			Field f = fileds[i];
			f.setAccessible(true);
			json.put(f.getName(), f.get(this));
		}
		return json;
	}
}
