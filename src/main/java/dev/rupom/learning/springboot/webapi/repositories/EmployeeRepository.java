package dev.rupom.learning.springboot.webapi.repositories;

import dev.rupom.learning.springboot.webapi.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //become a bean now
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
    // You can add custom query methods here if needed
}
