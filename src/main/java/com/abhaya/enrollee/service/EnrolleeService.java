package com.abhaya.enrollee.service;

import com.abhaya.enrollee.Model.Enrollee;
import com.abhaya.enrollee.Model.dto.DependentEnrolleeDto;

import java.util.List;


public interface EnrolleeService {

    Enrollee addEnrollee(Enrollee enrollee) throws Exception;  //  Add a new enrollee

    Enrollee updateEnrollee(Enrollee enrollee) throws Exception; // Modify an existing enrollee

    void deleteEnrollee(Enrollee enrollee) throws Exception; // Remove an enrollee entirely

    void addDependents(DependentEnrolleeDto dependentEnrolleeDto) throws Exception; // Add dependents to an enrollee

    void removeDependents(Enrollee enrollee) throws Exception; // Remove dependents from an enrollee

    void modifyDependents(Enrollee enrollee, Enrollee dependentEnrollee) throws Exception; //  Modify existing dependents

    List<Enrollee> getAllEnrollees() throws Exception; // get all enrollees

    Enrollee getEnrolleeById(Long id) throws Exception;

}
