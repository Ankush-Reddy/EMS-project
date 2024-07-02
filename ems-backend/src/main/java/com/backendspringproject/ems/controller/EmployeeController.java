package com.backendspringproject.ems.controller;

import com.backendspringproject.ems.dto.EmployeeDto;
import com.backendspringproject.ems.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//once we annotate a class with @RestController annotation, then this class becomes a spring mvc rest controllers and this class is capable to handle Http requests.
//@RequestMapping annotation to define the base URL for all the rest API's that we are going to build with in this controller
@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private EmployeeService employeeService;

    //Build Add Employee REST API
    // First we need to create a method and then we will make that method as a rest API by using spring annotations.

    //@PostMapping to map the incoming http post request to this method
    //@RequestBody will extract the JSON from the Http request and it will convert that JSON in to Employee Java object
    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto){
        //let us call employee service
        EmployeeDto savedEmployee = employeeService.createEmployee(employeeDto);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);

        //whenever we pass JSON in the HTTP request in postman client, we make sure that the JSON attributes should be same as the Dto class fields
        //because we are using @RequestBody annotation here to extract the Json and convert that Json into employeeDto Java object
    }

    @GetMapping("{id}")
    // Build Get Employee REST API
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") Long employeeId){
        EmployeeDto employeeDto=employeeService.getEmployeeById(employeeId);
        return ResponseEntity.ok(employeeDto);
    }


    @GetMapping
    //Build GetAll Employees REST API
    public ResponseEntity<List<EmployeeDto>> getAllEmployees(){
        List<EmployeeDto> employees=employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @PutMapping("{id}")
    //Build Update Employee REST API
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") Long employeeId, @RequestBody EmployeeDto updateEmployee){
        EmployeeDto employeeDto=employeeService.updateEmployee(employeeId,updateEmployee);
        return ResponseEntity.ok(employeeDto);
    }

    @DeleteMapping("{id}")
    //Build Delete Employee REST API
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long employeeId){
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.ok("Employee Deleted Successfully");
    }
}
