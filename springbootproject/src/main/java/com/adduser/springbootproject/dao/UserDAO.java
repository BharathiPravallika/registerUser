package com.adduser.springbootproject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.adduser.springbootproject.models.UserData;

@Repository
public class UserDAO {

	private LocalDateTime today = LocalDateTime.now();

	@Autowired
	private JdbcTemplate jdbcTemplate;

	String INSERT_USER = "INSERT INTO userdata( fullname, mobile, email, password, created_time) VALUES( ?, ?, ?, ?, current_timestamp)";
	String GET_EXISTING_USERS = "SELECT * FROM userdata";
//	String CHECK_USER = "SELECT userid FROM userdata WHERE email = ?";
//	String CHECK_USER = "SELECT EXISTS(SELECT FROM userdata WHERE email = ?)";

	public int addUser(UserData user) throws Exception{

		System.out.println("User -- " + user);
		int userId = -1;
		try {
			KeyHolder keyHolder = new GeneratedKeyHolder();
			int rowsInserted = 0;
			rowsInserted = this.jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException{
					PreparedStatement addUser = connection.prepareStatement(INSERT_USER,  Statement.RETURN_GENERATED_KEYS);
					addUser.setString(1,
                            user.getFullName());
					addUser.setString(2,
                            user.getMobile());
					addUser.setString(3,
                            user.getEmail());
					addUser.setString(4,
                            user.getPassword());
					return addUser;
					
				}
			}, keyHolder);
			if(rowsInserted > 0)
				userId = keyHolder.getKey().intValue();
		}catch(Exception e) {
			throw e;
		}
		return userId;
		
//		final KeyHolder holder = new GeneratedKeyHolder();
//
//		rowsInserted = this.jdbcTemplate.update(INSERT_USER, user.getFullName(), user.getMobile(), user.getEmail(),
//				user.getPassword(), today, today);
//
//		return rowsInserted;
	}


	public List<UserData> getUserId(String emailId) {

		// Implementation of RowMapper interface
		System.out.println("Getting the userdata in dao");
		return jdbcTemplate.query("SELECT userid from userdata Where email  = '"
				+ emailId + "'", new RowMapper<UserData>() {

			public UserData mapRow(ResultSet rs, int rowNum) throws SQLException {
				if(rs == null)
					return null;
				UserData userid = new UserData();
				userid.setUserId(rs.getInt("userid"));
//				user.setEmail(rs.getString("email"));
				return userid;
				
			}
		});
	}

}