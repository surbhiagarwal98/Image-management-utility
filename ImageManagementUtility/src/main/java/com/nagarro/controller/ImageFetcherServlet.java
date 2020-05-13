package com.nagarro.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nagarro.model.Image;
import com.nagarro.service.ImageServiceImp;
import com.nagarro.service.api.ImageService;

/**
 * Servlet implementation class ImageFetcherServlet
 */
public class ImageFetcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImageFetcherServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		   if (request.getSession().getAttribute("user") == null) {
	            request.getRequestDispatcher("index.jsp").forward(request, response);
	        } else {
	            ImageService imageService = new ImageServiceImp();
	            int sno = Integer.parseInt(request.getParameter("sno"));
	            Image image = imageService.getImage(sno);
	            if (image != null) {
	                response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
	                try {
	                    response.getOutputStream().flush();
	                    response.getOutputStream().write(image.getImg());
	                    response.getOutputStream().close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }

	 
	}

	}


