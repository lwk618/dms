/**
 * 
 */
package dms.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import dms.bean.DepartureSlot;
import dms.db.DBConnector;

/**
 * @author Kit
 *
 */
public class ReportDAO {
	public Map<String, Integer> getReportGroupByHour(String from, String to) {
		try (Connection conn = DBConnector.getDBConnection()) {
			String sql = "SELECT date_format(`actualPushbackTime`, '%Y-%m-%d %H:00:00' ) as time, count(*) as counter "
					+ " FROM `DepartureSlot` "
					+ " WHERE `status` = 'departure' "
					+ " and scheduledPushbackTime between ? and ? "
					+ " group by HOUR(`actualPushbackTime`);";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, from);
			statement.setString(2, to);
			ResultSet rs = statement.executeQuery();
			Map<String, Integer> dataMap = new HashMap<>();
			while (rs.next()) {
				dataMap.put(rs.getString("time"), rs.getInt("counter"));
			}
			return dataMap;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Map<String, Integer> getReportGroupByMonth(String from, String to) {
		try (Connection conn = DBConnector.getDBConnection()) {
			String sql = "SELECT date_format(`actualPushbackTime`, '%Y-%m' ) as time, count(*) as counter "
					+ " FROM `DepartureSlot` "
					+ " WHERE `status` = 'departure' "
					+ " and scheduledPushbackTime between ? and ? "
					+ " group by MONTH(`actualPushbackTime`);";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, from);
			statement.setString(2, to);
			ResultSet rs = statement.executeQuery();
			Map<String, Integer> dataMap = new HashMap<>();
			while (rs.next()) {
				dataMap.put(rs.getString("time"), rs.getInt("counter"));
			}
			return dataMap;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	public int getTotalDeparture(int airlineId, String from, String to) {
		try (Connection conn = DBConnector.getDBConnection()) {
			String sql = "select count(*) as counter from DepartureSlot "
					+ " where "
					+ " status in('departure', 'cancel')"
					+ " and scheduledPushbackTime between ? and ? "
					+ " and aircraftId in (select aircraftId from aircraft where airlineId = ?) ";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, from);
			statement.setString(2, to);
			statement.setInt(3, airlineId);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				return rs.getInt("counter");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int getOnTimeDeparture(int airlineId, String from, String to) {
		try (Connection conn = DBConnector.getDBConnection()) {
			String sql = "select count(*) as counter from DepartureSlot "
					+ " where "
					+ " status = 'departure' "
					+ " and scheduledPushbackTime between ? and ? "
					+ " and aircraftId in (select aircraftId from aircraft where airlineId = ?) "
					+ " and slotId not in (select toDsId from exchangeapplication where `type` = 'delay' and `status` = `accepted`)";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, from);
			statement.setString(2, to);
			statement.setInt(3, airlineId);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				return rs.getInt("counter");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int getDelayedDeparture(int airlineId, String from, String to) {
		try (Connection conn = DBConnector.getDBConnection()) {
			String sql = "select count(*) as counter from DepartureSlot "
					+ " where "
					+ " status = 'departure' "
					+ " and scheduledPushbackTime between ? and ? "
					+ " and aircraftId in (select aircraftId from aircraft where airlineId = ?) "
					+ " and slotId in (select toDsId from exchangeapplication where `type` = 'delay' and `status` = `accepted`)";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, from);
			statement.setString(2, to);
			statement.setInt(3, airlineId);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				return rs.getInt("counter");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	
	public int getCancellationDeparture(int airlineId, String from, String to) {
		try (Connection conn = DBConnector.getDBConnection()) {
			String sql = "select count(*) as counter from DepartureSlot "
					+ " where "
					+ " status = 'cancel' "
					+ " and scheduledPushbackTime between ? and ? "
					+ " and aircraftId in (select aircraftId from aircraft where airlineId = ?) ";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, from);
			statement.setString(2, to);
			statement.setInt(3, airlineId);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				return rs.getInt("counter");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

}
