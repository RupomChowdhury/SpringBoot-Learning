package dev.rupom.learning.springboot.webapi.services;

import dev.rupom.learning.springboot.webapi.configs.MapperConfig;
import dev.rupom.learning.springboot.webapi.dto.EmployeeDTO;
import dev.rupom.learning.springboot.webapi.entities.EmployeeEntity;
import dev.rupom.learning.springboot.webapi.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final MapperConfig mapper;

    public EmployeeService(EmployeeRepository employeeRepository, MapperConfig mapper) {
        this.employeeRepository = employeeRepository;
        this.mapper = mapper;
    }

    public EmployeeDTO getEmployeeById(Long employeeId){
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).orElse(null);
        return mapper.toEmployeeDTO(employeeEntity);
    }
    public List<EmployeeDTO> getAllEmployees(){
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        return employeeEntities
                .stream()
                .map(employeeEntity -> mapper.toEmployeeDTO(employeeEntity))
                .collect(Collectors.toList());
    }
    public EmployeeDTO createEmployee(EmployeeDTO inputEmployee){
        EmployeeEntity employeeEntity = employeeRepository.save(mapper.toEmployeeEntity(inputEmployee));
        return mapper.toEmployeeDTO(employeeEntity);
    }
}
