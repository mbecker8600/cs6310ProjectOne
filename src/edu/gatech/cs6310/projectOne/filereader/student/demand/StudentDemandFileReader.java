package edu.gatech.cs6310.projectOne.filereader.student.demand;

import edu.gatech.cs6310.projectOne.filereader.ResourceFileReader;

public class StudentDemandFileReader extends ResourceFileReader<StudentDemandFile> {

	@Override
	public StudentDemandFile parseLine(String line) {
		String[] splitLine = line.split(DELIMITER);
		
		StudentDemandFile student = new StudentDemandFile();
		student.setStudentId(Integer.parseInt(splitLine[0]));
		student.setCourseId(Integer.parseInt(splitLine[1]));
		student.setSemesterId(Integer.parseInt(splitLine[2]));
		return student;
	}
	
	@Override
	public String getFileLocation() {
		return "";
	}
	
}
