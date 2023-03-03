package com.example.producer.util;

import com.example.producer.dto.EmployeeDTO;
import com.example.producer.entity.Employee;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;

@Component
public class EmployeeUtil {

    @Autowired
    private ModelMapper modelMapper;
    public void copyNonNullValues(Employee requestBody, Employee entityBody){
        if(requestBody.getId()!=null){
            entityBody.setId(requestBody.getId());
        }

        if(requestBody.getName()!=null){
            entityBody.setName(requestBody.getName());
        }

        if(requestBody.getDesignation()!=null){
            entityBody.setDesignation(requestBody.getDesignation());
        }
    }

    public EmployeeDTO convertToDto(Employee employee) {
        EmployeeDTO dto = modelMapper.map(employee,EmployeeDTO.class);
        dto.setId(String.valueOf(employee.getId()));
        dto.setName(employee.getName());
        dto.setDesignation(employee.getDesignation());
        return dto;
    }

    public Employee convertToEntity (EmployeeDTO dto) throws ParseException{
        Employee emp = modelMapper.map(dto,Employee.class);
        emp.setId(Integer.valueOf(dto.getId()));
        emp.setName(dto.getName());
        emp.setDesignation(dto.getDesignation());
        return emp;
    }
}
