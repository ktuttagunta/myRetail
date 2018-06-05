package com.myRetail.product.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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
	
	@Autowired
	private WebApplicationContext context;
	
	 private MockMvc mockMvc;

	 private ObjectMapper mapper;
	
	@Before
	public void setUp() {
		   MockitoAnnotations.initMocks(this);
	       mapper = new ObjectMapper(new JsonFactory());
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
        ResponseEntity<Product> responseEntity = new ResponseEntity<Product>(HttpStatus.OK);

        product.setPrice(price);
        when(service.getProduct("13860428")).thenReturn(responseEntity);

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/product/13860428");

         MvcResult result = this.mockMvc.perform(builder).andExpect(status().isOk()).andReturn();

        String content = result.getResponse().getContentAsString();
        
        Product productResponse = mapper.readValue(content, new TypeReference<Product>() {
        });


        assert (productResponse.getName().equals("The Big Lebowski"));
    }
	

}
