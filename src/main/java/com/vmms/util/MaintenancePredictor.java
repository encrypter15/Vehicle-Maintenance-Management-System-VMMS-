package com.vmms.util;

import com.vmms.model.MaintenanceAlert;
import com.vmms.model.OBDData;
import com.vmms.model.Vehicle;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class MaintenancePredictor {
    public MaintenanceAlert predictMaintenance(Vehicle vehicle, OBDData currentData) {
        // Simplified prediction
        if (currentData.getTroubleCodes() != null && !currentData.getTroubleCodes().isEmpty()) {
            return new MaintenanceAlert(vehicle.getVin(), "Maintenance required", 
                Collections.singletonList("Check engine"));
        }
        return null;
    }
}
