package com.jpm.stockmarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jpm.stockmarket.model.GetGBCEResponse;
import com.jpm.stockmarket.service.StockMarketService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/exchange")
public class StockMarketController {

	@Autowired
	private StockMarketService stockMarketService;

	@ApiOperation(value = "Calculate GBCE Index", notes = "Calculate GBCE Index")
	@RequestMapping(value = "/gbce", method = RequestMethod.GET)
	public GetGBCEResponse getGBCEIndex() {
		Double gbce = stockMarketService.getGBCE();
		GetGBCEResponse response = new GetGBCEResponse.Builder().gbce(gbce).build();
		return response;
	}

}
