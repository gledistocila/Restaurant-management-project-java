package model;

public class Table {
	
       private int tableID;
       private String tableName;
       private int restaurantId;
       
	public int getTableID() {
		return tableID;
	}
	public void setTableID(int tableID) {
		this.tableID = tableID;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public int getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}
	public Table(int tableID, String tableName) {
		super();
		this.tableID = tableID;
		this.tableName = tableName;
	}
	public Table() {
		
	}
	public Table(int tableID, String tableName, int restaurantId) {
		super();
		this.tableID = tableID;
		this.tableName = tableName;
		this.restaurantId = restaurantId;
	}
    
}
      
       
	