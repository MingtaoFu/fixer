package team.hustsoft.PD;

import org.json.simple.JSONObject;
import team.hustsoft.basic.DetailedPartsList;
import team.hustsoft.basic.RepairRecord;
import java.util.*;
import team.hustsoft.DA.DetailedPartsListDA;
import team.hustsoft.DA.RepairRecordDA;


public class DetailedPLManageService {
    private static DetailedPLManageService instance = new DetailedPLManageService();
//
    public static DetailedPLManageService getInstance() {
        return instance;
    }
//
//    public ArrayList<DetailedPartsList> query(int plid) {
//        DetailedPartsListDA plda=new DetailedPartsListDA();
//        return plda.query(plid);
//    }
    public ArrayList<DetailedPartsList> query(String search, String order) {
        DetailedPartsListDA plda=new DetailedPartsListDA();
        return plda.query(search,order);
    }

    // public ArrayList<DetailedPartsList> queryRequest() {
    //     DetailedPartsListDA request = new DetailedPartsListDA();
    //     return request.query();
    // }
//
   public int insert(DetailedPartsList rr){
       DetailedPartsListDA rrda = new DetailedPartsListDA();
       return rrda.insert(rr);
   }
//
//    public int update(RepairRecord rr) {
//        RepairRecordDA rrda = new RepairRecordDA();
//        return rrda.update(rr);
//    }
//
//    public int delete(int rrid){
//        RepairRecordDA rrda = new RepairRecordDA();
//        return rrda.delete(rrid);
//    }
}