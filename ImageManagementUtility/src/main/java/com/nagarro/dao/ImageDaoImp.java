package com.nagarro.dao;

import org.hibernate.Query;
import org.hibernate.Session;

import com.nagarro.dao.api.ImageDao;
import com.nagarro.model.Image;
import com.nagarro.util.HibernateUtil;

public class ImageDaoImp implements ImageDao {

	public void addImage(Image image) {
		try {
			Session session = HibernateUtil.getSession();
			session.getTransaction().begin();
			session.save(image);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void editImage(Image img,int sno) {
		try {
			Session session = HibernateUtil.getSession();
			session.getTransaction().begin();
			Image image = session.get(Image.class,sno);
			image.setImg(img.getImg());
			image.setName(img.getName());
			image.setSize(img.getSize());
			System.out.println(img.getSize());
			session.update(image);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void deleteImage(int sno) {
		try {
			Session session = HibernateUtil.getSession();
			session.beginTransaction();
			Image image = session.get(Image.class,sno);
			session.delete(image); 
			session.getTransaction().commit();
			System.out.println("deletion successful!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Image getImage(int sno) {
		Image image = null;
		Session session = HibernateUtil.getSession();
		try {
			session.getTransaction().begin();
			System.out.println(sno);
			String queryString = "from Image where sno = :sno";
			Query query = session.createQuery(queryString);
			query.setParameter("sno", sno);
			Object queryResult = query.uniqueResult();
			image = (Image)queryResult;
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return image;
	}

}
