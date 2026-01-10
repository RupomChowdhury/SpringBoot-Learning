package dev.rupom.learning.springboot.webapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import jdk.jfr.BooleanFlag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
    private Long id;

    @NotBlank(message = "Name is required.")
    @Size(min = 5, max = 30, message = "Number of characters in name should be in range: [5-30]")
    private String name;

    @Email(message = "Invalid email format.")
    private String email;

    @Min(value = 18,message = "Age should be greater than 18")
    @Max(value = 60,message = "Age should be less than 60")
    private Integer age;

//    @JsonProperty("joiningDate")
    @NotNull @PastOrPresent
    private LocalDate dateOfJoining;

    @NotBlank(message = "Role is required.")
    @Pattern(regexp = "^(admin|user)$",message = "Invalid role.")
    private String role;

    @NotNull @Positive(message = "Salary should be positive.")
    @Digits(integer = 6, fraction = 2,message = "Invalid salary format: [XXXXXX.XX]")
    @DecimalMax(value = "100000.99",message = "Salary should be less than 100000.99")
    @DecimalMin(value = "1000.59",message = "Salary should be greater than 1000.00")
    private Double salary;

    @AssertTrue(message = "Employee should be active.")
    private Boolean isActive;
}
