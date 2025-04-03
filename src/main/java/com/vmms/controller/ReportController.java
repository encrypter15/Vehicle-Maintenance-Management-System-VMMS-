package com.vmms.controller;

import com.vmms.model.Report;
import com.vmms.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/reports")
public class ReportController {
    @Autowired private ReportService reportService;

    @GetMapping("/{type}")
    public ResponseEntity<Report> getReport(@PathVariable String type, 
                                            @RequestParam String start, 
                                            @RequestParam String end) {
        LocalDateTime startTime = LocalDateTime.parse(start);
        LocalDateTime endTime = LocalDateTime.parse(end);
        Report report = reportService.generateReport(type, startTime, endTime);
        return ResponseEntity.ok(report);
    }
}
