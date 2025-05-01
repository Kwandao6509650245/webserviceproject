package com.example.service.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

@Entity
@JsonIgnoreProperties({ "service" })
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int ratingValue;
    private String review;

    @ManyToOne
    @JoinColumn(name = "service_id")
    @JsonManagedReference
    private NailService service;

    public Rating() {
    }

    public Rating(NailService service, int ratingValue, String review) {
        this.service = service;
        this.ratingValue = ratingValue;
        this.review = review;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRatingValue() {
        return ratingValue;
    }

    public void setRatingValue(int ratingValue) {
        this.ratingValue = ratingValue;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public NailService getService() {
        return service;
    }

    public void setService(NailService service) {
        this.service = service;
    }

}
