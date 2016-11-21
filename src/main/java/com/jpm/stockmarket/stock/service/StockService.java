package com.jpm.stockmarket.stock.service;

import com.jpm.stockmarket.stock.model.Stock;

public interface StockService{
	Double getDividendYield(String stockSymbol, Double stockPrice);
	
	Stock getStock(String stockSymbol);

	Double getPERatio(String symbol, Double stockPrice);
	
	Double getVolWeightedPice(String symbol);

}
