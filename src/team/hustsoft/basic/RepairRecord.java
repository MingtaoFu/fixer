package team.hustsoft.basic;
import java.sql.Timestamp;
import org.json.simple.JSONObject;
import java.lang.reflect.Field;
import java.util.regex.*;
public class RepairRecord{
	private int rrid;
	private int did;
	private Timestamp distributeTime;
	private String maintenance;
	private String detectionRecord;
	private String repairRecord;
	private Timestamp repairTime;
	private String workload;
	private String requiredPart;
	private int status;
	private int delayDegree;

	public RepairRecord(int did,Timestamp distributeTime,String maintenance,String detectionRecord,
		String repairRecord,Timestamp repairTime,String workload,String requiredPart,int status,int delayDegree){
		this.did = did;
		if(distributeTime == null){
			this.distributeTime = new Timestamp(1000l);
		}
		else{
			this.distributeTime = distributeTime;
		}
		this.maintenance = maintenance;
		this .detectionRecord = detectionRecord;
		this.repairRecord = repairRecord;
		if(repairTime==null){
			this.repairTime = new Timestamp(1000l);
		}
		else{
			this.repairTime = repairTime;
		}
		this.workload = workload;
		this.requiredPart = requiredPart;
		this.status = status;
		this.delayDegree = delayDegree;
	}


	public int getRrid(){
	    return this.rrid;
	}

	public void setRrid(int rrid){
	    this.rrid = rrid;
	}

	public int getDid(){
	    return this.did;
	}

	public void setDid(int did){
	    this.did = did;
	}

	public Timestamp getDistributeTime(){
	    return this.distributeTime;
	}

	public void setDistributeTime(Timestamp distributeTime){
	    this.distributeTime = distributeTime;
	}

	public String getMaintenance(){
	    return this.maintenance;
	}

	public void setMaintenance(String maintenance){
	    this.maintenance = maintenance;
	}

	public String getDetectionRecord(){
	    return this.detectionRecord;
	}

	public void setDetectionRecord(String detectionRecord){
	    this.detectionRecord = detectionRecord;
	}


	public String getRepairRecord(){
	    return this.repairRecord;
	}

	public void setRepairRecord(String repairRecord){
	    this.repairRecord = repairRecord;
	}


	public Timestamp getRepairTime(){
	    return this.repairTime;
	}

	public void setRepairTime(Timestamp repairTime){
	    this.repairTime = repairTime;
	}


	public String getWorkload(){
	    return this.workload;
	}

	public void setWorkload(String workload){
	    this.workload = workload;
	}


	public String getRequiredPart(){
	    return this.requiredPart;
	}

	public void setRequiredPart(String requiredPart){
	    this.requiredPart = requiredPart;
	}

	public int getStatus(){
	    return this.status;
	}

	public void setStatus(int status){
	    this.status = status;
	}

	public int getDelayDegree(){
	    return this.delayDegree;
	}

	public void setDelayDegree(int delayDegree){
	    this.delayDegree = delayDegree;
	}
	// public JSONObject toJSON() {
	// 	JSONObject json = new JSONObject();
	// 	Class cls = this.getClass();
	// 	Field[] fileds = cls.getDeclaredFields();
	// 	for(int i = 0; i < fileds.length; i++) {
	// 		Field f = fileds[i];
	// 		f.setAccessible(true);
	// 		try {
	// 			if(f.get(this) instanceof Timestamp){
	// 				json.put(f.getName(), f.get(this).toString());	
	// 			}
	// 			else{
	// 				json.put(f.getName(), f.get(this));
	// 			}
	// 		} catch (Exception e) {
	// 			System.out.println(e);
	// 		}
	// 	}
	// 	return json;
	// }
		public JSONObject toJSON() {
		JSONObject json = new JSONObject();
		Class cls = this.getClass();
		Field[] fileds = cls.getDeclaredFields();

		String patternStr = "(20[0-9]{2}(-[0-9]{2}){2} [0-9]{2}:[0-9]{2})";
    Pattern ptn = Pattern.compile(patternStr);

		for(int i = 0; i < fileds.length; i++) {
			Field f = fileds[i];
			f.setAccessible(true);
			try {
				String value = f.get(this).toString();
				Matcher matcher = ptn.matcher(value);
        if(matcher.find()) {
            value = matcher.group(1);
        }
				json.put(f.getName(), value);
			} catch (Exception e) {

			}
		}
		return json;
	}


}
