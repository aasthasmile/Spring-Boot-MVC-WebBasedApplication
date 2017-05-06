package csulb.edu.aasthajain.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.dao.support.DaoSupport;

@Repository
public class UserRepository implements UserDAL{

	@Autowired
	public JdbcTemplate jdbcTemplate;

	
	@SuppressWarnings("unchecked")
	public List<User> getAllUser() {
		String query="Select id,first_name,last_name,age,gender,gpa from User";
		return jdbcTemplate.query(query, new RowMapper() {

			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				User userlist=new User(rs.getInt("id"), 
						rs.getString("first_name"), 
						rs.getString("last_name"), 
						rs.getString("age"),
						rs.getString("gender"),
						rs.getString("gpa"));
				return userlist;
			}
		});
		
	}

	
	public int CreateUser(User user) {
		 String insertQuery="Insert into User(first_name,last_name,age,gender,gpa) VALUES(?,?,?,?,?)";	
		 
		 return jdbcTemplate.update(insertQuery,new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, user.getFname());
				pstmt.setString(2, user.getLname());
				pstmt.setString(3, user.getAge());
				pstmt.setString(4, user.getGender());
				pstmt.setString(5, user.getGpa());
			}
		});
	}

	@Override
	public int updateUser(User user) {
		String updatequery="UPDATE User set first_name=?,last_name=?,age=?,gender=?,gpa=? where id=?";
		
		return jdbcTemplate.update(updatequery, new PreparedStatementSetter(){

			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, user.getFname());
				pstmt.setString(2, user.getLname());
				pstmt.setString(3, user.getAge());
				pstmt.setString(4, user.getGender());
				pstmt.setString(5, user.getGpa());
				pstmt.setInt(6, user.getId());
			}
			
		});
		
	}

	@Override
	public int deleteUser(String id) {
		String deleteQuery="DELETE FROM USER where id=?";
		
		return jdbcTemplate.update(deleteQuery, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setInt(1, Integer.parseInt(id));
				
			}
		});
	
	}

	@Override
	public void insertBatch() {
	jdbcTemplate.execute("DROP TABLE USER IF EXISTS");
	
//	jdbcTemplate.execute("CREATE TABLE USER(id integer,first_name varchar(100),last_name varchar(100),"
//			+ "age varchar(100),gender varchar(100),gpa varchar(100)");
	
	jdbcTemplate.batchUpdate("INSERT INTO USER(first_name,last_name,age,gender,gpa) VALUES(?,?,?,?,?)",intitalData());
		
	}


	private List<Object[]>  intitalData() {
		List<Object[]> users=new ArrayList<>();
		users.add("John Doe 25 M 3.7".split(" "));
		users.add("Jane Doe 23 F 3.8".split(" "));
		users.add("Sarah Bosch 34 F 4.0".split(" "));
		users.add("Michael Murray 20 M 4.0".split(" "));
		users.add("Barry Coy 19 M 3.4".split(" "));
		users.add("Daisy Chen 19 F 2.8".split(" "));
		users.add("May Williams 15 F 3.8".split(" "));
		users.add("Alex Johnson 40 M 3.2".split(" "));
		users.add("Ceasar McCoy 39 M 3.5".split(" "));
		users.add("Paula May 22 F 3.6".split(" "));		
		return users;
	}
}
