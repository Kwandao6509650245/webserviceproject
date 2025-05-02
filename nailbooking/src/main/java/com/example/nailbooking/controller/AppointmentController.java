package com.example.nailbooking.controller;

import com.example.nailbooking.model.*;
import com.example.nailbooking.repository.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
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

        for (int dayOffset = 0; dayOffset <= 2; dayOffset++) {
            LocalDate date = now.toLocalDate().plusDays(dayOffset);
            LocalDateTime start = date.atTime(9, 0);
            LocalDateTime end = date.atTime(18, 0);

            for (LocalDateTime slot = start; slot.isBefore(end); slot = slot.plusHours(1)) {
                // ถ้าเป็นวันนี้ และ slot ก่อนเวลาปัจจุบัน ให้ข้าม
                if (dayOffset == 0 && slot.isBefore(now.plusHours(1))) {
                    continue;
                }

                if (!repository.existsByTime(slot)) {
                    slots.add(slot);
                }
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

@Controller
class HomeController {
    @GetMapping("/")
    public String home() {
        return "redirect:/index.html";
    }
}