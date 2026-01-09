package dev.rupom.learning.springboot.webapi.configs;

import dev.rupom.learning.springboot.webapi.dto.EmployeeDTO;
import dev.rupom.learning.springboot.webapi.entities.EmployeeEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapperConfig {
    EmployeeEntity toEmployeeEntity(EmployeeDTO employeeDTO);
    EmployeeDTO toEmployeeDTO(EmployeeEntity employeeEntity);
}
