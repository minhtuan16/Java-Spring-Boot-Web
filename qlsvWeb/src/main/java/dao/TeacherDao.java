package dao;

import java.sql.SQLException;
import java.util.List;

import model.Teacher;

public interface TeacherDao {

	void insert(Teacher t) throws SQLException;

	void update(Teacher t) throws SQLException;

	void delete(int id) throws SQLException;

	List<Teacher> selectAll() throws SQLException;

}