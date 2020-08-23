package com.abhaya.enrollee.controller;

import com.abhaya.enrollee.Model.Enrollee;
import com.abhaya.enrollee.Model.Exceptions.DataAccessException;
import com.abhaya.enrollee.Model.dto.DependentEnrolleeDto;
import com.abhaya.enrollee.Model.dto.EnrolleeDto;
import com.abhaya.enrollee.service.EnrolleeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/v1/enrollee")
public class EnrolleeController {

    private static final Logger LOG = LoggerFactory.getLogger(EnrolleeController.class);
    @Autowired
    EnrolleeService enrolleeService;

    @GetMapping("/getAll")
    public List<Enrollee> getAllEnrollees() {

        try {
            return enrolleeService.getAllEnrollees();

        } catch (DataAccessException ex) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        } catch (Exception ex) {
            throw new ResponseStatusException(
                    HttpStatus.SERVICE_UNAVAILABLE, "Something went wrong, Please contact administrator", ex);
        }
    }

    @PostMapping("/create/enrollee")
    public Enrollee createEnrollee(@RequestBody Enrollee enrollee) {

        try {
            return enrolleeService.addEnrollee(enrollee);
        } catch (DataAccessException ex) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
        } catch (Exception ex) {
            throw new ResponseStatusException(
                    HttpStatus.SERVICE_UNAVAILABLE, "Something went wrong, Please contact administrator", ex);
        }

    }

    @PostMapping("/update/enrollee")
    public Enrollee upateEnrollee(@RequestBody Enrollee enrollee) {

        try {
            return enrolleeService.updateEnrollee(enrollee);
        } catch (DataAccessException ex) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
        } catch (Exception ex) {
            throw new ResponseStatusException(
                    HttpStatus.SERVICE_UNAVAILABLE, "Something went wrong, Please contact administrator", ex);
        }
    }

    @PostMapping("/remove/enrollee")
    public void removeEnrollee(@RequestBody Enrollee enrollee) {

        try {
            enrolleeService.deleteEnrollee(enrollee);
        } catch (DataAccessException ex) {
            LOG.info(ex.getMessage());
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, ex.getMessage(), ex);

        } catch (Exception ex) {
            throw new ResponseStatusException(
                    HttpStatus.SERVICE_UNAVAILABLE, "Something went wrong, Please contact administrator", ex);
        }
    }

    @PostMapping("/add/dependents")
    public void addDependents(@RequestBody DependentEnrolleeDto dependentEnrolleeDto) {

        try {
            enrolleeService.addDependents(dependentEnrolleeDto);
        } catch (DataAccessException ex) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
        } catch (Exception ex) {
            throw new ResponseStatusException(
                    HttpStatus.SERVICE_UNAVAILABLE, "Something went wrong, Please contact administrator", ex);
        }
    }

    @PostMapping("/remove/dependents")
    public void removeDependents(@RequestBody Enrollee enrollee) {

        try {
            enrolleeService.removeDependents(enrollee);
        } catch (DataAccessException ex) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
        } catch (Exception ex) {
            throw new ResponseStatusException(
                    HttpStatus.SERVICE_UNAVAILABLE, "Something went wrong, Please contact administrator", ex);
        }
    }

    @PostMapping("modify/dependents")
    public void modifyDependents(@RequestBody EnrolleeDto enrolleeDto) {
        // more to think about this
    }

}
