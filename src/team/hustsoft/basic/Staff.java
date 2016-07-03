package team.hustsoft.basic;
import team.hustsoft.SHA;
public class Staff{
	private int id;
	private String name;
	private String password;
	private int type;
  public Staff(int id, String name, String password, int type) {
		this.id = id;
		this.name = name;
    this.password = password;
    this.type = type;
  }
  public int getType() {
    return this.type;
  }
	public String getPassword() {
		return this.password;
	}
}
