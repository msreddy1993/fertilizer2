/**
 * 
 */
package com.sss.service.impl;

import java.util.List;

import com.sss.dao.ProductDao;
import com.sss.pojo.Product;
import com.sss.service.ProductService;

/**
 * @author Santhosh
 *
 */
public class ProductServiceImpl implements ProductService{

	/* (non-Javadoc)
	 * @see com.sss.service.ProductService#getAllProducts(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public List<Product> getAllProducts() {
		ProductDao dao = new ProductDao();
		return dao.getAllProducts();
	}
	
}
