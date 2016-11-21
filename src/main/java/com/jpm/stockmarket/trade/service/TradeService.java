package com.jpm.stockmarket.trade.service;

import java.util.List;

import com.jpm.stockmarket.trade.model.StockTradeRequest;
import com.jpm.stockmarket.trade.model.Trade;

public interface TradeService{

	void addTrade(StockTradeRequest tradeRequest);
	
	List<Trade> getTradesInLastMinutes(String symbol, int minutes);

	List<Trade> getAllTrades();

}
