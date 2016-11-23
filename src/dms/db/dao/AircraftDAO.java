package dms.db.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.bouncycastle.util.test.Test;

import com.fasterxml.jackson.databind.ser.std.StaticListSerializerBase;

import dms.bean.Aircraft;

public abstract class AircraftDAO extends BasicDAO<Aircraft> {

	public static String TABLE = "Aircraft";

	public static class COLUMN {
		public static String ID = "aircraftId";
		public static String AIRLINE_ID = "airlineId";
		public static String CODE = "code";
	}

	@Override
	public Aircraft get(int id) {
		return get("select * from `" + TABLE + "` where `" + COLUMN.ID + "` = ?;", id);
	}

	@Override
	public List<Aircraft> query() {
		return query("select * from `" + TABLE + "`;");
	}

	@Override
	public boolean insert(Aircraft bean) {
		int id = insert("insert into `" + TABLE + "` ("
				+ " `" + COLUMN.AIRLINE_ID + "`"
				+ ",`" + COLUMN.CODE + "`"
				+ ") values(?, ?)", bean.getAirlineId(), bean.getCode());
		bean.setId(id);
		return true;
	}

	@Override
	public boolean update(Aircraft bean) {
		return update("update `" + TABLE + "` set "
				+ " `" + COLUMN.AIRLINE_ID + "`=?"
				+ ",`" + COLUMN.CODE + "`=?"
				+ " where `" + COLUMN.ID + "`=? ", bean.getAirlineId(), bean.getCode(), bean.getId());
	}

	@Override
	public boolean delete(Aircraft bean) {
		return delete("delete from `" + TABLE + "` where `" + COLUMN.ID + "`=? ", bean.getId());
	}

	@Override
	public boolean exist(Aircraft bean) {
		return get(bean.getId()) != null;
	}

	@Override
	protected Aircraft rowToObj(ResultSet rs) throws SQLException {
		Aircraft bean = new Aircraft();
		bean.setId(rs.getInt(COLUMN.ID));
		bean.setAirlineId(rs.getInt(COLUMN.AIRLINE_ID));
		bean.setCode(rs.getString(COLUMN.CODE));
		return bean;
	}

}
