package com.vmms.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class OBDData {
    @Id
    private String id;
    private String vin;
    private String engineRPM;
    private String vehicleSpeed;
    private String coolantTemp;
    private String fuelPressure;
    private List<String> troubleCodes;
    private LocalDateTime timestamp;

    public OBDData() {}

    public OBDData(String vin, String engineRPM, String vehicleSpeed, String coolantTemp, String fuelPressure, List<String> troubleCodes, LocalDateTime timestamp) {
        this.id = vin + "_" + timestamp.toString();
        this.vin = vin;
        this.engineRPM = engineRPM;
        this.vehicleSpeed = vehicleSpeed;
        this.coolantTemp = coolantTemp;
        this.fuelPressure = fuelPressure;
        this.troubleCodes = troubleCodes;
        this.timestamp = timestamp;
    }

    // Getters and setters
    public String getId() { return id; }
    public String getVin() { return vin; }
    public String getEngineRPM() { return engineRPM; }
    public String getVehicleSpeed() { return vehicleSpeed; }
    public String getCoolantTemp() { return coolantTemp; }
    public String getFuelPressure() { return fuelPressure; }
    public List<String> getTroubleCodes() { return troubleCodes; }
    public LocalDateTime getTimestamp() { return timestamp; }
}
