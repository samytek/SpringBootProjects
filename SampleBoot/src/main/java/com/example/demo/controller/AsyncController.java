package com.example.demo.controller;

import com.example.demo.service.AsyncTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AsyncController {

    @Autowired
    AsyncTaskService asyncTaskService;

    @PostMapping("placeDemoOrder")
    public String placeDemoOrder() throws InterruptedException {
        asyncTaskService.inventoryChecked();
        asyncTaskService.getPayment();
        asyncTaskService.placeOrder();
        asyncTaskService.notifyCustomer();
        return "success";
    }
}
