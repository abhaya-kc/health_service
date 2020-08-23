package com.abhaya.enrollee.Model.dto;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EnrolleeDto {

    private String name;

    private Boolean activationStatus = false;

    private Date birthDate; // fix date type (maybe temporal)

    private String phoneNumber;

    private EnrolleeDto parentEnrollee;

    private List<EnrolleeDto> dependentEnrolleeDtos;

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

    public EnrolleeDto getParentEnrollee() {
        return parentEnrollee;
    }

    public void setParentEnrollee(EnrolleeDto parentEnrollee) {
        this.parentEnrollee = parentEnrollee;
    }

    public List<EnrolleeDto> getDependentEnrolleeDtos() {
        return dependentEnrolleeDtos;
    }

    public void setDependentEnrolleeDtos(List<EnrolleeDto> dependentEnrolleeDtos) {
        if(this.dependentEnrolleeDtos == null){
            this.dependentEnrolleeDtos = new ArrayList<>();
        }
        for(EnrolleeDto enrolleeDto: dependentEnrolleeDtos){
            enrolleeDto.setParentEnrollee(this);
        }
        this.dependentEnrolleeDtos = dependentEnrolleeDtos;
    }
}

