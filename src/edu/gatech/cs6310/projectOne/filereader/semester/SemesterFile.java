package edu.gatech.cs6310.projectOne.filereader.semester;

import java.util.Date;

public class SemesterFile {
	private int id;
	private String semesterName;
	private Date startDate;
	private Date endDate;
	
	public int getId() {
		return id;
	}
	protected void setId(int id) {
		this.id = id;
	}
	public String getSemesterName() {
		return semesterName;
	}
	protected void setSemesterName(String semesterName) {
		this.semesterName = semesterName;
	}
	public Date getStartDate() {
		return startDate;
	}
	protected void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	protected void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}
