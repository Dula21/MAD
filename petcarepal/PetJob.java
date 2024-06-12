package com.example.petcarepal;

public class PetJob {

    private long jobId;
    private int customerId;
    private int caregiverId;
    private double price;
    private String status;
    private String email;
    private int petAge;
    private String petName;
    private String petBreed;
    private String address;
    private long id;

    private boolean isBooked;

    public PetJob(long jobId, String petBreed, String status, String petName, double price, int customerId, int caregiverId, int petAge, String address, String email) {
        this.id = id;
        this.customerId = customerId;
        this.caregiverId = caregiverId;
        this.price = price;
        this.status = status;
        this.email = email;
        this.petAge = petAge;
        this.petName = petName;
        this.petBreed = petBreed;
        this.address = address;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getCaregiverId() {
        return caregiverId;
    }

    public void setCaregiverId(int caregiverId) {
        this.caregiverId = caregiverId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPetAge() {
        return petAge;
    }

    public void setPetAge(int petAge) {
        this.petAge = petAge;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getPetBreed() {
        return petBreed;
    }

    public void setPetBreed(String petBreed) {
        this.petBreed = petBreed;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isBooked() {
        return false;
    }
}
