package com.tech_freaks.shopcart.controller;

import java.math.BigDecimal;
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
	CartService cartService ;
	
	@Autowired
	ProductService productService;

	protected final Log logger = LogFactory.getLog(getClass());
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public ModelAndView addToCart(@RequestParam Map<String, String> request) {
		logger.info("Inside add to cart "+request.get("productId")+ " qty "+request.get("quantity"));
		Cart cart = null;
		try {
			BigDecimal bigQty = new BigDecimal(request.get("quantity"));
			Cartitem cartItem = new Cartitem();
			Product product = productService.getProduct(new Integer(request.get("productId")));
			logger.info("product fetched from db is "+product.getName());
			cartItem.setProduct(product);
			cartItem.setQuantity(Integer.parseInt(request.get("quantity")));
			cartItem.setUnitprice(product.getUnitprice());
			cartItem.setTotalprice(cartItem.getUnitprice().multiply(bigQty));
			cartService.addToCart(cartItem);
			
			cart = cartService.getActiveCart(true);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("cart", "cart", cart  );
	}
	
	@RequestMapping(value="/view", method=RequestMethod.GET)
	public ModelAndView viewCart() {
		Cart cart = cartService.getActiveCart(true);
		logger.info("The active cart id is "+cart.getCartId());
		return new ModelAndView("cart", "cart", cart  );
	}
	
	@RequestMapping(value="/change", method=RequestMethod.POST)
	public ModelAndView changeCart(@RequestParam Map<String, String> request) {
		logger.info("Inside change to cart "+request.get("cartItemId")+ " qty "+request.get("quantity")+" action is "+request.get("action"));
		String action  = request.get("action");
		if(action!=null && !action.equals("")) {
			Cartitem cartItem = new Cartitem();
			cartItem.setCartitemId(new Integer(request.get("cartItemId")));
			cartItem.setQuantity(Integer.parseInt(request.get("quantity")));
			if(action.equals(UPDATE_ACTION)) {
				cartService.updateCartItem(cartItem);
			} else { // Delete action
				cartService.removeFromCart(cartItem);
			}
		}
		
		Cart cart = cartService.getActiveCart(true);
		logger.info("The active cart id is "+cart.getCartId());
		return new ModelAndView("cart", "cart", cart  );
	}
	
	
}
