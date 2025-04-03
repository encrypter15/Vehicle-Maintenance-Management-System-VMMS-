package com.vmms.model;

import java.util.List;

public class MaintenanceAlert {
    private String vin;
    private String message;
    private List<String> recommendedActions;

    public MaintenanceAlert(String vin, String message, List<String> recommendedActions) {
        this.vin = vin;
        this.message = message;
        this.recommendedActions = recommendedActions;
    }

    // Getters
    public String getVin() { return vin; }
    public String getMessage() { return message; }
    public List<String> getRecommendedActions() { return recommendedActions; }
}
