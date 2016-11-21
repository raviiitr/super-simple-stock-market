package com.jpm.stockmarket.stock.repository.impl;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jpm.stockmarket.common.repository.impl.CommonRepositoryImpl;
import com.jpm.stockmarket.database.StockMarketDB;
import com.jpm.stockmarket.stock.model.Stock;
import com.jpm.stockmarket.stock.repository.StockRepository;

@Repository
public class StockRepositoryImpl extends CommonRepositoryImpl<String, Stock> implements StockRepository {

	@Autowired
	private StockMarketDB stockMarketDB;
	
	@Override
	protected ConcurrentHashMap<String, Stock> getMap() {
		return stockMarketDB.getStockMarketDB();
	}

	
}
