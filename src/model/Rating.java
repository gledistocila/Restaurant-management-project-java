package model;

public class Rating {
       private int rateId;
       private String rateName;
       private int memberId;
       private int foodId;
       
	public int getRateId() {
		return rateId;
	}
	public void setRateId(int rateId) {
		this.rateId = rateId;
	}
	public String getRateName() {
		return rateName;
	}
	public void setRateName(String rateName) {
		this.rateName = rateName;
	}
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public int getFoodId() {
		return foodId;
	}
	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}
	public Rating(int rateId, String rateName, int memberId, int foodId) {
		super();
		this.rateId = rateId;
		this.rateName = rateName;
		this.memberId = memberId;
		this.foodId = foodId;
	}
       
       
	
}
