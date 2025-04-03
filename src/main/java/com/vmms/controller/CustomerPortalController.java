package com.vmms.controller;

import com.vmms.model.WorkOrder;
import com.vmms.service.PaymentService;
import com.vmms.service.VehicleMaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
public class CustomerPortalController {
    @Autowired private VehicleMaintenanceService service;
    @Autowired private PaymentService paymentService;

    @GetMapping("/status/{orderId}")
    public ResponseEntity<WorkOrder> getRepairStatus(@PathVariable String orderId) {
        // Simplified: In reality, fetch from DB
        return ResponseEntity.ok(new WorkOrder(orderId, new com.vmms.model.Vehicle("VIN123", "Toyota", "Camry", 2020)));
    }

    @GetMapping("/invoice/{orderId}")
    public ResponseEntity<Double> getInvoice(@PathVariable String orderId) {
        // Simplified
        return ResponseEntity.ok(100.0);
    }

    @PostMapping("/payment/{orderId}")
    public ResponseEntity<String> processPayment(@PathVariable String orderId, @RequestParam String token) throws Exception {
        WorkOrder order = new WorkOrder(orderId, new com.vmms.model.Vehicle("VIN123", "Toyota", "Camry", 2020)); // Placeholder
        Charge charge = paymentService.processPayment(orderId, token, order.getTotalCost());
        order.setPaid(true);
        return ResponseEntity.ok("Payment successful: " + charge.getId());
    }
}
