package com.sideworksa.demo.models;

import javax.persistence.*;

@Entity
@Table(name = "workers")
public class Worker {

    @Id
    @GeneratedValue
    private long id;

    //first name
    @Column(nullable = false)
    private String firstName;

    //last name
    @Column(nullable = false)
    private String lastName;

    //phone
    @Column
    private String phone;

    //bio
    @Column
    private String bio;

    //skills
    @Column
    private String skills;

    // connecting Worker profile to User
    @OneToOne
    private User user;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Worker(String firstName, String lastName, String phone, String bio, String skills) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.bio = bio;
        this.skills = skills;
    }

    public Worker() {}
}
