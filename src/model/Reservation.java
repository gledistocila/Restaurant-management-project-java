package model;

import java.time.LocalDate;;

public class Reservation {
       private int reservationId;
       private int memberId;
       private int tableId;
       private LocalDate reservationDate;
       
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
	public LocalDate getReservationDate() {
		return reservationDate;
	}
	public void setReservationDate(LocalDate reservationDate) {
		this.reservationDate = reservationDate;
	}
	public Reservation(int reservationId, int memberId, int tableId, LocalDate reservationDate) {
		super();
		this.reservationId = reservationId;
		this.memberId = memberId;
		this.tableId = tableId;
		this.reservationDate = reservationDate;
	}
    public Reservation() {
    	
    }

}
