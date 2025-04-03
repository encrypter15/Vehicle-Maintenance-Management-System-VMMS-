package com.vmms.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Technician {
    @Id
    private String id;
    private String name;
    private Map<LocalDateTime, Boolean> availability = new HashMap<>();

    public Technician() {}

    public Technician(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public boolean isAvailable(LocalDateTime time) {
        return availability.getOrDefault(time, true);
    }

    public void bookTime(LocalDateTime time) {
        availability.put(time, false);
    }

    // Getters and setters
    public String getId() { return id; }
    public String getName() { return name; }
    public Map<LocalDateTime, Boolean> getAvailability() { return availability; }
}
