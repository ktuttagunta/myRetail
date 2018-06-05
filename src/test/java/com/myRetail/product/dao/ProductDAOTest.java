package com.myRetail.product.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myRetail.product.model.Price;
import com.myRetail.product.model.Product;

@ActiveProfiles("unit-test")
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class ProductDAOTest {
	/** The rest template. */
	
	@Mock
	private RestTemplate restTemplate;
	
	
	@InjectMocks
	private ProductDAO dao;
	
	private Price price;

	private Product product;
	
	String PRODUCT_URL = "http://redsky.target.com/v2/pdp/tcin/13860428?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics";
	
	String responseJson = "{\"product\":{\"item\":{\"tcin\":\"13860428\",\"product_description\":{\"title\":\"The Big Lebowski (Blu-ray)\"}}}}";
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		product = new Product();
		product.setId("13860428");
		price = new Price();
		price.setCurrency_code("USD");
		price.setValue(new BigDecimal("450.50"));
		product.setPrice(price);
	}

	@Test
	public void testGetProductDetails() throws RestClientException, JsonParseException, IOException {
		
		JsonNode root = getJsonNode();
		System.out.println("#$$$$$$$$$$$$$4"+root.at("/product/item/product_description/title").asText());
		Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.any())).thenReturn(responseJson);
		
		ResponseEntity<Product> response = dao.getProductFromRedSky("13860428");

		assertNotNull(response);
		System.out.println("#$$$$$$$$$$$$$4"+response.getBody().getName());
		assertTrue(response.getBody().getName().equals("The Big Lebowski (Blu-ray)"));
	}
	 public JsonNode getJsonNode() throws JsonParseException, IOException  {
	        

	        ObjectMapper mapper = new ObjectMapper();
	        JsonFactory factory = mapper.getFactory();
	        JsonParser jp = factory.createParser(responseJson);
	        return mapper.readTree(jp);
	    }
}
