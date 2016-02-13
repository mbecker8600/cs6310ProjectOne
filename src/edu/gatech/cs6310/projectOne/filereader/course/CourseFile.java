package edu.gatech.cs6310.projectOne.filereader.course;

public class CourseFile {
	
	private int id;
	private String courseName;
	private String courseNumber;
	private boolean fallAvailability;
	private boolean springAvailability;
	private boolean summerAvailability;
	private String availability;
	
	public int getId() {
		return id;
	}
	protected void setId(int id) {
		this.id = id;
	}
	public String getCourseName() {
		return courseName;
	}
	protected void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getCourseNumber() {
		return courseNumber;
	}
	protected void setCourseNumber(String courseNumber) {
		this.courseNumber = courseNumber;
	}
	public boolean isFallAvailability() {
		return fallAvailability;
	}
	protected void setFallAvailability(boolean fallAvailability) {
		this.fallAvailability = fallAvailability;
	}
	public boolean isSpringAvailability() {
		return springAvailability;
	}
	protected void setSpringAvailability(boolean springAvailability) {
		this.springAvailability = springAvailability;
	}
	public boolean isSummerAvailability() {
		return summerAvailability;
	}
	protected void setSummerAvailability(boolean summerAvailability) {
		this.summerAvailability = summerAvailability;
	}
	public String getAvailability() {
		return availability;
	}
	protected void setAvailability(String availability) {
		this.availability = availability;
	}

}
