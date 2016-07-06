package team.hustsoft.PD;

import org.json.simple.JSONObject;
import team.hustsoft.basic.RepairRecord;
import java.util.*;
import team.hustsoft.DA.RepairRecordDA;

public class RepairManageService {
	private static RepairManageService instance = new RepairManageService();

	public static RepairManageService getInstance() {
		return instance;
	}

	public ArrayList<RepairRecord> query(int did) {
		RepairRecordDA rrda=new RepairRecordDA();
		return rrda.query(did);
	}
	/////
	public ArrayList<RepairRecord> query() {
		RepairRecordDA rrda=new RepairRecordDA();
		return rrda.query();
	}
	public ArrayList<RepairRecord> query(String ename) {
		RepairRecordDA rrda=new RepairRecordDA();
		return rrda.query(ename);
	}
	public ArrayList<JSONObject> query_u(String search) {
		RepairRecordDA rrda=new RepairRecordDA();
		ArrayList<String>rrs =  rrda.query_u(search);
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


	public int insert(RepairRecord rr){
		RepairRecordDA rrda = new RepairRecordDA();
		return rrda.insert(rr);
	}

	public int update(RepairRecord rr) {
		RepairRecordDA rrda = new RepairRecordDA();
		return rrda.update(rr);
	}

<<<<<<< HEAD
/*
	public int delete(int rrid){
		RepairRecordDA rrda = new RepairRecordDA();
		return rrda.delete(rrid);
	}
	*/
}
=======
	// public int delete(int rrid){
	// 	RepairRecordDA rrda = new RepairRecordDA();
	// 	return rrda.delete(rrid);
	// }
}
>>>>>>> d21dca9f63cd8c15dfa1256af42f1116b5309b08
