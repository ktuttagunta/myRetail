package com.myRetail.product.controller;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.myRetail.product.model.Price;
import com.myRetail.product.model.Product;
import com.myRetail.product.service.ProductLookupService;

@ActiveProfiles("unit-test")
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class ProductLookupControllerTest {
	
	@Mock (name = "productLookupService")
	ProductLookupService service;
	
	@InjectMocks
	ProductLookupController controller;
	
	
	 private MockMvc mockMvc;
	
	@Before
	public void setUp() {
		   MockitoAnnotations.initMocks(this);
	       mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	@Test
	public final void getProduct() throws Exception {
		
		String id="13860428";

       
        Product product=new Product();
        product.setId(id);
        product.setName("The Big Lebowski");
        Price price = new Price();
        price.setValue(new BigDecimal(25.50));
        price.setCurrency_code("USD");
        ResponseEntity<Product> responseEntity = new ResponseEntity<Product>(product,HttpStatus.OK);

        product.setPrice(price);
        when(service.getProduct("13860428")).thenReturn(responseEntity);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/products/13860428").accept(
				MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertTrue(200 ==result.getResponse().getStatus());
       
    }
	

}
