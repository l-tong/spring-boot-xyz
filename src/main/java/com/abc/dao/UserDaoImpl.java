package com.abc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.abc.model.User;
import com.abc.model.UserGroupMembership;

@Transactional
@Repository
public class UserDaoImpl {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoImpl.class);
 
	@Autowired
	@Qualifier("jdbcTemplate1")
	private JdbcTemplate jdbcTemplate1;

	@Autowired
	@Qualifier("jdbcTemplate2")
	private JdbcTemplate jdbcTemplate2;
	
	public UserDaoImpl(JdbcTemplate jdbcTemplate1, JdbcTemplate jdbcTemplate2) {
		this.jdbcTemplate1 = jdbcTemplate1;
		this.jdbcTemplate2 = jdbcTemplate2;
	}
	
	public List<User> getAllSystem1User() {
		String sql = "select SYSTEM1_USER_ID,SYSTEM1_DISPLAYNAME from SYSTEM1_USER";
		//get users list from db1
		List<User> list = new ArrayList<User>();
		
		try {
			LOGGER.debug("Getting system1 user list");
			list = jdbcTemplate1.query(sql, new UserRowMapper());
		} catch (Exception e) {
			list = new ArrayList<User>();
			e.printStackTrace();
		}
		
		return list;
	}
 
	public List<User> getAllSystem2User() {
		String sql = "select SYSTEM2_USER_ID,SYSTEM2_DISPLAYNAME from SYSTEM2_USER";
		//get users list from system
		List<User> list = new ArrayList<User>();
		try {
			list = jdbcTemplate2.query(sql, new User2RowMapper());
		} catch (Exception e) {
			list = new ArrayList<User>();
			e.printStackTrace();
		}

		return list;
	}
 
	public List<UserGroupMembership> getAllSystem1UserGroupMembership() {
		String sql = "select SYSTEM1_USER_ID,SYSTEM1_GROUP_NAME from SYSTEM1_USER_GROUP_MEMBERSHIP";
		//get users list from db1
		List<UserGroupMembership> list = new ArrayList<UserGroupMembership>();
		try {
			LOGGER.debug("Getting system1 user group membership list");
			list = jdbcTemplate1.query(sql, new UserGroupMembership1RowMapper());
		} catch (Exception e) {
			list = new ArrayList<UserGroupMembership>();
			e.printStackTrace();
		}
		return list;
	}
 
	public List<UserGroupMembership> getAllSystem2UserGroupMembership() {

		String sql = "select SYSTEM2_USER_ID,SYSTEM2_GROUP_NAME from SYSTEM2_USER_GROUP_MEMBERSHIP";
		//get users list from system
		List<UserGroupMembership> list = new ArrayList<UserGroupMembership>();
		try {
			LOGGER.debug("Getting system2 user group membership list");
			list = jdbcTemplate2.query(sql, new UserGroupMembership2RowMapper());
		} catch (Exception e) {
			list = new ArrayList<UserGroupMembership>();
			e.printStackTrace();
		}
		return list;
	}
	
	public void insertSystem1User(User user){

		String sql = "INSERT INTO SYSTEM2_USER " +
		"(SYSTEM2_USER_ID, SYSTEM2_DISPLAYNAME) VALUES (?, ?)";
		
		try {
			jdbcTemplate2.update(sql, new Object[] { 
				user.getId(),user.getDisplayName()  
			});
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
 
	public void insert(User user){

		String sql = "INSERT INTO SYSTEM2_USER " +
		"(SYSTEM2_USER_ID, SYSTEM2_DISPLAYNAME) VALUES (?, ?)";
		
		try {
			jdbcTemplate2.update(sql, new Object[] { 
				user.getId(),user.getDisplayName()  
			});
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void update(User user){

		String sql = "UPDATE SYSTEM2_USER " +
		"SET SYSTEM2_DISPLAYNAME = ? WHERE SYSTEM2_USER_ID = ?";
		try {
			jdbcTemplate2.update(sql, new Object[] {
				user.getId(),user.getDisplayName()  
			});
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void delete(User user){

		String sql1 = "DELETE FROM SYSTEM2_USER_GROUP_MEMBERSHIP WHERE SYSTEM2_USER_ID = ?";
		String sql2 = "DELETE FROM SYSTEM2_USER WHERE SYSTEM2_USER_ID = ?";
		
		try {
			jdbcTemplate2.update(sql1, new Object[] { 
				user.getId()
			});
			
			jdbcTemplate2.update(sql2, new Object[] { 
				user.getId()
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void insertUserGroupMembership(UserGroupMembership usg){

		String sql = "INSERT INTO SYSTEM2_USER_GROUP_MEMBERSHIP " +
		"(SYSTEM2_USER_ID, SYSTEM2_GROUP_NAME) VALUES (?, ?)";
		
		try {
			jdbcTemplate2.update(sql, new Object[] { 
				usg.getId(),usg.getGroupName()  
			});
		} catch (Exception e) {
			System.err.println("Error inserting: " + usg);
			e.printStackTrace();
		}
	}
	
	public void deleteUserGroupMembership(UserGroupMembership usg){

		String sql1 = "DELETE FROM SYSTEM2_USER_GROUP_MEMBERSHIP WHERE SYSTEM2_USER_ID = ? and SYSTEM2_GROUP_NAME = ?";

		try {
			jdbcTemplate2.update(sql1, new Object[] { 
				usg.getId(), usg.getGroupName()
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
 
	class UserRowMapper implements RowMapper<User>{
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setId(rs.getLong("SYSTEM1_USER_ID"));
			user.setDisplayName(rs.getString("SYSTEM1_DISPLAYNAME"));

			return user;
		}

	}

	class User2RowMapper implements RowMapper<User> {
		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setId(rs.getLong("SYSTEM2_USER_ID"));
			user.setDisplayName(rs.getString("SYSTEM2_DISPLAYNAME"));

			return user;
		}
	}

	class UserGroupMembership1RowMapper implements RowMapper<UserGroupMembership> {
		@Override
		public UserGroupMembership mapRow(ResultSet rs, int rowNum) throws SQLException {
			UserGroupMembership ugm = new UserGroupMembership();
			ugm.setId(rs.getLong("SYSTEM1_USER_ID"));
			ugm.setGroupName(rs.getString("SYSTEM1_GROUP_NAME"));

			return ugm;
		}
	}

	class UserGroupMembership2RowMapper implements RowMapper<UserGroupMembership> {
		@Override
		public UserGroupMembership mapRow(ResultSet rs, int rowNum) throws SQLException {
			UserGroupMembership ugm = new UserGroupMembership();
			ugm.setId(rs.getLong("SYSTEM2_USER_ID"));
			ugm.setGroupName(rs.getString("SYSTEM2_GROUP_NAME"));

			return ugm;
		}
	}
}