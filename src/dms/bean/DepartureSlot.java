package dms.bean;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

public class DepartureSlot {
	private int id;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
	private Timestamp scheduledPushbackTime;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
	private Timestamp requiredPushbackTime;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
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