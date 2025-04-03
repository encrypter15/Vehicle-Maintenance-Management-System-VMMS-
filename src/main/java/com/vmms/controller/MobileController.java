package com.vmms.controller;

import com.vmms.model.WorkOrder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/mobile")
public class MobileController {
    @GetMapping("/status")
    public ResponseEntity<List<WorkOrder>> getCustomerOrders(@RequestParam String customerId) {
        // Simplified
        WorkOrder order = new WorkOrder("123", new com.vmms.model.Vehicle("VIN123", "Toyota", "Camry", 2020));
        return ResponseEntity.ok(Collections.singletonList(order));
    }
}

