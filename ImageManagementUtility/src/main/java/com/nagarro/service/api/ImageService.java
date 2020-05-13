package com.nagarro.service.api;

import com.nagarro.model.Image;

public interface ImageService {
	void addImage(Image image);
	void editImage(Image img,int sno);
	void deleteImage(int sno);
	Image getImage(int sno);
	
}
