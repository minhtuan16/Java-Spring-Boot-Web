package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Course;


public class CourseDAOIMPL implements CourseDAO {
	

	@Override
	public void add(Course c) throws SQLException {

		String sql = "INSERT INTO COURSE(id, name) VALUES(?, ?)";

		Connection conn = JDBCConnection.getConn();
		PreparedStatement ps = conn.prepareStatement(sql);

		ps.setInt(1, c.getId());
		ps.setString(2, c.getName());

		ps.executeUpdate();// de update du lieu: them/sua/xoa

	}
		//Ctrl + shift + O: de xoa may import thua

	@Override
	public void update(Course c) throws SQLException {
		String sql = "UPDATE COURSE SET name = ? WHERE id = ?";

		Connection conn = JDBCConnection.getConn();
		PreparedStatement ps = conn.prepareStatement(sql);

		ps.setString(1, c.getName());
		ps.setInt(2, c.getId());

		ps.executeUpdate();// de update du lieu: them/sua/xoa
	}

	@Override
	public void delete(int id) throws SQLException {
		String sql = "DELETE FROM COURSE WHERE id = ?";

		Connection conn = JDBCConnection.getConn();
		PreparedStatement ps = conn.prepareStatement(sql);

		ps.setInt(1, id);

		ps.executeUpdate();// de update du lieu: them/sua/xoa
	}

	@Override
	public List<Course> selectAll() throws SQLException {
		String sql = "SELECT id, name FROM COURSE";

		Connection conn = JDBCConnection.getConn();

		PreparedStatement ps = conn.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();

		List<Course> courseList = new ArrayList<Course>();

		while (rs.next()) {
			Course c = new Course();

			c.setId(rs.getInt("id"));
			c.setName(rs.getString("name"));

			courseList.add(c);
		}

		return courseList;

	}
}