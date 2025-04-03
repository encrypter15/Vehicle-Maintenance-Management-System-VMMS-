package com.vmms.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Vehicle {
    @Id
    private String vin;
    private String make;
    private String model;
    private int year;

    @OneToMany(cascade = CascadeType.ALL)
    private List<OBDData> diagnosticHistory = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    private List<WorkOrder> serviceHistory = new ArrayList<>();

    public Vehicle() {}

    public Vehicle(String vin, String make, String model, int year) {
        this.vin = vin;
        this.make = make;
        this.model = model;
        this.year = year;
    }

    // Getters and setters
    public String getVin() { return vin; }
    public void setVin(String vin) { this.vin = vin; }
    public String getMake() { return make; }
    public void setMake(String make) { this.make = make; }
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }
    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }
    public List<OBDData> getDiagnosticHistory() { return diagnosticHistory; }
    public List<WorkOrder> getServiceHistory() { return serviceHistory; }
    public void addDiagnosticData(OBDData data) { diagnosticHistory.add(data); }
    public void addWorkOrder(WorkOrder order) { serviceHistory.add(order); }
}
