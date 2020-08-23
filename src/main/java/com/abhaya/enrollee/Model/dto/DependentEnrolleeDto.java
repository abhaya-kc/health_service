package com.abhaya.enrollee.Model.dto;

import com.abhaya.enrollee.Model.Enrollee;

import java.util.List;

public class DependentEnrolleeDto {

    private Long enrolleeId;

    private List<Enrollee> enrollees;

    public Long getEnrolleeId() {
        return enrolleeId;
    }

    public void setEnrolleeId(Long enrolleeId) {
        this.enrolleeId = enrolleeId;
    }

    public List<Enrollee> getEnrollees() {
        return enrollees;
    }

    public void setEnrolleeDtoList(List<Enrollee> enrollees) {
        this.enrollees = enrollees;
    }
}
