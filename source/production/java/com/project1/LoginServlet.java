package com.project1;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.project2.dao.User;
import com.project2.database.classes.UserDbAccess;
import com.project2.database.interfaces.UserDaoInterface;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

@WebServlet(name = "loginServlet", urlPatterns = { "/login" }, loadOnStartup = 1)
public class LoginServlet extends HttpServlet {
	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// System.out.println("Happy Diwali");
		ApplicationContext context = new ClassPathXmlApplicationContext("application_context.xml");
		UserDbAccess dao = (UserDbAccess) context.getBean("UserDbAccess");

		HttpSession session = request.getSession();
		if (request.getParameter("logout") != null) {
			session.invalidate();
			response.sendRedirect("login");
			return;
		} else if (session.getAttribute("username") != null) {
			response.sendRedirect("shortner");
			return;
		} else {
			request.setAttribute("loginFailed", false);
			request.getRequestDispatcher("/WEB-INF/jsp/view/login.jsp").forward(request, response);
			return;
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("username") != null) {
			response.sendRedirect("shortner");
			return;
		}

		String input_case = request.getParameter("input_case");
		// System.out.println("INPUT CASE"+input_case);
		switch (input_case) {
		case "login":
			this.login_check(request, response);
			break;
		case "signup":
			this.signinForm(request, response);
			break;
		case "sign-up":
			this.signup_add(request, response);
			break;
		case "public":
			this.show_long_url(request, response);
			// default:
			// response.sendRedirect("login");
			// break;
		}
	}
	private void login_check(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String loginUsername = request.getParameter("username");
		String loginPassword = request.getParameter("password");
		// ApplicationContext ctx = new
		// ClassPathXmlApplicationContext("application_context.xml");
		ServletContext servletContext = getServletContext();
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);

		UserDbAccess loginDao = (UserDbAccess) ctx.getBean("UserDbAccess");
		User user = loginDao.getUser(loginUsername, loginPassword);
		// System.out.println("Hi these values are returned from database:
		// "+user.getUsername());

		if (user == null) {
			request.setAttribute("loginFailed", true);
			request.getRequestDispatcher("/WEB-INF/jsp/view/login.jsp").forward(request, response);

		} else {
			session.setAttribute("username", loginUsername);
			request.changeSessionId();

			// dao.insertUser(user);
			response.sendRedirect("shortner");
		}
		
	}

	private void signinForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/view/signinForm.jsp").forward(request, response);
	}

	private void signup_add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String signin_username = request.getParameter("input_username");
		String signin_password = request.getParameter("input_password");

		System.out.println("uname : " + signin_username + "  pass:  " + signin_password);
		ApplicationContext context = new ClassPathXmlApplicationContext("application_context.xml");
		User user = (User) context.getBean("User");
		user.setUsername(signin_username);
		user.setPassword(signin_password);

		UserDbAccess dao = (UserDbAccess) context.getBean("UserDbAccess");

		dao.insertUser(user);

		response.sendRedirect("login");

	}

	private void show_long_url(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String public_short = request.getParameter("ip_shorturl");
		// System.out.println(public_short);
		if (ShortnerServlet.shortnerDatabase.containsKey(public_short)) {
			// ShortnerServlet.shortnerDatabase.get(public_short);
			request.setAttribute("public_short", public_short);
			request.setAttribute("shortnerDatabase", ShortnerServlet.shortnerDatabase);
		}
		request.getRequestDispatcher("/WEB-INF/jsp/view/publicForm.jsp").forward(request, response);
	}

}
