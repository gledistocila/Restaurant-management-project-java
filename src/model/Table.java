package model;

public class Table {
	
       private int tableID;
       private String tableName;
       
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
	public Table(int tableID, String tableName) {
		super();
		this.tableID = tableID;
		this.tableName = tableName;
	}

}
      
       
	