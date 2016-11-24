package dms.bean;

public class RespResult {

	boolean success;
	
	public RespResult() {
		super();
	}
	public RespResult(boolean success) {
		this.success = success;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
}
