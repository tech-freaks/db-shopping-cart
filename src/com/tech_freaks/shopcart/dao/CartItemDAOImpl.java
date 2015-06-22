package com.tech_freaks.shopcart.dao;

import java.math.BigDecimal;

import com.tech_freaks.shopcart.model.Cartitem;
import com.tech_freaks.shopcart.service.CartService;

public class CartItemDAOImpl extends AbstractDAO<Cartitem> implements
		CartItemDAO  {


	@Override
	public Integer addCartItem(Cartitem cartItem) {
		return persist(cartItem);
	}

	@Override
	public void removeCartItem(Cartitem cartItem) {
		cartItem = findById(cartItem.getCartitemId(), Cartitem.class);
		delete(cartItem);

	}

	@Override
	public void updateCartItem(Cartitem cartItem) {
		Cartitem currCartItem = findById(cartItem.getCartitemId(), Cartitem.class);
		currCartItem.setQuantity(cartItem.getQuantity());
		currCartItem.setTotalprice(currCartItem.getUnitprice().multiply(new BigDecimal(cartItem.getQuantity())));
		update(currCartItem);

	}
	
	

}
