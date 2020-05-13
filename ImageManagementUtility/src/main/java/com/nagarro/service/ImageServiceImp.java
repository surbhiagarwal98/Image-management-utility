package com.nagarro.service;

import com.nagarro.dao.ImageDaoImp;
import com.nagarro.dao.api.ImageDao;
import com.nagarro.model.Image;
import com.nagarro.service.api.ImageService;

public class ImageServiceImp implements ImageService {

	public void addImage(Image image) {
		ImageDao imagedao = new ImageDaoImp();
		imagedao.addImage(image);
	}

	public void editImage(Image img,int sno) {
		ImageDao imagedao = new ImageDaoImp();
		imagedao.editImage(img,sno);

	}

	public void deleteImage(int sno) {
		ImageDao imagedao = new ImageDaoImp();
		imagedao.deleteImage(sno);

	}

	public Image getImage(int sno) {
		ImageDao imagedao = new ImageDaoImp();
		Image image = imagedao.getImage(sno);

		return image;
	}

}
