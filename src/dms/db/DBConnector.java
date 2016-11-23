package dms.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBConnector {
	public static Connection conn = null;
	public static DataSource ds = null;

	public static void initDataSource() {
		try {
			if (ds == null) {
				// For JBoss 7.1.1
				ds = (DataSource) (new InitialContext()).lookup("java:jboss/datasources/dms");
			}
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public static void init() {
		if (ds == null) {
			initDataSource();
		}
	}

	public static void disconnect() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static Connection getDBConnection() {
		try {
			if (ds == null) {
				initDataSource();
			}
			return ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String getNextSequence(String tableName) {
		init();
		String sql = "select nextval('" + tableName + "');";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			String sequenceValue = rs.getString("nextval");
			disconnect();
			return sequenceValue;
		} catch (SQLException e) {
			e.printStackTrace();
			return "";
		}
	}
}
