package dev.rupom.learning.springboot.webapi.controller;

import dev.rupom.learning.springboot.webapi.dto.EmployeeDTO;
import dev.rupom.learning.springboot.webapi.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path ="/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable("id") Long employeeId){
        Optional<EmployeeDTO> employeeDTO = employeeService.getEmployeeById(employeeId);
        return employeeDTO
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(){
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }
    @PostMapping(path = "/add")
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody @Valid EmployeeDTO inputEmployee){
        EmployeeDTO savedEmployee = employeeService.createEmployee(inputEmployee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @PutMapping(path="/update/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployeeById(@PathVariable Long id, @RequestBody EmployeeDTO updateEmployee){
        return ResponseEntity.ok(employeeService.updateEmployeeById(id,updateEmployee));
    }

    @DeleteMapping(path="/delete/{id}")
    public ResponseEntity<Boolean> deleteEmployeeById(@PathVariable Long id){
        boolean gotDeleted = employeeService.deleteEmployeeById(id);
        if(gotDeleted) return ResponseEntity.ok(true);
        return ResponseEntity.notFound().build();
    }

    @PatchMapping(path = "/edit/{id}")
    public ResponseEntity<EmployeeDTO> editEmployeeById(@PathVariable Long id, @RequestBody Map<String, Object> updateData){
        EmployeeDTO employeeDTO = employeeService.editEmployeeById(id,updateData);
        if(employeeDTO == null ) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(employeeDTO);
    }

}
