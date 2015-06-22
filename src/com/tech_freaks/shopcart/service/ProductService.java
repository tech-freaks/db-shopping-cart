package com.tech_freaks.shopcart.service;

import java.util.List;

import com.tech_freaks.shopcart.model.Product;

public interface ProductService {

	public List<Product> listProducts();
	
	public Product getProduct(Integer id);
}
