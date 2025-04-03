package com.vmms.util;

import com.vmms.model.OBDData;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collections;

@Component
public class OBDReader {
    public OBDData readDiagnostics(String vin) {
        // Simulated OBD-II reading
        return new OBDData(vin, "3000", "60", "90", "40", Collections.singletonList("P0300"), LocalDateTime.now());
    }
}
