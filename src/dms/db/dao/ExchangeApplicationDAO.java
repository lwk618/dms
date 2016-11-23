package dms.db.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dms.bean.ExchangeApplication;

public class ExchangeApplicationDAO extends BasicDAO<ExchangeApplication> {

	public static String TABLE = "ExchangeApplication";

	public static class COLUMN {
		public static String ID = "exchangeId";
		public static String TYPE = "type";
		public static String STATUS = "status";
		public static String USER_ID = "userId";
		public static String FROM_DEPARTURE_SLOT_ID = "fromDsId";
		public static String TO_DEPARTURE_SLOT_ID = "toDsId";
	}

	@Override
	public ExchangeApplication get(int id) {
		return get("select * from `" + TABLE + "` where `" + COLUMN.ID + "` = ?;", id);
	}

	@Override
	public List<ExchangeApplication> query() {
		return query("select * from `" + TABLE + "`;");
	}

	@Override
	public boolean insert(ExchangeApplication bean) {
		int id = insert("insert into `" + TABLE + "` ("
				+ " `" + COLUMN.TYPE + "`"
				+ ",`" + COLUMN.STATUS + "`"
				+ ",`" + COLUMN.USER_ID + "`"
				+ ",`" + COLUMN.FROM_DEPARTURE_SLOT_ID + "`"
				+ ",`" + COLUMN.TO_DEPARTURE_SLOT_ID + "`"
				+ ") values(?, ?, ?, ?, ?)",
				bean.getType(), bean.getStatus(), bean.getUserId(), bean.getFromDSId(), bean.getToDSId());
		bean.setId(id);
		return true;
	}

	@Override
	public boolean update(ExchangeApplication bean) {
		return update("update `" + TABLE + "` set "
				+ " `" + COLUMN.TYPE + "`=?"
				+ ",`" + COLUMN.STATUS + "`=?"
				+ ",`" + COLUMN.USER_ID + "`=?"
				+ ",`" + COLUMN.FROM_DEPARTURE_SLOT_ID + "`=?"
				+ ",`" + COLUMN.TO_DEPARTURE_SLOT_ID + "`=?"
				+ " where `" + COLUMN.ID + "`=? ", 
				bean.getType(), bean.getStatus(), bean.getUserId(), bean.getFromDSId(), bean.getToDSId(), bean.getId());
	}

	@Override
	public boolean delete(ExchangeApplication bean) {
		return delete("delete from `" + TABLE + "` where `" + COLUMN.ID + "`=? ", bean.getId());
	}

	@Override
	public boolean exist(ExchangeApplication bean) {
		return get(bean.getId()) != null;
	}

	@Override
	protected ExchangeApplication rowToObj(ResultSet rs) throws SQLException {
		ExchangeApplication bean = new ExchangeApplication();
		bean.setId(rs.getInt(COLUMN.ID));
		bean.setType(rs.getString(COLUMN.TYPE));
		bean.setStatus(rs.getString(COLUMN.STATUS));
		bean.setUserId(rs.getInt(COLUMN.USER_ID));
		bean.setFromDSId(rs.getInt(COLUMN.FROM_DEPARTURE_SLOT_ID));
		bean.setToDSId(rs.getInt(COLUMN.TO_DEPARTURE_SLOT_ID));
		return bean;
	}

}
