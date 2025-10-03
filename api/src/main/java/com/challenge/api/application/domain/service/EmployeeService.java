package com.challenge.api.application.domain.service;

import com.challenge.api.application.domain.model.Employee;
import com.challenge.api.application.domain.model.EmployeeImpl;
import com.challenge.api.application.port.CreateEmployeeUseCase;
import com.challenge.api.application.port.GetAllEmployeesUseCase;
import com.challenge.api.application.port.GetEmployeeByUuidUseCase;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class EmployeeService implements GetAllEmployeesUseCase, GetEmployeeByUuidUseCase, CreateEmployeeUseCase {
    private final Map<UUID, Employee> employeeStorage = new ConcurrentHashMap<>();

    public EmployeeService(){
        initializeMockData();
    }

    @Override
    public List<Employee> getAllEmployees(){
        return new ArrayList<>(employeeStorage.values());
    }

    @Override
    public Employee getEmployeeByUuid(UUID uuid){
        return employeeStorage.get(uuid);
    }

    @Override
    public Employee createEmployee(String firstName, String lastName, String email,
                                   Integer salary, Integer age, String jobTitle,
                                   Instant contractHireDate) {
        // Generate UUID and set hire date if not provided
        UUID uuid = UUID.randomUUID();
        Instant hireDate = contractHireDate != null ? contractHireDate : Instant.now();

        // Build the employee
        Employee employee = EmployeeImpl.builder()
                .uuid(uuid)
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .salary(salary)
                .age(age)
                .jobTitle(jobTitle)
                .contractHireDate(hireDate)
                .fullName(firstName + " " + lastName) // Optional: set full name
                .build();

        // Store the employee
        employeeStorage.put(uuid, employee);

        return employee;
    }

    private void initializeMockData() {
        Employee emp1 = EmployeeImpl.builder()
                .uuid(UUID.randomUUID())
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@company.com")
                .salary(75000)
                .age(30)
                .jobTitle("Software Engineer")
                .contractHireDate(Instant.now().minusSeconds(365 * 24 * 60 * 60))
                .build();

        Employee emp2 = EmployeeImpl.builder()
                .uuid(UUID.randomUUID())
                .firstName("Jane")
                .lastName("Smith")
                .email("jane.smith@company.com")
                .salary(95000)
                .age(35)
                .jobTitle("Senior Software Engineer")
                .contractHireDate(Instant.now().minusSeconds(730 * 24 * 60 * 60))
                .build();

        Employee emp3 = EmployeeImpl.builder()
                .uuid(UUID.randomUUID())
                .firstName("Bob")
                .lastName("Johnson")
                .email("bob.johnson@company.com")
                .salary(120000)
                .age(40)
                .jobTitle("Engineering Manager")
                .contractHireDate(Instant.now().minusSeconds(1095 * 24 * 60 * 60))
                .build();

        employeeStorage.put(emp1.getUuid(), emp1);
        employeeStorage.put(emp2.getUuid(), emp2);
        employeeStorage.put(emp3.getUuid(), emp3);
    }
}
