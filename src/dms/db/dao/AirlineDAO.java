package dms.db.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dms.bean.Airline;

public class AirlineDAO extends BasicDAO<Airline> {

	public static String TABLE = "Airline";

	public static class COLUMN {
		public static String ID = "airlineId";
		public static String NAME = "name";
		public static String GATE_ID = "gateId";
	}

	@Override
	public Airline get(int id) {
		return get("select * from `" + TABLE + "` where `" + COLUMN.ID + "` = ?;", id);
	}

	@Override
	public List<Airline> query() {
		return query("select * from `" + TABLE + "`;");
	}

	@Override
	public boolean insert(Airline bean) {
		int id = insert("insert into `" + TABLE + "` ("
				+ " `" + COLUMN.NAME + "`"
				+ ",`" + COLUMN.GATE_ID + "`"
				+ ") values(?, ?)", bean.getName(), bean.getGateId());
		bean.setId(id);
		return true;
	}

	@Override
	public boolean update(Airline bean) {
		return update("update `" + TABLE + "` set "
				+ " `" + COLUMN.NAME + "`=?"
				+ ",`" + COLUMN.GATE_ID + "`=?"
				+ " where `" + COLUMN.ID + "`=? ", bean.getName(), bean.getGateId(), bean.getId());
	}

	@Override
	public boolean delete(Airline bean) {
		return delete("delete from `" + TABLE + "` where `" + COLUMN.ID + "`=? ", bean.getId());
	}

	@Override
	public boolean exist(Airline bean) {
		return get(bean.getId()) != null;
	}

	@Override
	protected Airline rowToObj(ResultSet rs) throws SQLException {
		Airline bean = new Airline();
		bean.setId(rs.getInt(COLUMN.ID));
		bean.setName(rs.getString(COLUMN.NAME));
		bean.setGateId(rs.getString(COLUMN.GATE_ID));
		return bean;
	}

}
