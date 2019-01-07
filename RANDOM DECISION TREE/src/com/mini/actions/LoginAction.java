package com.mini.actions;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;

import org.omg.CORBA.Context;

import com.mini.daoImpl.SecurityDaoImpl;
import com.mini.delegate.SecurityDelegate;

import com.mini.beans.RegisterTo;
import com.mini.delegate.SecurityDelegate;
import com.mini.exception.ConnectionException;
import com.mini.exception.LoginException;
import com.mini.util.UtilConstants;

public class LoginAction extends HttpServlet {
	private int hitCount;

	public void init() {

		hitCount = 0;
	}

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		hitCount++;
		String role = "";

		String target = "";

		boolean newbie = false;

		HttpSession session = request.getSession();
		String servername = request.getServerName();
		System.out.println("servername is************" + servername);

		if (session != null) {
			System.out.println("session exist");
		}

		else {
			System.out.println("session is null");
		}

		RegisterTo lt = new RegisterTo();

		String username = request.getParameter("username");
		lt.setUsername(username);
		String pass = request.getParameter("password");
		lt.setPassword(pass);

		session.setAttribute("username", username);

		System.out.println("username=" + username);
		System.out.println("password=" + pass);

		String pathinf = request.getPathInfo();
		System.out.println("path information is =" + pathinf);
		String serpath = request.getServletPath();
		System.out.println("servlet path is " + serpath);
		String schema = request.getScheme();
		System.out.println("schema is " + schema);
		StringBuffer url = request.getRequestURL();
		System.out.println("url is " + url);
		String qs = request.getQueryString();
		System.out.println("query string  is " + qs);
		System.out.println("request is from browser  is " + (url + "?" + qs));

		String s1 = "iam very good boy";
		String s2 = "iam very bad girl";

		Pattern pat = Pattern.compile(s1);
		System.out.println("pattren value is$$$$$$$$$$$$$$$$$$$$$$$" + pat);

		Matcher m = pat.matcher(s2);
		System.out.println("m  objectvalue is$$$$$$$$$$$$$$$$$$$$$$$" + m);

		String sid1 = session.getId();
		System.out.println(" THE NEW SESSION ID IS" + sid1);
		String sid2 = null;

		// Get the current session ID by searching the received cookies.

		Cookie[] cookies = request.getCookies();

		if (cookies != null) {
			System.out.println(" i am in ");
			System.out.println(" cookie length is" + cookies.length);
			for (int i = 0; i < cookies.length; i++) {

				if (cookies[i].getName().equals("acookie"))

				{
					System.out
							.println(" cookie name  fromm  (((((((((cookie is  "
									+ cookies[i].getName());

					sid2 = cookies[i].getValue();
					System.out.println(" cookie value from ((((((((((((cookie "
							+ sid2);

					break;
				}
			}
		}

		// If the session ID wasn't sent, generate one.
		// Then be sure to send it to the client with the response.
		if (sid2 == null) {

			// System.out.println(" sid value from server "+sid);
			Cookie c = new Cookie("acookie", sid1);
			c.setMaxAge(60 * 60 * 24 * 365);
			response.addCookie(c);

		}

		InetAddress ownIP = InetAddress.getLocalHost();
		System.out.println("IP of my system is := " + ownIP.getHostAddress());

		float a = 3, b = 4;
		float x = a / b;
		System.out.println("++++++++++" + x);

		session.setAttribute("hit", hitCount);
		try {
			System.out.println(" cooikie value before comparing" + sid2);
			System.out.println(" session id value " + sid1);

			
				role = new SecurityDelegate().loginCheck(lt);
			
			System.out.println("in LoginAction Role is.........." + role);

			if (role.equalsIgnoreCase(UtilConstants._USER)) {

				session.setAttribute("status", "Welcome " + username);
				String s = (String) session.getAttribute("status");
				System.out.println(s);
				target = UtilConstants._USER_HOME;

				session.setAttribute(UtilConstants._LOGINUSER, username);
				session.setAttribute(UtilConstants._ROLE, role);
				session.setAttribute(UtilConstants._PASSWORD, pass);
				
				

			}

			

			else if (role.equalsIgnoreCase(UtilConstants._ADMIN)) {

				session.setAttribute("status", "Welcome " + username);

				String s = (String) session.getAttribute("status");

				target = UtilConstants._ADMIN_HOME;

				session.setAttribute(UtilConstants._LOGINUSER, username);
				session.setAttribute(UtilConstants._ROLE, role);
				session.setAttribute(UtilConstants._PASSWORD, pass);
				

			}

			
			else {

				request.setAttribute("status1", UtilConstants._INVALID_USER);
				session.setAttribute(UtilConstants._ROLE, role);
				target = UtilConstants._LOGIN_FAILED_PAGE;
			}

		} catch (ConnectionException e) {

			e.getMessage();
			request.setAttribute("status1", e.getMessage());
			target = UtilConstants._LOGIN_FAILED_PAGE;
		} catch (NullPointerException e) {
			e.getMessage();
			session.setAttribute("status1", e.getMessage());
			RequestDispatcher rd = request
					.getRequestDispatcher(UtilConstants._LOGIN_FAILED_PAGE);
			rd.forward(request, response);

		}

		RequestDispatcher rd = request.getRequestDispatcher(target);
		rd.forward(request, response);

	}

}
