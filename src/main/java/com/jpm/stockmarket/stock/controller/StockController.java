package com.jpm.stockmarket.stock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jpm.stockmarket.common.exception.RestException;
import com.jpm.stockmarket.common.model.ResultCode;
import com.jpm.stockmarket.stock.model.GetDividendYieldResponse;
import com.jpm.stockmarket.stock.model.GetPERatioResponse;
import com.jpm.stockmarket.stock.model.GetVolWeightedResponse;
import com.jpm.stockmarket.stock.service.StockService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/exchange")
public class StockController {

	@Autowired
	private StockService stockService;

	@ApiOperation(value = "Calculate Dividend Yield", notes = "Calculate Dividend Yield for given stock symbol and price")
	@RequestMapping(value = "/stock/{symbol}/dividendyield", method = RequestMethod.GET)
	@ApiImplicitParams({
			@ApiImplicitParam(required = true, name = "symbol", paramType = "path", value = "stock symbol") })
	public GetDividendYieldResponse getDividentYield(@RequestParam(required = true) Double stockPrice,
			@PathVariable(required=true,name="symbol") String symbol) {
		if (stockPrice <= 0) {
			throw new RestException(ResultCode.BAD_REQUEST, "stockPrice should be positive");
		}
		Double dividendYield = stockService.getDividendYield(symbol, stockPrice);
		GetDividendYieldResponse response = new GetDividendYieldResponse.Builder().dividendYield(dividendYield)
				.stockPrice(stockPrice).symbol(symbol).build();
		return response;
	}

	@ApiOperation(value = "Calculate PE Ratio", notes = "Calculate PE Ratio for given stock symbol and price")
	@RequestMapping(value = "/stock/{symbol}/peratio", method = RequestMethod.GET)
	@ApiImplicitParams({
			@ApiImplicitParam(required = true, name = "symbol", paramType = "path", value = "stock symbol") })
	public GetPERatioResponse getPERatio(@RequestParam(required = true) Double stockPrice,
			@PathVariable("symbol") String symbol) {
		if (stockPrice <= 0) {
			throw new RestException(ResultCode.BAD_REQUEST, "stockPrice should be positive");
		}

		Double peRatio = stockService.getPERatio(symbol, stockPrice);
		GetPERatioResponse response = new GetPERatioResponse.Builder().peRatio(peRatio).stockPrice(stockPrice)
				.symbol(symbol).build();
		return response;
	}

	@ApiOperation(value = "Calculate Volume Weighted Price", notes = "Calculate Volume Weighted Price for given stock symbol")
	@RequestMapping(value = "/stock/{symbol}/vwprice", method = RequestMethod.GET)
	@ApiImplicitParams({
			@ApiImplicitParam(required = true, name = "symbol", paramType = "path", value = "stock symbol") })
	public GetVolWeightedResponse getVolumeWeightedPrice(@PathVariable("symbol") String symbol) {
		Double volWeightedPrice = stockService.getVolWeightedPice(symbol);

		GetVolWeightedResponse response = new GetVolWeightedResponse.Builder().symbol(symbol)
				.volWeighedPrice(volWeightedPrice).build();
		return response;
	}
}
