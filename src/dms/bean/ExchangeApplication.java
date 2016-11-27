package dms.bean;

public class ExchangeApplication {
	public static class TYPE{
		public static String DELAY = "d";
		public static String EARLY = "e";
	}
	
	public static class STATUS{
		public static String PENDING = "pending";
		public static String ACCEPTED = "accepted";
		public static String REJECTED = "rejected";
		public static String CANCEL = "cancel";
	}
	
	private int id;
	private String type;
	private String status;
	private int userId;
	private int fromDSId;
	private int toDSId;
	
//	private User user;
//	private DepartureSlot fromDS;
//	private DepartureSlot toDS;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getFromDSId() {
		return fromDSId;
	}
	public void setFromDSId(int fromDSId) {
		this.fromDSId = fromDSId;
	}
	public int getToDSId() {
		return toDSId;
	}
	public void setToDSId(int toDSId) {
		this.toDSId = toDSId;
	}
//	public User getUser() {
//		return user;
//	}
//	public void setUser(User user) {
//		this.user = user;
//	}
//	public DepartureSlot getFromDS() {
//		return fromDS;
//	}
//	public void setFromDS(DepartureSlot fromDS) {
//		this.fromDS = fromDS;
//	}
//	public DepartureSlot getToDS() {
//		return toDS;
//	}
//	public void setToDS(DepartureSlot toDS) {
//		this.toDS = toDS;
//	}
}
