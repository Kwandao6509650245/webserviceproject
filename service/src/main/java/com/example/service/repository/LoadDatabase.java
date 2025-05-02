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
                        NailService s1 = new NailService("Gel Nails",
                                        "Helps your nails look beautiful and durable.",
                                        600);
                        NailService s2 = new NailService("Spa",
                                        "A relaxing spa service for your hands and feet.",
                                        500);
                        NailService s3 = new NailService("Pimple Extraction",
                                        "A service to remove blackheads and acne from your face.",
                                        500);
                        NailService s4 = new NailService("Eyelash Extension",
                                        "Eyelash extension service.",
                                        900);

                        serviceRepo.save(s1);
                        serviceRepo.save(s2);
                        serviceRepo.save(s3);
                        serviceRepo.save(s4);

                        // Rating ต้องใช้ Service object
                        Rating r1 = new Rating(5, "Great service!");
                        Rating r2 = new Rating(4, "Very good");
                        Rating r3 = new Rating(3, "Could be better");

                        ratingRepo.save(r1);
                        ratingRepo.save(r2);
                        ratingRepo.save(r3);
                };
        }
}