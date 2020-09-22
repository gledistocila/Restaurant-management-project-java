package model;

public class Food {
	   
	   private int foodId;
       private String foodName;
       private int foodPrice;
       private String foodCategory;
       
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	public int getFoodPrice() {
		return foodPrice;
	}
	public void setFoodPrice(int foodPrice) {
		this.foodPrice = foodPrice;
	}
	
	public int getFoodId() {
		return foodId;
	}
	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}
	public String getFoodCategory() {
		return foodCategory;
	}
	public void setFoodCategory(String foodCategory) {
		this.foodCategory = foodCategory;
	}
	
	public Food(int foodId, String foodName, int foodPrice, String foodCategory) {
		super();
		this.foodId = foodId;
		this.foodName = foodName;
		this.foodPrice = foodPrice;
		this.foodCategory = foodCategory;
	}
	@Override
	public String toString() {
		return "Ushqim: " + foodName + ", Cmimi: " + foodPrice;
	} 
	public Food() {
		
	}
}
