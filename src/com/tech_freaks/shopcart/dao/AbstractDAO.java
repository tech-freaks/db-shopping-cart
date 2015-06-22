package com.tech_freaks.shopcart.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class AbstractDAO <T> {
	
	@Autowired
    private SessionFactory sessionFactory;
 
    protected Session getSession(){
        return sessionFactory.getCurrentSession();
    }
    
    protected Session openSession() {
    	return sessionFactory.openSession();
    }
    
    public <T>  T findById(Integer id, Class myClass) {
    	return (T) getSession().load(myClass, id);
    }
 
    public Integer persist(T t) {
       return (Integer) getSession().save(t);
    }
    
    public void update(T t) {
    	getSession().update(t);
    	getSession().flush();
    }
     
    public void delete(T t) {
        getSession().delete(t);
        getSession().flush();
    }
}
