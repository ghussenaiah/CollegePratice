package com.mini.actions;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mini.util.UtilConstants;

public class LogOutAction extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		session.setAttribute("user", null);
		session.setAttribute("role", null);
		session.invalidate();
		RequestDispatcher rd = request
				.getRequestDispatcher(UtilConstants._LOGOUT);
		rd.forward(request, response);
	}

}
