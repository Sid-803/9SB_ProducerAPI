package com.example.producer.controller;

import com.example.producer.dto.EmployeeDTO;
import com.example.producer.entity.Employee;
import com.example.producer.service.EmployeeService;
import com.example.producer.util.EmployeeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    public EmployeeDTO employeeDTO;

    @Autowired
    private EmployeeUtil employeeUtil;

    @GetMapping("/employee-list")
    public ResponseEntity<?> getEmployee(){
        return new ResponseEntity<List<EmployeeDTO>>(employeeService.getAllEmployees().stream().map(employee -> employeeDTO ).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<EmployeeDTO> getOneEmployee(@PathVariable Integer id) throws ParseException {
        return new ResponseEntity<EmployeeDTO>(employeeUtil.convertToDto(employeeService.getEmployeeById(id)),HttpStatus.OK);
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Integer id){
        employeeService.deleteById(id);
        return new ResponseEntity<String>("Employee deleted successfully with id: "+id,HttpStatus.OK);
    }

    @PutMapping("/employee/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable Integer id, @RequestBody EmployeeDTO employeeDto) throws java.text.ParseException {
        Employee emp = employeeUtil.convertToEntity(employeeDto);
        employeeService.updateById(emp,id);
        return new ResponseEntity<EmployeeDTO>(employeeDto,HttpStatus.OK);
    }

    @PostMapping("/save-employee")
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO employeeDTO) throws java.text.ParseException {
        Employee emp = employeeUtil.convertToEntity(employeeDTO);
        employeeService.saveEmployee(emp);
        return new ResponseEntity<EmployeeDTO>(employeeDTO,HttpStatus.OK);

    }

}
