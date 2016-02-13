package edu.gatech.cs6310.projectOne.model.student;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.cs6310.projectOne.model.Model;
import edu.gatech.cs6310.projectOne.model.course.Course;

public class Student extends Model{
	
	private List<Course> courses;

	public Student(int id) {
		super(id);
	}
	
	public void addCourse(Course c){
		if(courses == null){
			courses = new ArrayList<Course>();
		}
		courses.add(c);
	}

	public List<Course> getCourses() {
		return courses;
	}
	
	public boolean needsCourse(Course c){
		boolean needsCourse = false;
		for(Course course: courses){
			if(course.equals(c)){
				needsCourse = true;
			}
			else{
				for(Course prereq: course.getPrerequisites()){
					if(prereq.equals(c)) {
						needsCourse = true;
					}
				}
			}
		}
		return needsCourse;
	}

}
