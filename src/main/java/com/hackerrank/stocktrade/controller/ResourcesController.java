package com.hackerrank.stocktrade.controller;

import com.hackerrank.stocktrade.service.TradeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/erase")
public class ResourcesController {

    private final TradeService tradeService;

    public ResourcesController(TradeService tradeService) {
        this.tradeService = tradeService;
    }

    @DeleteMapping
    public void eraseAllTrades(){
        tradeService.eraseAllTrades();

    }
}
