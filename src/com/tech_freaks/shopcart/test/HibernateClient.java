package com.tech_freaks.shopcart.test;

import java.math.BigDecimal;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.tech_freaks.shopcart.model.Product;

public class HibernateClient {
	
	public static void main(String[] args) {
		
		Transaction tx = null;
		Session session = null;
		SessionFactory factory= null; 
		 try{
	         factory = new Configuration().configure().buildSessionFactory();
	      }catch (Throwable ex) { 
	         System.err.println("Failed to create sessionFactory object." + ex);
	         throw new ExceptionInInitializerError(ex); 
	      }
		try {
			//factory = new Configuration().configure().buildSessionFactory();
			session = factory.openSession();
		   tx = session.beginTransaction();
		   // do some work
		   Product product = new Product("Nokia Lumia 930", "260665", "The Nokia Lumia 930 comes with the latest Windows Phone experience, so the things that matter most are always with you whether you’re on your smartphone, Xbox or PC. Exclusive LiveTiles keep you up to speed with what’s happening in your world, while new features like customisable background images for your home screen make Windows Phone 8.1 more personal than ever.", "/images/lumia930.png", 'Y', new BigDecimal(349.00));
		   Integer productId = (Integer) session.save(product);
		   System.out.println("Product id saved is "+productId);
		   tx.commit();
		}
		catch (Exception e) {
		   if (tx!=null) tx.rollback();
		   e.printStackTrace(); 
		}finally {
		   session.close();
		}
	}
}
