package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Teacher;

public class TeacherDaoIMPL implements TeacherDao {

	@Override
	public void insert(Teacher t) throws SQLException {

		String sql = "INSERT INTO TEACHER(person_id, email) VALUES (?, ?)";

		Connection conn = JDBCConnection.getConn();
		PreparedStatement pS = conn.prepareStatement(sql);

		pS.setInt(1, t.getId());
		pS.setString(2, t.getEmail());

		pS.executeUpdate();

	}

	@Override
	public void update(Teacher t) throws SQLException {

		String sql = "UPDATE TEACHER SET email = ? WHERE person_id = ?";

		Connection conn = JDBCConnection.getConn();

		PreparedStatement pS = conn.prepareStatement(sql);

		pS.setString(1, t.getEmail());
		pS.setInt(2, t.getId());

		pS.executeUpdate();

	}

	@Override
	public void delete(int id) throws SQLException {

		String sql = "DELETE FROM TEACHER WHERE person_id = ? ";

		Connection conn = JDBCConnection.getConn();

		PreparedStatement pS = conn.prepareStatement(sql);

		pS.setInt(1, id);

		pS.executeUpdate();

	}

	@Override
	public List<Teacher> selectAll() throws SQLException {

		String sql = "select id, name, address, email from teacher\r\n"
				+ "inner join person on person.id = teacher.person_id; ";
		Connection conn = JDBCConnection.getConn();

		PreparedStatement pS = conn.prepareStatement(sql);

		ResultSet rs = pS.executeQuery();
		List<Teacher> teacherList = new ArrayList<Teacher>();
		while (rs.next()) {
			Teacher t = new Teacher();
			t.setId(rs.getInt("id"));
			t.setName(rs.getString("name"));
			t.setAddress(rs.getString("address"));
			t.setEmail(rs.getString("email"));

			teacherList.add(t);
		}
		return teacherList;
	}

}
