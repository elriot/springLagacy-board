package com.movie.domain;

public class TheatherVO {
	private String tt_num;
	private String tt_seatNum;
	private boolean isBooked;
	
	public boolean isBooked() {
		return isBooked;
	}
	public void setBooked(boolean isBooked) {
		this.isBooked = isBooked;
	}
	public String getTt_num() {
		return tt_num;
	}
	public void setTt_num(String tt_num) {
		this.tt_num = tt_num;
	}
	public String getTt_seatNum() {
		return tt_seatNum;
	}
	public void setTt_seatNum(String tt_seatNum) {
		this.tt_seatNum = tt_seatNum;
	}
	
}
