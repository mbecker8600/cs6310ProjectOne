package edu.gatech.cs6310.projectOne.model.student;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.cs6310.projectOne.filereader.ResourceFileReader;
import edu.gatech.cs6310.projectOne.filereader.student.demand.StudentDemandFile;
import edu.gatech.cs6310.projectOne.filereader.student.demand.StudentDemandFileReader;
import edu.gatech.cs6310.projectOne.model.ModelBuilder;
import edu.gatech.cs6310.projectOne.model.course.Course;
import edu.gatech.cs6310.projectOne.model.course.CourseBuilder;

public class StudentBuilder extends ModelBuilder<Student>{
	
	List<Student> students = new ArrayList<Student>();
	String fileLocation;
	
	
	public StudentBuilder(String fileLocation){
		this.fileLocation = fileLocation;
	}

	@Override
	protected List<Student> buildModel() {
		ResourceFileReader<StudentDemandFile> studentDemandFileReader = new StudentDemandFileReader();
		List<StudentDemandFile> fileStudentDemand = studentDemandFileReader.getContents(fileLocation);
		
		
		students = new ArrayList<Student>();
		for(StudentDemandFile fileObject : fileStudentDemand){
			Student student = findById(fileObject.getStudentId(), students);
			if(student == null){
				student = new Student(fileObject.getStudentId());
				students.add(student);
			}
			
			ModelBuilder<Course> courseBuilder = new CourseBuilder();
			Course course = courseBuilder.findById(fileObject.getCourseId());
			student.addCourse(course);
			
		}
		return students;
	}
}
