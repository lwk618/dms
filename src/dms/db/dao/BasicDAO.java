/**
 * 
 */
package dms.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dms.db.DBConnector;

/**
 * @author Kit
 *
 */
public abstract class BasicDAO<T> implements DAOInterface<T> {

	protected int insert(String sql, Object... args) {
		try (Connection conn = DBConnector.getDBConnection();
				PreparedStatement statement = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
			for (int i = 0; i < args.length; i++) {
				statement.setObject(i + 1, args[i]);
			}
			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			int id = 0;
			if (rs.next()) {
				id = rs.getInt(1);
			}
			return id;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	protected boolean update(String sql, Object... args) {
		try (Connection conn = DBConnector.getDBConnection();
				PreparedStatement statement = conn.prepareStatement(sql)) {
			for (int i = 0; i < args.length; i++) {
				statement.setObject(i + 1, args[i]);
			}
			statement.executeUpdate();
			return true;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	protected boolean delete(String sql, Object... args) {
		return update(sql, args);
	}

	protected T get(String sql, Object... args) {
		try (Connection conn = DBConnector.getDBConnection();
				PreparedStatement statement = conn.prepareStatement(sql)) {
			for (int i = 0; i < args.length; i++) {
				statement.setObject(i + 1, args[i]);
			}
			ResultSet rs = statement.executeQuery();

			T data = null;
			if (rs.next()) {
				data = rowToObj(rs);
			}
			return data;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	protected List<T> query(String sql, Object... args) {
		try (Connection conn = DBConnector.getDBConnection();
				PreparedStatement statement = conn.prepareStatement(sql)) {
			for (int i = 0; i < args.length; i++) {
				statement.setObject(i + 1, args[i]);
			}
			ResultSet rs = statement.executeQuery();

			List<T> dataList = new ArrayList<>();
			while (rs.next()) {
				dataList.add(rowToObj(rs));
			}
			return dataList;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	protected abstract T rowToObj(ResultSet rs) throws SQLException;
}
