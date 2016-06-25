package team.hustsoft.PD;
import team.hustsoft.PD.SHA;

public class Account{
  public static Boolean authorized(int id, String pwd) {
    String pwd_sha = SHA.encode(pwd);
    return false;
  }
}

abstract class Staff{
	private int id;
	private String name;
	private String password;
	private int permission;
}

class CustomerCare extends Staff{
}
