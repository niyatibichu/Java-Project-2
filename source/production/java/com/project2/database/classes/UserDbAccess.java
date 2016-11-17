package com.project2.database.classes;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.project2.dao.User;
import com.project2.database.interfaces.UserDaoInterface;

public class UserDbAccess implements UserDaoInterface {
	public static int id = 5;
	private JdbcTemplate jdbcTemplate;

	@Override
	public void insertUser(User user) {
		System.out.println("jdbc t:  " + getJdbcTemplate());
		String sql = "INSERT INTO user" + "(ID,USERNAME,PASSWORD) VALUES (?,?,?)";
		getJdbcTemplate().update(sql, new Object[] { id++, user.getUsername(), user.getPassword() });
	}

	@Override
	public User getUser(String username, String password) {
		// TODO Auto-generated method stub
		User loginUser = null;
		String loginQuery = "SELECT username, password FROM user WHERE USERNAME= ? AND password= ?";
		try {
			loginUser = (User) getJdbcTemplate().queryForObject(loginQuery, new Object[] { username, password },
					new UserRowMapper());
		} catch (EmptyResultDataAccessException e) {
			System.out.println("Error" + e);
		}
		return loginUser;
	}

	@Override
	public User getUserID(String username) {
		// TODO Auto-generated method stub
		User linkUserId = null;
		String loginQuery = "SELECT ID FROM user WHERE USERNAME= ?";
		try {
			linkUserId = (User) getJdbcTemplate().queryForObject(loginQuery, new Object[] { username },
					new UserIdMapper());
		} catch (EmptyResultDataAccessException e) {
			System.out.println("Error" + e);
		}
		return linkUserId;
	}

	/**
	 * @return the id
	 */
	public static int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public static void setId(int id) {
		UserDbAccess.id = id;
	}

	/**
	 * @return the jdbcTemplate
	 */
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	/**
	 * @param jdbcTemplate
	 *            the jdbcTemplate to set
	 */
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}
