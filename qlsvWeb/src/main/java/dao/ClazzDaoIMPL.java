package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Clazz;
import model.Course;

public class ClazzDaoIMPL implements ClazzDao {

	@Override
	public void insert(Clazz c) throws SQLException{

		String sql = "INSERT INTO Clazz(id, name, course_id) "
				+ "VALUES (?, ?, ?)";
		
		Connection conn = JDBCConnection.getConn();

			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, c.getId());
			ps.setString(2, c.getName());
			ps.setInt(3, c.getCourse().getId());
			ps.executeUpdate();
	
	}

	@Override
	public void update(Clazz c) throws SQLException{

		String sql = "UPDATE Clazz set name = ?, course_id = ? WHERE id = ?";
		
		Connection conn = JDBCConnection.getConn();
		

			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, c.getName());
			ps.setInt(2, c.getCourse().getId());
			ps.setInt(3, c.getId());
			
			ps.executeUpdate();
		
	}

	@Override
	public void delete(int id) throws SQLException{

		String sql = "DELETE FROM Clazz WHERE id = ?";
		
		Connection conn = JDBCConnection.getConn();
		

			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, id);
			
			ps.executeUpdate();
	
	}

	@Override
	public List<Clazz> selectAll() throws SQLException{

		String sql = "select cl.id, cl.name, cl.course_id, c.name AS NameCourse FROM Clazz cl\r\n"
				+ "inner join course c on c.id = cl.course_id;";
		
		Connection conn = JDBCConnection.getConn();
		

			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			List<Clazz> lists = new ArrayList<Clazz>();
			
			while(rs.next()) {
				
				Clazz cl = new Clazz();
				cl.setId(rs.getInt("id"));
				cl.setName(rs.getString("name"));
				
				Course c = new Course();
				c.setId(rs.getInt("course_id"));
				c.setName(rs.getString("NameCourse"));
				
				cl.setCourse(c);
				
				lists.add(cl);
			}
			return lists;
	}


}
