package team.hustsoft.DA;

import team.hustsoft.basic.RepairRecord;
import com.mysql.jdbc.Driver;
import team.hustsoft.DA.DABase;

import java.util.*;
import java.sql.*;

public class RepairRecordDA extends DABase{
	public ArrayList<String> query_u(String search) {
     		ArrayList<String> records = new ArrayList<String>();
		conn = initialize();
		     String parttern;
		     if(search == null || search.equals("")) {
		       parttern = "\'%\'";
		     } else {
		       parttern = "%";
		       for (int i = 0; i < search.length(); i++) {
		         parttern += search.charAt(i);
		         parttern += "%";
		       }
		       parttern = "\'" + parttern + "\'";
		     }

		String sql = "select userName from User where userName like"+parttern+"and characters=\'3\';";
		ResultSet rs = null;
		RepairRecord repairRecord0  = null;
		try{
			rs = statement.executeQuery(sql);
			while(rs.next()) {
				String userName = rs.getString("userName");
				records.add(userName);
			}
		}
		catch (SQLException e) {
			System.out.println(e);
			return null;
		}
		finally{
			terminate();
		}
		return records;
	}
	public ArrayList<RepairRecord> query(int did) {
     		ArrayList<RepairRecord> records = new ArrayList<RepairRecord>();
		conn = initialize();
		String sql = "select * from RepairRecord where did=\'" + did + "\';";
		ResultSet rs = null;
		RepairRecord repairRecord0  = null;
		try{
			rs = statement.executeQuery(sql);
			while(rs.next()) {
				int rrid = rs.getInt("rrid");
				Timestamp distributeTime = rs.getTimestamp("distributeTime");
				String maintenance = rs.getString("maintenance");
				String detectionRecord  = rs.getString("detectionRecord");
				String repairRecord = rs.getString("repairRecord");
				Timestamp repairTime = rs.getTimestamp("repairTime");
				String workload = rs.getString("workload");
				String requiredPart = rs.getString("requiredPart");
				int status = rs.getInt("status");
				int delayDegree = rs.getInt("delayDegree");
				repairRecord0 = new RepairRecord(did,distributeTime,maintenance,detectionRecord,
						repairRecord,repairTime,workload,requiredPart,status,delayDegree);
				repairRecord0.setRrid(rrid);
				records.add(repairRecord0);
			}
		}
		catch (SQLException e) {
			System.out.println(e);
			return null;
		}
		finally{
			terminate();
		}
		return records;
	}
	public ArrayList<RepairRecord> query() {
     		ArrayList<RepairRecord> records = new ArrayList<RepairRecord>();
		conn = initialize();
		String sql = "select * from RepairRecord ORDER BY status;";
		ResultSet rs = null;
		RepairRecord repairRecord0  = null;
		try{
			rs = statement.executeQuery(sql);
			while(rs.next()) {
				int did = rs.getInt("did");
				int rrid = rs.getInt("rrid");
				Timestamp distributeTime = rs.getTimestamp("distributeTime");
				String maintenance = rs.getString("maintenance");
				String detectionRecord  = rs.getString("detectionRecord");
				String repairRecord = rs.getString("repairRecord");
				Timestamp repairTime = rs.getTimestamp("repairTime");
				String workload = rs.getString("workload");
				String requiredPart = rs.getString("requiredPart");
				int status = rs.getInt("status");
				int delayDegree = rs.getInt("delayDegree");
				repairRecord0 = new RepairRecord(did,distributeTime,maintenance,detectionRecord,
						repairRecord,repairTime,workload,requiredPart,status,delayDegree);
				repairRecord0.setRrid(rrid);
				records.add(repairRecord0);
			}
		}
		catch (SQLException e) {
			System.out.println(e);
			return null;
		}
		finally{
			terminate();
		}
		return records;
	}

