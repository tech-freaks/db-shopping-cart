package com.tech_freaks.shopcart.service;

import java.util.List;

import javax.transaction.Transactional;

import com.tech_freaks.shopcart.dao.ProductDAO;
import com.tech_freaks.shopcart.model.Product;

public class ProductServiceImpl implements ProductService {
	
	
	private ProductDAO productDAO;
	
	public void setProductDao(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}

	@Override
	@Transactional
	public List<Product> listProducts() {
		
		return productDAO.getAllProducts();
	}

	@Override
	@Transactional
	public Product getProduct(Integer id) {
		return productDAO.findById(id);
	}
}
