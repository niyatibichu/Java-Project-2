/*package com.project2.database.classes;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.project2.dao.Links;

public class LinkRowMapper {
	public Links mapRow(ResultSet rs, int rowNum) throws SQLException {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("application_context.xml");
   	 	Links LinksInfo = (Links) ctx.getBean("Links");
   	 	//User userInfo = new User();
   	 LinksInfo.setLinkId(rs.getInt("linkid"));
   	 LinksInfo.setShortUrl(rs.getString("shorturl"));
   	LinksInfo.setLongUrl(rs.getString("longurl"));
   	LinksInfo.setClicks(rs.getInt("clicks"));
		return LinksInfo;		
}
}
*/