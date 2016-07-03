package team.hustsoft.PD;
import team.hustsoft.SHA;
import team.hustsoft.DA.AccountDA;
import team.hustsoft.basic.Staff;

public class AccountManageService{
  public static AccountManageService instance = new AccountManageService();
  public static AccountManageService getInstance() {
    return instance;
  }
  public Staff login(String name, String pwd) {
    AccountDA accountDA = new AccountDA();
    String pwd_sha = SHA.encode(pwd);
    Staff staff = accountDA.query(name, pwd_sha);
    return staff;
  }
  public Boolean authorize (String name, String pwd_sha, int property) {
    AccountDA accountDA = new AccountDA();
    Boolean bool = accountDA.authorize(name, pwd_sha, property);
    return bool;
  }
}
