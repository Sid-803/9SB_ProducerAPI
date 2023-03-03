package com.example.producer.serviceImpl;

import com.example.producer.entity.Employee;
import com.example.producer.repository.EmployeeRepository;
import com.example.producer.service.EmployeeService;
import com.example.producer.util.EmployeeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeUtil employeeUtil;



    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeUtil employeeUtil) {
        this.employeeRepository = employeeRepository;
        this.employeeUtil = employeeUtil;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Integer id) {
        return employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Not Found"));
    }

    @Override
    public void deleteById(Integer id) {
        try{
         employeeRepository.deleteById(id);
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public Employee updateById(Employee employee, Integer id) {
        Employee emp = employeeRepository.findById(id).orElseThrow(()-> new RuntimeException("Employee Not found"));
        employeeUtil.copyNonNullValues(employee,emp);
        employeeRepository.save(emp);
        return emp;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        employeeRepository.save(employee);
        return employee;
    }

}
