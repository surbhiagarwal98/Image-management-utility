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
 * Servlet implementation class ImageServlet
 */
public class ImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ImageServlet() {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//System.out.print("I have reached here........................");
		if (request.getSession().getAttribute("user") == null) {
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} else {
			String imageName = null;
			byte bytes[] = null;
			double imageSize = 0;
			//System.out.println(ServletFileUpload.isMultipartContent(request));
			if (ServletFileUpload.isMultipartContent(request)) {
				try {
					System.out.println("We are here in try block..");
					DiskFileItemFactory factory = new DiskFileItemFactory();
					ServletFileUpload upload = new ServletFileUpload(factory);
					List<FileItem> fileItems = upload.parseRequest(new ServletRequestContext(request));
					for (FileItem item : fileItems) {
						if (item.isFormField()) {
							imageName = item.getString();
						} else {
							imageSize = item.getSize() / 1024;
							bytes = item.get();
						}
					}
					request.setAttribute("message", "File Uploaded Successfully");
					
					User user = (User) request.getSession().getAttribute("user");
					Image image = new Image(imageName, imageSize, bytes);
					image.setUser(user);

					if (user != null) {
						System.out.println("User is not null");
						if (image.getSize() < 1024) {
							System.out.println("Image size is in limit");
							if (CheckImageSizeUtil.getTotalImageSize(user.getUserName()) + image.getSize() < 10240) {
								ImageService imageService = new ImageServiceImp();
								imageService.addImage(image);
								LoginService loginService = new LoginServiceImp();
								User userUpdated = loginService
										.getUser(((User) request.getSession().getAttribute("user")).getUserName());
								request.getSession().setAttribute("user", userUpdated);
								System.out.println("Image uploaded successfully");
								request.getSession().setAttribute("message", "File Uploaded Sucesfully");
								request.getRequestDispatcher("home.jsp").forward(request, response);

							} else {
								System.out.println("Images size exceeded > 10 MB");
								request.getSession().setAttribute("message", "File Upload Failed");
								request.getRequestDispatcher("home.jsp").forward(request, response);
							}
						} else {
							System.out.println("Image size exceeded");
							request.getSession().setAttribute("message", "File Upload Failed");
							request.getRequestDispatcher("home.jsp").forward(request, response);
						}
					}
				} catch (Exception ex) {
					request.setAttribute("message", "File Upload Failed due to " + ex);
				}

			} else {
				request.setAttribute("message", "Sorry image could not be uploaded");
			}
		}

	}

}
