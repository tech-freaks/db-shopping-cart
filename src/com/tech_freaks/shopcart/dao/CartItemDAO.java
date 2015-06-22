package com.tech_freaks.shopcart.dao;

import com.tech_freaks.shopcart.model.Cartitem;

public interface CartItemDAO  {

	public Integer addCartItem(Cartitem cartItem);
	
	public void removeCartItem(Cartitem cartItem);
	
	public void updateCartItem(Cartitem cartItem);
}
