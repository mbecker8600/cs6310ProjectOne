package edu.gatech.cs6310.projectOne.scheduler;

import java.util.List;

import edu.gatech.cs6310.projectOne.model.course.Course;
import edu.gatech.cs6310.projectOne.model.semester.Semester;
import edu.gatech.cs6310.projectOne.model.student.Student;
import gurobi.GRB;
import gurobi.GRBEnv;
import gurobi.GRBException;
import gurobi.GRBModel;
import gurobi.GRBVar;

public abstract class Scheduler {
	
	protected List<Student> students;
	protected List<Course> courses;
	protected List<Semester> semesters;
	protected GRBModel model;
	protected GRBEnv env;
	protected GRBVar[][][] yijk;

	public Scheduler(List<Student> students, List<Course> courses, List<Semester> semesters){
		try{
			env = new GRBEnv("mip1.log");
			env.set(GRB.IntParam.LogToConsole, 0);
			model = new GRBModel(env);
		}
		catch (GRBException e) {
			e.printStackTrace();
		}
		
		this.students = students;
		this.courses = courses;
		this.semesters = semesters;
	}
	
	public float optimize() throws GRBException{
		initializeGRBVar();
		setupObjective();
		setupConstraints();
		model.optimize();
		return (float) model.get(GRB.DoubleAttr.ObjVal);
	}
	
	private void initializeGRBVar() throws GRBException{
		yijk = new GRBVar[getStudents().size()][getCourses().size()][getSemesters().size()];
		for(int i=0;i<getStudents().size();i++){
			for(int j=0;j<getCourses().size();j++){
				for(int k=0;k<getSemesters().size();k++){
					yijk[i][j][k] = model.addVar(0, 1, 0.0, GRB.BINARY, 
							"Student " + getStudents().get(i).getId() + "_" +
							"Course " + getCourses().get(j).getName() + "_" +
							"Semester " + getSemesters().get(k).getName());
				}
			}
		}
		model.update();
	}
	
	private void printOutput() throws GRBException{
		for(int i=0;i<students.size();i++){
			for(int j=0;j<courses.size();j++){
				for(int k=0;k<semesters.size();k++){
					if (yijk[i][j][k].get(GRB.DoubleAttr.X) == 1) {
						System.out.printf("Student %d takes Course %d during Semester %d\n", 
								students.get(i).getId(), 
								courses.get(j).getId(), 
								semesters.get(k).getId());
					}
				}
			}
			System.out.println();
		}
		
	}
	
	protected abstract void setupObjective() throws GRBException;
	
	protected abstract void setupConstraints() throws GRBException;
	
	public List<Student> getStudents() {
		return students;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public List<Semester> getSemesters() {
		return semesters;
	}

	public GRBModel getModel() {
		return model;
	}

}
