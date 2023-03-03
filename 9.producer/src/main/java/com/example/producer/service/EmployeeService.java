package com.example.producer.service;

import com.example.producer.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> getAllEmployees();

    Employee getEmployeeById(Integer id);

    void deleteById(Integer id);

    Employee updateById(Employee employee, Integer id);

    Employee saveEmployee(Employee employee);

}
