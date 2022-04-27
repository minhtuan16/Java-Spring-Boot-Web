package service;

import java.sql.SQLException;
import java.util.List;

import dao.TeacherDao;
import dao.TeacherDaoIMPL;
import model.Teacher;

public class TeacherServiceIMPL implements TeacherService {

	TeacherDao teacherDao = new TeacherDaoIMPL();
	PersonService pService = new PersonServiceIMPL();
//	@Override
//	public void input(Teacher t) throws SQLException {
//
//		System.out.println("Nhap thong tin: ");
//		System.out.println("Nhap id: ");
//		t.setId(new Scanner(System.in).nextInt());
//
//		System.out.println("Nhap name:");
//		t.setName(new Scanner(System.in).nextLine());
//
//		System.out.println("Nhap address:");
//		t.setAddress(new Scanner(System.in).nextLine());
//
//		System.out.println("Nhap email: ");
//		t.setEmail(new Scanner(System.in).nextLine());
//	}
//
//	@Override
//	public void info(Teacher t) throws SQLException {
//
//		System.out.println("Danh sach: ");
//		System.out.println("ID: " + t.getId());
//		System.out.println("Name: " + t.getName());
//		System.out.println("Address: " + t.getAddress());
//		System.out.println("Email: " + t.getEmail());
//
//	}

	@Override
	public void insert(Teacher t) throws SQLException {
		pService.insert(t);
		teacherDao.insert(t);
		System.out.println("insert thanh cong !");

	}

	@Override
	public void update(Teacher t) throws SQLException {

		teacherDao.update(t);
		System.out.println("update thanh cong !");
	}

	@Override
	public void delete(int id) throws SQLException {
		teacherDao.delete(id);
		pService.delete(id);
		System.out.println("delete thanh cong !");
	}

	@Override
	public List<Teacher> read() throws SQLException {
		return teacherDao.selectAll();
	}

}
