package com.vmms.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Part {
    @Id
    private String partNumber;
    private String description;
    private int quantity;
    private double price;

    public Part() {}

    public Part(String partNumber, String description, int quantity, double price) {
        this.partNumber = partNumber;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
    }

    public void decrementQuantity() { if (quantity > 0) quantity--; }

    // Getters and setters
    public String getPartNumber() { return partNumber; }
    public String getDescription() { return description; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }
}

