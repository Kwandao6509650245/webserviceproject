package com.example.service.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.example.service.model.Rating;
import com.example.service.model.NailService;
import com.example.service.repository.RatingRepository;
import com.example.service.repository.ServiceRepository;

import java.net.http.HttpHeaders;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ServiceController {

    private final ServiceRepository serviceRepository;
    private final RatingRepository ratingRepository;
    private final RestTemplate restTemplate;

    @Value("${nailbooking.api.booking.url}")
    private String bookingApiUrl;

    public ServiceController(ServiceRepository serviceRepository, RatingRepository ratingRepository,
            RestTemplate restTemplate) {
        this.serviceRepository = serviceRepository;
        this.ratingRepository = ratingRepository;
        this.restTemplate = restTemplate;
    }

    // 1. API แสดงบริการ
    @GetMapping("/services")
    public List<NailService> getAllServices() {
        return serviceRepository.findAll();
    }

    // 2. API เรตติ้งหลังการใช้บริการ
    @PostMapping("/rate-service")
    public Rating rateService(@RequestBody Rating rating) {
        return ratingRepository.save(rating);
    }

    // 3. API ค้นหาวันว่างจากฝั่งที่ 1
    @GetMapping("/available-slots")
    public List<String> getAvailableSlots() {
        // เรียกใช้ API จากฝั่งที่ 1
        List<String> availableSlots = restTemplate.getForObject(bookingApiUrl + "/api/available-slots", List.class);
        return availableSlots;
    }

    // 4. API การจองคิว (ส่งคำขอไปฝั่งที่ 1)
    @PostMapping("/book-appointment")
    public String bookAppointment(@RequestBody AppointmentRequest request) {
        // ส่งคำขอไปยังฝั่งที่ 1
        restTemplate.postForObject(bookingApiUrl + "/api/book-appointment", request, String.class);
        return "Booking request sent successfully.";
    }

}
