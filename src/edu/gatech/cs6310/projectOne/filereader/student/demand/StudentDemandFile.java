package edu.gatech.cs6310.projectOne.filereader.student.demand;

public class StudentDemandFile {
	
	private int studentId;
	private int courseId;
	private int semesterId;
	
	public int getStudentId() {
		return studentId;
	}
	protected void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public int getCourseId() {
		return courseId;
	}
	protected void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public int getSemesterId() {
		return semesterId;
	}
	protected void setSemesterId(int semesterId) {
		this.semesterId = semesterId;
	}

}
