package com.vmms.model;

import java.time.LocalDateTime;
import java.util.Map;

public class Report {
    private String type;
    private LocalDateTime start;
    private LocalDateTime end;
    private Map<String, Object> data;

    public Report(String type, LocalDateTime start, LocalDateTime end, Map<String, Object> data) {
        this.type = type;
        this.start = start;
        this.end = end;
        this.data = data;
    }

    // Getters
    public String getType() { return type; }
    public LocalDateTime getStart() { return start; }
    public LocalDateTime getEnd() { return end; }
    public Map<String, Object> getData() { return data; }
}
