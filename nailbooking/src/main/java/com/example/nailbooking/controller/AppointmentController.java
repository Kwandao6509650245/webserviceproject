package com.example.nailbooking.controller;

import com.example.nailbooking.model.*;
import com.example.nailbooking.repository.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class AppointmentController {
    @Value("${nailbooking.api.service.url}")
    private String serviceApiUrl;

    private RestTemplate restTemplate;

    private final AppointmentRepository repository;

    public AppointmentController(AppointmentRepository repository, RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.repository = repository;
    }

    @GetMapping("/available-slots")
    public List<LocalDateTime> getAvailableSlots() {
        List<LocalDateTime> slots = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now().withMinute(0).withSecond(0).withNano(0);
        LocalDateTime start = now.withHour(9).withMinute(0);
        LocalDateTime end = now.withHour(18).withMinute(0);

        if (now.isAfter(end)) {
            start = start.plusDays(1);
            end = end.plusDays(1);
        } else if (now.isAfter(start)) {
            start = now.plusHours(1);
        }

        for (LocalDateTime slot = start; slot.isBefore(end); slot = slot.plusHours(1)) {
            if (!repository.existsByTime(slot)) {
                slots.add(slot);
            }
        }
        return slots;
    }

    @PostMapping("/book-appointment")
    public Appointment bookAppointment(@RequestBody Map<String, String> payload) {
        String name = payload.get("name");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime time = LocalDateTime.parse(payload.get("time"), formatter);
        if (repository.existsByTime(time)) {
            throw new RuntimeException("Slot already booked");
        }
        return repository.save(new Appointment(name, time));
    }

    @GetMapping("/appointments")
    public List<Appointment> getAllAppointments() {
        return repository.findAll();
    }

    @GetMapping("/rating")
    public List<RatingRequest> getAllRating() {
        // เรียกใช้ API จากฝั่งที่ 1
        List<RatingRequest> rating = restTemplate.getForObject(serviceApiUrl + "/api/rate-service", List.class);
        return rating;
    }

}
