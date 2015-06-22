package com.tech_freaks.shopcart.dao;

import java.util.List;

import com.tech_freaks.shopcart.model.Cart;

public interface CartDAO {
	
	public static final char ACTIVE_CART_STATUS = 'A';
	public static final char INACTIVE_CART_STATUS = 'I';
	public static final char COMPLETE_CART_STATUS = 'C';

	public Integer createNewCart(Cart cart) ;

	public void updateCart(Cart cart);
	
	public List<Cart> findByStatus(char status, boolean openSession);
}
