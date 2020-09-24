package model;

public class Restaurant {
    
	private int restaurantId;
	private String restaurantName;
	
	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}
	
	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public Restaurant() {
		
	}
	
	public Restaurant(String restaurantName) {
		super();
		this.restaurantName = restaurantName;
	}

	public Restaurant(int restaurantId, String restaurantName) {
		super();
		this.restaurantId = restaurantId;
		this.restaurantName = restaurantName;
	}

}