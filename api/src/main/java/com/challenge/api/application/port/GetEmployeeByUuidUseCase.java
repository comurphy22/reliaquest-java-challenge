package com.challenge.api.application.port;

import com.challenge.api.application.domain.model.Employee;
import java.util.UUID;

public interface GetEmployeeByUuidUseCase {
    Employee getEmployeeByUuid(UUID uuid);
}
