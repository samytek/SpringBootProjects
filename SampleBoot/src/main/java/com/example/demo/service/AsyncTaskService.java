package com.example.demo.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncTaskService {

    public void inventoryChecked() throws InterruptedException {
        Thread.sleep(4000);
        System.out.println("Inventory is Available");
    }

    @Async
    public void getPayment() throws InterruptedException {
        Thread.sleep(4000);
        System.out.println("Payment has been successfully completed");
    }

    @Async
    public void placeOrder() throws InterruptedException {
        Thread.sleep(4000);
        System.out.println("Order has been successfully completed");
    }

    @Async
    public void notifyCustomer() throws InterruptedException {
        Thread.sleep(4000);
        System.out.println("Hello Customer, your order has been successfully completed");
    }
}
