package com.example.nailbooking.repository;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.nailbooking.model.*;

@Configuration
public class LoadDatabase {

    public static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(AppointmentRepository repository) {
        return args -> {
            repository.save(new Appointment("Alice", LocalDateTime.of(2025, 5, 2, 10, 0)));
            repository.save(new Appointment("Bob", LocalDateTime.of(2025, 5, 2, 14, 0)));
        };
    }
}