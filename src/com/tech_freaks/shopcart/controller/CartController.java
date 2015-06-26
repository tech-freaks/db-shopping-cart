package com.tech_freaks.shopcart.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tech_freaks.shopcart.model.Cart;
import com.tech_freaks.shopcart.model.Cartitem;
import com.tech_freaks.shopcart.model.Product;
import com.tech_freaks.shopcart.service.CartService;
import com.tech_freaks.shopcart.service.ProductService;

@Controller
@RequestMapping("/cart")
public class CartController {

	private final static String UPDATE_ACTION = "Update";
	private final static String DELETE_ACTION = "Delete";

	@Autowired
	CartService cartService;

	@Autowired
	ProductService productService;

	protected final Log logger = LogFactory.getLog(getClass());

	private boolean validateQuantity(String strQty) {
		boolean isSuccess = false;
		try {
			int qty = Integer.parseInt(strQty);
			if (qty > 0)
				isSuccess = true;

		} catch (NumberFormatException nfe) {
			logger.error("Quantity value entered is not numeric " + strQty);
		}
		return isSuccess;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView addToCart(@RequestParam Map<String, String> request) {
		logger.info("Inside add to cart " + request.get("productId") + " qty "
				+ request.get("quantity"));
		Cart cart = null;
		Map<String, Object> response = new HashMap<>();
		if (validateQuantity(request.get("quantity"))) {

			try {
				BigDecimal bigQty = new BigDecimal(request.get("quantity"));
				Cartitem cartItem = new Cartitem();
				Product product = productService.getProduct(new Integer(request
						.get("productId")));
				logger.info("product fetched from db is " + product.getName());
				cartItem.setProduct(product);
				cartItem.setQuantity(Integer.parseInt(request.get("quantity")));
				cartItem.setUnitprice(product.getUnitprice());
				cartItem.setTotalprice(cartItem.getUnitprice().multiply(bigQty));
				cartService.addToCart(cartItem);

				cart = cartService.getActiveCart(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			cart = cartService.getActiveCart(true);
			response.put("errorMessage",
					"Item was not added to cart. Quantity should be numeric value greater than 0");
			// model.addAttribute();
		}
		response.put("cart", cart);

		return new ModelAndView("cart", response);
	}

	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public ModelAndView viewCart() {
		Cart cart = cartService.getActiveCart(true);
		logger.info("The active cart id is " + cart.getCartId());
		return new ModelAndView("cart", "cart", cart);
	}

	@RequestMapping(value = "/change", method = RequestMethod.POST)
	public ModelAndView changeCart(@RequestParam Map<String, String> request) {
		Map<String, Object> response = new HashMap<>();
		logger.info("Inside change to cart " + request.get("cartItemId")
				+ " qty " + request.get("quantity") + " action is "
				+ request.get("action"));
		String action = request.get("action");
		if (action != null && !action.equals("")) {
			if (validateQuantity(request.get("quantity"))) {
				Cartitem cartItem = new Cartitem();
				cartItem.setCartitemId(new Integer(request.get("cartItemId")));
				cartItem.setQuantity(Integer.parseInt(request.get("quantity")));
				if (action.equals(UPDATE_ACTION)) {
					cartService.updateCartItem(cartItem);
				} else { // Delete action
					cartService.removeFromCart(cartItem);
				}
			} else {
				response.put("errorMessage",
						"Unable to update item in cart. Quantity should be numeric value greater than 0");
			}
		}

		Cart cart = cartService.getActiveCart(true);
		response.put("cart", cart);
		logger.info("The active cart id is " + cart.getCartId());
		return new ModelAndView("cart", response);
	}

}
