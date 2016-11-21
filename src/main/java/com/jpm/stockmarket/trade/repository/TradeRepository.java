package com.jpm.stockmarket.trade.repository;

import java.util.List;

import com.jpm.stockmarket.common.repository.CommonRepository;
import com.jpm.stockmarket.trade.model.Trade;

public interface TradeRepository extends CommonRepository<String, List<Trade>> {
	Trade create(String key, Trade value);

	List<Trade> tradesInlastMinutes(String key, int minutes);
	
	List<Trade> getAllTrades();
	
}
