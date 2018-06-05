package com.myRetail.product.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import com.myRetail.product.model.Product;
import com.myRetail.product.service.ProductLookupService;

@RestController
public class ProductLookupController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductLookupController.class);
	@Autowired
	ProductLookupService service;

	@RequestMapping(value="/product/{prodId}",method=RequestMethod.GET,produces= {"application/json; charset=utf-8"})
	
	public DeferredResult<ResponseEntity<Product>> getProduct(@PathVariable("prodId") String prodId,@RequestHeader MultiValueMap<String,String> headers)
	{
		// Validate the body with Rules .
		DeferredResult<ResponseEntity<Product>> defferedResult= new DeferredResult<ResponseEntity<Product>>();
		
		defferedResult.setResult(service.getProduct(prodId));
		
		return defferedResult;
	}
	
	/*
	 * PUT REquest
	 */

		@RequestMapping(value="/product/{prodId}",method=RequestMethod.PUT,produces= {"application/json; charset=utf-8"})
		
		public DeferredResult<ResponseEntity<Product>> putProduct(@PathVariable("prodId") String prodId,@RequestBody Product product,@RequestHeader MultiValueMap<String,String> headers)
		{
			DeferredResult<ResponseEntity<Product>> defferedResult= new DeferredResult<ResponseEntity<Product>>();
			
			defferedResult.setResult(service.putProduct(product));
			
			return defferedResult;
		}
}
