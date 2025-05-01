package com.example.service.controller;

public class AppointmentRequest {
    private String name;
    private String time;

    public AppointmentRequest() {
        // จำเป็นสำหรับ Spring/Jackson
    }

    public AppointmentRequest(String name, String time) {
        this.name = name;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTime(String time) {
        this.time = time;
    }
}