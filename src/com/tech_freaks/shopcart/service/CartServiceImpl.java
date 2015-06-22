package com.tech_freaks.shopcart.service;

import java.math.BigDecimal;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tech_freaks.shopcart.dao.CartDAO;
import com.tech_freaks.shopcart.dao.CartItemDAO;
import com.tech_freaks.shopcart.model.Cart;
import com.tech_freaks.shopcart.model.Cartitem;

public class CartServiceImpl implements CartService {

	protected final Log logger = LogFactory.getLog(getClass());

	private CartDAO cartDao;

	private CartItemDAO cartItemDao;

	public void setCartDao(CartDAO cartDao) {
		this.cartDao = cartDao;
	}

	public void setCartItemDao(CartItemDAO cartItemDao) {
		this.cartItemDao = cartItemDao;
	}

	@Override
	@Transactional
	public Cart getActiveCart(boolean openSession) {
		List<Cart> currentCarts = cartDao
				.findByStatus(CartDAO.ACTIVE_CART_STATUS, openSession);
		Cart currCart = null;

		// No active carts
		if (currentCarts == null || currentCarts.size() == 0) {
			currCart = createCart();
		} else {
			currCart = currentCarts.get(0);
			logger.info("Cart id fetched from db is "+currCart.getCartId());
		}
		return currCart;
	}

	@Override
	@Transactional
	public void addToCart(Cartitem cartItem) {
		Cart currCart = getActiveCart(false);

		// We have an active cart here
		BigDecimal bigQty = new BigDecimal(cartItem.getQuantity());
		cartItem.setCart(currCart);

		cartItem.setTotalprice(cartItem.getUnitprice().multiply(bigQty));

		
		
		Integer cartItemId = cartItemDao.addCartItem(cartItem);
		logger.info("Cart item id inserted is "+cartItemId);
		cartItem.setCartitemId(cartItemId);
		currCart.getCartitems().add(cartItem);
		recalcCartTotal(currCart);
		cartDao.updateCart(currCart);
		
	}
	
	@Override
	@Transactional
	public void removeFromCart(Cartitem cartItem) {
		cartItemDao.removeCartItem(cartItem);
		
		Cart currCart = getActiveCart(false);
		recalcCartTotal(currCart);
		cartDao.updateCart(currCart);
		
	}
	
	@Override
	@Transactional
	public void updateCartItem(Cartitem cartItem) {
		cartItemDao.updateCartItem(cartItem);
		
		Cart currCart = getActiveCart(false);
		recalcCartTotal(currCart);
		cartDao.updateCart(currCart);
	}
	
	public void recalcCartTotal(Cart currCart ) {
		BigDecimal totalCart = new BigDecimal(0);
		for(Object oCartItem : currCart.getCartitems()) {;
			Cartitem cartItem = (Cartitem) oCartItem;
			totalCart = totalCart.add(cartItem.getTotalprice());
			logger.info("cartItem total is "+cartItem.getTotalprice().toString());
			logger.info("total cart now is "+totalCart.toString());
		}
		currCart.setTotalPrice(totalCart);
		currCart.setProductPrice(totalCart);
		
	}

	@Transactional
	public Cart createCart() {
		Cart currCart = new Cart();
		currCart.setStatus(CartDAO.ACTIVE_CART_STATUS);
		currCart.setProductPrice(new BigDecimal(0.0));
		currCart.setTotalPrice(new BigDecimal(0.0));
		currCart.setTaxPrice(new BigDecimal(0.0));
		currCart.setShippingPrice(new BigDecimal(0.0));
		Integer cartId = cartDao.createNewCart(currCart);
		currCart.setCartId(cartId);
		logger.info("Cart with id created " + cartId);
		return currCart;
	}

}
