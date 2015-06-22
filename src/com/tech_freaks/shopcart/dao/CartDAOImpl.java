package com.tech_freaks.shopcart.dao;

import java.util.List;

import org.hibernate.Query;

import com.tech_freaks.shopcart.model.Cart;

public class CartDAOImpl extends AbstractDAO<Cart> implements CartDAO {

	@Override
	public Integer createNewCart(Cart cart) {
		return persist(cart);
	}
	
	@Override
	public List<Cart> findByStatus(char status, boolean openSession) {
		String hql = "FROM Cart C WHERE C.status = '"+status+"'";
		Query query = null;
		if(openSession)
			query = openSession().createQuery(hql);
		else
			query =getSession().createQuery(hql);
		List<Cart> results = (List<Cart>) query.list();
		return results;
	}

	@Override
	public void updateCart(Cart cart) {
		update(cart);
	}
}
