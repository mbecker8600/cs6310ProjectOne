package edu.gatech.cs6310.projectOne.model.course;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.cs6310.projectOne.model.Model;
import edu.gatech.cs6310.projectOne.model.semester.Semester;

public class Course extends Model{

	private String name;
	private List<Course> prerequisites;
	private boolean springAvailability;
	private boolean fallAvailability;
	private boolean summerAvailability;
	
	public Course(int id, String name) {
		super(id);
		this.name = name;
		prerequisites = new ArrayList<Course>();
	}
	
	public void addPrerequisite(Course c){
		prerequisites.add(c);
	}

	public List<Course> getPrerequisites() {
		return prerequisites;
	}

	public String getName() {
		return name;
	}
	
	public boolean isAvailable(Semester s){
		boolean isAvailable = false;
		if(s.getName().contains("Fall") && fallAvailability){
			isAvailable = true;
		}
		else if(s.getName().contains("Summer") && summerAvailability){
			isAvailable = true;
		}
		else if(s.getName().contains("Spring") && springAvailability){
			isAvailable = true;
		}
		return isAvailable;
	}
	
	protected boolean isSpringAvailability() {
		return springAvailability;
	}

	protected void setSpringAvailability(boolean springAvailability) {
		this.springAvailability = springAvailability;
	}

	protected boolean isFallAvailability() {
		return fallAvailability;
	}

	protected void setFallAvailability(boolean fallAvailability) {
		this.fallAvailability = fallAvailability;
	}

	protected boolean isSummerAvailability() {
		return summerAvailability;
	}

	protected void setSummerAvailability(boolean summerAvailability) {
		this.summerAvailability = summerAvailability;
	}

}
