package com.tech_freaks.shopcart.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tech_freaks.shopcart.model.Product;
import com.tech_freaks.shopcart.service.ProductService;

@Controller
@RequestMapping("/products")
public class ProductListController {
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private ProductService productService;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getProducts() {
		Map<String, List<Product>> map = new HashMap<>();
		logger.info("Inside Products " );
		List<Product> productList = productService.listProducts();
		if(productList!=null) {
			logger.info("Product in db count is "+productList.size());
			map.put("productList", productList);
		}
        return new ModelAndView("products", map);
	}
}
