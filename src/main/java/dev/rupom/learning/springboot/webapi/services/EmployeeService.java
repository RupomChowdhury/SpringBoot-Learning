package dev.rupom.learning.springboot.webapi.services;

import dev.rupom.learning.springboot.webapi.configs.MapperConfig;
import dev.rupom.learning.springboot.webapi.dto.EmployeeDTO;
import dev.rupom.learning.springboot.webapi.entities.EmployeeEntity;
import dev.rupom.learning.springboot.webapi.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final MapperConfig mapper;

    public EmployeeService(EmployeeRepository employeeRepository, MapperConfig mapper) {
        this.employeeRepository = employeeRepository;
        this.mapper = mapper;
    }

    public Optional<EmployeeDTO> getEmployeeById(Long employeeId){
//       Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(employeeId);
        return employeeRepository.findById(employeeId).map(mapper::toEmployeeDTO);
    }

    public List<EmployeeDTO> getAllEmployees(){
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        return employeeEntities
                .stream()
                .map(mapper::toEmployeeDTO)
                .toList();
    }

    public EmployeeDTO createEmployee(EmployeeDTO inputEmployee){
        EmployeeEntity employeeEntity = employeeRepository.save(mapper.toEmployeeEntity(inputEmployee));
        return mapper.toEmployeeDTO(employeeEntity);
    }

    public EmployeeDTO updateEmployeeById(Long id, EmployeeDTO updateEmployee){ //has some problem in this logic. not saving if id isn't present.
        EmployeeEntity employeeEntity = mapper.toEmployeeEntity(updateEmployee);
        employeeEntity.setId(id);
        employeeEntity = employeeRepository.save(employeeEntity);
        return mapper.toEmployeeDTO(employeeEntity);
    }

    public boolean isExistsByEmployeeId(Long id){
        return employeeRepository.existsById(id);
    }

    public Boolean deleteEmployeeById(Long id){
        if(!isExistsByEmployeeId(id)) return false;
        employeeRepository.deleteById(id);
        return true;
    }

    public EmployeeDTO editEmployeeById(Long id, Map<String, Object> updateData) {
        if(!isExistsByEmployeeId(id)) return null;
        EmployeeEntity employeeEntity = employeeRepository.findById(id).get();
        updateData.forEach((field,value)->{
            Field fieldToBeUpdated = ReflectionUtils.findField(EmployeeEntity.class,field);
            fieldToBeUpdated.setAccessible(true);
            ReflectionUtils.setField(fieldToBeUpdated,employeeEntity,value);
        });
        return mapper.toEmployeeDTO(employeeRepository.save(employeeEntity));
    }
}
