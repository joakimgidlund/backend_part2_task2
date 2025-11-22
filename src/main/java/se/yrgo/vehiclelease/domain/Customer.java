package se.yrgo.vehiclelease.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Customer {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String phoneNumber;

    @OneToMany(mappedBy = "customer")
    List<Vehicle> leasedVehicles = new ArrayList<>();

    public Customer() { /* Empty constructor for Spring */ }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Vehicle> getLeasedVehicles() {
        return leasedVehicles;
    }

    public void setLeasedVehicles(List<Vehicle> leasedVehicles) {
        this.leasedVehicles = leasedVehicles;
    }
}
