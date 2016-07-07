package team.hustsoft.PD;
import team.hustsoft.basic.Financial;
import team.hustsoft.DA.FinancialDA;
import java.util.*;


public class FinancialManageService {
  /**
   * class instance
   */
   private static FinancialManageService instance = new FinancialManageService();

   public static FinancialManageService getInstance() {
     return instance;
   }


   public ArrayList<Financial> query(String sid) {
     FinancialDA financialDA = new FinancialDA();
     ArrayList<Financial> financials = financialDA.query(sid);
     return financials;
   }



   // public int delete(int sid) {
   //   FinancialDA financialDA = new FinancialDA();
   //   return financialDA.delete(sid);
   // }

   // public int insert(Financial financial) {
   //   FinancialDA financialDA = new FinancialDA();
   //   return financialDA.insert(financial);
   // }

   public int update(int sid) {
     FinancialDA financialDA = new FinancialDA();
     return financialDA.update(sid);
   }
}
