package team.hustsoft.basic;
import team.hustsoft.SHA;
import org.json.simple.JSONObject;
import java.lang.reflect.Field;

public class Staff{
	private int uid;
	private String userName;
	private String password;
	private int characters;
  public Staff(int id, String name, String password, int type) {
		this.uid = id;
		this.userName = name;
    this.password = password;
    this.characters = type;
  }
  public int getCharacters() {
    return this.characters;
  }
	public String getPassword() {
		return this.password;
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
