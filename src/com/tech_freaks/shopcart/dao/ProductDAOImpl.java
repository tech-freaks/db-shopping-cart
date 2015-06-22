package com.tech_freaks.shopcart.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tech_freaks.shopcart.model.Product;

public class ProductDAOImpl extends AbstractDAO implements ProductDAO {
	
	
	//private static final Logger logger = LoggerFactory.getLogger(PersonDAOImpl.class);
	@Override
	public List<Product> getAllProducts() {
		Session session = getSession();
        List<Product> productList = session.createQuery("from Product").list();
        return productList;
	}
	
	@Override
	public Product findById(Integer id) {
		return (Product) getSession().get(Product.class, id);
	}

}
