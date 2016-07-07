package team.hustsoft.PD;
import team.hustsoft.basic.Parts;
//import team.hustsoft.basic.DevicePrinter;
import team.hustsoft.DA.PartsDA;
import org.json.simple.JSONObject;
import java.util.*;

public class PartsManageService {

    private static PartsManageService instance = new PartsManageService();

    public static PartsManageService getInstance() {
        return instance;
    }

    public ArrayList<Parts> query(String search, String order) {
        PartsDA partsDA = new PartsDA();
        ArrayList<Parts> parts = partsDA.query(search, order);
        return parts;
    }
    public int query_pid(String partName){
        PartsDA rrda = new PartsDA();
        return rrda.query_pid(partName);
    }

    public ArrayList<JSONObject> query_p(String search) {
        PartsDA rrda=new PartsDA();
        ArrayList<String>rrs =  rrda.query_p(search);
        ArrayList<JSONObject> list = new ArrayList<JSONObject>();
        for(int i = 0; i < rrs.size(); i++) {
            JSONObject json = new JSONObject();
            json.put("value", rrs.get(i));
            JSONObject data = new JSONObject();
            data.put("content", rrs.get(i));
            json.put("data", data);
            list.add(json);
        }
        return list;
    }
    public int delete(int id) {
        PartsDA partsDA = new PartsDA();
        return partsDA.delete(id);
    }

    public int insert(Parts parts) {
        PartsDA partsDA = new PartsDA();
        return partsDA.insert(parts);
    }

    public int update(Parts parts) {
        PartsDA partsDA = new PartsDA();
        return partsDA.update(parts);
    }
    public int delivery(int pid,int quantity) {
        PartsDA partsDA = new PartsDA();
        return partsDA.delivery(pid,quantity);
    }

//    public  DevicePrinter print(int did){
//        PartsDA partsDA = new PartsDA();
//        return partsDADA.print(did);
//    }

}