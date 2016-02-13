package edu.gatech.cs6310.projectOne.filereader.course.dependency;

import edu.gatech.cs6310.projectOne.filereader.ResourceFileReader;

public class CourseDependenciesFileReader extends ResourceFileReader<CourseDependecyFile>{

	@Override
	public CourseDependecyFile parseLine(String line) {
		String[] splitLine = line.split(DELIMITER);
		
		CourseDependecyFile courseDependecy = new CourseDependecyFile();
		courseDependecy.setPrereqCourseId(Integer.parseInt(splitLine[0]));
		courseDependecy.setDependentCourseId(Integer.parseInt(splitLine[1]));
		return courseDependecy;
	}
	
	@Override
	public String getFileLocation() {
		return "resources/static/course_dependencies.csv";
	}

}
