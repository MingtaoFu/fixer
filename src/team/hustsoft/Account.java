package team.hustsoft;
import team.hustsoft.SHA;

public class Account{
  public static Boolean authorized(int id, String pwd) {
    String pwd_sha = SHA.encode(pwd);
    return false;
  }
}
