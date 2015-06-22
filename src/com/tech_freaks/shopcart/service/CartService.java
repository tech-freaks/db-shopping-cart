package com.tech_freaks.shopcart.service;

import com.tech_freaks.shopcart.model.Cart;
import com.tech_freaks.shopcart.model.Cartitem;

public interface CartService {

	public void addToCart(Cartitem cartItem);
	
	public Cart getActiveCart(boolean openSession);
	
	public void removeFromCart(Cartitem cartItem) ;
	
	public void updateCartItem(Cartitem cartItem);
}
