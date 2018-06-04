package com.myRetail.product.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.cassandra.core.CassandraAdminOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;
import com.myRetail.product.dao.PriceDAO;
import com.myRetail.product.dao.ProductDAO;
import com.myRetail.product.model.Price;
import com.myRetail.product.model.Product;

@ActiveProfiles("unit-test")
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class ProductLookupControllerTest {


	
	private static CassandraAdminOperations adminTemplate;
	@Mock
	public RestTemplate restTemplate;
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		//RestAssuredMockMvc.webAppContextSetup(context);
		//RestAssuredMockMvc.config().asyncConfig(withTimeout(10, TimeUnit.SECONDS));

	}
	
	@Test
	public void test() {
		PriceDAO priceDao = new PriceDAO();
		Price price = priceDao.getPriceInfo("222");
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$4"+price.getValue());
		assertTrue(price.getCurrency_code().equals("USD"));
		
		ProductDAO producteDao = new ProductDAO();
		ResponseEntity<Product> response = producteDao.getProductFromRedSky("222");
		//assertTrue(null != response.getBody());
		assertTrue(HttpStatus.NOT_FOUND == response.getStatusCode());
		
	}

	@Test
	public void test_Success() {		
		ProductDAO producteDao = new ProductDAO();
		ResponseEntity<Product> response = producteDao.getProductFromRedSky("13860428");
		//assertTrue(null != response.getBody());
		assertTrue(HttpStatus.OK == response.getStatusCode());
		
	}
	
	@Test
	public void test_Controller() {		
		ProductDAO producteDao = new ProductDAO();
		ResponseEntity<Product> response = producteDao.getProductFromRedSky("13860428");
		//assertTrue(null != response.getBody());
		assertTrue(HttpStatus.OK == response.getStatusCode());
		
	}


}
