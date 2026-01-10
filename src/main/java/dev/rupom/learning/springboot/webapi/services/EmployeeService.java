package dev.rupom.learning.springboot.webapi.services;

import dev.rupom.learning.springboot.webapi.configs.MapperConfig;
import dev.rupom.learning.springboot.webapi.dto.EmployeeDTO;
import dev.rupom.learning.springboot.webapi.entities.EmployeeEntity;
import dev.rupom.learning.springboot.webapi.exceptions.ResourceNotFoundException;
import dev.rupom.learning.springboot.webapi.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final MapperConfig mapper;

    public EmployeeService(EmployeeRepository employeeRepository, MapperConfig mapper) {
        this.employeeRepository = employeeRepository;
        this.mapper = mapper;
    }

    public EmployeeDTO getEmployeeById(Long employeeId){
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).orElseThrow(()-> new ResourceNotFoundException("Employee Not Found"));
        return mapper.toEmployeeDTO(employeeEntity);
    }

    public List<EmployeeDTO> getAllEmployees(){
        List<EmployeeDTO> employeeDTOs = employeeRepository.findAll()
                .stream()
                .map(mapper::toEmployeeDTO)
                .toList();
        if(!employeeDTOs.isEmpty()) return employeeDTOs;
        throw new ResourceNotFoundException("No Employees Found");
    }

    public EmployeeDTO createEmployee(EmployeeDTO inputEmployee){
        EmployeeEntity employeeEntity = employeeRepository.save(mapper.toEmployeeEntity(inputEmployee));
        return mapper.toEmployeeDTO(employeeEntity);
    }

    public void isExistsByEmployeeId(Long id){
        if(!employeeRepository.existsById(id)) throw new ResourceNotFoundException("Employee Not Found");
    }

    public EmployeeDTO updateEmployeeById(Long id, EmployeeDTO updateEmployee){
        isExistsByEmployeeId(id);
        EmployeeEntity employeeEntity = mapper.toEmployeeEntity(updateEmployee);
        employeeEntity.setId(id);
        employeeEntity = employeeRepository.save(employeeEntity);
        return mapper.toEmployeeDTO(employeeEntity);
    }

    public Boolean deleteEmployeeById(Long id){
        isExistsByEmployeeId(id);
        employeeRepository.deleteById(id);
        return true;
    }

    public EmployeeDTO editEmployeeById(Long id, Map<String, Object> updateData) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee Not Found"));
        updateData.forEach((field,value)->{
            Field fieldToBeUpdated = ReflectionUtils.findField(EmployeeEntity.class,field);
            fieldToBeUpdated.setAccessible(true);
            ReflectionUtils.setField(fieldToBeUpdated,employeeEntity,value);
        });
        return mapper.toEmployeeDTO(employeeRepository.save(employeeEntity));
    }
}
