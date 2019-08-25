package com.crawler.model;

import java.io.Serializable;

import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
public class Stock implements Serializable {
	private String company, price, percentage;

	public Stock() {
	}
	
	  Stock(String company, String price,String percentage) {
		    this.company = company;
		    this.price = price;
		    this.percentage = percentage;
		  }
}
