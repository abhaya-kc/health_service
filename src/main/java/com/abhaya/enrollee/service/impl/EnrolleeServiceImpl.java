package com.abhaya.enrollee.service.impl;

import com.abhaya.enrollee.Model.Enrollee;
import com.abhaya.enrollee.Model.Exceptions.DataAccessException;
import com.abhaya.enrollee.Model.dto.DependentEnrolleeDto;
import com.abhaya.enrollee.repository.EnrolleeRepository;
import com.abhaya.enrollee.service.EnrolleeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("enrolleeservice")
public class EnrolleeServiceImpl implements EnrolleeService {

    @Autowired
    EnrolleeRepository enrolleeRepository;

    private static final Logger LOG = LoggerFactory.getLogger(EnrolleeServiceImpl.class);

    final String NOT_FOUND = "NO RELATED ENROLEE FOUND";

    final String CANNOT_CREATE = "CAN NOT CREATE AN ENROLLEE";

    final String NO_ID = "NO ID PROVIDED TO LOOKUP EMPLOYEE";

    @Override
    public Enrollee addEnrollee(Enrollee enrollee) throws DataAccessException{
        if(enrollee.getId() != null){
            Optional<Enrollee> savedEnrolleeOptional = enrolleeRepository.findById(enrollee.getId());
            if(savedEnrolleeOptional.isPresent()){
                LOG.info(CANNOT_CREATE);
                throw new DataAccessException(CANNOT_CREATE);
            } else {
                return enrolleeRepository.save(enrollee);

            }
        } else {
            return enrolleeRepository.save(enrollee);
        }
    }

    @Override
    public Enrollee updateEnrollee(Enrollee enrollee) throws DataAccessException {
        if(enrollee.getId() != null){
            Optional<Enrollee> savedEnrolleeOptional = enrolleeRepository.findById(enrollee.getId());
            if(savedEnrolleeOptional.isPresent()){
                Enrollee savedEnrollee = savedEnrolleeOptional.get();
                savedEnrollee.setName(enrollee.getName());
                savedEnrollee.setBirthDate(enrollee.getBirthDate());
                savedEnrollee.setPhoneNumber(enrollee.getPhoneNumber());
                savedEnrollee.setParentEnrollee(enrollee.getParentEnrollee());
                savedEnrollee.setActivationStatus(enrollee.getActivationStatus());
                savedEnrollee.setDependentEnrollees(enrollee.getDependentEnrollees());
                return enrolleeRepository.save(savedEnrollee);
            } else {
                throw new DataAccessException(NOT_FOUND);
            }
        } else {
            LOG.info(NO_ID);
            throw new DataAccessException(NO_ID);
        }
    }

    @Override
    public void deleteEnrollee(Enrollee enrollee) throws DataAccessException {
        if(enrollee.getId() != null){
            Optional<Enrollee> savedEnrolleeOptional = enrolleeRepository.findById(enrollee.getId());
            if(savedEnrolleeOptional.isPresent()){
                Enrollee savedEnrollee = savedEnrolleeOptional.get();
                if(savedEnrollee.getDependentEnrollees() != null && !savedEnrollee.getDependentEnrollees().isEmpty()){
                    for(Enrollee eachEnrollee: savedEnrollee.getDependentEnrollees()){
                        eachEnrollee.setParentEnrollee(null);
                    }
                }
                savedEnrollee.setDependentEnrollees(new ArrayList<>());
                enrolleeRepository.delete(savedEnrolleeOptional.get());
            } else {
                LOG.info(NOT_FOUND);
                throw new DataAccessException(NOT_FOUND);
            }
        } else {
            LOG.info(NO_ID);
            throw new DataAccessException(NO_ID);
        }
    }

    @Override
    public void addDependents(DependentEnrolleeDto dependentEnrolleeDto) throws DataAccessException {
        if(dependentEnrolleeDto.getEnrolleeId() != null){
            Optional<Enrollee> savedEnrolleeOptional = enrolleeRepository.findById(dependentEnrolleeDto.getEnrolleeId());
            if(savedEnrolleeOptional.isPresent()){
                Enrollee savedEnrollee = savedEnrolleeOptional.get();
                for(Enrollee enrollee: dependentEnrolleeDto.getEnrollees()){
                    enrollee.setParentEnrollee(savedEnrollee);
                }
                enrolleeRepository.saveAll(dependentEnrolleeDto.getEnrollees());
            } else {
                LOG.info(NOT_FOUND);
                throw new DataAccessException(NOT_FOUND);
            }
        } else {
            LOG.info(NO_ID);
            throw new DataAccessException(NO_ID);
        }
    }

    @Override
    public void removeDependents(Enrollee enrollee) throws DataAccessException {
        if(enrollee.getId() != null){
            Optional<Enrollee> savedEnrolleeOptional = enrolleeRepository.findById(enrollee.getId());
            if(savedEnrolleeOptional.isPresent()){
                Enrollee savedEnrollee = savedEnrolleeOptional.get();
                if(savedEnrollee.getDependentEnrollees() != null && !savedEnrollee.getDependentEnrollees().isEmpty()){
                    enrolleeRepository.deleteAll(savedEnrollee.getDependentEnrollees());
                } else {
                    LOG.info("No dependents Enrollees found");
                }
            } else {
                LOG.info(NOT_FOUND);
                throw new DataAccessException(NOT_FOUND);
            }
        } else {
            LOG.info(NO_ID);
            throw new DataAccessException(NO_ID);
        }
    }

    @Override
    public void modifyDependents(Enrollee enrollee, Enrollee dependentEnrollee) {
        // thinking of use case here
        // some room for thoughts here !!
    }

    @Override
    public List<Enrollee> getAllEnrollees() {
        List<Enrollee> enrollees = (List<Enrollee>) enrolleeRepository.findAll();

        return enrollees
                .stream()
                .filter(c -> c.getParentEnrollee() == null)
                .collect(Collectors.toList());
    }

    @Override
    public Enrollee getEnrolleeById(Long id) throws DataAccessException{
        Optional<Enrollee> enrolleeOptional = enrolleeRepository.findById(id);
        if(enrolleeOptional.isPresent()){
            return enrolleeOptional.get();
        } else {
            LOG.info(NOT_FOUND);
            throw new DataAccessException(NOT_FOUND);
        }
    }


}
