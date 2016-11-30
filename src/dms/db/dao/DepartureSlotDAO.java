package dms.db.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import dms.bean.DepartureSlot;

public class DepartureSlotDAO extends BasicDAO<DepartureSlot> {

	public static String TABLE = "DepartureSlot";

	public static class COLUMN {
		public static String ID = "slotId";
		public static String SCHEDULED_PUSHBACK_TIME = "scheduledPushbackTime";
		public static String REQUIRED_PUSHBACK_TIME = "requiredPushbackTime";
		public static String ACTUAL_PUSHBACK_TIME = "actualPushbackTime";
		public static String GATE_ID = "gateId";
		public static String STATUS = "status";
		public static String AIRCRAFT_ID = "aircraftId";
	}

	@Override
	public DepartureSlot get(int id) {
		return get("select * from `" + TABLE + "` where `" + COLUMN.ID + "` = ?;", id);
	}

	@Override
	public List<DepartureSlot> query() {
		return query("select * from `" + TABLE + "`;");
	}
	
	public List<DepartureSlot> query(String from, String to) {
		return query("select * from `" + TABLE + "`where `" + COLUMN.SCHEDULED_PUSHBACK_TIME + "` between ? and ? ;", from, to);
	}
	
	public List<DepartureSlot> query(Timestamp from, Timestamp to) {
		return query("select * from `" + TABLE + "`where `" + COLUMN.SCHEDULED_PUSHBACK_TIME + "` between ? and ? ;", from, to);
	}
	
	public List<DepartureSlot> queryByStatus(String status, String from, String to) {
		return query("select * from `" + TABLE + "`where `status` = ? and `" + COLUMN.SCHEDULED_PUSHBACK_TIME + "` between ? and ? ;", status, from, to);
	} 
	
	public List<DepartureSlot> queryByStatus(String status, Timestamp from, Timestamp to) {
		return query("select * from `" + TABLE + "`where `status` = ? and `" + COLUMN.SCHEDULED_PUSHBACK_TIME + "` between ? and ? ;", status, from, to);
	} 
	
	public List<DepartureSlot> queryByAircraft(int aircraftId) {
		return query("select * from `" + TABLE + "` where `" + COLUMN.AIRCRAFT_ID + "` = ?;", aircraftId);
	}
	
	public List<DepartureSlot> queryByAircraft(int aircraftId, String from, String to) {
		return query("select * from `" + TABLE + "` where `" + COLUMN.AIRCRAFT_ID + "` = ? and `" + COLUMN.SCHEDULED_PUSHBACK_TIME + "` between ? and ?;", aircraftId, from, to);
	}

	public List<DepartureSlot> queryByAircraftAndStatus(int aircraftId, String status, String from, String to){
		return query("select * from `" + TABLE + "` where `" + COLUMN.AIRCRAFT_ID + "` = ? and `status` = ? and `" + COLUMN.SCHEDULED_PUSHBACK_TIME + "` between ? and ?;", aircraftId, status, from, to);
	}
	
	@Override
	public boolean insert(DepartureSlot bean) {
		int id = insert("insert into `" + TABLE + "` ("
				+ " `" + COLUMN.SCHEDULED_PUSHBACK_TIME + "`"
				+ ",`" + COLUMN.REQUIRED_PUSHBACK_TIME + "`"
				+ ",`" + COLUMN.ACTUAL_PUSHBACK_TIME + "`"
				+ ",`" + COLUMN.GATE_ID + "`"
				+ ",`" + COLUMN.STATUS + "`"
				+ ",`" + COLUMN.AIRCRAFT_ID + "`"
				+ ") values(?, ?, ?, ?, ?, ?)", bean.getScheduledPushbackTime(), bean.getRequiredPushbackTime(), bean.getActualPushbackTime(), bean.getGateId(), bean.getStatus(),
				bean.getAircraftId());
		bean.setId(id);
		return true;
	}

	@Override
	public boolean update(DepartureSlot bean) {
		return update("update `" + TABLE + "` set "
				+ " `" + COLUMN.SCHEDULED_PUSHBACK_TIME + "`=?"
				+ ",`" + COLUMN.REQUIRED_PUSHBACK_TIME + "`=?"
				+ ",`" + COLUMN.ACTUAL_PUSHBACK_TIME + "`=?"
				+ ",`" + COLUMN.GATE_ID + "`=?"
				+ ",`" + COLUMN.STATUS + "`=?"
				+ ",`" + COLUMN.AIRCRAFT_ID + "`=?"
				+ " where `" + COLUMN.ID + "`=? ", bean.getScheduledPushbackTime(), bean.getRequiredPushbackTime(), bean.getActualPushbackTime(), bean.getGateId(), bean.getStatus(),
				bean.getAircraftId(), bean.getId());
	}

	@Override
	public boolean delete(DepartureSlot bean) {
		return delete("delete from `" + TABLE + "` where `" + COLUMN.ID + "`=? ", bean.getId());
	}

	@Override
	public boolean exist(DepartureSlot bean) {
		return get(bean.getId()) != null;
	}

	@Override
	protected DepartureSlot rowToObj(ResultSet rs) throws SQLException {
		DepartureSlot bean = new DepartureSlot();
		bean.setId(rs.getInt(COLUMN.ID));
		bean.setScheduledPushbackTime(rs.getTimestamp(COLUMN.SCHEDULED_PUSHBACK_TIME));
		bean.setRequiredPushbackTime(rs.getTimestamp(COLUMN.REQUIRED_PUSHBACK_TIME));
		bean.setActualPushbackTime(rs.getTimestamp(COLUMN.ACTUAL_PUSHBACK_TIME));
		bean.setGateId(rs.getString(COLUMN.GATE_ID));
		bean.setStatus(rs.getString(COLUMN.STATUS));
		bean.setAircraftId((Integer)rs.getObject(COLUMN.AIRCRAFT_ID));
		return bean;
	}

}
