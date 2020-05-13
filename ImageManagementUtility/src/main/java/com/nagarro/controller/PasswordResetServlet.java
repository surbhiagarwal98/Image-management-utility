package com.nagarro.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nagarro.service.LoginServiceImp;
import com.nagarro.service.api.LoginService;

/**
 * Servlet implementation class PasswordResetServlet
 */
public class PasswordResetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		String username = request.getParameter("userName");
		String newPassword = request.getParameter("newPassword");
		String confirmPassword = request.getParameter("confirmPassword");
		
		if(newPassword.equals(confirmPassword)) {
			LoginService service = new LoginServiceImp();
			boolean isSuccessfulReset = service.resetPassword(username, newPassword);
			
			if(isSuccessfulReset) {
				response.sendRedirect("index.jsp");
			}else {
				response.sendRedirect("resetPassword.jsp");
			}
		}else {
			System.out.println("Password confirmation failed!");
			response.sendRedirect("resetPassword.jsp");
		}
		
		
	}

}
