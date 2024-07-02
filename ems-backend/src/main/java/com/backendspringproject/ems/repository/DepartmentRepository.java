package com.backendspringproject.ems.repository;

import com.backendspringproject.ems.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department,Long> {
    //once department repository interface extends JPA Repository interface, then this department
    //repository will get CRUD methods to perform CRUD database operations on department entity.
    //Spring data JPA repository will automatically provide the implementation for all the CRUD methods that
    //we can use to perform CRUD database operations on this department JPA entity
}
