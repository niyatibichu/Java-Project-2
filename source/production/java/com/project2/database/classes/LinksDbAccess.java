package com.project2.database.classes;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.project2.dao.Links;
import com.project2.dao.User;
import com.project2.database.interfaces.UserDaoInterface;

import com.project2.database.interfaces.LinksDaoInterface;

public class LinksDbAccess extends JdbcDaoSupport implements LinksDaoInterface {
	private JdbcTemplate jdbcTemplateObj;
	public static int id =0 ;
	@Override
	public void insertLinks(Links links) {
		// TODO Auto-generated method stub
		//String sql = "insert into xyz valus";
		//jdbcTemplateObj.query(sql, null);
		
		System.out.println("links jdbc t:  "+getJdbcTemplate());
		
		String sql="INSERT INTO links"+"(LINKID,USERID,LONGURL,SHORTURL,CLICKS) VALUES (?,?,?,?,?)";
		getJdbcTemplate().update(sql,new Object[]{id++,links.getUserId(),links.getLongUrl(),links.getShortUrl(),links.getClicks()});

	}

	@Override
	public List<Links> getLinksByUser(int userId) {
		// TODO Auto-generated method stub
		//List<Links> linkUser = null;
		List<Links> linkUser= new ArrayList<Links>();
		System.out.println("USERID:"+userId);
		String loginQuery="SELECT linkid,shorturl,longurl,clicks FROM links WHERE userid= ?";
		try{			
		List<Map<String,Object>> linksUser=getJdbcTemplate().queryForList(loginQuery,new Object[]{userId});
		for(Map row: linksUser){
			ApplicationContext ctx= new ClassPathXmlApplicationContext("application_context.xml");
			 Links linkByUser=(Links)ctx.getBean("Links");
			linkByUser.setLinkId((Integer)row.get("linkid"));
			linkByUser.setShortUrl((String)row.get("shorturl"));
			linkByUser.setLongUrl((String)row.get("longurl"));
			linkByUser.setClicks((Integer)row.get("clicks"));	
			linkUser.add(linkByUser);
		}
		
		}catch(EmptyResultDataAccessException e){
			System.out.println("Error"+e);
		}
		return linkUser;
	}

	@Override
	public Links getLongUrl(String shortUrl) {
		// TODO Auto-generated method stub
	//	jdbcTemplateObj.queryForObject(sql, requiredType);
		return null;
	}

	/**
	 * @return the jdbcTemplateObj
	 */
	public JdbcTemplate getJdbcTemplateObj() {
		return jdbcTemplateObj;
	}

	/**
	 * @param jdbcTemplateObj the jdbcTemplateObj to set
	 */
	public void setJdbcTemplateObj(JdbcTemplate jdbcTemplateObj) {
		this.jdbcTemplateObj = jdbcTemplateObj;
	}

}
