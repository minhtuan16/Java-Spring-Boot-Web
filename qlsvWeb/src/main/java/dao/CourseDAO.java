package dao;

import java.sql.SQLException;
import java.util.List;

import model.Course;

public interface CourseDAO {
	void add(Course c) throws SQLException;
	
	void update(Course c) throws SQLException;
	
	void delete(int id) throws SQLException;
	
	List<Course> selectAll() throws SQLException;
}
