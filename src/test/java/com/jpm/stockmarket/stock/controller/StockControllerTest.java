package com.jpm.stockmarket.stock.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jpm.stockmarket.common.model.ResultCode;
import com.jpm.stockmarket.trade.model.StockTradeRequest;
import com.jpm.stockmarket.trade.model.TradeType;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class StockControllerTest {
	private static ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mvc;

    @Test
    public void getDividendYieldWithInvalidSymbol() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/exchange/stock/abc/dividendyield?stockPrice=10.0").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value(equalTo(ResultCode.STOCK_NOT_FOUND.getCode())))
                .andExpect(jsonPath("$.message").value(equalTo(ResultCode.STOCK_NOT_FOUND.getMessage())));
    }
    
    @Test
    public void getDividendYieldWithNoPrice() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/exchange/stock/POP/dividendyield").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value(equalTo(ResultCode.BAD_REQUEST.getCode())))
                .andExpect(jsonPath("$.message").value(equalTo(ResultCode.BAD_REQUEST.getMessage())));
    }
    
    @Test
    public void getDividendYield() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/exchange/stock/POP/dividendyield?stockPrice=2").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.dividendYield").value(equalTo(4.0)))
                .andExpect(jsonPath("$.symbol").value(equalTo("POP")));
    }
    
    @Test
    public void getPERatioWithInvalidSymbol() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/exchange/stock/abc/peratio?stockPrice=10.0").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value(equalTo(ResultCode.STOCK_NOT_FOUND.getCode())))
                .andExpect(jsonPath("$.message").value(equalTo(ResultCode.STOCK_NOT_FOUND.getMessage())));
    }
    
    @Test
    public void getPERatioWithNoPrice() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/exchange/stock/POP/peratio").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value(equalTo(ResultCode.BAD_REQUEST.getCode())))
                .andExpect(jsonPath("$.message").value(equalTo(ResultCode.BAD_REQUEST.getMessage())));
    }
    
    @Test
    public void getPERatio() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/exchange/stock/POP/peratio?stockPrice=2").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.peRatio").value(equalTo(0.5)))
                .andExpect(jsonPath("$.symbol").value(equalTo("POP")));
    }
    
    @Test
    public void getVolWeightedWithInvalidSymbol() throws Exception {
    	postTrade("POP", 10l, 10.0);
    	
    	postTrade("POP", 20l, 20.0);
    	
        mvc.perform(MockMvcRequestBuilders.get("/exchange/stock/POP1/vwprice").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value(equalTo(ResultCode.STOCK_NOT_FOUND.getCode())))
                .andExpect(jsonPath("$.message").value(equalTo(ResultCode.STOCK_NOT_FOUND.getMessage())));
    }
    
    @Test
    public void getVolWeighted() throws Exception {
    	postTrade("POP", 10l, 10.0);
    	
    	postTrade("POP", 20l, 20.0);
    	
        mvc.perform(MockMvcRequestBuilders.get("/exchange/stock/POP/vwprice").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.volWeighedPrice").value(equalTo(16.666666666666668)))
                .andExpect(jsonPath("$.symbol").value(equalTo("POP")));
    }
    
    private void postTrade(String symbol, Long shareQuantity, Double tradePrice) throws Exception{
    	
        StockTradeRequest request = new StockTradeRequest();
        request.setSharesQuantity(shareQuantity);
        request.setSymbol(symbol);
        request.setTradePrice(tradePrice);
        request.setType(TradeType.Buy);
        
     	mvc.perform(
                 post("/exchange/trade")
                 .contentType(MediaType.APPLICATION_JSON)
     			.content(mapper.writeValueAsString(request))
             )
                 .andExpect(status().isOk())
                 .andExpect(jsonPath("$.code").value(equalTo(ResultCode.SUCCESS.getCode())))
                 .andExpect(jsonPath("$.message").value(equalTo(ResultCode.SUCCESS.getMessage())));
    }
}
