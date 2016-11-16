package com.project1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.project2.dao.Links;
import com.project2.dao.User;
import com.project2.database.classes.UserDbAccess;

import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

@WebServlet(
        name = "shortnerServlet",
        urlPatterns = {"/shortner"},
        loadOnStartup = 1
)

public class ShortnerServlet extends HttpServlet
{
   
	private static final long serialVersionUID = 1L;
	private static final String char_List="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-.";
	private static final int string_length=6;
    public static HashMap<String, UserUrl> shortnerDatabase = new HashMap<>();
  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        if(request.getSession().getAttribute("username") == null)
        {
            response.sendRedirect("login");
            return;
        }
        
        String action = request.getParameter("action");
        if(action == null)
            action = "list";
        switch(action)
        {
            case "create":
                this.showUrlForm(request, response);
                break;
                default:
                	this.listUrls(request,response);
                	break;
               }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        if(request.getSession().getAttribute("username") == null)
        {
            response.sendRedirect("login");
            return;
        }
      	
        	
        String action = request.getParameter("action");
        
        if(action == null)
            action = "list";
        switch(action)
        {
            case "create":
                this.createUrl(request, response);
                break;
            case "list":
            default:
                response.sendRedirect("shortner");
                break;
        }
        
        
       
    }

    private void showUrlForm(HttpServletRequest request,
                                HttpServletResponse response)
            throws ServletException, IOException
    {
        request.getRequestDispatcher("/WEB-INF/jsp/view/urlForm.jsp")
               .forward(request, response);
    }
private void listUrls(HttpServletRequest request,
        HttpServletResponse response)
throws ServletException, IOException
{
	request.setAttribute("shortnerDatabase", shortnerDatabase);
	request.getRequestDispatcher("/WEB-INF/jsp/view/listUrls.jsp").forward(request, response);
}

    private void createUrl(HttpServletRequest request,
                              HttpServletResponse response)
            throws ServletException, IOException
    {
    	
    	String domain_string="http://localhost:8080/urlshortener/short/";
    	UserUrl userurl=new UserUrl();
    	//new code start
    	ApplicationContext context = new ClassPathXmlApplicationContext("application_context.xml");
    	//User user=(User) context.getBean("User");
    	String Databaseusername= (String)request.getSession().getAttribute("username");
    	UserDbAccess dao = (UserDbAccess) context.getBean("UserDbAccess");
    	LinksDbAccess linkdao = (LinksDbAccess) context.getBean("LinksDbAccess");
    	User user = dao.getUserID(Databaseusername);
    	Links link = (Links) context.getBean("Links");
   	    	 link.setUserId(user.getId());;
   	    	 link.setLongUrl(request.getParameter("input_url"));;
   	    	String string_random=randomStringGenerator();
   	        String short_url=domain_string+string_random;
   	    	 link.setShortUrl(short_url);
   	    	linkdao.insertLinks(link);  	 
   	//new code end 
   	  
    	
    	
   	    userurl.setlongUrl(request.getParameter("input_url"));
        
    userurl.setclicks(0);
    userurl.setshortUrl(short_url);
   
    userurl.setuserName(Databaseusername);
   synchronized(this){ 
   shortnerDatabase.put(short_url, userurl);

   }
   //System.out.println(shortnerDatabase);
   
  /*
   Iterator it = shortnerDatabase.entrySet().iterator();
   while (it.hasNext()) {
       Map.Entry pair = (Map.Entry)it.next();
       
       UserUrl u =  (UserUrl) pair.getValue();
       
    	   System.out.println(pair.getKey() + " = " + u.getlongUrl() + ", " + u.getshortUrl() + ", " + u.getclicks());
    	   System.out.println(pair.getValue() );
//       it.remove(); // avoids a ConcurrentModificationException
   }
   */
   response.sendRedirect("shortner?action=list");
   }

    public String randomStringGenerator(){
		 StringBuffer randStr = new StringBuffer();
	        for(int i=0; i<string_length; i++){
	            int number = randomNumber();
	            char ch = char_List.charAt(number);
	            randStr.append(ch);
	        }
	        return randStr.toString();
	}
	
	private int randomNumber(){
		int randomInt = 0;
       Random randomGenerator = new Random();
       randomInt = randomGenerator.nextInt(char_List.length());
       if (randomInt - 1 == -1) {
           return randomInt;
       } else {
           return randomInt - 1;
       }
	}
	
}
