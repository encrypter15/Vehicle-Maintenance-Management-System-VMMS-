package com.vmms.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class WorkOrder {
    @Id
    private String orderId;

    @ManyToOne
    private Vehicle vehicle;

    private String status; // PENDING, IN_PROGRESS, COMPLETED

    @ManyToOne
    private Technician assignedTechnician;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Part> usedParts = new ArrayList<>();

    @OneToOne
    private OBDData diagnosticData;

    private double totalCost;
    private boolean paid;

    public WorkOrder() {}

    public WorkOrder(String orderId, Vehicle vehicle) {
        this.orderId = orderId;
        this.vehicle = vehicle;
        this.status = "PENDING";
        this.totalCost = 0.0;
        this.paid = false;
    }

    public void updateStatus(String newStatus) { this.status = newStatus; }
    public void addPart(Part part) { 
        usedParts.add(part); 
        totalCost += part.getPrice(); 
    }

    // Getters and setters
    public String getOrderId() { return orderId; }
    public Vehicle getVehicle() { return vehicle; }
    public String getStatus() { return status; }
    public Technician getAssignedTechnician() { return assignedTechnician; }
    public void setAssignedTechnician(Technician technician) { this.assignedTechnician = technician; }
    public List<Part> getUsedParts() { return usedParts; }
    public OBDData getDiagnosticData() { return diagnosticData; }
    public void setDiagnosticData(OBDData diagnosticData) { this.diagnosticData = diagnosticData; }
    public double getTotalCost() { return totalCost; }
    public boolean isPaid() { return paid; }
    public void setPaid(boolean paid) { this.paid = paid; }
}
