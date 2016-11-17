package com.project2.database.classes;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.RowMapper;
import com.project2.dao.User;

public class UserRowMapper implements RowMapper<User> {
	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("application_context.xml");
		User userInfo = (User) ctx.getBean("User");
		// User userInfo = new User();
		// userInfo.setId(rs.getInt("id"));
		userInfo.setUsername(rs.getString("username"));
		userInfo.setPassword(rs.getString("password"));
		return userInfo;
	}
}
