package com.example.nailbooking.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Appointment {
    @Id
    @GeneratedValue
    private Long id;

    private String customerName;
    private LocalDateTime time;

    public Appointment() {
    }

    public Appointment(String customerName, LocalDateTime time) {
        this.customerName = customerName;
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
