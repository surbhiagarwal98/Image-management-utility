package com.nagarro.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

import com.nagarro.model.Image;
import com.nagarro.model.User;
import com.nagarro.service.ImageServiceImp;
import com.nagarro.service.LoginServiceImp;
import com.nagarro.service.api.ImageService;
import com.nagarro.service.api.LoginService;
import com.nagarro.util.CheckImageSizeUtil;

/**
 * Servlet implementation class ImageEditServlet
 */
public class ImageEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} else {
			LoginService loginService = new LoginServiceImp();
			
			int sno = Integer.parseInt(request.getParameter("sno"));
			
			ImageService imageService = new ImageServiceImp();
			Image img = imageService.getImage(sno);
			double currentImageSize = img.getSize();
			double imageSize = 0;
			byte[] bytes = null;
			String imageName = null;

			response.setContentType("text/html;charset=ISO-8859-1");
			try {
				if (!ServletFileUpload.isMultipartContent(request)) {
					System.out.println("sorry. No file uploaded");
					return;
				}

				ServletFileUpload servletFileUpload = new ServletFileUpload(new DiskFileItemFactory());

				List<FileItem> items = servletFileUpload.parseRequest(new ServletRequestContext(request));
				System.out.println(items.size());

				for (FileItem file : items) {
					if (file.isFormField()) {
						imageName = file.getString();
						if (!imageName.isEmpty()) {
							img.setName(imageName);
						}
					} else {
						imageSize = file.getSize() / 1024;
						if (imageSize != 0) {
							bytes = file.get();
							img.setSize(imageSize);
							img.setImg(bytes);
						}
					}
				}
			} catch (Exception e) {
				System.err.println("Error occurred ");
			}

			if (img.getSize() > 1024 || (CheckImageSizeUtil.getTotalImageSize(user.getUserName()) + imageSize
					- currentImageSize) > 10240) {
				System.out.println("Image size exceeded");
			} else {
				imageService.editImage(img,sno);
			}
			User userUpdated = loginService
					.getUser(((User) request.getSession().getAttribute("user")).getUserName());
			request.getSession().setAttribute("user", userUpdated);
			request.getRequestDispatcher("home.jsp").forward(request, response);
		}
	}

}
