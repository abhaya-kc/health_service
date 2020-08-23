package com.abhaya.enrollee.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Enrollee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private Boolean activationStatus = false;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date birthDate;

    private String phoneNumber;

    @JsonIgnore
    @ManyToOne
    private Enrollee parentEnrollee;

    @OneToMany(mappedBy = "parentEnrollee", fetch = FetchType.LAZY,orphanRemoval = true)
    private List<Enrollee> dependentEnrollees;

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

    public Boolean getActivationStatus() {
        return activationStatus;
    }

    public void setActivationStatus(Boolean activationStatus) {
        this.activationStatus = activationStatus;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Enrollee> getDependentEnrollees() {
        return dependentEnrollees;
    }

    public void setDependentEnrollees(List<Enrollee> dependentEnrollees) {
        if(this.dependentEnrollees == null){
            this.dependentEnrollees = new ArrayList<>();
        }
        for(Enrollee enrollee: dependentEnrollees){
            enrollee.setParentEnrollee(this);
        }
        this.dependentEnrollees = dependentEnrollees;
    }

    public Enrollee getParentEnrollee() {
        return parentEnrollee;
    }

    public void setParentEnrollee(Enrollee parentEnrollee) {
        this.parentEnrollee = parentEnrollee;
    }
}
