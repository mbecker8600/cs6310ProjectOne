package edu.gatech.cs6310.projectOne.model.semester;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.cs6310.projectOne.filereader.ResourceFileReader;
import edu.gatech.cs6310.projectOne.filereader.semester.SemesterFile;
import edu.gatech.cs6310.projectOne.filereader.semester.SemesterFileReader;
import edu.gatech.cs6310.projectOne.model.ModelBuilder;

public class SemesterBuilder extends ModelBuilder<Semester>{

	@Override
	protected List<Semester> buildModel() {
		ResourceFileReader<SemesterFile> semesterFileReader = new SemesterFileReader();
		List<SemesterFile> fileSemesters = semesterFileReader.getContents();
		List<Semester> semesters = new ArrayList<Semester>(fileSemesters.size());
		for(SemesterFile fileObject : fileSemesters){
			Semester semester = new Semester(fileObject.getId(), fileObject.getSemesterName());
			semesters.add(semester);
		}
		return semesters;
	}

}
