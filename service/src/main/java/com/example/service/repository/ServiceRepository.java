package com.example.service.repository;

import com.example.service.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<NailService, Long> {
}
