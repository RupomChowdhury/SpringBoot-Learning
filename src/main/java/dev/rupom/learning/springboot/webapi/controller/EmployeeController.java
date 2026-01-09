package dev.rupom.learning.springboot.webapi.controller;

import dev.rupom.learning.springboot.webapi.dto.EmployeeDTO;
import dev.rupom.learning.springboot.webapi.services.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path ="/{id}")
    public EmployeeDTO getEmployeeById(@PathVariable("id") Long employeeId){
        return employeeService.getEmployeeById(employeeId);
    }

    @GetMapping
    public List<EmployeeDTO> getAllEmployees(){
        return employeeService.getAllEmployees();
    }
    @PostMapping(path = "/add")
    public EmployeeDTO createEmployee(@RequestBody EmployeeDTO inputEmployee){
        return employeeService.createEmployee(inputEmployee);
    }

    @PutMapping(path="/update/{id}")
    public EmployeeDTO updateEmployeeById(@PathVariable Long id, @RequestBody EmployeeDTO updateEmployee){
        return employeeService.updateEmployeeById(id,updateEmployee);
    }

    @DeleteMapping(path="/delete/{id}")
    public Boolean deleteEmployeeById(@PathVariable Long id){
        return employeeService.deleteEmployeeById(id);
    }
}
