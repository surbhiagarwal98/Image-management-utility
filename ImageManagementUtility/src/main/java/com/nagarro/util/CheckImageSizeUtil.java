package com.nagarro.util;

import java.util.Set;

import com.nagarro.model.Image;
import com.nagarro.model.User;
import com.nagarro.service.LoginServiceImp;
import com.nagarro.service.api.LoginService;

public class CheckImageSizeUtil {

	public static double getTotalImageSize(String username) {
		double totalSize = 0;
		LoginService loginService = new LoginServiceImp();
		User user = loginService.getUser(username);
		Set<Image> images = user.getImages();
		for (Image image : images) {
			totalSize += image.getSize();
		}
		return totalSize;
	}

}
