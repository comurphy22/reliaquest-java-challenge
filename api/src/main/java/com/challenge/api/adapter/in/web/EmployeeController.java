package com.challenge.api.adapter.in.web;

import com.challenge.api.adapter.in.web.dto.CreateEmployeeDto;
import com.challenge.api.application.domain.model.Employee;
import com.challenge.api.application.port.CreateEmployeeUseCase;
import com.challenge.api.application.port.GetAllEmployeesUseCase;
import com.challenge.api.application.port.GetEmployeeByUuidUseCase;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/v1/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final GetAllEmployeesUseCase getAllEmployeesUseCase;
    private final GetEmployeeByUuidUseCase getEmployeeByUuidUseCase;
    private final CreateEmployeeUseCase createEmployeeUseCase;

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return getAllEmployeesUseCase.getAllEmployees();
    }

    @GetMapping("employees/{id}")
    public Employee getEmployeeByUuid(@PathVariable("id") UUID uuid) {
        Employee employee = getEmployeeByUuidUseCase.getEmployeeByUuid(uuid);

        if (employee == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No employees found with given UUID: " + uuid);
        }

        return employee;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Employee createEmployee(@RequestBody CreateEmployeeDto employeeDto) {
        if (employeeDto.getFirstName() == null
                || employeeDto.getLastName() == null
                || employeeDto.getEmail() == null
                || employeeDto.getSalary() == null
                || employeeDto.getAge() == null
                || employeeDto.getJobTitle() == null
                || employeeDto.getContractHireDate() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "All employee fields are required");
        }

        return createEmployeeUseCase.createEmployee(
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail(),
                employeeDto.getSalary(),
                employeeDto.getAge(),
                employeeDto.getJobTitle(),
                employeeDto.getContractHireDate());
    }
}
