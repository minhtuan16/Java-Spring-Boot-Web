package service;

import java.sql.SQLException;
import java.util.List;

import model.Course;

public interface CourseService {

//	void input(Course cs);
//	
//	void info(Course cs);
	
	// luu xuong file/database
	void insert(Course cs) throws SQLException;
	
	void update(Course cs) throws SQLException;
	
	void delete(int id) throws SQLException;
	
	List<Course> getAll() throws SQLException;
	
	
}
