package edu.gatech.cs6310.projectOne.scheduler;

import java.util.List;

import edu.gatech.cs6310.projectOne.model.course.Course;
import edu.gatech.cs6310.projectOne.model.semester.Semester;
import edu.gatech.cs6310.projectOne.model.student.Student;
import gurobi.GRB;
import gurobi.GRBException;
import gurobi.GRBLinExpr;
import gurobi.GRBVar;

public class MinimumClassCapacityScheduler extends Scheduler{
	
	private static int N_MAX = 2;
	private GRBVar x;
	
	public MinimumClassCapacityScheduler(List<Student> students,
			List<Course> courses, List<Semester> semesters) {
		super(students, courses, semesters);
	}
	
	@Override
	protected void setupObjective() throws GRBException {
		initializeXVar();
		initializeMinimumCourseCapacityObjective();
	}

	@Override
	protected void setupConstraints() throws GRBException{
		setupCourseAvailabilityConstraint();
		setupMaxCourseConstraint();
		setupCourseConstraint();
		setupPrerequisiteConstraint();
	}
	
	private void initializeXVar() throws GRBException{
		x = model.addVar(0, students.size(), 0.0, GRB.INTEGER, "Max Class Num");
		model.update();
	}
	
	private void initializeMinimumCourseCapacityObjective() throws GRBException{
		for(int j=0;j<courses.size();j++){
			for(int k=0;k<semesters.size();k++){
				GRBLinExpr courseCapacityObjective = new GRBLinExpr();
				for(int i=0;i<students.size();i++){
					courseCapacityObjective.addTerm(1, yijk[i][j][k]);
				}
				model.addConstr(courseCapacityObjective, GRB.LESS_EQUAL, x, "model constraint");
			}
		}
		GRBLinExpr xExpression = new GRBLinExpr();
		xExpression.addTerm(1, x);
		model.setObjective(xExpression,GRB.MINIMIZE);
	}
	
	private void setupCourseAvailabilityConstraint() throws GRBException {
		for(int j=0;j<courses.size();j++){
			for(int k=0;k<semesters.size();k++){
				if(!courses.get(j).isAvailable(semesters.get(k))){
					GRBLinExpr courseAvailabilityConstraint = new GRBLinExpr();
					for(int i=0;i<students.size();i++){
						courseAvailabilityConstraint.addTerm(1, yijk[i][j][k]);
					}
					model.addConstr(courseAvailabilityConstraint, GRB.EQUAL, 0, "Student ");
				}
			}
		}
		
	}

	private void setupMaxCourseConstraint() throws GRBException {
		for(int i=0;i<students.size();i++){
			for(int k=0;k<semesters.size();k++){
				GRBLinExpr maxCoursesConstraint = new GRBLinExpr();
				for(int j=0;j<courses.size();j++){
					maxCoursesConstraint.addTerm(1, yijk[i][j][k]);
				}
				String courseName = "MAXCOURSE_Student" + students.get(i).getId() + "_Semester" + semesters.get(k).getName();
				model.addConstr(maxCoursesConstraint, GRB.LESS_EQUAL, N_MAX, courseName);
			}
		}
	}
	
	private void setupCourseConstraint() throws GRBException {
		for(int i=0;i<students.size();i++){
			for(int j=0;j<courses.size();j++){
				GRBLinExpr courseConstraint = new GRBLinExpr();
				for(int k=0;k<semesters.size();k++){
					courseConstraint.addTerm(1, yijk[i][j][k]);
				}
				String course = "COURSE_Student" + students.get(i).getId() + "_Course" + courses.get(j).getName();
				if(students.get(i).needsCourse(courses.get(j))){
					model.addConstr(courseConstraint, GRB.EQUAL, 1, course);
				}
				else{
					model.addConstr(courseConstraint, GRB.EQUAL, 0, course);
				}
			}
		}
	}
	
	private void setupPrerequisiteConstraint() throws GRBException {
		for(int i=0;i<students.size();i++){
			for(int j=0;j<courses.size();j++){
				if(courses.get(j).getPrerequisites() != null || !courses.get(j).getPrerequisites().isEmpty()){
					GRBLinExpr dependentClassConstraint = new GRBLinExpr();
					
					//setup dependent course constraint
					for(int k=0;k<semesters.size();k++){
						dependentClassConstraint.addTerm(k+1, yijk[i][j][k]);
					}
					
					//setup prereq course constraint
					GRBLinExpr prereqConstraint = new GRBLinExpr();
					for(int m=0;m<courses.get(j).getPrerequisites().size();m++){
						for(int k=0;k<semesters.size();k++){
							dependentClassConstraint.addTerm(k+1, yijk[i][m][k]);
						}
					}
					model.addConstr(prereqConstraint, GRB.LESS_EQUAL, dependentClassConstraint, "prereq_constraint");
				}
			}
		}
	}

}
