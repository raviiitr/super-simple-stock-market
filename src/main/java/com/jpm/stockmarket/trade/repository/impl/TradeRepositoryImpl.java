package com.jpm.stockmarket.trade.repository.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jpm.stockmarket.common.repository.impl.CommonRepositoryImpl;
import com.jpm.stockmarket.trade.database.StockTradeDB;
import com.jpm.stockmarket.trade.model.Trade;
import com.jpm.stockmarket.trade.repository.TradeRepository;

@Repository
public class TradeRepositoryImpl extends CommonRepositoryImpl<String, List<Trade>> implements TradeRepository {
	
	@Autowired
	private StockTradeDB stockTradeDB;

	@Override
	protected ConcurrentHashMap<String, List<Trade>> getMap() {
		return stockTradeDB.getStockMarketDB();
	}

	@Override
	public Trade create(String key, Trade value) {
		List<Trade> trades = load(key);
		if(trades == null){
			trades = new ArrayList<Trade>();
		}
		trades.add(value);
		create(key, trades);
		return value;
	}	
	
    @Override
    public List<Trade> tradesInlastMinutes(String key, int minutes) {

        Date date = new Date();
        long time = date.getTime() - (minutes*60*1000);
        List<Trade> listTrades = load(key);
        List<Trade> tradesInLastMinutes = null;
        
        if(listTrades!=null)
        	tradesInLastMinutes = listTrades
            .stream()
            .filter(entity -> entity.getTimestamp().getTime() >= time)
            .collect(Collectors.toList());
        
        return tradesInLastMinutes;
    }

	@Override
	public List<Trade> getAllTrades() {
		List<Trade> trades = list().stream()
			        .flatMap(List::stream)
			        .collect(Collectors.toList());
		return trades;
	}
    
}
