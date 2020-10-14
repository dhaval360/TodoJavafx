package model;

import java.time.LocalDate;

public class taskObject {
	
	private String title;
	private String discription;
	private String startDate;
	private String endDate;
	private int preference;
	
	public taskObject(String title, String discription, String startDate,String endDate, int preference) {
		this.title = title;
		this.startDate = startDate;
		this.discription = discription;
		this.endDate = endDate;
		this.preference = preference;
	}
	
	public taskObject() {
		// TODO Auto-generated constructor stub
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setDiscription(String discription) {
		this.discription = discription;
	}
	
	public void setStartDate(String date) {
		this.startDate = date;
	}
	
	public void setEndDate(String date) {
		this.endDate = date;
	}
	
	public void setPreference(int p) {
		this.preference = p;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getDiscription() {
		return discription;
	}
	
	public String getEndDate() {
		return endDate;
	}
	
	public String getStartDate() {
		return startDate;
	}
	
	public int getPreference() {
		return preference;
	}

}
