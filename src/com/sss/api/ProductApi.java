package com.sss.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sss.pojo.Product;
import com.sss.service.ProductService;
import com.sss.util.ProductUtil;

/**
 * @author Santhosh
 *
 */
@Path("/api/product")
public class ProductApi {
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Product> getProductsList() throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		ProductService service = ProductUtil.getProductService();
		return service.getAllProducts();
	}

}
