package team.hustsoft.PD.TableClass;
import team.hustsoft.PD.SHA;
public class Staff{
	private int id;
	private String name;
	private String password;
	private int type;
  public Staff(String name, String password) {
		this.name = name;
    this.password = SHA.encode(password);
    this.type = 2;
  }
  public int getType() {
    return this.type;
  }
}

class CustomerCare extends Staff{
	public CustomerCare(String name, String password) {
		super(name, password);
	}
}
