package edu.gatech.cs6310.projectOne.filereader.course;

import edu.gatech.cs6310.projectOne.filereader.ResourceFileReader;

public class CourseFileReader extends ResourceFileReader<CourseFile> {
	
	@Override
	public CourseFile parseLine(String line) {
		String[] splitLine = line.split(DELIMITER);
		
		CourseFile course = new CourseFile();
		course.setId(Integer.parseInt(splitLine[0]));
		course.setCourseName(splitLine[1]);
		course.setCourseNumber(splitLine[2]);
		course.setFallAvailability(parseBinaryBoolean(splitLine[3]));
		course.setSpringAvailability(parseBinaryBoolean(splitLine[4]));
		course.setSummerAvailability(parseBinaryBoolean(splitLine[5]));
		course.setAvailability(splitLine[6]);
		return course;
	}
	
	private boolean parseBinaryBoolean(String s){
		if(Integer.parseInt(s) == 0) return false;
		else return true;
	}

	@Override
	public String getFileLocation() {
		return "resources/static/courses.csv";
	}

}
