package com.challenge.api.application.port;

import com.challenge.api.application.domain.model.Employee;

import java.util.List;

public interface GetAllEmployeesUseCase {
    List<Employee> getAllEmployees();
}
