package service;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import dao.PersonDao;
import dao.PersonDaoIMPL;
import model.Person;

public class PersonServiceIMPL implements PersonService {

//	@Override
//	public void input(Person p) {
//		
//		System.out.println("Nhap thong tin: ");
//		System.out.println("Nhap id: ");
//		p.setId(new Scanner(System.in).nextInt());
//
//		System.out.println("Nhap name:");
//		p.setName(new Scanner(System.in).nextLine());
//		
//		System.out.println("Nhap address:");
//		p.setAddress(new Scanner(System.in).nextLine());
//		
//	}
//
//	@Override
//	public void info(Person p) {
//
//		System.out.println("Danh sach: ");
//		System.out.println("ID: " + p.getId());
//		System.out.println("Name: " + p.getName());
//		System.out.println("Address: " + p.getAddress());
//	}

	@Override
	public void insert(Person p) throws SQLException{
		
		PersonDao dao = new PersonDaoIMPL();
		dao.insert(p);
	}

	@Override
	public void update(Person p) throws SQLException{
		
		PersonDao dao = new PersonDaoIMPL();
		dao.update(p);
	}

	@Override
	public void delete(int id) throws SQLException{
		
		PersonDao dao = new PersonDaoIMPL();
		dao.delete(id);
	}

	@Override
	public List<Person> getAll() throws SQLException{

		PersonDao dao = new PersonDaoIMPL();
		return dao.selectAll();
	}

}
