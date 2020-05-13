package com.nagarro.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nagarro.model.User;
import com.nagarro.service.LoginServiceImp;
import com.nagarro.service.api.LoginService;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor
	 */
	public LoginServlet() {
	}

	/**
	 * takes input parameters and verifies them for login
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String username = request.getParameter("userName");
		String password = request.getParameter("password");

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("home.jsp");

		LoginService service = new LoginServiceImp();
		if (service.isUser(username,password)) {
			System.out.println("Login successful!");
			LoginService loginService = new LoginServiceImp();
			User user = loginService.getUser(username);
			request.getSession().setAttribute("authorized", "true");
			request.getSession().setAttribute("user", user);	
			requestDispatcher.forward(request, response);
		} else {
			request.setAttribute("message", "Invalid login!");
			requestDispatcher.include(request, response);
		}
		out.close();
	}
}
