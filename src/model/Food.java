package model;

public class Food {
	   
	   private int foodId;
       private String foodName;
       private int foodPrice;
       private int categoryId;
       private int foodQuantity;
       
    public int getFoodId() {
   		return foodId;
   	}
   	public void setFoodId(int foodId) {
   		this.foodId = foodId;
   	}   
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
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public int getFoodQuantity() {
		return foodQuantity;
	}
	public void setFoodQuantity(int foodQuantity) {
		this.foodQuantity = foodQuantity;
	}
	
	@Override
	public String toString() {
		return "Ushqim: " + foodName + ", Cmimi: " + foodPrice;
	} 
	public Food() {
		
	}
	public Food(int foodId, String foodName, int foodPrice, int categoryId, int foodQuantity) {
		super();
		this.foodId = foodId;
		this.foodName = foodName;
		this.foodPrice = foodPrice;
		this.categoryId = categoryId;
		this.foodQuantity = foodQuantity;
	}
		
}
