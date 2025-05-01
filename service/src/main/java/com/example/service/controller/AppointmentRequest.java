package com.example.service.controller;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AppointmentRequest {
    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") // ใช้ format ที่ตรงกับรูปแบบที่ส่งมาใน JSON
    private LocalDateTime time;

    public AppointmentRequest() {
        // จำเป็นสำหรับ Spring/Jackson
    }

    public AppointmentRequest(String name, LocalDateTime time) {
        this.name = name;
        this.time = time;
    }

    // Constructor ที่รับ name และ time ในรูปแบบ String
    public AppointmentRequest(String name, String time) {
        this.name = name;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        this.time = LocalDateTime.parse(time, formatter); // แปลง time ที่เป็น String เป็น LocalDateTime
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
