package com.example.project.model.entities;

import com.example.project.model.data.ds.Address;
import com.example.project.model.data.ds.Phone;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //private String name;

    @JsonIgnore
    @Embedded
    private Address address;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userProfile_id", referencedColumnName = "id")
    private UserProfile userProfile;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "client_cosmetologist",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "cosmetologist_id")
    )
    private List<Cosmetologist> cosmetologists = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Transaction> transactions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id=id;
    }

//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile=userProfile;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public List<Cosmetologist> getCosmetologists() {
        return cosmetologists;
    }

    public void setCosmetologists(List<Cosmetologist> cosmetologists) {
        this.cosmetologists = cosmetologists;
    }

    public void addCosmetologist(Cosmetologist cosmetologist) {
        this.cosmetologists.add(cosmetologist);
    }

    public void removeCosmetologist(Cosmetologist cosmetologist) {
        this.cosmetologists.remove(cosmetologist);
    }

    public Address getAddress() {
        return this.address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Phone getPhone() {
        return this.userProfile.getPhone();
    }
}