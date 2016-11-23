package dms.bean;

import java.sql.Timestamp;

public class DepartureSlot {
	private int id;
	private Timestamp scheduledPushbackTime;
	private Timestamp requiredPushbackTime;
	private Timestamp actualPushbackTime;
	private String gateId;
	private String status;
	private int aircraftId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Timestamp getScheduledPushbackTime() {
		return scheduledPushbackTime;
	}
	public void setScheduledPushbackTime(Timestamp scheduledPushbackTime) {
		this.scheduledPushbackTime = scheduledPushbackTime;
	}
	public Timestamp getRequiredPushbackTime() {
		return requiredPushbackTime;
	}
	public void setRequiredPushbackTime(Timestamp requiredPushbackTime) {
		this.requiredPushbackTime = requiredPushbackTime;
	}
	public Timestamp getActualPushbackTime() {
		return actualPushbackTime;
	}
	public void setActualPushbackTime(Timestamp actualPushbackTime) {
		this.actualPushbackTime = actualPushbackTime;
	}
	public String getGateId() {
		return gateId;
	}
	public void setGateId(String gateId) {
		this.gateId = gateId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getAircraftId() {
		return aircraftId;
	}
	public void setAircraftId(int aircraftId) {
		this.aircraftId = aircraftId;
	}
}