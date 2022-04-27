package service;

import java.sql.SQLException;
import java.util.List;

import dao.ClazzDao;
import dao.ClazzDaoIMPL;
import model.Clazz;

public class ClazzServiceIMPL implements ClazzService {

	ClazzDao dao = new ClazzDaoIMPL();
//	CourseService cs = new CourseServiceIMPL();
	@Override
	public void insert(Clazz c) throws SQLException{
		dao.insert(c);
		System.out.println("Insert thanh cong !");
	}

	@Override
	public void update(Clazz c)  throws SQLException{
		dao.update(c);
		System.out.println("Update thanh cong !");
	}

	@Override
	public void delete(int id)  throws SQLException{

		dao.delete(id);
		System.out.println("Delete thanh cong !");
	}

	@Override
	public List<Clazz> selectAll()  throws SQLException{
		return dao.selectAll();
	}
}
