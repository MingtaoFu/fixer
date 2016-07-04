package team.hustsoft.basic;
import java.sql.Timestamp;


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




}
