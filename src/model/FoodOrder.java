package model;

public class FoodOrder {
	
             private int numberPerTotal;
             private int foodId;
             private int orderId;
             
			public int getNumberPerTotal() {
				return numberPerTotal;
			}
			public void setNumberPerTotal(int numberPerTotal) {
				this.numberPerTotal = numberPerTotal;
			}
			public int getFoodId() {
				return foodId;
			}
			public void setFoodId(int foodId) {
				this.foodId = foodId;
			}
			public int getOrderId() {
				return orderId;
			}
			public void setOrderId(int orderId) {
				this.orderId = orderId;
			}
			
			public FoodOrder(int numberPerTotal, int foodId, int orderId) {
				super();
				this.numberPerTotal = numberPerTotal;
				this.foodId = foodId;
				this.orderId = orderId;
			}
             
            public FoodOrder() {
            	
            }
             
}
