package com.challenge.api.adapter.in.web;

import com.challenge.api.adapter.in.web.dto.CreateEmployeeDto;
import com.challenge.api.application.domain.model.Employee;
import java.util.List;
import java.util.UUID;

import com.challenge.api.application.port.CreateEmployeeUseCase;
import com.challenge.api.application.port.GetAllEmployeesUseCase;
import com.challenge.api.application.port.GetEmployeeByUuidUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

/**
 * Fill in the missing aspects of this Spring Web REST Controller. Don't forget to add a Service layer.
 */
@RestController
@RequestMapping("/api/v1/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final GetAllEmployeesUseCase getAllEmployeesUseCase;
    private final GetEmployeeByUuidUseCase getEmployeeByUuidUseCase;
    private final CreateEmployeeUseCase createEmployeeUseCase;

    /**
     * @implNote Need not be concerned with an actual persistence layer. Generate mock Employee models as necessary.
     * @return One or more Employees.
     */
    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return getAllEmployeesUseCase.getAllEmployees();
    }

    /**
     * @implNote Need not be concerned with an actual persistence layer. Generate mock Employee model as necessary.
     * @param uuid Employee UUID
     * @return Requested Employee if exists
     */
    @GetMapping("employees/{id}")
    public Employee getEmployeeByUuid(@PathVariable("id") UUID uuid) {
        return getEmployeeByUuidUseCase.getEmployeeByUuid(uuid);
    }

    /**
     * @implNote Need not be concerned with an actual persistence layer.
     * @param requestBody hint!
     * @return Newly created Employee
     */
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Employee createEmployee(@RequestBody CreateEmployeeDto employeeDto) {
        return createEmployeeUseCase.createEmployee(
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail(),
                employeeDto.getSalary(),
                employeeDto.getAge(),
                employeeDto.getJobTitle(),
                employeeDto.getContractHireDate()
        );
    }
}
