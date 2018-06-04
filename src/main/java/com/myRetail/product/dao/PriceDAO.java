package com.myRetail.product.dao;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import com.datastax.driver.core.querybuilder.Update;
import com.myRetail.product.model.Price;
import com.myRetail.product.model.PriceEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.stereotype.Component;

@Component("priceDAO")
public class PriceDAO {
	@Autowired
	public Environment env;
	public static final String KEYSPACE = "product";
	public static final String TABLE_NAME = "price";
	public static final String PROD_ID = "prodid";
	public static final String SUCCESS = "SUCCESS";
	public static final String FAILURE = "FAIL";
	
	@Autowired
	CassandraOperations cassandraTemplate;
	@Autowired
	Session session;

	public Price getPriceInfo(String prodId) {
		Price price = null;
		PriceEntity priceEntity = new PriceEntity();
		try {
			
			Select cqlGetPrice = QueryBuilder.select().from(TABLE_NAME);
			cqlGetPrice.where(QueryBuilder.eq(PROD_ID, prodId));
			priceEntity = cassandraTemplate.selectOne(cqlGetPrice, PriceEntity.class);
			price = new Price();
			price.setCurrency_code(priceEntity.getCurrency());
			price.setValue(priceEntity.getPrice());
		} catch (Exception e) {
			e.printStackTrace();
			// Do nothing
		}
		return price;

	}

	public String puPriceInfo(Price price, String prodId) {
		try {

			Update cqlPutPrice = QueryBuilder.update(TABLE_NAME);
			if (null != price.getValue()) {
				cqlPutPrice.with(QueryBuilder.set("price", price.getValue().toPlainString()));
			}
			if (null != price.getCurrency_code()) {
				cqlPutPrice.with(QueryBuilder.set("currency", price.getCurrency_code()));
			}
			cqlPutPrice.where(QueryBuilder.eq(PROD_ID, prodId));
			
			session.execute(cqlPutPrice.toString());

		} catch (Exception e) {
			e.printStackTrace();
			return FAILURE;
		}
		return SUCCESS;

	}

}
