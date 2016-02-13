package edu.gatech.cs6310.projectOne.model.course;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.cs6310.projectOne.filereader.ResourceFileReader;
import edu.gatech.cs6310.projectOne.filereader.course.CourseFile;
import edu.gatech.cs6310.projectOne.filereader.course.CourseFileReader;
import edu.gatech.cs6310.projectOne.filereader.course.dependency.CourseDependecyFile;
import edu.gatech.cs6310.projectOne.filereader.course.dependency.CourseDependenciesFileReader;
import edu.gatech.cs6310.projectOne.model.ModelBuilder;

public class CourseBuilder extends ModelBuilder<Course>{

	@Override
	protected List<Course> buildModel() {
		ResourceFileReader<CourseFile> courseFileReader = new CourseFileReader();
		List<CourseFile> fileCourses = courseFileReader.getContents();
		
		ResourceFileReader<CourseDependecyFile> courseDependencyFileReader = new CourseDependenciesFileReader();
		List<CourseDependecyFile> fileCourseDependencies = courseDependencyFileReader.getContents();
		
		List<Course> courses = buildCourses(fileCourses);
		addPrereqCourses(fileCourseDependencies, courses);
		
		return courses;
	}

	private void addPrereqCourses(List<CourseDependecyFile> fileCourseDependencies, List<Course> courses) {
		for(CourseDependecyFile prereq : fileCourseDependencies){
			Course dependentCourse = findById(prereq.getDependentCourseId(), courses);
			dependentCourse.addPrerequisite(findById(prereq.getPrereqCourseId(), courses));
		}
	}

	private List<Course> buildCourses(List<CourseFile> fileCourses) {
		List<Course> courses = new ArrayList<Course>(fileCourses.size());
		for(CourseFile fileObject : fileCourses){
			Course course = new Course(fileObject.getId(), fileObject.getCourseName());
			course.setFallAvailability(fileObject.isFallAvailability());
			course.setSpringAvailability(fileObject.isSpringAvailability());
			course.setSummerAvailability(fileObject.isSummerAvailability());
			courses.add(course);
		}
		return courses;
	}

}
