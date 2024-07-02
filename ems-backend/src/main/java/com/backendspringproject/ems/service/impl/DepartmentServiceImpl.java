package com.backendspringproject.ems.service.impl;

import com.backendspringproject.ems.dto.DepartmentDto;
import com.backendspringproject.ems.entity.Department;
import com.backendspringproject.ems.exception.ResourceNotFoundException;
import com.backendspringproject.ems.mapper.DepartmentMapper;
import com.backendspringproject.ems.repository.DepartmentRepository;
import com.backendspringproject.ems.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service //this annotation will automatically create a spring bean for this department service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;

    @Override
    public DepartmentDto createDepartment(DepartmentDto departmentDto) {
        Department department= DepartmentMapper.mapTodepartment(departmentDto);
        Department saveDepartment=departmentRepository.save(department);
        return DepartmentMapper.mapToDepartmentDto(saveDepartment);
    }

    @Override
    public DepartmentDto getDepartmentById(Long departmentId) {
        Department department=departmentRepository.findById(departmentId).orElseThrow(
                () -> new ResourceNotFoundException("Department not exists with a given id: " + departmentId)
        );
        return DepartmentMapper.mapToDepartmentDto(department);
    }

    @Override
    public List<DepartmentDto> getAllDepartments() {
        List<Department> departments=departmentRepository.findAll();
        return departments.stream().map((department) -> DepartmentMapper.mapToDepartmentDto(department))
                .collect(Collectors.toList());
    }

    @Override
    public DepartmentDto updateDepartment(Long departmentId, DepartmentDto updatedDepartment) {
        Department department=departmentRepository.findById(departmentId).orElseThrow(
                ()->new ResourceNotFoundException("Department not exists with the given id: " + departmentId)
        );
        department.setDepartmentName(updatedDepartment.getDepartmentName());
        department.setDepartmentDescription(updatedDepartment.getDepartmentDescription());
        Department savedDepartment=departmentRepository.save(department);
        return DepartmentMapper.mapToDepartmentDto(savedDepartment);
    }

    @Override
    public void deleteDepartment(Long departmentId) {
        departmentRepository.findById(departmentId).orElseThrow(
                ()-> new ResourceNotFoundException("Department does not exists with given id: " + departmentId)
        );
        departmentRepository.deleteById(departmentId);
    }


}
