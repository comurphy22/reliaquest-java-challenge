package com.challenge.api.application.port;

import com.challenge.api.application.domain.model.Employee;

public interface CreateEmployeeUseCase {
    Employee createEmployee(
            String firstName,
            String lastName,
            String email,
            Integer salary,
            Integer age,
            String jobTitle,
            java.time.Instant contractHireDate);
}
