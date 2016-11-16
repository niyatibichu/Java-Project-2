package com.project1;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
        name = "RedirectServlet",
        urlPatterns = {"/short/*"},
        loadOnStartup = 1
)

public class RedirectLong extends HttpServlet {

	/**
	 * 
	 */
	int click;
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub

        try{
        	String uniURLPath=req.getRequestURL().toString();
        	//String uniURLIdentifier=uniURLPath.replaceAll("/","");
        	System.out.println("URI-------"+uniURLPath);
        	click=ShortnerServlet.shortnerDatabase.get(uniURLPath).getclicks();
        	click++;
        	ShortnerServlet.shortnerDatabase.get(uniURLPath).setclicks(click);
        	System.out.println(ShortnerServlet.shortnerDatabase.get(uniURLPath).getlongUrl());
        	resp.sendRedirect(ShortnerServlet.shortnerDatabase.get(uniURLPath).getlongUrl());
        	return;
        }
    	catch (Exception e)
    	{
    		System.out.println("Invalid Short Link, or other.");
    	}

	}
	
	

}
