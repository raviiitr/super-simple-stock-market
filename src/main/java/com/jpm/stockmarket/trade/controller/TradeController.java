package com.jpm.stockmarket.trade.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jpm.stockmarket.common.model.APIResponse;
import com.jpm.stockmarket.common.model.ResultCode;
import com.jpm.stockmarket.trade.model.StockTradeRequest;
import com.jpm.stockmarket.trade.service.TradeService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/exchange")
public class TradeController {
	
	@Autowired
	private TradeService tradeService;
    
	@ApiOperation(
			value = "Post a Stock Trade", 
			notes = "Post a Stock Trade"
	)
	@RequestMapping(value = "/trade", method = RequestMethod.POST)
    public APIResponse<Boolean> postTrade(@Valid @RequestBody StockTradeRequest tradeRequest) {
		tradeService.addTrade(tradeRequest);
        return new APIResponse<>(ResultCode.SUCCESS);
    }
	    
}
