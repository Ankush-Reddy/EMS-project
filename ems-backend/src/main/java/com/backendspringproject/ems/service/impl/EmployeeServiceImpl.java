package com.backendspringproject.ems.service.impl;

import com.backendspringproject.ems.dto.EmployeeDto;
import com.backendspringproject.ems.entity.Department;
import com.backendspringproject.ems.entity.Employee;
import com.backendspringproject.ems.exception.ResourceNotFoundException;
import com.backendspringproject.ems.mapper.EmployeeMapper;
import com.backendspringproject.ems.repository.DepartmentRepository;
import com.backendspringproject.ems.repository.EmployeeRepository;
import com.backendspringproject.ems.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private DepartmentRepository departmentRepository;
    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        //we need to convert EmployeeDto in to Employee JPA because we need to store Employee Entity in to database
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Department department=departmentRepository.findById(employeeDto.getDepartmentId())
                .orElseThrow(()->new ResourceNotFoundException("Department is not exists with id: " + employeeDto.getDepartmentId()));
        employee.setDepartment(department);
        // next save this Employee entity into database
        Employee savedEmployee = employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

//    @Override
//    public EmployeeDto getEmployeeById(Long employeeId) {
//        Employee employee=employeeRepository.findById(employeeId)
//                .orElseThrow(() -> new ResourceNotFoundException("Employee is not exist with given id: " + employeeId));
//        //first create the custom exception
//        //whenever a Employee with a given ID is not exist in database, then we have to throw the custom exception
//        return EmployeeMapper.mapToEmployeeDto(employee);
//    }
//
//    @Override
//    public List<EmployeeDto> getAllEmployees() {
//        List<Employee> employees=employeeRepository.findAll();
//        return employees.stream().map((employee) -> EmployeeMapper.mapToEmployeeDto(employee)).collect(Collectors.toList());
//    }
        @Override
        public EmployeeDto getEmployeeById(Long employeeId) {
            Employee employee = employeeRepository.findById(employeeId)
                    .orElseThrow(() ->
                            new ResourceNotFoundException("Employee is not exists with given id : " + employeeId));

            return EmployeeMapper.mapToEmployeeDto(employee);
        }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map((employee) -> EmployeeMapper.mapToEmployeeDto(employee))
                .collect(Collectors.toList());
    }


    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee) {
        Employee employee=employeeRepository.findById(employeeId).orElseThrow(
                ()->new ResourceNotFoundException("Employee is not exist with given id: "+ employeeId)
        );
        employee.setFirstName(updatedEmployee.getFirstName());
        employee.setLastName(updatedEmployee.getLastName());
        employee.setEmail(updatedEmployee.getEmail());
        Department department=departmentRepository.findById(updatedEmployee.getDepartmentId())
                .orElseThrow(()->new ResourceNotFoundException("Department is not exists with id: " + updatedEmployee.getDepartmentId()));
        employee.setDepartment(department);
        Employee updatedEmployeeObj=employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(updatedEmployeeObj);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        Employee employee=employeeRepository.findById(employeeId).orElseThrow(
                ()->new ResourceNotFoundException("Employee is not exist with given id: "+ employeeId)
        );

        employeeRepository.deleteById(employeeId);
    }


}
