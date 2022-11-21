package com.adduser.springbootproject.dao;

import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.adduser.springbootproject.models.UserData;

@Repository
public class UserDAO {

	private LocalDateTime today = LocalDateTime.now();

	@Autowired
	private JdbcTemplate jdbcTemplate;

	String INSERT_USER = "INSERT INTO userdata(fullname, mobile, email, password, created_time, updated_time) VALUES( ?, ?, ?, ?, ?, ?)";
	String GET_EXISTING_USERS = "SELECT * FROM userdata";
//	String CHECK_USER = "SELECT userid FROM userdata WHERE email = ?";
//	String CHECK_USER = "SELECT EXISTS(SELECT FROM userdata WHERE email = ?)";

	public int addUser(UserData user) {

		System.out.println("User -- " + user);
		int rowsInserted = 0;
		rowsInserted = this.jdbcTemplate.update(INSERT_USER, user.getFullName(), user.getMobile(), user.getEmail(),
				user.getPassword(), today, today);

		return rowsInserted;
	}

//	public UserData getUserId(Long userid) throws SQLDataException{
//
//		String sql = "SELECT id FROM userdata WHERE email = ?";
//		
//		try {
//			return jdbcTemplate.queryForObject(sql, new Object[] { userid }, (rs, rowNum) -> new UserData());
//        } catch (EmptyResultDataAccessException ex) {
//        	System.out.println("User Id not found");
//        	return null;
//        }
//	}

	public List<UserData> getUserId(String emailId) {

		// Implementation of RowMapper interface
		System.out.println("Getting the userdata in dao");
		return jdbcTemplate.query("SELECT userid from userdata Where email  = '"
				+ emailId + "'", new RowMapper<UserData>() {

			public UserData mapRow(ResultSet rs, int rowNum) throws SQLException {
				if(rs == null)
					return null;
				UserData user = new UserData();
				user.setUserId(rs.getInt("userid"));
//				user.setEmail(rs.getString("email"));
				return user;
			}
		});
	}

//	
//	public UserData userData(Long userid) {
//
//        String sql = "SELECT * FROM userdata WHERE userid = ?";
//
//        return jdbcTemplate.queryForObject("SELECT userid,fullname,email,password,confirmPassword from userdata Where email = ?", new Object[]{userid}, (rs, rowNum) ->
//                new UserData(
//                        rs.getLong("userid"),
//                        rs.getString("fullname"),
//                        rs.getString("email")
//                    ));
//
//    }

//	public UserData getUserId(String emailId) {
//		String sql = "SELECT * FROM userdata WHERE email = ?";
//		
//		return (UserData) this.jdbcTemplate.queryForObject(sql, new Object[]{emailId}, new RowMapper<UserData>(){
//			@Override
//			public UserData mapRow(ResultSet rs, int rwNumber) throws SQLException {
//				if(rs == null)
//					return null;
//				UserData user = new UserData();
//				user.setUserId(rs.getInt("userid"));
//				
//				return user;
//			}
//		});
//	}

//	public boolean isEmailIdExists(String email) {
//		return jdbcTemplate.queryForObject(CHECK_USER, Boolean.class, email);
//	}

//	public int getUserId(String emailId) {
//		int userId = 0;
//		try {
//			userId = this.jdbcTemplate.query(psc, rowMapper) ("SELECT userid FROM userdata WHERE email=?" , new Object[] {emailId} , Integer.class);
//			
//			System.out.println("userid:" + userId);
//		}
//		catch(Exception e) {
//			System.out.println("Exception occured");
//		}
//		
//		return userId;
//		}

//	@SuppressWarnings("deprecation")
//	public int getUserId(String emailId) {
//		System.out.println("getting user id");
//		int userId = 0;
//		
//		userId = this.jdbcTemplate.queryForObject("SELECT userid FROM userdata WHERE email=?", new Object[] { emailId },
//				Integer.class);
//		return userId;
//	}
//	
//	public int validUserCredentials(String emailId) {
//		int userId = 0;
//		userId = this.jdbcTemplate.queryForObject(CHECK_USER, new Object[] {emailId} , Integer.class);
//		return userId;
//	}

}