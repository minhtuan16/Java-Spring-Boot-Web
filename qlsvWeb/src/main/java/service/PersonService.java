package service;

import java.sql.SQLException;
import java.util.List;

import model.Person;

public interface PersonService {

//	void input(Person p);
//	
//	void info(Person p);
	
	void insert(Person p)throws SQLException;
	
	void update(Person p)throws SQLException;
	
	void delete(int id)throws SQLException;
	
	List<Person> getAll()throws SQLException;
}
