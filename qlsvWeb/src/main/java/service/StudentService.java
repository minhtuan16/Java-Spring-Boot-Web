package service;

import java.sql.SQLException;
import java.util.List;

import model.Student;

public interface StudentService {

//	void input(Student p) throws SQLException;
//
//	void info(Student p) throws SQLException;

	void insert(Student p) throws SQLException;

	void update(Student p) throws SQLException;

	void delete(int person_id) throws SQLException;

	List<Student> read() throws SQLException;
}
