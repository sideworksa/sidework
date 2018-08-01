package com.sideworksa.demo.models;


import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    // id
    @Id
    @GeneratedValue
    private long id;

    // username
    @Column(nullable = false, unique = true)
    private String username;

    // email
    @Column(nullable = false, unique = true)
    private String email;

    // password
    @Column(nullable = false)
    private String password;


    // password validation
    private transient String confirmPassword;

    private transient String newPassword;

    private transient String confirmNewPassword;


    // connecting User profile to Business
    @OneToOne
    private Business business;

    // connecting User profile to Worker
    @OneToOne
    private Worker worker;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmNewPassword() {
        return confirmNewPassword;
    }

    public void setConfirmNewPassword(String confirmNewPassword) {
        this.confirmNewPassword = confirmNewPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }


    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }


    public User(Business business) {

        this.business = business;
    }

    public User(Worker worker) {

        this.worker = worker;
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    // copy constructor for authentication
    public User(User copy) {
        id = copy.id; // This line is SUPER important! Many things won't work if it's absent
        email = copy.email;
        username = copy.username;
        password = copy.password;
    }

    public User(String username, String email, String password, Business business, Worker worker) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.business = business;
        this.worker = worker;
    }

    public User() {}

}
