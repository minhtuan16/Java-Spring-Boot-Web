package dao;

import java.sql.SQLException;
import java.util.List;

import model.Student;

public interface StudentDao {

	void insert(Student s) throws SQLException;

	void update(Student s) throws SQLException;

	void delete(int person_id) throws SQLException;

	List<Student> selectAll() throws SQLException;

}