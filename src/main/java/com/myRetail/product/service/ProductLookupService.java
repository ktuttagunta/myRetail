package com.myRetail.product.service;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.myRetail.product.model.Product;

@Service("productLookupService")
public class ProductLookupService {
	
	public ResponseEntity<Product> getProduct(String prodId)
	{
		return null;
		
	}

}
