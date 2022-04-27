package service;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import dao.PersonDao;
import dao.PersonDaoIMPL;
import dao.StudentDao;
import dao.StudentDaoIMPL;
import model.Student;

public class StudentServiceIMPL implements StudentService {

	StudentDao studentDao = new StudentDaoIMPL();
	PersonService pService = new PersonServiceIMPL();

//	@Override
//	public void input(Student p) throws SQLException {
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
//		System.out.println("Nhap ma sv: ");
//		p.setMaSV(new Scanner(System.in).nextLine());
//	}
//
//	@Override
//	public void info(Student p) throws SQLException {
//
//		System.out.println("Danh sach: ");
//		System.out.println("ID: " + p.getId());
//		System.out.println("Name: " + p.getName());
//		System.out.println("Address: " + p.getAddress());
//		System.out.println("Ma SV: " + p.getMaSV());
//	}

	@Override
	public void insert(Student p) throws SQLException {
		pService.insert(p);
		studentDao.insert(p);
		System.out.println("insert thanh cong !");

	}

	@Override
	public void update(Student p) throws SQLException {
		studentDao.update(p);
		System.out.println("update thanh cong !");
	}

	@Override
	public void delete(int person_id) throws SQLException {
		studentDao.delete(person_id);
		PersonDao pdao = new PersonDaoIMPL();
		pdao.delete(person_id);
//		pService.delete(person_id);
		
		System.out.println("delete thanh cong !");
	}

	@Override
	public List<Student> read() throws SQLException {
		return studentDao.selectAll();
	}

}
