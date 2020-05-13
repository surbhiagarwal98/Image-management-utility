package com.nagarro.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nagarro.model.User;
import com.nagarro.service.ImageServiceImp;
import com.nagarro.service.LoginServiceImp;
import com.nagarro.service.api.ImageService;
import com.nagarro.service.api.LoginService;

/**
 * Servlet implementation class ImageDeleteServlet
 */
public class ImageDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getSession().getAttribute("user") == null) {
			response.sendRedirect("index.jsp");
		} else {
			LoginService loginService = new LoginServiceImp();
			ImageService imageService = new ImageServiceImp();
			int sno = Integer.parseInt(request.getParameter("sno"));
			System.out.println(sno);
			imageService.deleteImage(sno);
			
			User userUpdated = loginService
					.getUser(((User)request.getSession().getAttribute("user")).getUserName());
			request.getSession().setAttribute("user", userUpdated);
			response.sendRedirect("home.jsp");
		}
	}

}
