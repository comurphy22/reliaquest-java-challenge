package com.challenge.api.application.domain.model;

import java.time.Instant;
import java.util.UUID;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeImpl implements Employee {
    @NonNull private UUID uuid;

    @NonNull private String firstName;

    @NonNull private String lastName;

    @NonNull private String fullName;

    @NonNull private Integer salary;

    @NonNull private Integer age;

    @NonNull private String jobTitle;

    @NonNull private String email;

    @NonNull private Instant contractHireDate;

    private Instant contractTerminationDate;
}
