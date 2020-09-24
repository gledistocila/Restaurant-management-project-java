package model;

public class Order {
       private int orderId;
       private int total;
       private int staffId;
       private int memberId;
    
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getStaffId() {
		return staffId;
	}
	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	
    public Order(int orderId, int total, int staffId, int memberId) {
		super();
		this.orderId = orderId;
		this.total = total;
		this.staffId = staffId;
		this.memberId = memberId;
	}
	public Order() {
    	
    }
	
}
