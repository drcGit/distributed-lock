package com.stock.service;

import com.stock.pojo.Stock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Administrator
 */
@Service
public class StockService {

    @Autowired
    private RedissonClient redissonClient;
    private Stock stock = new Stock();

    private ReentrantLock lock = new ReentrantLock();

    /**
     * redission 加锁
     */
    public void redissonLock(){
        Lock lock = redissonClient.getLock("lock");
        lock.lock();
        try {
            stock.setStock(stock.getStock() - 1);
            System.out.println(Thread.currentThread().getName() + "库存余量：" + stock.getStock());
            Thread.sleep(3*1000);
        } catch (InterruptedException e) {

        } finally {
            lock.unlock();
        }
    }

    /**
     * ReentrantLock 加锁
     */
    public void deductByReentrantLock(){
        lock.lock();
        try {
            stock.setStock(stock.getStock() - 1);
            System.out.println("库存余量：" + stock.getStock());
        } finally {
            lock.unlock();
        }
    }
}
