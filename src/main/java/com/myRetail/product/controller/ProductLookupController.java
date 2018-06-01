package com.myRetail.product.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.servlet.mvc.condition.ProducesRequestCondition;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.myRetail.product.model.Product;
import org.springframework.web.context.request.async.DeferredResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class ProductLookupController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductLookupController.class);

	@RequestMapping(value="/url/{prodId}",method=RequestMethod.GET,produces= {"application/json; charset=utf-8"})
	
	public DeferredResult<ResponseEntity<Product>> getProduct(@RequestHeader MultiValueMap<String,String> headers)
	{
		DeferredResult<ResponseEntity<Product>> defferedResult= new DeferredResult();
		
		return defferedResult;
	}
}
