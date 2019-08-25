package com.crawler.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crawler.model.Stock;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
@CrossOrigin
@RestController
//restcontroller use jackson serialization by default .it does not use JSONObject of org.json packages
public class ApiController {

	// public Greeting greeting(@RequestParam(value="name", defaultValue="World")
	// String name) {
	@RequestMapping("/api/getStockData")
	public ResponseEntity<List<Stock>> stockData() {

		List<Stock> response = new ArrayList<Stock>();

			Document doc;
			try {
				doc = Jsoup.connect("https://www.msn.com/en-us/money/markets").get();
				// work-around as jsoup cant select element with classes that contain (); . <ul>
				Elements eles = doc.select("div.secondaryIndicesPane > div > a");

				int i = 0;
				for(Element s: eles) {
				Stock stock = new Stock();
					stock.setCompany(s.select("div.secondaryIndexTile > div.inline.title.fontbase > p").text());
					stock.setPrice(s.select("div.secondaryIndexTile > div.inline.price.fontbase").text());
					stock.setPercentage(s.select("div.secondaryIndexTile > div.inline.chp.fontbaseminus1").text());
					response.add(stock);			
					i++;
				}		
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		return new ResponseEntity<>(
			      response, 
			      HttpStatus.OK);
	}// end stockData()

}
