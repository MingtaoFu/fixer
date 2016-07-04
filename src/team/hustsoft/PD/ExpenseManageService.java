package team.hustsoft.PD;
import team.hustsoft.basic.Expense;
import team.hustsoft.DA.ExpenseDA;
import java.util.*;

public class ExpenseManageService {
  /**
   * class instance
   */
   private static ExpenseManageService instance = new ExpenseManageService();

   public static ExpenseManageService getInstance() {
     return instance;
   }

public Expense query(int sid){
     ExpenseDA expenseDA = new ExpenseDA();
     return expense;
   }

   public int delete(int sid) {
     ExpenseDA expenseDA = new ExpenseDA();
     return expenseDA.delete(sid);
   }

   public int insert(Expense expense) {
     ExpenseDA expenseDA = new ExpenseDA();
     return expenseDA.insert(expense);
   }
expense
   public int update(Expense expense) {
     ExpenseDA expenseDA = new ExpenseDA();
     return expenseDA.update(expense);
   }
}
