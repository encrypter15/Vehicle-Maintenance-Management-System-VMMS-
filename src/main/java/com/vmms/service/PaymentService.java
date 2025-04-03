package com.vmms.service;

import com.stripe.Stripe;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentService {
    @Value("${stripe.api.key}")
    private String stripeApiKey;

    public Charge processPayment(String orderId, String token, double amount) throws Exception {
        Stripe.apiKey = stripeApiKey;
        Map<String, Object> params = new HashMap<>();
        params.put("amount", (int) (amount * 100)); // Convert to cents
        params.put("currency", "usd");
        params.put("source", token);
        params.put("description", "Payment for Work Order: " + orderId);
        return Charge.create(params);
    }
}
