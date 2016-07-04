package team.hustsoft.PD;
import team.hustsoft.basic.Parts;
//import team.hustsoft.basic.DevicePrinter;
import team.hustsoft.DA.PartsDA;
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
//    public  DevicePrinter print(int did){
//        PartsDA partsDA = new PartsDA();
//        return partsDADA.print(did);
//    }

}