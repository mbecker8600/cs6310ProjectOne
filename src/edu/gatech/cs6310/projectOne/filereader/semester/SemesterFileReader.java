package edu.gatech.cs6310.projectOne.filereader.semester;

import edu.gatech.cs6310.projectOne.filereader.ResourceFileReader;

public class SemesterFileReader extends ResourceFileReader<SemesterFile> {
	
	@Override
	public SemesterFile parseLine(String line) {
		String[] splitLine = line.split(DELIMITER);
		
		SemesterFile semester = new SemesterFile();
		semester.setId(Integer.parseInt(splitLine[0]));
		semester.setSemesterName(splitLine[1]);
		return semester;
	}

	@Override
	public String getFileLocation() {
		return "resources/static/semesters.csv";
	}

}
