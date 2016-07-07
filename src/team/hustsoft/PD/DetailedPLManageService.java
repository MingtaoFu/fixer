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

    public ArrayList<RepairRecord> queryRequest() {
        RepairRecordDA request = new RepairRecordDA();
        return request.query();
    }

    public int insert(DetailedPartsList pl){
        DetailedPartsListDA plda = new DetailedPartsListDA();
        return plda.insert(pl);
    }

    public int update(DetailedPartsList pl) {
        DetailedPartsListDA plda = new DetailedPartsListDA();
        return plda.update(pl);
    }

    public int delete(DetailedPartsList pl) {
        DetailedPartsListDA plda = new DetailedPartsListDA();
        return plda.delete(pl.getPlid());
    }
    
}