package edu.gatech.cs6310.projectOne.model.semester;

import edu.gatech.cs6310.projectOne.model.Model;

public class Semester extends Model{
	
	private String name;
	
	public Semester(int id, String name) {
		super(id);
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
}
