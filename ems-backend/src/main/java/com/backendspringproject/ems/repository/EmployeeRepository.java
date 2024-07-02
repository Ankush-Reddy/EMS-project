package com.backendspringproject.ems.repository;

import com.backendspringproject.ems.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    // this employee repository will get CRUD methods to perform CRUD database operations on Employee
}
