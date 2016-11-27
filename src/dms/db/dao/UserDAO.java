package dms.db.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dms.bean.User;

public class UserDAO extends BasicDAO<User> {

	public static String TABLE = "User";

	public static class COLUMN {
		public static String ID = "userId";
		public static String LOGIN_ID = "loginId";
		public static String PASSWORD = "password";
		public static String TYPE = "type";
		public static String AIRLINE_ID = "airlineId";
		public static String STATUS = "status";
		public static String LAST_LOGIN = "lastLogin";
	}

	@Override
	public User get(int id) {
		return get("select * from `" + TABLE + "` where `" + COLUMN.ID + "` = ?;", id);
	}
	
	public User getByLoginId(String loginId){
		return get("select * from `" + TABLE + "` where `" + COLUMN.LOGIN_ID + "` = ?;", loginId);
	}
	
	@Override
	public List<User> query() {
		return query("select * from `" + TABLE + "`;");
	}

	@Override
	public boolean insert(User bean) {
		System.out.println("airlineId:"+bean.getAirlineId());
		int id = insert("insert into `" + TABLE + "` ("
				+ " `" + COLUMN.LOGIN_ID + "`"
				+ ", `" + COLUMN.PASSWORD + "`"
				+ ", `" + COLUMN.TYPE + "`"
				+ ", `" + COLUMN.AIRLINE_ID + "`"
				+ ", `" + COLUMN.STATUS + "`"
				+ ", `" + COLUMN.LAST_LOGIN + "`"
				+ ") values(?, ?, ?, ?, ?, ?)",
				bean.getLoginId(), bean.getPassword(), bean.getType(), bean.getAirlineId(),
				bean.getStatus(), bean.getLastLogin());
		bean.setId(id);
		return true;
	}

	@Override
	public boolean update(User bean) {
		return update("update `" + TABLE + "` set "
				+ " `" + COLUMN.LOGIN_ID + "`=?"
				+ ",`" + COLUMN.PASSWORD + "`=?"
				+ ",`" + COLUMN.TYPE + "`=?"
				+ ",`" + COLUMN.AIRLINE_ID + "`=?"
				+ ",`" + COLUMN.STATUS + "`=?"
				+ ",`" + COLUMN.LAST_LOGIN + "`=?"
				+ " where `" + COLUMN.ID + "`=? ",
				bean.getLoginId(), bean.getPassword(), bean.getType(), bean.getAirlineId(),
				bean.getStatus(), bean.getLastLogin(), bean.getId());
	}

	@Override
	public boolean delete(User bean) {
		return delete("delete from `" + TABLE + "` where `" + COLUMN.ID + "`=? ", bean.getId());
	}

	@Override
	public boolean exist(User bean) {
		return get(bean.getId()) != null;
	}

	@Override
	protected User rowToObj(ResultSet rs) throws SQLException {
		User bean = new User();
		bean.setId(rs.getInt(COLUMN.ID));
		bean.setLoginId(rs.getString(COLUMN.LOGIN_ID));
		bean.setPassword(rs.getString(COLUMN.PASSWORD));
		bean.setType(rs.getString(COLUMN.TYPE));
		bean.setAirlineId(rs.getInt(COLUMN.AIRLINE_ID));
		bean.setStatus(rs.getString(COLUMN.STATUS));
		bean.setLastLogin(rs.getTimestamp(COLUMN.LAST_LOGIN));
		return bean;
	}

}
