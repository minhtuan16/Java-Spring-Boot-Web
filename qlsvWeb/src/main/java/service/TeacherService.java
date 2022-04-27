package service;

import java.sql.SQLException;
import java.util.List;

import model.Teacher;

public interface TeacherService {

//	void input(Teacher t) throws SQLException;
//
//	void info(Teacher t) throws SQLException;

	void insert(Teacher t) throws SQLException;

	void update(Teacher t) throws SQLException;

	void delete(int id) throws SQLException;

	List<Teacher> read() throws SQLException;
}
