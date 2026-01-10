package dev.rupom.learning.springboot.webapi.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employees")
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    private Integer age;
    private java.time.LocalDate dateOfJoining;
    private String role;
    private Double salary;
    private Boolean isActive;
}
