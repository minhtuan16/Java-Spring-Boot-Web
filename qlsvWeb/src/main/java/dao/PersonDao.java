package dao;

import java.sql.SQLException;
import java.util.List;

import model.Person;

public interface PersonDao {

	void insert(Person p)throws SQLException;
	
	void update(Person p)throws SQLException;
	
	void delete(int id)throws SQLException;
	
	List<Person> selectAll()throws SQLException;
	
}