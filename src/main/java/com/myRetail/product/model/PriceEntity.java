package com.myRetail.product.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({"value","currency_code"})
public class PriceEntity {
	@JsonProperty("prodid")
	String prodid;
	@JsonProperty("price")
	BigDecimal price;
	@JsonProperty("currency")
	String currency;
	/**
	 * @return the prodid
	 */
	public String getProdid() {
		return prodid;
	}
	/**
	 * @param prodid the prodid to set
	 */
	public void setProdid(String prodid) {
		this.prodid = prodid;
	}
	/**
	 * @return the price
	 */
	public BigDecimal getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	/**
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	}
	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

}
