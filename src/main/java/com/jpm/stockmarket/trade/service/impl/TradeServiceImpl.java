package com.jpm.stockmarket.trade.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jpm.stockmarket.stock.service.StockService;
import com.jpm.stockmarket.stock.service.impl.StockServiceImpl;
import com.jpm.stockmarket.trade.model.StockTradeRequest;
import com.jpm.stockmarket.trade.model.Trade;
import com.jpm.stockmarket.trade.repository.TradeRepository;
import com.jpm.stockmarket.trade.service.TradeService;

@Service
public class TradeServiceImpl implements TradeService {
	private static final Logger log = LoggerFactory.getLogger(StockServiceImpl.class);
	
	@Autowired
	private TradeRepository tradeRepository;
	
	@Autowired
	private StockService stockService;

	@Override
	public void addTrade(StockTradeRequest tradeRequest) {
		log.debug("Adding trade {} ", tradeRequest);
		stockService.getStock(tradeRequest.getSymbol());
		
		Trade trade = new Trade.Builder().symbol(tradeRequest.getSymbol()).type(tradeRequest.getType())
				.tradedPrice(tradeRequest.getTradePrice()).sharesQuantity(tradeRequest.getSharesQuantity()).timestamp(new Date()).build();
		
		tradeRepository.create(tradeRequest.getSymbol(), trade);
		log.debug("Adding trade Success");
		
	}

	@Override
	public List<Trade> getTradesInLastMinutes(String symbol, int minutes) {
		log.debug("Geting Last Trades for symbol {} in last minutes {} ", symbol, minutes);
		List<Trade> trades = tradeRepository.tradesInlastMinutes(symbol, minutes);
		
		log.debug("Geting Last Trades [{}] for symbol {} in last minutes {} ", trades, symbol, minutes);
		return trades;
	}

	@Override
	public List<Trade> getAllTrades() {
		log.debug("Geting All trades");
		List<Trade> trades = tradeRepository.getAllTrades();
		
		log.debug("Geting All Trades [{}]", trades);
		return trades;
	}

	
	
	


	
}
