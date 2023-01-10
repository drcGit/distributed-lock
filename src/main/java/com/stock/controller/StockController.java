package com.stock.controller;

import com.stock.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StockController {

    @Autowired
    private StockService stockService;

    @GetMapping("stock/redissonLock")
    public String redissonLock(){
        this.stockService.redissonLock();
        return "hello stock redissonLock！！";
    }

    @GetMapping("stock/deductByReentrantLock")
    public String deductByReentrantLock(){
        this.stockService.deductByReentrantLock();
        return "hello stock deductByReentrantLock！！";
    }
}
