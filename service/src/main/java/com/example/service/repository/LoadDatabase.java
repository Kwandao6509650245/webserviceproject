package com.example.service.repository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.service.model.Rating;
import com.example.service.model.NailService;
import com.example.service.repository.ServiceRepository;
import com.example.service.repository.RatingRepository;

@Configuration
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(ServiceRepository serviceRepo, RatingRepository ratingRepo) {
        return args -> {
            NailService s1 = new NailService("Nail Polish", "Basic nail polish service", 200);
            NailService s2 = new NailService("Nail Art", "Creative nail art with design", 500);

            serviceRepo.save(s1);
            serviceRepo.save(s2);

            // Rating ต้องใช้ Service object
            Rating r1 = new Rating(s1, 5, "Great service!");
            Rating r2 = new Rating(s1, 4, "Very good");
            Rating r3 = new Rating(s2, 3, "Could be better");

            ratingRepo.save(r1);
            ratingRepo.save(r2);
            ratingRepo.save(r3);
        };
    }
}