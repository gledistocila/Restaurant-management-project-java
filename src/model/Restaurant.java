package model;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    
	private int restaurantId;
	private String restaurantName;
	private List<Member> members = new ArrayList<Member>();
	private List<Staff> employees = new ArrayList<Staff>();
	
	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public List<Staff> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Staff> employees) {
		this.employees = employees;
	}

	
	public List<Member> getMembers() {
		return members;
	}

	public void setMembers(List<Member> member) {
		this.members = members;
	}

	public Restaurant() {
		
	}
	public Restaurant(String restaurantName) {
		super();
		this.restaurantName = restaurantName;
	}

	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}
 
	
}
