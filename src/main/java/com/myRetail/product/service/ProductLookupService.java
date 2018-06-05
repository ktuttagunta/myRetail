package com.myRetail.product.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.myRetail.product.dao.PriceDAO;
import com.myRetail.product.dao.ProductDAO;
import com.myRetail.product.model.Price;
import com.myRetail.product.model.Product;

@Service("productLookupService")
public class ProductLookupService {
	 
	 @Autowired
	 PriceDAO priceDAO;
	 @Autowired
	 ProductDAO prodDAO;
	 
	 @Autowired
		Environment env;
	 
	
	public ResponseEntity<Product> getProduct(String prodId)
	{
		ResponseEntity<Product> response = prodDAO.getProductFromRedSky(prodId);
		if (null == response || null == response.getBody() || response.getStatusCode() != HttpStatus.OK) {
			return createErrorResponse("Product Info not Found",HttpStatus.NOT_FOUND);

		} else {
			Price price = priceDAO.getPriceInfo(prodId);
			response.getBody().setPrice(price);

		}
		return response;
	}
	
	private ResponseEntity<Product> createErrorResponse(String errorMessage, HttpStatus status) {
		Product errorProduct = new Product();
		errorProduct.setErrorMessage(errorMessage);
		return new ResponseEntity<>(errorProduct, status);

	}

	public ResponseEntity<Product> putProduct(Product product)
	{
		if(null == product.getPrice() || null == product.getId())
		{
			return createErrorResponse("Invalid Request Product Id and Price info are Required",HttpStatus.BAD_REQUEST);
		}
		String  status = priceDAO.puPriceInfo(product.getPrice(), product.getId());
		product.setStatus(status);
		return new ResponseEntity<Product>(product,HttpStatus.OK);
	}
}





