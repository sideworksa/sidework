package com.sideworksa.demo.models;

import javax.persistence.*;

@Entity
@Table(name = "listings")
public class Listing {
    //id
    @Id @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String title;

    //shift details
    @Column(nullable = false)
    private String description;

    // connected to business table, one business can post many job listings
    @ManyToOne
    @JoinColumn (name = "business_id")
    private Business business;


    public Listing(long id, String description, Business business) {
        this.id = id;
        this.description = description;
        this.business = business;
    }

    public Listing() {

    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }


    public Listing(String description) {
        this.description = description;
    }
}