package dao;

import java.sql.SQLException;
import java.util.List;

import model.Clazz;

public interface ClazzDao {

	void insert(Clazz c) throws SQLException;

	void update(Clazz c) throws SQLException;

	void delete(int id) throws SQLException;

	List<Clazz> selectAll() throws SQLException;
}
