/**
 * 
 */
package com.sss.service;

import java.util.List;

import com.sss.pojo.Product;

/**
 * @author Santhosh
 *
 *	Interface related to Products
 */
public interface ProductService {
	
	/**
	 * Fetch all products of user
	 * @param request
	 * @return
	 */
	public abstract List<Product> getAllProducts();
	
}