	public ArrayList<RepairRecord> query(String ename) {
		if(ename == null){
			return null;
		}
     		ArrayList<RepairRecord> records = new ArrayList<RepairRecord>();
		conn = initialize();
		String sql = "select * from RepairRecord WHERE maintenance=\'"+ename+"\'' ORDER BY status;";
		ResultSet rs = null;
		RepairRecord repairRecord0  = null;
		try{
			rs = statement.executeQuery(sql);
			while(rs.next()) {
				int did = rs.getInt("did");
				int rrid = rs.getInt("rrid");
				Timestamp distributeTime = rs.getTimestamp("distributeTime");
				String maintenance = rs.getString("maintenance");
				String detectionRecord  = rs.getString("detectionRecord");
				String repairRecord = rs.getString("repairRecord");
				Timestamp repairTime = rs.getTimestamp("repairTime");
				String workload = rs.getString("workload");
				String requiredPart = rs.getString("requiredPart");
				int status = rs.getInt("status");
				int delayDegree = rs.getInt("delayDegree");
				repairRecord0 = new RepairRecord(did,distributeTime,maintenance,detectionRecord,
						repairRecord,repairTime,workload,requiredPart,status,delayDegree);
				repairRecord0.setRrid(rrid);
				records.add(repairRecord0);
			}
		}
		catch (SQLException e) {
			System.out.println(e);
			return null;
		}
		finally{
			terminate();
		}
		return records;
	}

	public int insert(RepairRecord rr){
		if(rr==null){
			return -1;
		}
		conn = initialize();
		int did = rr.getDid();
		Timestamp distributeTime = rr.getDistributeTime();
		String maintenance = rr.getMaintenance();
		String detectionRecord  = rr.getDetectionRecord();
		String repairRecord = rr.getRepairRecord();
		Timestamp repairTime = rr.getRepairTime();
		String workload = rr.getWorkload();
		String requiredPart = rr.getRequiredPart();
		int status = rr.getStatus();
		int delayDegree = rr.getDelayDegree();
		String sql = "INSERT INTO RepairRecord(did,distributeTime,maintenance,detectionRecord,"+
			"repairRecord,repairTime,workload,requiredPart,status,delayDegree) VALUES("+did+",\'"+distributeTime.toString()+"\',\'"+
			maintenance+"\',\'"+detectionRecord+"\',\'"+repairRecord+"\',\'"+repairTime.toString()+"\',\'"+workload+"\',\'"+
			requiredPart+"\',\'"+status+"\',\'"+delayDegree+"\');";
		try{
			statement.executeUpdate(sql);
		}
		catch(SQLException e){
			System.out.println(e);
			return -2;
		}
		finally{
			terminate();
		}
		return 1;
	}

	public int update(RepairRecord rr) {
		if (rr == null)
			return -1;
		int rrid =rr.getRrid();
		String sql0="SELECT rrid FROM RepairRecord WHERE rrid="+rrid+";";
		ResultSet rs = null;
		conn = initialize();
		try{
			rs = statement.executeQuery(sql0);
			if(!rs.next()){
				terminate();
				return -1;
			}
		}
		catch(SQLException e){
			System.out.println(e);//?
			return -2;
		}
		int did = rr.getDid();
		Timestamp distributeTime = rr.getDistributeTime();
		String maintenance = rr.getMaintenance();
		String detectionRecord  = rr.getDetectionRecord();
		String repairRecord = rr.getRepairRecord();
		Timestamp repairTime = rr.getRepairTime();
		String workload = rr.getWorkload();
		String requiredPart = rr.getRequiredPart();
		int status = rr.getStatus();
		int delayDegree = rr.getDelayDegree();
		String sql = "UPDATE RepairRecord SET did ="+did+",distributeTime=\'"+distributeTime.toString()+"\',maintenance=\'"+maintenance+
			"\',detectionRecord=\'"+detectionRecord+"\',repairRecord=\'"+repairRecord+"\',repairTime=\'"+repairTime.toString()+
			"\',workload=\'"+workload+"\',requiredPart=\'"+requiredPart+"\',status=\'"+status+"\',delayDegree=\'"+delayDegree+
			"\' WHERE rrid = "+rrid+";";
		try{
			statement.executeUpdate(sql);
		}
		catch(SQLException e){
			System.out.println(e);//?
			return -2;
		}
		finally{
			terminate();
		}
		return 1;
	}

	public  int delete(int rrid) {
		String sql0="SELECT rrid FROM RepairRecord WHERE rrid="+rrid+";";
		ResultSet rs = null;
		conn = initialize();
		try{
			rs = statement.executeQuery(sql0);
			if(!rs.next()){
				terminate();
				return -1;
			}
		}
		catch(SQLException e){
			System.out.println(e);//?
			return -2;
		}
		String sql = "DELETE FROM RepairRecord where rrid ="+rrid+";";
		//conn = initialize();
		try{
			statement.executeUpdate(sql);
		}
		catch(SQLException e){
			System.out.println(e);//?
			return -2;
		}
		finally{
			terminate();
		}
		return 1;



	}
}