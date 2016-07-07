package team.hustsoft.PD;
import team.hustsoft.basic.Expense;
import team.hustsoft.DA.ExpenseDA;
import java.util.*;
import org.json.simple.JSONObject;


public class ExpenseManageService {
  /**
   * class instance
   */
   private static ExpenseManageService instance = new ExpenseManageService();

   public static ExpenseManageService getInstance() {
     return instance;
   }


   public ArrayList<Expense> query(String sid) {
     ExpenseDA expenseDA = new ExpenseDA();
     ArrayList<Expense> expenses = expenseDA.query(sid);
     return expenses;
   }

   public int delete(int sid) {
     ExpenseDA expenseDA = new ExpenseDA();
     return expenseDA.delete(sid);
   }

   public int insert(Expense expense) {
     ExpenseDA expenseDA = new ExpenseDA();
     return expenseDA.insert(expense);
   }

   public int update(Expense expense) {
     ExpenseDA expenseDA = new ExpenseDA();
     return expenseDA.update(expense);
   }

   public JSONObject print(int ssid) {
     ExpenseDA expenseDA = new ExpenseDA();
     JSONObject printInfo = expenseDA.printInfo(ssid);
     ArrayList<JSONObject> parts = expenseDA.getParts(ssid);
     printInfo.put("parts", parts);
     return printInfo;
   }
}
