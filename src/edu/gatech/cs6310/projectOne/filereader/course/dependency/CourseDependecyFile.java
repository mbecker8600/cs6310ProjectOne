package edu.gatech.cs6310.projectOne.filereader.course.dependency;

public class CourseDependecyFile {
	private int prereqCourseId;
	private int dependentCourseId;
	
	public int getPrereqCourseId() {
		return prereqCourseId;
	}
	protected void setPrereqCourseId(int prereqCourseId) {
		this.prereqCourseId = prereqCourseId;
	}
	public int getDependentCourseId() {
		return dependentCourseId;
	}
	protected void setDependentCourseId(int dependentCourseId) {
		this.dependentCourseId = dependentCourseId;
	}
}
