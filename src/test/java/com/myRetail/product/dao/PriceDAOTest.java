package com.myRetail.product.dao;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.datastax.driver.core.querybuilder.Select;
import com.myRetail.product.model.Price;
import com.myRetail.product.model.PriceEntity;

@ActiveProfiles("unit-test")
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class PriceDAOTest {
	
	@InjectMocks
	PriceDAO dao;
	
	@Mock(name = "cassandraTemplate")
	CassandraOperations cassandraTemplate;
	
	
	private PriceEntity priceEntity;
	
	private Price price;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		//RestAssuredMockMvc.webAppContextSetup(context);
		priceEntity = new PriceEntity();
		priceEntity.setPrice(new BigDecimal("450.50"));
		priceEntity.setCurrency("USD");
		priceEntity.setProdid("222");
		price = new Price();
		price.setCurrency_code("USD");
		price.setValue(new BigDecimal("450.50"));
	}
	
	
	
	@Test
	public final void testGetPrice() {

		Mockito.when(cassandraTemplate.selectOne(Mockito.isA(Select.class), Mockito.any()))
				.thenReturn(priceEntity);
		Price result = dao.getPriceInfo("222");		
		assertTrue(result != null);
		assertTrue(result.getValue().equals(price.getValue()));
	}



}
