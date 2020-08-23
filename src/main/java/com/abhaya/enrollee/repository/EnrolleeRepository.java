package com.abhaya.enrollee.repository;

import com.abhaya.enrollee.Model.Enrollee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface EnrolleeRepository extends CrudRepository<Enrollee, Long> {
}
