package com.vmms.service;

import com.vmms.model.*;
import com.vmms.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class VehicleMaintenanceService {
    private Map<String, Vehicle> vehicles = new HashMap<>();
    @Autowired private InventoryManager inventory;
    @Autowired private OBDReader obdReader;
    @Autowired private MaintenancePredictor predictor;
    @Autowired private TechnicianScheduler scheduler;

    public WorkOrder createWorkOrder(String vin, OBDData diagnostics) {
        Vehicle vehicle = vehicles.computeIfAbsent(vin, k -> new Vehicle(vin, "Unknown", "Unknown", 2020));
        WorkOrder order = new WorkOrder(UUID.randomUUID().toString(), vehicle);
        order.setDiagnosticData(diagnostics);
        vehicle.addDiagnosticData(diagnostics);
        vehicle.addWorkOrder(order);

        MaintenanceAlert alert = predictor.predictMaintenance(vehicle, diagnostics);
        if (alert != null) {
            // In a real system, store recommended actions
        }

        return order;
    }

    public void assignTechnician(WorkOrder order, LocalDateTime time) {
        Technician tech = scheduler.getAvailableTechnicians(time).get(0);
        order.setAssignedTechnician(tech);
        scheduler.assignTechnician(order, time);
    }
}
