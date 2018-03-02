package com.sideworksa.demo.models;

import javax.persistence.*;

@Entity
@Table(name = "businesses")
public class Business {

    @Id @GeneratedValue
    private long id;

    // name
    @Column
    private String businessName;

    //phone number
    @Column
    private String businessPhone;

    //website
    @Column
    private String website;

    //address
    @Column
    private String address;

    //info
    @Column
    private String businessInfo;

    // connecting Business profile to User
    @OneToOne
    private User user;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getBusinessPhone() {
        return businessPhone;
    }

    public void setBusinessPhone(String businessPhone) {
        this.businessPhone = businessPhone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBusinessInfo() {
        return businessInfo;
    }

    public void setBusinessInfo(String businessInfo) {
        this.businessInfo = businessInfo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Business(User user) {
        this.user = user;
    }


    public Business(String businessName, String businessPhone, String website, String address, String businessInfo) {
        this.businessName = businessName;
        this.businessPhone = businessPhone;
        this.website = website;
        this.address = address;
        this.businessInfo = businessInfo;
    }

    public Business() {

    }
}
