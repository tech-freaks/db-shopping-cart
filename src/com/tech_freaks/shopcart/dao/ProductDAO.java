package com.tech_freaks.shopcart.dao;

import java.util.List;

import com.tech_freaks.shopcart.model.Product;

public interface ProductDAO {

	public List<Product> getAllProducts();
	
	public Product findById(Integer id);
}
