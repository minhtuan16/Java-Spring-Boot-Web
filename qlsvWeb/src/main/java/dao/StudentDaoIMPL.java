package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Person;
import model.Student;

public class StudentDaoIMPL implements StudentDao {
	
	Person p = new Person();

	@Override
	public void insert(Student s) throws SQLException {

		String sql = "INSERT INTO STUDENT(person_id, maSV) VALUES (?, ?)";

		Connection conn = JDBCConnection.getConn();

		PreparedStatement pS = conn.prepareStatement(sql);

		pS.setInt(1, s.getId());
		pS.setString(2, s.getMaSV());

		pS.executeUpdate();

	}

	@Override
	public void update(Student s) throws SQLException {

		String sql = "UPDATE STUDENT SET maSV = ? WHERE person_id = ?";

		Connection conn = JDBCConnection.getConn();

		PreparedStatement pS = conn.prepareStatement(sql);

		pS.setString(1, s.getMaSV());
		pS.setInt(2, s.getId());

		pS.executeUpdate();

	}

	@Override
	public void delete(int person_id) throws SQLException {

		String sql = "DELETE FROM STUDENT WHERE person_id = ? ";

		Connection conn = JDBCConnection.getConn();

		PreparedStatement pS = conn.prepareStatement(sql);

		pS.setInt(1, person_id);
		
		pS.executeUpdate();

	}

	@Override
	public List<Student> selectAll() throws SQLException {

		String sql = "select id, name, address, maSV from student\r\n"
				+ "inner join person on person.id = student.person_id;";
		Connection conn = JDBCConnection.getConn();

		PreparedStatement pS = conn.prepareStatement(sql);

		ResultSet rs = pS.executeQuery();
		List<Student> studentList = new ArrayList<Student>();
		while (rs.next()) {
			Student s = new Student();
			s.setId(rs.getInt("id"));
			s.setName(rs.getString("name"));
			s.setAddress(rs.getString("address"));
			s.setMaSV(rs.getString("MaSV"));

			studentList.add(s);
		}
		return studentList;
	}

}
