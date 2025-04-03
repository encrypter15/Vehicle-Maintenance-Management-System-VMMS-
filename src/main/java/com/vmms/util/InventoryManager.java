package com.vmms.util;

import com.vmms.model.Part;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class InventoryManager {
    private Map<String, Part> partsInventory = new HashMap<>();

    public void addPart(String partNumber, String description, int quantity, double price) {
        partsInventory.put(partNumber, new Part(partNumber, description, quantity, price));
    }

    public Part usePart(String partNumber) {
        Part part = partsInventory.get(partNumber);
        if (part != null && part.getQuantity() > 0) {
            part.decrementQuantity();
            return part;
        }
        return null;
    }
}
