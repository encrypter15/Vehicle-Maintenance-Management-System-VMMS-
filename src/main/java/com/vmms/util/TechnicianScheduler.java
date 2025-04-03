package com.vmms.util;

import com.vmms.model.Technician;
import com.vmms.model.WorkOrder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class TechnicianScheduler {
    private List<Technician> technicians = new ArrayList<>();

    public TechnicianScheduler() {
        technicians.add(new Technician("T1", "John Doe"));
        technicians.add(new Technician("T2", "Jane Smith"));
    }

    public void assignTechnician(WorkOrder order, LocalDateTime scheduleTime) {
        Technician tech = getAvailableTechnicians(scheduleTime).get(0);
        order.setAssignedTechnician(tech);
        tech.bookTime(scheduleTime);
    }

    public List<Technician> getAvailableTechnicians(LocalDateTime time) {
        List<Technician> available = new ArrayList<>();
        for (Technician tech : technicians) {
            if (tech.isAvailable(time)) {
                available.add(tech);
            }
        }
        return available;
    }
}
