package edu.gatech.cs6310.projectOne;

import java.util.List;

import edu.gatech.cs6310.projectOne.model.ModelBuilder;
import edu.gatech.cs6310.projectOne.model.course.Course;
import edu.gatech.cs6310.projectOne.model.course.CourseBuilder;
import edu.gatech.cs6310.projectOne.model.semester.Semester;
import edu.gatech.cs6310.projectOne.model.semester.SemesterBuilder;
import edu.gatech.cs6310.projectOne.model.student.Student;
import edu.gatech.cs6310.projectOne.model.student.StudentBuilder;
import edu.gatech.cs6310.projectOne.scheduler.MinimumClassCapacityScheduler;
import edu.gatech.cs6310.projectOne.scheduler.Scheduler;
import gurobi.GRBException;

public class ProjectOne {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String fileInput = "";
		
		//initialize args
		for(int i=0; i<args.length; i++){
			String arg = args[i];
			if(arg.equals("-i")){
				fileInput = args[++i];
			}
		}

		//build student models from given file
		ModelBuilder<Student> studentBuilder = new StudentBuilder(fileInput);
		List<Student> students = studentBuilder.list();
		
		//build course models from static files 
		ModelBuilder<Course> courseBuilder = new CourseBuilder();
		List<Course> courses = courseBuilder.list();
		
		//build semesters models from static files
		ModelBuilder<Semester> semesterBuilder = new SemesterBuilder();
		List<Semester> semesters = semesterBuilder.list();
		
		Scheduler scheduler = new MinimumClassCapacityScheduler(students, courses, semesters);
		try {
			float result = scheduler.optimize();
			System.out.printf("X=%.2f", result);
		} catch (GRBException e) {
			e.printStackTrace();
		}
		
	}

}
