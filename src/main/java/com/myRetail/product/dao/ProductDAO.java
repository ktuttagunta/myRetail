package com.myRetail.product.dao;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.myRetail.product.model.Product;

@Component("prodDAO")
public class ProductDAO {
	
	public ResponseEntity<Product> getProductFromRedSky(String prodId)
	{
		String PRODUCT_URL = "https://redsky.target.com/v2/pdp/tcin/"+prodId+"?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics";
		Product product = new Product();
		try {
			RestTemplate restTemplate = new RestTemplate();
			
			JsonNode root = restTemplate.getForObject(PRODUCT_URL, JsonNode.class);
			String name = root.at("/product/item/product_description/title").asText();
			product.setName(name);
			product.setId(prodId);
			return new ResponseEntity<Product>(product,HttpStatus.OK);
		}

		catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Product>(product,HttpStatus.NOT_FOUND);
		}


	}

}
