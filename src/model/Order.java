package model;

public class Order {
       private int orderId;
       private int memberId;
       private int[] foodId;
       private int[] quantity;
       private int total;
       
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	
	public int[] getFoodId() {
		return foodId;
	}
	public void setFoodId(int[] foodId) {
		this.foodId = foodId;
	}
	public int[] getQuantity() {
		return quantity;
	}
	public void setQuantity(int[] quantity) {
		this.quantity = quantity;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public Order(int orderId, int memberId, int[] foodId, int[] quantity, int total) {
		super();
		this.orderId = orderId;
		this.memberId = memberId;
		this.foodId = foodId;
		this.quantity = quantity;
		this.total = total;
	}
       
    public Order() {
    	
    }
	
}
