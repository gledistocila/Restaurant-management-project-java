package model;

import java.time.LocalDate;
import java.util.Date;;

public class Reservation {
       private int reservationId;
       private int memberId;
       private int tableId;
              
	public int getReservationId() {
		return reservationId;
	}
	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public int getTableId() {
		return tableId;
	}
	public void setTableId(int tableId) {
		this.tableId = tableId;
	}
	
    public Reservation(int reservationId, int memberId, int tableId) {
		super();
		this.reservationId = reservationId;
		this.memberId = memberId;
		this.tableId = tableId;
		
    }
	public Reservation() {
    	
    }

}
