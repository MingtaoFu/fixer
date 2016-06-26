package team.hustsoft.PD;
import team.hustsoft.PD.SHA;

public class Account{
  public static Boolean authorized(int id, String pwd) {
    String pwd_sha = SHA.encode(pwd);
    return false;
  }
}
