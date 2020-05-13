package com.nagarro.dao.api;

import com.nagarro.model.Image;

public interface ImageDao {
	void addImage(Image image);
	void editImage(Image img,int sno);
	void deleteImage(int sno);
	Image getImage(int sno);
}
